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

import java.util.List;

import io.swagger.client.model.ChangeVM;
import io.swagger.client.model.ProductVersionVM;
import io.swagger.client.model.TranslationVM;

public class SingleChangeFragment extends Fragment {

    private static final String ARG_CHANGE_ID = "change_id";

    private ChangeVM mChange;

    private Spinner mTypeSpinner;
    private Spinner mGroupSpinner;
    private EditText mTextField;
    private EditText mAnnotationField;
    private EditText mDocumentationField;
    private EditText mTaskField;
    private EditText mMergeField;
    private Spinner mVersionSpinner;

    private Button mSaveButton;
    private Button mResetButton;
    private String[] typeArray;
    private String[] groupArray;
    private String[] versionArray;
    ChangeLab mChangeLab;

    public static SingleChangeFragment newInstance(int changeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHANGE_ID, changeId);

        SingleChangeFragment fragment = new SingleChangeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int changeID = (Integer) getArguments().getSerializable(ARG_CHANGE_ID);
        mChangeLab = ChangeLab.getInstance();
        mChange = mChangeLab.getChange(changeID);
    }
    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container,
                              Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_single_change, container, false);

        mTypeSpinner = (Spinner) v.findViewById(R.id.type_spinner);
        typeArray = getResources().getStringArray(R.array.types_array);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, typeArray);
        mTypeSpinner.setAdapter(typeAdapter);

        mGroupSpinner = (Spinner) v.findViewById(R.id.group_spinner);
        groupArray = getResources().getStringArray(R.array.groups_array);
        ArrayAdapter<String> groupAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, groupArray);
        mGroupSpinner.setAdapter(groupAdapter);

        mTextField = (EditText) v.findViewById(R.id.text_field);
        mAnnotationField = (EditText) v.findViewById(R.id.annotation_field);

        mDocumentationField = (EditText) v.findViewById(R.id.documentation_link_field);
        mTaskField = (EditText) v.findViewById(R.id.task_link_field);
        mMergeField = (EditText) v.findViewById(R.id.merge_link_field);

        mVersionSpinner = (Spinner) v.findViewById(R.id.change_version_spinner);
        versionArray = getResources().getStringArray(R.array.versions_array);
        ArrayAdapter<String> versionAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, versionArray);
        mVersionSpinner.setAdapter(versionAdapter);

        mSaveButton = (Button) v.findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update ChangeVM
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

        List<TranslationVM> mTranslations = mChange.getTranslations();
        ProductVersionVM mVersion = mChange.getVersion();
        String versionName = mVersion.getMajor() + "." + mVersion.getMinor() + "." + mVersion.getBuild();
        if (mVersion.getMajor() == Integer.MAX_VALUE){
            versionName = "Current version";
        }

        mTextField.setText(mTranslations.get(1).getText());
        mAnnotationField.setText(mTranslations.get(1).getAnnotation());
        mDocumentationField.setText(mTranslations.get(1).getDocumentationLink());
        mTaskField.setText(mChange.getTaskLink());
        mMergeField.setText(mChange.getMergeRequestLink());

        switch(mChange.getType().toString()) {
            case "Changed":
                mTypeSpinner.setSelection(1);
                break;
            case "Fixed":
                mTypeSpinner.setSelection(2);
                break;
            case "Removed":
                mTypeSpinner.setSelection(3);
                break;
            case "Deprecated":
                mTypeSpinner.setSelection(4);
                break;
            case "Security":
                mTypeSpinner.setSelection(5);
                break;
            default:
                mTypeSpinner.setSelection(0);
                break;
        }

        switch(mChange.getGroup().getName()) {
            case "Part1":
                mGroupSpinner.setSelection(1);
                break;
            case "Part2":
                mGroupSpinner.setSelection(2);
                break;
            default:
                mGroupSpinner.setSelection(0);
                break;
        }

        switch(versionName) {
            case "2018.1.1":
                mVersionSpinner.setSelection(1);
                break;
            case "Current version":
                mVersionSpinner.setSelection(2);
                break;
            default:
                mVersionSpinner.setSelection(0);
                break;
        }
    }
}