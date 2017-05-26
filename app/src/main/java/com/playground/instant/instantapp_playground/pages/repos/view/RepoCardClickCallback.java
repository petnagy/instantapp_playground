package com.playground.instant.instantapp_playground.pages.repos.view;

import com.playground.instant.instantapp_playground.data.GitHubRepo;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public interface RepoCardClickCallback {

    void onCardClick(GitHubRepo repo);

}
