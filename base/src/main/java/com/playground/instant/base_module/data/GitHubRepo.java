package com.playground.instant.base_module.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public class GitHubRepo implements Parcelable {

    private int id;

    private String name;

    private String html_url;

    private String language;

    private GitHubOwner owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public GitHubOwner getOwner() {
        return owner;
    }

    public void setOwner(GitHubOwner owner) {
        this.owner = owner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.html_url);
        dest.writeString(this.language);
        dest.writeParcelable(this.owner, flags);
    }

    public GitHubRepo() {
    }

    protected GitHubRepo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.html_url = in.readString();
        this.language = in.readString();
        this.owner = in.readParcelable(GitHubOwner.class.getClassLoader());
    }

    public static final Creator<GitHubRepo> CREATOR = new Creator<GitHubRepo>() {
        @Override
        public GitHubRepo createFromParcel(Parcel source) {
            return new GitHubRepo(source);
        }

        @Override
        public GitHubRepo[] newArray(int size) {
            return new GitHubRepo[size];
        }
    };
}
