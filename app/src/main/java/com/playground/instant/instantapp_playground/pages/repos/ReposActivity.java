package com.playground.instant.instantapp_playground.pages.repos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.playground.instant.instantapp_playground.ProjectApplication;
import com.playground.instant.instantapp_playground.R;
import com.playground.instant.instantapp_playground.data.GitHubRepo;
import com.playground.instant.instantapp_playground.injection.components.ActivityComponent;
import com.playground.instant.instantapp_playground.injection.components.DaggerActivityComponent;
import com.playground.instant.instantapp_playground.injection.modules.ActivityModule;
import com.playground.instant.instantapp_playground.network.GitHubEndpoint;
import com.playground.instant.instantapp_playground.pages.details.RepoDetailsActivity;
import com.playground.instant.instantapp_playground.pages.repos.presenter.ReposPresenter;
import com.playground.instant.instantapp_playground.pages.repos.view.RepoCardClickCallback;
import com.playground.instant.instantapp_playground.pages.repos.view.ReposView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by petnagy on 2017. 05. 24..
 */

public class ReposActivity extends AppCompatActivity implements RepoCardClickCallback {

    private static final String USER_NAME = "userName";

    @Inject
    ReposView view;

    @Inject
    ReposPresenter presenter;

    @Inject
    GitHubEndpoint endpoint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repos_layout);

        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(ProjectApplication.get(this).getComponent())
                .build();
        activityComponent.inject(this);

        Intent intent = getIntent();
        String userName = handleIntent(intent);

        view.onCreate(this);
        presenter.showView(view);

        if (userName == null) {
            //TODO Error message
        } else {
            handleNetworkCall(userName);
        }
    }

    private String handleIntent(Intent intent) {
        String appLinkAction = intent.getAction();
        Uri appLinkData = intent.getData();
        String userName = null;
        Log.d("Applink", appLinkAction);
        Log.d("Applink", appLinkData.toString());
        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null){
            userName = appLinkData.getQueryParameter("user");
        } else {
            userName = getUserName(intent);
        }
        return userName;
    }


    @Override
    public void onCardClick(GitHubRepo repo) {
        startActivity(RepoDetailsActivity.launchDetailsActivity(this, repo));
    }

    private void handleNetworkCall(String userName) {
        Call<List<GitHubRepo>> call = endpoint.getRepos(userName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                if (response.isSuccessful()) {
                    presenter.showData(response.body());
                } else {
                    //TODO error message
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                //TODO Error message
            }
        });
    }

    private String getUserName(Intent intent) {
        return intent.getStringExtra(USER_NAME);
    }

    public static Intent launchIntent(Context context, String userName) {
        Intent intent = new Intent(context, ReposActivity.class);
        intent.putExtra(USER_NAME, userName);
        return intent;
    }

}
