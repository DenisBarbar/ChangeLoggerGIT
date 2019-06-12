package com.fast_report.changelogger;

import android.util.Log;

import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.ChangesApi;
import io.swagger.client.model.ChangeVM;
import io.swagger.client.model.ChangesPageVM;

public class ChangeVMGetter implements Runnable {
    private static final String TAG = "ProductVMGetter";
    ChangeVMCallbackInterface mCallback;

    public ChangeVMGetter(ChangeVMCallbackInterface callback) {
        mCallback = callback;
    }

    public void run(){
        ApiClient client = new ApiClient();
        client.setBasePath("https://changelogger20180606030154.azurewebsites.net").setVerifyingSsl(false);
        ChangesApi api = new ChangesApi(client);

        Integer projectId = 1;
        Integer page = 1;
        String filterType = "";
        Integer versionId = 1;
        Integer take = 100;
        try {
            ChangesPageVM result = api.apiChangesProductByProductIdByPageByTakeByFilterTypeByVersionIdGet(projectId, page, filterType, versionId, take);
            List<ChangeVM> entries = result.getEntities();
            for (ChangeVM entry : entries) {
                Log.i(TAG, "-------------------Change information ---------------------");
                Log.i(TAG, "ID: #" + entry.getId());
                Log.i(TAG, "Type: " + entry.getType());
                Log.i(TAG, "Text: " + entry.getTranslations().get(0).getText());
                Log.i(TAG, "CreateDate: " + entry.getCreateDate());
                Log.i(TAG, "Author: " + entry.getUser().getName() + " " + entry.getUser().getFamilyName() + " (" + entry.getUser().getRole() + ")");
            }
            Log.w(TAG, "------------------------------------------");
            Log.w(TAG, result.toString());
        } catch (ApiException e) {
            Log.e(TAG, e.getMessage() + "\n" + e.getStackTrace().toString());
        }
    }
}
