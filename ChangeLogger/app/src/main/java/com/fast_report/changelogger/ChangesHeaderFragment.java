package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class ChangesHeaderFragment extends Fragment {

    Button newButton;
    private ChangeLab changeLab = ChangeLab.get(getActivity());
    private List<Change> mChanges = changeLab.getChanges();;

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
        newButton = (Button) view.findViewById(R.id.button_new);
        newButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Change change = new Change();
                change.setVersion("new");
                change.setType("changed");
                change.setGroup("public");
                change.setAuthor("автор");
                change.setChangedText("Изменения в данной версии");
                mChanges.add(change);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ChangesListFragment", "onHeaderStart");
    }
}
