package com.fast_report.changelogger;

import android.util.Log;

import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.ProductVersionsApi;
import io.swagger.client.model.ProductVersionVM;
import io.swagger.client.model.VersionsPageVM;

public class VersionVMGetter implements Runnable {
    private static final String TAG = "ProductVMGetter";
    VersionVMCallbackInterface mCallback;

    public VersionVMGetter(VersionVMCallbackInterface callback) {
        mCallback = callback;
    }

    public void run(){
        ApiClient client = new ApiClient();
        client.setBasePath("https://changelogger20180606030154.azurewebsites.net").setVerifyingSsl(false);
        ProductVersionsApi api = new ProductVersionsApi(client);

        Integer produtId = 1;
        Integer page = 1;
        String filterType = "";
        Integer versionId = 1;
        Integer take = 100;
        try {
            VersionsPageVM result = api.apiProductVersionsProductByProductIdByPageByTakeBySearchingTextGet(produtId, filterType, page, take);
            List<ProductVersionVM> versions = result.getEntities();
            for (ProductVersionVM entry : versions) {
                Log.i(TAG, "-------------------Versions information ---------------------");
                Log.i(TAG, "ID: #" + entry.getId());
                Log.i(TAG, "Type: " + entry.getType());
                Log.i(TAG, "CreateDate: " + entry.getCreateDate());
                Log.i(TAG, "Author: " + entry.getUser().getName() + " " + entry.getUser().getFamilyName() + " (" + entry.getUser().getRole() + ")");
            }
            Log.w(TAG, "------------------------------------------");
            Log.w(TAG, result.toString());

            mCallback.callback(versions);
        } catch (ApiException e) {
            mCallback.error(e);
            Log.e(TAG, e.getMessage() + "\n" + e.getStackTrace().toString());
        }
    }
}