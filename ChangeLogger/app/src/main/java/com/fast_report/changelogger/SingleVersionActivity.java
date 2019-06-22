package com.fast_report.changelogger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class SingleVersionActivity extends SingleFragmentActivity {

    private static final String EXTRA_VERSION_ID = "com.fast_report.changelogger.version_id";

    public static Intent newIntent(Context packageContext, int versionId){
        Intent intent = new Intent(packageContext, SingleVersionActivity.class);
        intent.putExtra(EXTRA_VERSION_ID, versionId);
        return intent;
    }

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, SingleVersionActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        int versionId = (Integer) getIntent().getSerializableExtra(EXTRA_VERSION_ID);
        return SingleVersionFragment.newInstance(versionId);
    }
}