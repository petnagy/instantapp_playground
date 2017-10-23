package com.playground.instant.commitlist_feature.pages.details.presenter;

import android.support.design.widget.Snackbar;

import com.playground.instant.base_module.common.View;
import com.playground.instant.base_module.data.GitCommit;
import com.playground.instant.base_module.data.GitHubRepo;
import com.playground.instant.commitlist_feature.pages.details.view.RepoDetailsView;

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
    public void showSnackBar(String appType) {
        Snackbar.make(view.getRootView(), appType, Snackbar.LENGTH_SHORT).show();
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
