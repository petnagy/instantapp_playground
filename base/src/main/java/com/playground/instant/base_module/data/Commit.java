package com.playground.instant.base_module.data;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public class Commit {

    private String message;

    private Committer committer;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }
}
