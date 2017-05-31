package com.playground.instant.commitlist_feature.pages.details.presenter;

import com.playground.instant.base_module.common.Presenter;
import com.playground.instant.base_module.data.GitCommit;
import com.playground.instant.base_module.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public interface RepoDetailsPresenter extends Presenter {

    void showRepoData(GitHubRepo repo);

    void showCommits(List<GitCommit> body);
}
