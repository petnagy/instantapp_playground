package com.playground.instant.instantapp_playground.pages.main.injection.components;

import com.playground.instant.base_module.injection.PerActivity;
import com.playground.instant.base_module.injection.components.ApplicationComponent;
import com.playground.instant.instantapp_playground.pages.main.MainActivity;
import com.playground.instant.instantapp_playground.pages.main.injection.modules.MainActivityModule;

import dagger.Component;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {MainActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
}
