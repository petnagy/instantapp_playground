package com.playground.instant.commitlist_feature.pages.details.injection.components;

import com.playground.instant.base_module.injection.PerActivity;
import com.playground.instant.base_module.injection.components.ApplicationComponent;
import com.playground.instant.commitlist_feature.pages.details.RepoDetailsActivity;
import com.playground.instant.commitlist_feature.pages.details.injection.modules.CommitListActivityModule;

import dagger.Component;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {CommitListActivityModule.class})
public interface ActivityComponent {

    //void inject(MainActivity activity);

    void inject(RepoDetailsActivity activity);
}
