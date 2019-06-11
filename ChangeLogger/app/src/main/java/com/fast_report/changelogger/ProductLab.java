package com.fast_report.changelogger;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.ProductsApi;
import io.swagger.client.model.ProductVM;
import io.swagger.client.model.ProductsPageVM;

public class ProductLab {

    private List<ProductVM> mProducts;
    private static final String TAG = "API_APP";

    public static final ProductLab sProductLab = new ProductLab();

    private ProductLab(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ProductsApi apiInstance = new ProductsApi();
                ApiClient client = new ApiClient();
                client.setBasePath("https://changelogger20180606030154.azurewebsites.net").setVerifyingSsl(false);
                apiInstance.setApiClient(client);
                Integer page = 1;
                Integer take = 100;
                try {
                    ProductsPageVM result = apiInstance.apiProductsSearchByPageByTakeBySearchingTextGet("", page, take);
                    mProducts = result.getEntities();
                    for (ProductVM entry : mProducts) {
                        Log.i(TAG, "-------------------Project information ---------------------");
                        Log.i(TAG, "ID: #" + entry.getId());
                        Log.i(TAG, "Name: " + entry.getName());
                        Log.i(TAG, "AvgBuildTime: " + entry.getAvgBuildTime());
                        Log.i(TAG, "Author: " + entry.getUser().getName() + " " + entry.getUser().getFamilyName() + " (" + entry.getUser().getRole() + ")");
                    }
                    Log.w(TAG, "------------------------------------------");
                    Log.w(TAG, result.toString());
                } catch (ApiException e) {
                    Log.e(TAG, e.getMessage() + "\n" + e.getStackTrace().toString());
                }
            }
        }).start();
    }

    public static ProductLab getInstance(){
        return sProductLab;
    }
    public List<ProductVM> getAllProducts(){
        return mProducts;
    }
    private ProductVM getProduct(int number){
        return mProducts.get(number-1);
    }
}
