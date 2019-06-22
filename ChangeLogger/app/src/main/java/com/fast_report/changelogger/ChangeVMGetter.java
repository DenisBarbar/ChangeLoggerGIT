package com.fast_report.changelogger;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.ChangesApi;
import io.swagger.client.model.ChangeVM;
import io.swagger.client.model.ChangesPageVM;

public class ChangeVMGetter implements Runnable {
    private static final String TAG = "ProductVMGetter";
    Integer mProjectId;
    ChangeVMCallbackInterface mCallback;

    public ChangeVMGetter(Integer projectId, ChangeVMCallbackInterface callback) {
        mProjectId = projectId;
        mCallback = callback;
    }

    public void run(){
        ApiClient client = new ApiClient();
        client.setBasePath("https://changelogger20180606030154.azurewebsites.net").setVerifyingSsl(false);
        ChangesApi api = new ChangesApi(client);

        String filterType = "All";
        Integer versionId = null;
        Integer take = null;
        try {
            Integer page = 1;
            Integer totalPages = 1;
            List<ChangeVM> allChanges = new ArrayList<>();
            do {
                ChangesPageVM result = api.apiChangesProductByProductIdByPageByTakeByFilterTypeByVersionIdGet(mProjectId, page, filterType, versionId, take);
                totalPages = result.getPage().getTotalPages();
                List<ChangeVM> changes = result.getEntities();
                page++;
                allChanges.addAll(changes);
            } while (page <= totalPages);

            mCallback.callback(allChanges);
        } catch (ApiException e) {
            mCallback.error(e);
            Log.e(TAG, e.getMessage() + "\n" + e.getStackTrace().toString());
        }
    }
}
