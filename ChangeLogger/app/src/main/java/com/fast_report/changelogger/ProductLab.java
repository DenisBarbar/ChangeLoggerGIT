package com.fast_report.changelogger;

import java.util.List;

import io.swagger.client.model.ProductVM;

public class ProductLab {

    private List<ProductVM> mProducts;
    private static final String TAG = "API_APP";

    public static final ProductLab sProductLab = new ProductLab();

    private ProductLab(){
    }

    public static ProductLab getInstance(){
        return sProductLab;
    }
    public List<ProductVM> getAllProducts(ProductVMCallbackInterface callback){
        new Thread(new ProductVMGetter(callback)).start();
        try {
            callback.await();
        } catch (InterruptedException iex) {
            callback.error(iex);
        }
        finally {
        }
        mProducts = callback.getProducts();
        return mProducts;
    }
    public ProductVM getProduct(Integer productId){
        for (ProductVM entry : mProducts) {
            if (entry.getId() == productId) return entry;
        }
        return null;
    }
}
