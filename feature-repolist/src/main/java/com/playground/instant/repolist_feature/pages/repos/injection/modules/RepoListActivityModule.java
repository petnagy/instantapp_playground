package com.playground.instant.repolist_feature.pages.repos.injection.modules;

import android.app.Activity;

import com.playground.instant.base_module.injection.PerActivity;
import com.playground.instant.base_module.injection.modules.BaseActivityModule;
import com.playground.instant.repolist_feature.pages.repos.presenter.DefaultReposPresenter;
import com.playground.instant.repolist_feature.pages.repos.presenter.ReposPresenter;
import com.playground.instant.repolist_feature.pages.repos.view.DefaultReposView;
import com.playground.instant.repolist_feature.pages.repos.view.RepoAdapter;
import com.playground.instant.repolist_feature.pages.repos.view.RepoCardClickCallback;
import com.playground.instant.repolist_feature.pages.repos.view.ReposView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@Module
public class RepoListActivityModule extends BaseActivityModule {

    public RepoListActivityModule(Activity activity) {
        super(activity);
    }

    @Provides
    @PerActivity
    ReposView provideReposView(RepoAdapter adapter) {
        return new DefaultReposView(adapter);
    }

    @Provides
    @PerActivity
    ReposPresenter provideReposPresenter() {
        return new DefaultReposPresenter();
    }

    @Provides
    @PerActivity
    RepoAdapter provideRepoAdapter() {
        return new RepoAdapter((RepoCardClickCallback) activity);
    }

}
