package com.playground.instant.instantapp_playground.pages.repos.presenter;

import com.playground.instant.instantapp_playground.common.Presenter;
import com.playground.instant.instantapp_playground.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public interface ReposPresenter extends Presenter {

    void showData(List<GitHubRepo> repoList);
}
