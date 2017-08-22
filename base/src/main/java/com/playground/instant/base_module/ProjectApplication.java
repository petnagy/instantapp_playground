package com.playground.instant.base_module;

import android.app.Application;
import android.content.Context;

import com.playground.instant.base_module.injection.components.ApplicationComponent;
import com.playground.instant.base_module.injection.components.DaggerApplicationComponent;

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
                .application(this)
                .build();
    }

    public ApplicationComponent getComponent() {
        return appComponent;
    }

}
