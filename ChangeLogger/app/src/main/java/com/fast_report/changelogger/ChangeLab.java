package com.fast_report.changelogger;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChangeLab {
    private static ChangeLab sChangeLab;

     private List<Change> mChanges;

    public static ChangeLab get(Context context) {
        if (sChangeLab == null){
            sChangeLab = new ChangeLab(context);
        }
        return sChangeLab;
    }

    private ChangeLab(Context context){
        mChanges = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Change change = new Change();
            change.setVersion("1.0" + i);
            change.setType("changed");
            change.setGroup("public");
            change.setAuthor("автор");
            change.setChangedText("Изменения в данной версии");
            mChanges.add(change);
        }
    }
    public List <Change> getChanges(){
        return mChanges;
    }
    public Change getChange(UUID id){
        for (Change change: mChanges){
            if (change.getId().equals(id)){
                return change;
            }
        }
        return null;
    }
}
