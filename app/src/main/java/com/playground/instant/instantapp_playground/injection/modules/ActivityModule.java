package com.playground.instant.instantapp_playground.injection.modules;

import android.app.Activity;
import android.content.Context;

import com.playground.instant.instantapp_playground.injection.PerActivity;
import com.playground.instant.instantapp_playground.pages.details.presenter.DefaultRepoDetailsPresenter;
import com.playground.instant.instantapp_playground.pages.details.presenter.RepoDetailsPresenter;
import com.playground.instant.instantapp_playground.pages.details.view.CommitAdapter;
import com.playground.instant.instantapp_playground.pages.details.view.DefaultRepoDetailsView;
import com.playground.instant.instantapp_playground.pages.details.view.RepoDetailsView;
import com.playground.instant.instantapp_playground.pages.main.presenter.DefaultMainPresenter;
import com.playground.instant.instantapp_playground.pages.main.presenter.MainPresenter;
import com.playground.instant.instantapp_playground.pages.main.view.DefaultMainView;
import com.playground.instant.instantapp_playground.pages.main.view.MainView;
import com.playground.instant.instantapp_playground.pages.repos.presenter.DefaultReposPresenter;
import com.playground.instant.instantapp_playground.pages.repos.presenter.ReposPresenter;
import com.playground.instant.instantapp_playground.pages.repos.view.DefaultReposView;
import com.playground.instant.instantapp_playground.pages.repos.view.RepoAdapter;
import com.playground.instant.instantapp_playground.pages.repos.view.RepoCardClickCallback;
import com.playground.instant.instantapp_playground.pages.repos.view.ReposView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
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

    @Provides
    @PerActivity
    MainView provideMainView() {
        return new DefaultMainView();
    }

    @Provides
    @PerActivity
    MainPresenter provideMainPresenter() {
        return new DefaultMainPresenter();
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

    @Provides
    @PerActivity
    RepoDetailsView provideRepoDetailsView(CommitAdapter adapter) {
        return new DefaultRepoDetailsView(adapter);
    }

    @Provides
    @PerActivity
    CommitAdapter provideCommitAdapter() {
        return new CommitAdapter();
    }

    @Provides
    @PerActivity
    RepoDetailsPresenter provideRepoDetailsPresenter() {
        return new DefaultRepoDetailsPresenter();
    }
}
