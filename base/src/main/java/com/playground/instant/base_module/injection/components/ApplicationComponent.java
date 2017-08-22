package com.playground.instant.base_module.injection.components;

import android.app.Application;
import android.content.Context;

import com.playground.instant.base_module.ProjectApplication;
import com.playground.instant.base_module.injection.ApplicationContext;
import com.playground.instant.base_module.injection.modules.ApplicationModule;
import com.playground.instant.base_module.network.GitHubEndpoint;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        ApplicationComponent build();
        @BindsInstance Builder application(Application application);
    }

    @ApplicationContext
    Context getContext();

    GitHubEndpoint getGitHubEndpoint();
}
