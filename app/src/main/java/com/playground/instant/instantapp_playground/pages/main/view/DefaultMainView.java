package com.playground.instant.instantapp_playground.pages.main.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.playground.instant.R;


/**
 * Created by petnagy on 2017. 05. 24..
 */

public class DefaultMainView implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private EditText userName;

    private ShowNextActivityCallback callback;

    private DrawerLayout drawer;

    private ConstraintLayout mainRootView;

    @Override
    public void onCreate(Activity activity) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);

        mainRootView = activity.findViewById(R.id.main_root_view);

        drawer = activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = activity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        userName = activity.findViewById(R.id.user_name);
        Button btnShowNextActivity = activity.findViewById(R.id.btn_next_screen);
        callback = (ShowNextActivityCallback) activity;
        btnShowNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onNextActivityPressed(userName.getText().toString());
            }
        });
    }

    @Override
    public View getRootView() {
        return mainRootView;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean handleBackPressed() {
        boolean result = false;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            result = true;
        }
        return result;
    }
}
