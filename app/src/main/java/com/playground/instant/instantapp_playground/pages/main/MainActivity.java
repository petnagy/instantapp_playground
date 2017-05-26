package com.playground.instant.instantapp_playground.pages.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.playground.instant.instantapp_playground.ProjectApplication;
import com.playground.instant.instantapp_playground.R;
import com.playground.instant.instantapp_playground.injection.components.ActivityComponent;
import com.playground.instant.instantapp_playground.injection.components.DaggerActivityComponent;
import com.playground.instant.instantapp_playground.injection.modules.ActivityModule;
import com.playground.instant.instantapp_playground.pages.main.presenter.MainPresenter;
import com.playground.instant.instantapp_playground.pages.main.view.MainView;
import com.playground.instant.instantapp_playground.pages.main.view.ShowNextActivityCallback;
import com.playground.instant.instantapp_playground.pages.repos.ReposActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ShowNextActivityCallback {

    @Inject
    MainView view;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(ProjectApplication.get(this).getComponent())
                .build();
        activityComponent.inject(this);

        view.onCreate(this);
        presenter.showView(view);
    }

    @Override
    public void onBackPressed() {
        boolean isBackPressedHandled = presenter.onBackPressed();
        if (!isBackPressedHandled) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNextActivityPressed(String userName) {
        Intent intent = ReposActivity.launchIntent(this, userName);
        startActivity(intent);
    }
}
