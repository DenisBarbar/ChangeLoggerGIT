package com.fast_report.changelogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ChangesListFragment extends Fragment {

    private RecyclerView mChangeRecyclerView;
    private ChangeAdapter mAdapter;
    public List<Change> mChanges;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d("ChangesListFragment", "onCreateView");
        ((MainActivity)getActivity()).setActionBarTitle("Changes");
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mChangeRecyclerView = (RecyclerView) view.findViewById(R.id.changes_recycler_view);
        mChangeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI(){
        ChangeLab changeLab = ChangeLab.get(getActivity());
        List<Change> changes = changeLab.getChanges(); //Здесь происходит связывание с синглетом?
        if(mAdapter == null){
            mAdapter = new ChangeAdapter(changes);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mChangeRecyclerView.setAdapter(mAdapter);
    }

    private class ChangeHolder extends RecyclerView.ViewHolder {

        private Change mChange;
        private TextView mVersionTextView;
        private TextView mTypeTextView;
        private TextView mGroupTextView;
        private TextView mAuthorTextView;
        private TextView mChangedTextView;
        private Button mDeleteButton;
        private Button mEditButton;

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
            mDeleteButton = (Button) itemView.findViewById(R.id.delete_button);
            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChanges.remove(mChange);
                    updateUI();
                }
            });
            mEditButton = (Button) itemView.findViewById(R.id.edit_button);
            mEditButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = SingleChangeActivity.newIntent(getActivity(), mChange.getId()); //Неявный вызов putExtra?
                    startActivity(intent);
                }
            });
        }
    }

    private class ChangeAdapter extends RecyclerView.Adapter<ChangeHolder>{

        public ChangeAdapter(List<Change> changes){
            mChanges = changes; //A здесь передача связи с синглетом на переменную коллекции фрагмента
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
            Change change = mChanges.get(position);
            holder.bindChange(change);
        }
        @Override
        public int getItemCount(){
            return mChanges.size();
        }
    }
}
