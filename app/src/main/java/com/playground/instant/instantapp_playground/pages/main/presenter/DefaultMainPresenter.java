package com.playground.instant.instantapp_playground.pages.main.presenter;

import com.playground.instant.base_module.common.View;
import com.playground.instant.instantapp_playground.pages.main.view.MainView;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public class DefaultMainPresenter implements MainPresenter {

    private MainView view;

    @Override
    public void showView(View view) {
        this.view = (MainView) view;
    }

    @Override
    public boolean onBackPressed() {
        return view.handleBackPressed();
    }
}
