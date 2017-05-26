package com.playground.instant.instantapp_playground.pages.details.view;

import com.playground.instant.instantapp_playground.common.View;
import com.playground.instant.instantapp_playground.data.GitCommit;
import com.playground.instant.instantapp_playground.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public interface RepoDetailsView extends View {

    void showRepoData(GitHubRepo repo);

    void showCommits(List<GitCommit> commitList);

    void hideProgress();
}
