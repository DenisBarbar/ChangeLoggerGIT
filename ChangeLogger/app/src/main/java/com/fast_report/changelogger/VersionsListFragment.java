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

import io.swagger.client.model.ProductVersionVM;
import io.swagger.client.model.UserVM;

public class VersionsListFragment extends Fragment {

    VersionLab mVersionLab = VersionLab.getInstance();
    RecyclerView mVersionsRecyclerView;

    VersionVMCallbackInterface mVersionVMCallback = new VersionVMCallback();
    ArrayList <ProductVersionVM> mVersions = new ArrayList<>(mVersionLab.getAllVersions(mVersionVMCallback));
    VersionAdapter mVersionAdapter = new VersionAdapter(mVersions);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Versions");
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mVersionsRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mVersionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mVersionsRecyclerView.setAdapter(mVersionAdapter);
        return view;
    }

    private class VersionAdapter extends RecyclerView.Adapter <VersionAdapter.VersionViewHolder>{

        ArrayList<ProductVersionVM> mVersionList;

        public VersionAdapter (ArrayList<ProductVersionVM> versionList){
            this.mVersionList = versionList;
        }

        public class VersionViewHolder extends RecyclerView.ViewHolder {

            private ProductVersionVM mVersion;

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

            public VersionViewHolder (View itemView){
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

            public void bindVersion(ProductVersionVM version) {
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
        public VersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_version, parent,false);
            return new VersionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VersionViewHolder holder, int position) {
            ProductVersionVM version = mVersionList.get(position);
            holder.bindVersion(version);
        }

        @Override
        public int getItemCount() {
            return mVersionList.size();
        }
    }
}