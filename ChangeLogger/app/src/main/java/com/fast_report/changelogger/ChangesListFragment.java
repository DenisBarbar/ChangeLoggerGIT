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
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.ChangeVM;
import io.swagger.client.model.ProductVersionVM;
import io.swagger.client.model.TranslationVM;
import io.swagger.client.model.UserVM;

public class ChangesListFragment extends Fragment {

    ChangeLab mChangeLab = ChangeLab.getInstance();
    RecyclerView mChangesRecyclerView;

    private Integer mProjectId = 1;
    ChangeVMCallbackInterface mChangeVMCallback = new ChangeVMCallback();
    ArrayList <ChangeVM> mChanges = new ArrayList<>(mChangeLab.getAllChanges(mProjectId, mChangeVMCallback));
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
            private TextView mListItemChangeVersion;
            private TextView mListItemChangeType;
            private TextView mListItemChangeGroup;
            private TextView mListItemChangeAuthor;
            private TextView mListItemChangeText;
            private TextView mListItemChangeAnnotation;
            private TextView mListItemTaskLink;
            private TextView mListItemMergeLink;
            private TextView mListItemDocumentationLink;
            private CheckBox mPrivateChangeCheckbox;

            private Button mDeleteButton;
            private Button mEditButton;

            public ChangeViewHolder (View itemView){
                super(itemView);
                mListItemChangeVersion = (TextView) itemView.findViewById(R.id.list_item_change_version);
                mListItemChangeType = (TextView) itemView.findViewById(R.id.list_item_change_type);
                mListItemChangeGroup = (TextView) itemView.findViewById(R.id.list_item_change_group);
                mListItemChangeAuthor = (TextView) itemView.findViewById(R.id.list_item_change_author);
                mListItemChangeText = (TextView) itemView.findViewById(R.id.list_item_change_text);
                mListItemChangeAnnotation = (TextView) itemView.findViewById(R.id.list_item_change_annotation);
                mListItemTaskLink = (TextView) itemView.findViewById(R.id.task_link);
                mListItemMergeLink = (TextView) itemView.findViewById(R.id.merge_link);
                mListItemDocumentationLink = (TextView) itemView.findViewById(R.id.documentation_link);
                mPrivateChangeCheckbox = (CheckBox) itemView.findViewById(R.id.private_change_checkbox);

                mEditButton = (Button) itemView.findViewById(R.id.edit_button);
                mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = SingleChangeActivity.newIntent(getActivity(), mChange.getId()); //Неявный вызов putExtra?
                        startActivity(intent);
                    }
                });
            }

            public void bindChange(ChangeVM change) {
                mChange = change;
                UserVM mAuthor = change.getUser();
                ProductVersionVM mVersion = change.getVersion();
                String versionName = mVersion.getMajor() + "." + mVersion.getMinor() + "." + mVersion.getBuild();
                if (mVersion.getMajor() == Integer.MAX_VALUE){
                    versionName = "Current version";
                }
                List <TranslationVM> mTranslations = change.getTranslations();
                mListItemChangeVersion.setText(versionName);
                mListItemChangeType.setText("Type: " + change.getType().toString());
                mListItemChangeGroup.setText("Group: "+ change.getGroup().getName());
                mListItemChangeAuthor.setText(mAuthor.getName()+" "+mAuthor.getFamilyName());
                mListItemChangeText.setText(mTranslations.get(1).getText());
                mListItemChangeAnnotation.setText(mTranslations.get(1).getAnnotation());
                mListItemTaskLink.setText(change.getTaskLink());
                mListItemMergeLink.setText((change.getMergeRequestLink()));
                mListItemDocumentationLink.setText(mTranslations.get(1).getDocumentationLink());
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