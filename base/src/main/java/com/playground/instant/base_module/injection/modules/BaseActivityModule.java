package com.playground.instant.base_module.injection.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@Module
public class BaseActivityModule {

    protected Activity activity;

    public BaseActivityModule(Activity activity) {
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
    
}
