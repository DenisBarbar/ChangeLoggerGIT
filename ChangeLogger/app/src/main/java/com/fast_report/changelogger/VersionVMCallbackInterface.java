package com.fast_report.changelogger;

import io.swagger.client.model.ProductVersionVM;

import java.util.List;

public interface VersionVMCallbackInterface {
    void callback(List<ProductVersionVM> versions);
    void await() throws InterruptedException;
    List<ProductVersionVM> getVersions();
    void error(Throwable what);
}
