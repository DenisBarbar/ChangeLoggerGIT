package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ChangesFragment extends Fragment {

    private RecyclerView mChangeRecyclerView;
    private ChangeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ((MainActivity)getActivity()).setActionBarTitle("Changes");
        View view = inflater.inflate(R.layout.fragment_changes_list, container, false);
        mChangeRecyclerView = (RecyclerView) view.findViewById(R.id.changes_recycler_view);
        mChangeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
            mAdapter = new ChangeAdapter(crimes);
            mChangeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ChangeHolder extends RecyclerView.ViewHolder {

        private Change mChange;
        private TextView mVersionTextView;
        private TextView mTypeTextView;
        private TextView mGroupTextView;
        private TextView mAuthorTextView;
        private TextView mChangedTextView;

        public void bindChange(Change change) {
            mChange = change;
            mVersionTextView.setText(mChange.getVersion());
            mTypeTextView.setText(mChange.getType());
            mGroupTextView.setText(mChange.getGroup());
            mAuthorTextView.setText(mChange.getAuthor());
            mChangedTextView.setText(mChange.getChangedText());
        }

        public ChangeHolder (View itemView){
            super(itemView);
            mVersionTextView = (TextView) itemView.findViewById(R.id.list_item_change_version);
            mTypeTextView = (TextView) itemView.findViewById(R.id.list_item_change_type);
            mGroupTextView = (TextView) itemView.findViewById(R.id.list_item_change_group);
            mAuthorTextView = (TextView) itemView.findViewById(R.id.list_item_change_author);
            mChangedTextView = (TextView) itemView.findViewById(R.id.list_item_changed_text);
        }

    }

    private class ChangeAdapter extends RecyclerView.Adapter<ChangeHolder>{

        private List<Change> mChanges;

        public ChangeAdapter(List<Change> changes){
            mChanges = changes;
        }

        @Override
        public ChangeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_change, parent, false);
            return new ChangeHolder(view);
        }

        @Override
        public void onBindViewHolder(ChangeHolder holder, int position){
            Change crime = mChanges.get(position);
            holder.bindChange(crime);
        }
        @Override
        public int getItemCount(){
            return mChanges.size();
        }
    }
}
