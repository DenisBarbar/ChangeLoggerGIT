package com.fast_report.changelogger;

import java.util.List;

import io.swagger.client.model.ProductVM;

public interface ProductVMCallbackInterface {
    void callback(List<ProductVM> products);
    void await() throws InterruptedException;
    List<ProductVM> getProducts();
    void error(Throwable what);
}