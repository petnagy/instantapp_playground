package com.playground.instant.instantapp_playground.pages.details.presenter;

import com.playground.instant.instantapp_playground.common.Presenter;
import com.playground.instant.instantapp_playground.data.GitCommit;
import com.playground.instant.instantapp_playground.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public interface RepoDetailsPresenter extends Presenter {

    void showRepoData(GitHubRepo repo);

    void showCommits(List<GitCommit> body);
}
