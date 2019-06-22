package com.fast_report.changelogger;

import android.content.Intent;
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

            private TextView mVersionOrderLabel;
            private TextView mVersionNameLabel;
            private TextView mVersionStateLabel;
            private TextView mVersionTypeLabel;
            private TextView mVersionChangesCountLabel;

            private Button mDeleteButton;
            private Button mEditButton;

            public VersionViewHolder (View itemView){
                super(itemView);
                mVersionOrderLabel = (TextView) itemView.findViewById(R.id.version_order_label);
                mVersionNameLabel = (TextView) itemView.findViewById(R.id.version_name_label);
                mVersionStateLabel = (TextView) itemView.findViewById(R.id.version_state_label);
                mVersionTypeLabel = (TextView) itemView.findViewById(R.id.version_type_label);
                mVersionChangesCountLabel = (TextView) itemView.findViewById(R.id.version_changes_count_label);
                mEditButton = (Button) itemView.findViewById(R.id.edit_button);
                mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = SingleVersionActivity.newIntent(getActivity(), mVersion.getId());
                        startActivity(intent);
                    }
                });
            }

            public void bindVersion(ProductVersionVM version) {
                mVersion = version;
                String versionName = mVersion.getMajor() + "." + mVersion.getMinor() + "." + mVersion.getBuild();
                if (version.getMajor() == Integer.MAX_VALUE){
                    versionName = "Current version";
                }
                mVersionOrderLabel.setText("#" + mVersion.getId().toString());
                mVersionNameLabel.setText("Version: " + versionName);
                mVersionStateLabel.setText(mVersion.getState().toString());
                mVersionTypeLabel.setText(mVersion.getType().toString());
                mVersionChangesCountLabel.setText("Changes count: " + Integer.toString(mVersion.getChangesCount(), 10));
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