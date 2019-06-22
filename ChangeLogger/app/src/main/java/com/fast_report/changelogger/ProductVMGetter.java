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
            mCallback.callback(products);
        } catch (ApiException e) {
            mCallback.error(e);
            Log.e(TAG, e.getMessage() + "\n" + e.getStackTrace().toString());
        }
    }
}