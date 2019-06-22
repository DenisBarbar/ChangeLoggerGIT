package com.fast_report.changelogger;

import java.util.List;

import io.swagger.client.model.ProductVM;
import io.swagger.client.model.ProductVersionVM;

public class VersionLab {

    private List<ProductVersionVM> mVersions;
    private static final String TAG = "API_APP";

    public static final VersionLab sVersionLab = new VersionLab();

    private VersionLab(){
    }

    public static VersionLab getInstance(){
        return sVersionLab;
    }
    public List<ProductVersionVM> getAllVersions(VersionVMCallbackInterface callback){
        new Thread(new VersionVMGetter(callback)).start();
        try {
            callback.await();
        } catch (InterruptedException iex) {
            callback.error(iex);
        }
        finally {
        }
        mVersions = callback.getVersions();
        return mVersions;
    }

    public ProductVersionVM getVersion(Integer versionId){
        for (ProductVersionVM entry : mVersions) {
            if (entry.getId() == versionId) return entry;
        }
        return null;
    }
}