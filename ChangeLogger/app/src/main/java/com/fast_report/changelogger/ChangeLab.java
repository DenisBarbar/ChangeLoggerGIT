package com.fast_report.changelogger;

import java.util.List;

import io.swagger.client.model.ChangeVM;

public class ChangeLab {

    private List<ChangeVM> mChanges;
    private static final String TAG = "API_APP";

    public static final ChangeLab sChangeLab = new ChangeLab();

    private ChangeLab(){
    }

    public static ChangeLab getInstance(){
        return sChangeLab;
    }
    public List<ChangeVM> getAllChanges(ChangeVMCallbackInterface callback){
        new Thread(new ChangeVMGetter(callback)).start();
        try {
            callback.await();
        } catch (InterruptedException iex) {
            callback.error(iex);
        }
        finally {
        }
        mChanges = callback.getChanges();
        return mChanges;
    }
}
