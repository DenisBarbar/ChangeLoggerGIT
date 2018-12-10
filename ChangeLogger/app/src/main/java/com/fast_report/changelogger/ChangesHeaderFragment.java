package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChangesHeaderFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ChangesHeaderFragment", "onCreateHeader");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d("ChangesHeaderFragment", "onCreateHeaderView");
        ((MainActivity)getActivity()).setActionBarTitle("Changes");
        View view = inflater.inflate(R.layout.fragment_changes_header, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ChangesListFragment", "onHeaderStart");
    }
}
