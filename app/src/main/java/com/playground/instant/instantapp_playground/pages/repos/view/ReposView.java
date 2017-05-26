package com.playground.instant.instantapp_playground.pages.repos.view;

import com.playground.instant.instantapp_playground.common.View;
import com.playground.instant.instantapp_playground.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public interface ReposView extends View {

    void hideProgress();

    void showRepoList(List<GitHubRepo> repoList);
}
