package com.playground.instant.base_module.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public class GitHubOwner implements Parcelable {

    private String login;

    private long id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeLong(this.id);
    }

    public GitHubOwner() {
    }

    protected GitHubOwner(Parcel in) {
        this.login = in.readString();
        this.id = in.readLong();
    }

    public static final Creator<GitHubOwner> CREATOR = new Creator<GitHubOwner>() {
        @Override
        public GitHubOwner createFromParcel(Parcel source) {
            return new GitHubOwner(source);
        }

        @Override
        public GitHubOwner[] newArray(int size) {
            return new GitHubOwner[size];
        }
    };
}
