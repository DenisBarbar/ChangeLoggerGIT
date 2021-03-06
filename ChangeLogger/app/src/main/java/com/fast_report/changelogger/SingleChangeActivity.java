package com.fast_report.changelogger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class SingleChangeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CHANGE_ID = "com.fast_report.changelogger.change_id";

    public static Intent newIntent(Context packageContext, int changeId){
        Intent intent = new Intent(packageContext, SingleChangeActivity.class);
        intent.putExtra(EXTRA_CHANGE_ID, changeId);
        return intent;
    }

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, SingleChangeActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        int changeId = (Integer) getIntent().getSerializableExtra(EXTRA_CHANGE_ID);
        return SingleChangeFragment.newInstance(changeId);
    }
}