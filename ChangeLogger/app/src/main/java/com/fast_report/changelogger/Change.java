package com.fast_report.changelogger;

import java.util.UUID;

public class Change {
    private UUID mId;
    private String mVersion;
    private String mType;
    private String mGroup;
    private String mAuthor;
    private String mChangedText;

    public Change() {
        //Генерирование уникального идентификатора
        mId = UUID.randomUUID();
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
