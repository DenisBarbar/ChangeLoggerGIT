package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.UUID;

public class SingleChangeFragment extends Fragment {

    private static final String ARG_CHANGE_ID = "change_id";
    private Change mChange;
    private EditText mVersionField;
    private EditText mAuthorField;
    private EditText mTextField;
    private Spinner mTypeSpinner;
    private Spinner mGroupSpinner;
    private Button mSaveButton;
    private Button mResetButton;
    private String[] typeArray;
    private String[] groupArray;
    //Отладочный костыль
    private boolean isNew = false;

    public static SingleChangeFragment newInstance(UUID changeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHANGE_ID, changeId);

        SingleChangeFragment fragment = new SingleChangeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID changeID = (UUID) getArguments().getSerializable(ARG_CHANGE_ID);
        if (changeID != null) {
            mChange = ChangeLab.get(getActivity()).getChange(changeID); //Передача ссылки на синглет или копирование коллекции?
        } else {
            mChange = new Change();
            isNew = true;
        }
    }
    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container,
                              Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_single_change, container, false);

        mVersionField = (EditText) v.findViewById(R.id.version_field);
        mAuthorField = (EditText) v.findViewById(R.id.author_field);
        mTextField = (EditText) v.findViewById(R.id.text_field);

        mTypeSpinner = (Spinner) v.findViewById(R.id.type_spinner);
        // получаем ресурсы
        typeArray = getResources().getStringArray(R.array.types_array);
        // создаем адаптер
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, typeArray);
        mTypeSpinner.setAdapter(typeAdapter);


        mGroupSpinner = (Spinner) v.findViewById(R.id.group_spinner);
        // получаем ресурс
        groupArray = getResources().getStringArray(R.array.groups_array);
        // создаем адаптер
        ArrayAdapter<String> groupAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, groupArray);
        mGroupSpinner.setAdapter(groupAdapter);

        mVersionField.setText(mChange.getVersion());
        mAuthorField.setText(mChange.getAuthor());
        mTextField.setText(mChange.getChangedText());
        updateTypeSpinner();
        updateGroupSpinner();

        mSaveButton = (Button) v.findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChange.setVersion(mVersionField.getText().toString());
                mChange.setAuthor(mAuthorField.getText().toString());
                mChange.setChangedText(mTextField.getText().toString());
                mChange.setType(mTypeSpinner.getSelectedItem().toString());
                mChange.setGroup(mGroupSpinner.getSelectedItem().toString());
                if (isNew){
                    ChangeLab changeLab = ChangeLab.get(getActivity());
                    changeLab.addChange(mChange);
                }
                getActivity().onBackPressed();
            }
        });
        mResetButton = (Button) v.findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVersionField.setText(mChange.getVersion());
                mAuthorField.setText(mChange.getAuthor());
                mTextField.setText(mChange.getChangedText());
                updateTypeSpinner();
                updateGroupSpinner();
            }
        });


        return v;
    }
    private void updateTypeSpinner(){
        for (int i=0; i<typeArray.length; i++){
            if (mChange.getType().equals(typeArray[i])) {
                mTypeSpinner.setSelection(i);
            }
        }
    }
    private void updateGroupSpinner(){
        for (int i=0; i<groupArray.length; i++){
            if (mChange.getGroup().equals(groupArray[i])) {
                mGroupSpinner.setSelection(i);
            }
        }
    }
}