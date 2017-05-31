package com.playground.instant.repolist_feature.pages.repos.presenter;

import com.playground.instant.base_module.common.View;
import com.playground.instant.base_module.data.GitHubRepo;
import com.playground.instant.repolist_feature.pages.repos.view.ReposView;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public class DefaultReposPresenter implements ReposPresenter {

    private ReposView view;

    @Override
    public void showView(View view) {
        this.view = (ReposView) view;
    }

    @Override
    public void showData(List<GitHubRepo> repoList) {
        view.hideProgress();
        view.showRepoList(repoList);
    }
}
