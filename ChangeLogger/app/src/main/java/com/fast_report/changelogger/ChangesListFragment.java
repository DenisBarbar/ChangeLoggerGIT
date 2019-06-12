package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import io.swagger.client.model.ChangeVM;
import io.swagger.client.model.UserVM;

public class ChangesListFragment extends Fragment {

    ChangeLab mChangeLab = ChangeLab.getInstance();
    RecyclerView mChangesRecyclerView;

    ChangeVMCallbackInterface mChangeVMCallback = new ChangeVMCallback();
    ArrayList <ChangeVM> mChanges = new ArrayList<>(mChangeLab.getAllChanges(mChangeVMCallback));
    ChangeAdapter mChangeAdapter = new ChangeAdapter(mChanges);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Changes");
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mChangesRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mChangesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mChangesRecyclerView.setAdapter(mChangeAdapter);
        return view;
    }

    private class ChangeAdapter extends RecyclerView.Adapter <ChangeAdapter.ChangeViewHolder>{

        ArrayList<ChangeVM> mChangeList;

        public ChangeAdapter (ArrayList<ChangeVM> changeList){
            this.mChangeList = changeList;
        }

        public class ChangeViewHolder extends RecyclerView.ViewHolder {

            private ChangeVM mChange;

            private TextView mProductOrderLabel;
            private TextView mProductNameLabel;
            private TextView mProductComitedLabel;
            private TextView mProductDescriptionLabel;
            private TextView mProductDocRepLabel;
            private TextView mProductDocRepLink;
            private TextView mProductAvgBuildLabel;
            private TextView mProductLangLabel;

            private Button mDeleteButton;
            private Button mEditButton;

            public ChangeViewHolder (View itemView){
                super(itemView);
                /*
                mProductOrderLabel = (TextView) itemView.findViewById(R.id.product_order_label);
                mProductNameLabel = (TextView) itemView.findViewById(R.id.product_name_label);
                mProductComitedLabel = (TextView) itemView.findViewById(R.id.product_comited_label);
                mProductDescriptionLabel = (TextView) itemView.findViewById(R.id.product_description_label);
                mProductDocRepLabel = (TextView) itemView.findViewById(R.id.product_doc_rep_label);
                mProductDocRepLink = (TextView) itemView.findViewById(R.id.product_doc_rep_link);
                mProductAvgBuildLabel = (TextView) itemView.findViewById(R.id.product_avg_time_label);
                mProductLangLabel = (TextView) itemView.findViewById(R.id.product_lang_label);
                */
            }

            public void bindChange(ChangeVM change) {
                /*
                UserVM Author = product.getUser();
                mProductOrderLabel.setText(product.getId().toString());
                mProductNameLabel.setText(product.getName());
                mProductComitedLabel.setText(Author.getName()+" "+Author.getFamilyName());
                mProductDescriptionLabel.setText(product.getDescription());
                //mProductDocRepLabel.setText(product.getId());
                mProductDocRepLink.setText(product.getRepositoryUrl());
                mProductAvgBuildLabel.setText((product.getAvgBuildTime()).toString());
                mProductLangLabel.setText(product.getTag());
                */
            }

        }

        @NonNull
        @Override
        public ChangeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_change, parent,false);
            return new ChangeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ChangeViewHolder holder, int position) {
            ChangeVM change = mChangeList.get(position);
            holder.bindChange(change);
        }

        @Override
        public int getItemCount() {
            return mChangeList.size();
        }
    }
}