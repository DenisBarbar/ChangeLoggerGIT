package com.fast_report.changelogger;

import java.util.List;

import io.swagger.client.model.ChangeVM;

public class ChangeLab {

    private List<ChangeVM> mChanges;

    public static final ChangeLab sChangeLab = new ChangeLab();

    private ChangeLab(){
    }

    public static ChangeLab getInstance(){
        return sChangeLab;
    }
    public List<ChangeVM> getAllChanges(Integer projectId, ChangeVMCallbackInterface callback){
        new Thread(new ChangeVMGetter(projectId, callback)).start();
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

    public ChangeVM getChange(Integer changeId){
        for (ChangeVM entry : mChanges) {
            if (entry.getId() == changeId) return entry;
        }
        return null;
    }
}
