package com.playground.instant.base_module;

import android.app.Application;
import android.content.Context;

import com.playground.instant.base_module.injection.components.ApplicationComponent;
import com.playground.instant.base_module.injection.components.DaggerApplicationComponent;
import com.playground.instant.base_module.injection.modules.ApplicationModule;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public class ProjectApplication extends Application {

    protected ApplicationComponent appComponent;

    public static ProjectApplication get(Context context) {
        return (ProjectApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        appComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return appComponent;
    }

}
