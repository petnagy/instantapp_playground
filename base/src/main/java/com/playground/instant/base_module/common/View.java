package com.playground.instant.base_module.common;

import android.app.Activity;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public interface View {

    void onCreate(Activity activity);

    android.view.View getRootView();
}
