package com.playground.instant.instantapp_playground.pages.details.presenter;

import com.playground.instant.instantapp_playground.common.View;
import com.playground.instant.instantapp_playground.data.GitCommit;
import com.playground.instant.instantapp_playground.data.GitHubRepo;
import com.playground.instant.instantapp_playground.pages.details.view.RepoDetailsView;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public class DefaultRepoDetailsPresenter implements RepoDetailsPresenter {

    private RepoDetailsView view;

    @Override
    public void showView(View view) {
        this.view = (RepoDetailsView) view;
    }

    @Override
    public void showRepoData(GitHubRepo repo) {
        view.showRepoData(repo);
    }

    @Override
    public void showCommits(List<GitCommit> commitList) {
        view.hideProgress();
        view.showCommits(commitList);
    }
}
