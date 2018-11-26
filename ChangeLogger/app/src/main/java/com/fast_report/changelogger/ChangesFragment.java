package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ChangesFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM y ' г.'", new Locale("ru"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ((MainActivity)getActivity()).setActionBarTitle("Changes");
        View view = inflater.inflate(R.layout.fragment_changes_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.changes_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        ChangeLab changeLab = ChangeLab.get(getActivity());
        List<Change> crimes = changeLab.getChanges();
        if(mAdapter == null){
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {

        private Change mChange;
        private TextView mVersionTextView;
        private TextView mTypeTextView;
        private TextView mGroupTextView;
        private TextView mAuthorTextView;

        public void bindCrime(Change change) {
            mChange = change;
            mVersionTextView.setText(mChange.getVersion());
            mTypeTextView.setText(mChange.getType());
            mGroupTextView.setText(mChange.getGroup());
            mAuthorTextView.setText(mChange.getAuthor());
        }

        public CrimeHolder (View itemView){
            super(itemView);
            mVersionTextView = (TextView) itemView.findViewById(R.id.list_item_change_version);
            mTypeTextView = (TextView) itemView.findViewById(R.id.list_item_change_type);
            mGroupTextView = (TextView) itemView.findViewById(R.id.list_item_change_group);
            mAuthorTextView = (TextView) itemView.findViewById(R.id.list_item_change_author);
        }

    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Change> mChanges;

        public CrimeAdapter(List<Change> changes){
            mChanges = changes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_change, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position){
            Change crime = mChanges.get(position);
            holder.bindCrime(crime);
        }
        @Override
        public int getItemCount(){
            return mChanges.size();
        }
    }
}
