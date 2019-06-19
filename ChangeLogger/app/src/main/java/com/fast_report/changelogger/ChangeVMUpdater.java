package com.fast_report.changelogger;

import android.util.Log;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.ChangesApi;
import io.swagger.client.model.Change;

public class ChangeVMUpdater implements Runnable {
    private static final String TAG = "ProductVMGetter";
    private Integer mChangeId;
    private Change mChange;

    public ChangeVMUpdater(Integer changeId, Change change) {
        mChangeId = changeId;
        mChange = change;
    }

    public void run(){
        ApiClient client = new ApiClient();
        client.setBasePath("https://changelogger20180606030154.azurewebsites.net").setVerifyingSsl(false);
        ChangesApi api = new ChangesApi(client);
        try {
            api.apiChangesByIdPut(mChangeId, mChange);
                /*
                Log.i(TAG, "-------------------Versions information ---------------------");
                Log.i(TAG, "ID: #" + entry.getId());
                Log.i(TAG, "Type: " + entry.getType());
                Log.i(TAG, "CreateDate: " + entry.getCreateDate());
                Log.i(TAG, "Author: " + entry.getUser().getName() + " " + entry.getUser().getFamilyName() + " (" + entry.getUser().getRole() + ")");
                */
            Log.w(TAG, "------------------------------------------");

        } catch (ApiException e) {
            Log.e(TAG, e.getMessage() + "\n" + e.getStackTrace().toString());
        }
    }
}
