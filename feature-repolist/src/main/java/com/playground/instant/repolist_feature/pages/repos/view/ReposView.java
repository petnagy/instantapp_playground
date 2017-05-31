package com.playground.instant.repolist_feature.pages.repos.view;

import com.playground.instant.base_module.common.View;
import com.playground.instant.base_module.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public interface ReposView extends View {

    void hideProgress();

    void showRepoList(List<GitHubRepo> repoList);
}
