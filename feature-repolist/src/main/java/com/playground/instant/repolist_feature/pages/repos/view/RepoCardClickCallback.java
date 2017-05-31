package com.playground.instant.repolist_feature.pages.repos.view;

import com.playground.instant.base_module.data.GitHubRepo;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public interface RepoCardClickCallback {

    void onCardClick(GitHubRepo repo);

}
