package com.fast_report.changelogger;

import java.util.List;

import io.swagger.client.model.ChangeVM;

public interface ChangeVMCallbackInterface {
    void callback(List<ChangeVM> changes);
    void await() throws InterruptedException;
    List<ChangeVM> getChanges();
    void error(Throwable what);
}
