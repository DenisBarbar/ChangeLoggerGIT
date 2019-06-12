package com.fast_report.changelogger;

import android.util.Log;

import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.ProductsApi;
import io.swagger.client.model.ProductVM;
import io.swagger.client.model.ProductsPageVM;

public class ProductVMGetter   implements Runnable {
    private static final String TAG = "ProductVMGetter";
    ProductVMCallbackInterface mCallback;

    public ProductVMGetter(ProductVMCallbackInterface callback) {
        mCallback = callback;
    }

    public void run() {
        ProductsApi apiInstance = new ProductsApi();
        ApiClient client = new ApiClient();
        client.setBasePath("https://changelogger20180606030154.azurewebsites.net").setVerifyingSsl(false);
        apiInstance.setApiClient(client);
        Integer page = 1;
        Integer take = 100;
        try {
            ProductsPageVM result = apiInstance.apiProductsSearchByPageByTakeBySearchingTextGet("", page, take);
            List<ProductVM> products = result.getEntities();
            for (ProductVM entry : products) {
                Log.i(TAG, "-------------------Project information ---------------------");
                Log.i(TAG, "ID: #" + entry.getId());
                Log.i(TAG, "Name: " + entry.getName());
                Log.i(TAG, "AvgBuildTime: " + entry.getAvgBuildTime());
                Log.i(TAG, "Author: " + entry.getUser().getName() + " " + entry.getUser().getFamilyName() + " (" + entry.getUser().getRole() + ")");
                Log.i(TAG, "productUrl: " + entry.getProductUrl());
                Log.i(TAG, "repositoryUrl: " + entry.getRepositoryUrl());
            }
            Log.w(TAG, "------------------------------------------");
            Log.w(TAG, result.toString());

            mCallback.callback(products);
        } catch (ApiException e) {
            mCallback.error(e);
            Log.e(TAG, e.getMessage() + "\n" + e.getStackTrace().toString());
        }
    }
}