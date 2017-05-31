package com.playground.instant.instantapp_playground.pages.main.injection.modules;

import android.app.Activity;

import com.playground.instant.base_module.injection.PerActivity;
import com.playground.instant.base_module.injection.modules.BaseActivityModule;
import com.playground.instant.commitlist_feature.pages.details.presenter.DefaultRepoDetailsPresenter;
import com.playground.instant.commitlist_feature.pages.details.presenter.RepoDetailsPresenter;
import com.playground.instant.commitlist_feature.pages.details.view.CommitAdapter;
import com.playground.instant.commitlist_feature.pages.details.view.DefaultRepoDetailsView;
import com.playground.instant.commitlist_feature.pages.details.view.RepoDetailsView;
import com.playground.instant.instantapp_playground.pages.main.presenter.DefaultMainPresenter;
import com.playground.instant.instantapp_playground.pages.main.presenter.MainPresenter;
import com.playground.instant.instantapp_playground.pages.main.view.DefaultMainView;
import com.playground.instant.instantapp_playground.pages.main.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@Module
public class MainActivityModule extends BaseActivityModule {

    public MainActivityModule(Activity activity) {
        super(activity);
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

}
