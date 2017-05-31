package com.playground.instant.base_module.injection.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@Module
public class BaseActivityModule {

    protected Activity activity;

    public BaseActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

//    @Provides
//    @PerActivity
//    MainView provideMainView() {
//        return new DefaultMainView();
//    }
//
//    @Provides
//    @PerActivity
//    MainPresenter provideMainPresenter() {
//        return new DefaultMainPresenter();
//    }
//
//    @Provides
//    @PerActivity
//    ReposView provideReposView(RepoAdapter adapter) {
//        return new DefaultReposView(adapter);
//    }
//
//    @Provides
//    @PerActivity
//    ReposPresenter provideReposPresenter() {
//        return new DefaultReposPresenter();
//    }
//
//    @Provides
//    @PerActivity
//    RepoAdapter provideRepoAdapter() {
//        return new RepoAdapter((RepoCardClickCallback) activity);
//    }
//
//    @Provides
//    @PerActivity
//    RepoDetailsView provideRepoDetailsView(CommitAdapter adapter) {
//        return new DefaultRepoDetailsView(adapter);
//    }
//
//    @Provides
//    @PerActivity
//    CommitAdapter provideCommitAdapter() {
//        return new CommitAdapter();
//    }
//
//    @Provides
//    @PerActivity
//    RepoDetailsPresenter provideRepoDetailsPresenter() {
//        return new DefaultRepoDetailsPresenter();
//    }
}
