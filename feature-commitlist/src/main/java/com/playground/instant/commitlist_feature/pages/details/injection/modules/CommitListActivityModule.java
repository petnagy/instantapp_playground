package com.playground.instant.commitlist_feature.pages.details.injection.modules;

import android.app.Activity;

import com.playground.instant.base_module.injection.PerActivity;
import com.playground.instant.base_module.injection.modules.BaseActivityModule;
import com.playground.instant.commitlist_feature.pages.details.presenter.DefaultRepoDetailsPresenter;
import com.playground.instant.commitlist_feature.pages.details.presenter.RepoDetailsPresenter;
import com.playground.instant.commitlist_feature.pages.details.view.CommitAdapter;
import com.playground.instant.commitlist_feature.pages.details.view.DefaultRepoDetailsView;
import com.playground.instant.commitlist_feature.pages.details.view.RepoDetailsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@Module
public class CommitListActivityModule extends BaseActivityModule {

    public CommitListActivityModule(Activity activity) {
        super(activity);
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
