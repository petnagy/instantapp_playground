package com.playground.instant.commitlist_feature.pages.details.view;

import com.playground.instant.base_module.common.View;
import com.playground.instant.base_module.data.GitCommit;
import com.playground.instant.base_module.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public interface RepoDetailsView extends View {

    void showRepoData(GitHubRepo repo);

    void showCommits(List<GitCommit> commitList);

    void hideProgress();
}
