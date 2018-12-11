package com.fast_report.changelogger;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.UUID;

public class SingleChangeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CHANGE_ID = "com.fast_report.changelogger.change_id";

    public static Intent newIntent(Context packageContext, UUID changeId){
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
        UUID changeId = (UUID) getIntent().getSerializableExtra(EXTRA_CHANGE_ID);
        return SingleChangeFragment.newInstance(changeId);
    }

    //Метод скрытия клавиатуры по любому нажатию
    /*
    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }
        return super.dispatchTouchEvent(event);
    }
    */
}