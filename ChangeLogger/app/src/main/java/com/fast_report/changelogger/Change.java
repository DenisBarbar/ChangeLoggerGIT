package com.fast_report.changelogger;

import java.util.UUID;

public class Change {
    private UUID mId;
    private String mVersion;
    private String mType;
    private String mGroup;
    private String mAuthor;
    private String mText;
    private String mAnnotation;
    private String mDocumentationLink;
    private String mTaskLink;
    private String mMergeLink;
    private boolean mPrivateChange;


    private String mChangedText;

    public Change() {
        mId = UUID.randomUUID();
        mType = "added";
        mGroup = "public";
    }

    public UUID getId() {
        return mId;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getGroup() {
        return mGroup;
    }

    public void setGroup(String group) {
        mGroup = group;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getChangedText() {
        return mChangedText;
    }

    public void setChangedText(String changedText) {
        mChangedText = changedText;
    }
}
