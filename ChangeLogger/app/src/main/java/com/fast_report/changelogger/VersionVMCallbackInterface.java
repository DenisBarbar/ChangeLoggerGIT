package com.fast_report.changelogger;

import com.squareup.okhttp.internal.Version;

import java.util.List;

public interface VersionVMCallbackInterface {
    void callback(List<Version> versions);
    void await() throws InterruptedException;
    List<Version> getVersions();
    void error(Throwable what);
}
