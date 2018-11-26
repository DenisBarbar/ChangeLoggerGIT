package com.fast_report.changelogger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CRUDFragment extends Fragment {


    public CRUDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("CRUD Logs");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crud, container, false);
    }

}
