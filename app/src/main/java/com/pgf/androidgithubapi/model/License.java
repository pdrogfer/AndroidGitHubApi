package com.pgf.androidgithubapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

public class License implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("spdx_id")
    private String spdxId;

    @SerializedName("key")
    private String key;

    @SerializedName("url")
    private String url;

    @SerializedName("node_id")
    private String nodeId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpdxId(String spdxId) {
        this.spdxId = spdxId;
    }

    public String getSpdxId() {
        return spdxId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeId() {
        return nodeId;
    }

    @Override
    public String toString() {
        return
                "License{" +
                        "name = '" + name + '\'' +
                        ",spdx_id = '" + spdxId + '\'' +
                        ",key = '" + key + '\'' +
                        ",url = '" + url + '\'' +
                        ",node_id = '" + nodeId + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.spdxId);
        dest.writeString(this.key);
        dest.writeString(this.url);
        dest.writeString(this.nodeId);
    }

    public License() {
    }

    protected License(Parcel in) {
        this.name = in.readString();
        this.spdxId = in.readString();
        this.key = in.readString();
        this.url = in.readString();
        this.nodeId = in.readString();
    }

    public static final Parcelable.Creator<License> CREATOR = new Parcelable.Creator<License>() {
        @Override
        public License createFromParcel(Parcel source) {
            return new License(source);
        }

        @Override
        public License[] newArray(int size) {
            return new License[size];
        }
    };
}