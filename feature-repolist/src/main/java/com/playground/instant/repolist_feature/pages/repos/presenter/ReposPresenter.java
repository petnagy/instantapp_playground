package com.playground.instant.repolist_feature.pages.repos.presenter;

import com.playground.instant.base_module.common.Presenter;
import com.playground.instant.base_module.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public interface ReposPresenter extends Presenter {

    void showData(List<GitHubRepo> repoList);
}
