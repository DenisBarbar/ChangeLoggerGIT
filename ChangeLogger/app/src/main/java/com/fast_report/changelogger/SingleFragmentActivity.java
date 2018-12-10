package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity {

    @LayoutRes
    protected int getLayoutResId(){
        return R.layout.content_main;
    }

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_fragment);

        if (fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.main_fragment, fragment)
                    .commit();
        }
    }
}
