package com.playground.instant.repolist_feature.pages.repos.injection.components;

import com.playground.instant.base_module.injection.PerActivity;
import com.playground.instant.base_module.injection.components.ApplicationComponent;
import com.playground.instant.repolist_feature.pages.repos.ReposActivity;
import com.playground.instant.repolist_feature.pages.repos.injection.modules.RepoListActivityModule;

import dagger.Component;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {RepoListActivityModule.class})
public interface ActivityComponent {

    void inject(ReposActivity activity);
}
