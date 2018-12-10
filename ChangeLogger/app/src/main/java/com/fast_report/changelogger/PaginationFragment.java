package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PaginationFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("PaginationFragment", "onCreatePagination");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d("PaginationFragment", "onCreatePagination");
        View view = inflater.inflate(R.layout.fragment_pagination, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("PaginationFragment", "onCreatePagination");
    }
}
