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

import io.swagger.client.model.ProductVersionVM;

public class SingleVersionFragment extends Fragment {


    private static final String ARG_VERSION_ID = "version_id";

    private ProductVersionVM mVersion;
    private EditText mMajorField;
    private EditText mMinorField;
    private EditText mBuildField;
    private EditText mNameField;
    private Spinner mTypeSpinner;
    private Spinner mStateSpinner;
    private Button mSaveButton;
    private Button mResetButton;
    private String[] typeArray;
    private String[] stateArray;
    VersionLab mVersionLab;

    public static SingleVersionFragment newInstance(int versionId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_VERSION_ID, versionId);
        SingleVersionFragment fragment = new SingleVersionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int versionID = (Integer) getArguments().getSerializable(ARG_VERSION_ID);
        mVersionLab = VersionLab.getInstance();
        mVersion = mVersionLab.getVersion(versionID);

    }
    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container,
                              Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_single_version, container, false);
        mMajorField = (EditText) v.findViewById(R.id.version_major_field);
        mMinorField = (EditText) v.findViewById(R.id.version_minor_field);
        mBuildField = (EditText) v.findViewById(R.id.version_build_field);
        mNameField = (EditText) v.findViewById(R.id.version_name_field);

        mTypeSpinner = (Spinner) v.findViewById(R.id.version_type_spinner);
        typeArray = getResources().getStringArray(R.array.version_types);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, typeArray);
        mTypeSpinner.setAdapter(typeAdapter);

        mStateSpinner = (Spinner) v.findViewById(R.id.version_state_spinner);
        stateArray = getResources().getStringArray(R.array.versions_states);
        ArrayAdapter<String> stateAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, stateArray);
        mStateSpinner.setAdapter(stateAdapter);

        mSaveButton = (Button) v.findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update ProductVersionVM
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
        mMajorField.setText(mVersion.getMajor().toString());
        mMinorField.setText(mVersion.getMinor().toString());
        mBuildField.setText(mVersion.getBuild().toString());
        mNameField.setText(mVersion.getName());

        switch(mVersion.getType().toString()) {
            case "Night":
                mTypeSpinner.setSelection(1);
                break;
            case "None":
                mTypeSpinner.setSelection(2);
                break;
            default:
                mTypeSpinner.setSelection(0);
                break;
        }

        switch(mVersion.getState().toString()) {
            case "Building":
                mStateSpinner.setSelection(1);
                break;
            case "Failed":
                mStateSpinner.setSelection(2);
                break;
            case "Success":
                mStateSpinner.setSelection(3);
                break;
            default:
                mStateSpinner.setSelection(0);
                break;
        }
    }
}
