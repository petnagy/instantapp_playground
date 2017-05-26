package com.playground.instant.instantapp_playground.pages.main.view;

import android.app.Activity;
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

import com.playground.instant.instantapp_playground.R;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public class DefaultMainView implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private EditText userName;

    private Button btnShowNextActivity;

    private ShowNextActivityCallback callback;

    private DrawerLayout drawer;

    @Override
    public void onCreate(Activity activity) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);

        drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        userName = (EditText) activity.findViewById(R.id.user_name);
        btnShowNextActivity = (Button) activity.findViewById(R.id.btn_next_screen);
        callback = (ShowNextActivityCallback) activity;
        btnShowNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onNextActivityPressed(userName.getText().toString());
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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
