package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import io.swagger.client.model.ProductVM;

public class SingleProductFragment extends Fragment {


    private static final String ARG_PRODUCT_ID = "product_id";

    private ProductVM mProduct;

    private EditText mNameField;
    private EditText mDescriptionField;
    private EditText mProductURLField;
    private EditText mRepositoryURLField;
    private EditText mTagField;
    private EditText mAvgTimeField;

    private Button mSaveButton;
    private Button mResetButton;
    ProductLab mProductLab;

    public static SingleProductFragment newInstance(int productId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT_ID, productId);

        SingleProductFragment fragment = new SingleProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int productID = (Integer) getArguments().getSerializable(ARG_PRODUCT_ID);
        mProductLab = ProductLab.getInstance();
        mProduct = mProductLab.getProduct(productID);
    }
    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container,
                              Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_single_product, container, false);

        mNameField = (EditText) v.findViewById(R.id.product_name_field);
        mDescriptionField = (EditText) v.findViewById(R.id.product_desc_field);
        mProductURLField = (EditText) v.findViewById(R.id.product_url_field);
        mRepositoryURLField = (EditText) v.findViewById(R.id.product_rep_field);
        mTagField = (EditText) v.findViewById(R.id.product_tag_field);
        mAvgTimeField = (EditText) v.findViewById(R.id.product_avg_time_field);

        mSaveButton = (Button) v.findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update ProductVM
            }
        });
        mResetButton = (Button) v.findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fill();
            }
        });
        fill();
        return v;
    }
    private void fill(){
        mNameField.setText(mProduct.getName());
        mDescriptionField.setText(mProduct.getDescription());
        mProductURLField.setText(mProduct.getProductUrl());
        mRepositoryURLField.setText(mProduct.getRepositoryUrl());
        mTagField.setText(mProduct.getTag());
        mAvgTimeField.setText(mProduct.getAvgBuildTime().toString());
    }
}