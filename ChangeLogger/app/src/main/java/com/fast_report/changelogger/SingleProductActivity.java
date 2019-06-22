package com.fast_report.changelogger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class SingleProductActivity extends SingleFragmentActivity {

    private static final String EXTRA_PRODUCT_ID = "com.fast_report.changelogger.product_id";

    public static Intent newIntent(Context packageContext, int productId){
        Intent intent = new Intent(packageContext, SingleProductActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        return intent;
    }

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, SingleProductActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        int productId = (Integer) getIntent().getSerializableExtra(EXTRA_PRODUCT_ID);
        return SingleProductFragment.newInstance(productId);
    }
}