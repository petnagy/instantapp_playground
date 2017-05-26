package com.playground.instant.instantapp_playground.injection.components;

import com.playground.instant.instantapp_playground.injection.PerActivity;
import com.playground.instant.instantapp_playground.injection.modules.ActivityModule;
import com.playground.instant.instantapp_playground.pages.details.RepoDetailsActivity;
import com.playground.instant.instantapp_playground.pages.main.MainActivity;
import com.playground.instant.instantapp_playground.pages.repos.ReposActivity;

import dagger.Component;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(ReposActivity activity);

    void inject(RepoDetailsActivity activity);
}
