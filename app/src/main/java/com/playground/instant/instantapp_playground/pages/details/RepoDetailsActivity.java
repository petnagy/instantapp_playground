package com.playground.instant.instantapp_playground.pages.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.playground.instant.instantapp_playground.ProjectApplication;
import com.playground.instant.instantapp_playground.R;
import com.playground.instant.instantapp_playground.data.GitCommit;
import com.playground.instant.instantapp_playground.data.GitHubRepo;
import com.playground.instant.instantapp_playground.injection.components.ActivityComponent;
import com.playground.instant.instantapp_playground.injection.components.DaggerActivityComponent;
import com.playground.instant.instantapp_playground.injection.modules.ActivityModule;
import com.playground.instant.instantapp_playground.network.GitHubEndpoint;
import com.playground.instant.instantapp_playground.pages.details.presenter.RepoDetailsPresenter;
import com.playground.instant.instantapp_playground.pages.details.view.RepoDetailsView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public class RepoDetailsActivity extends AppCompatActivity {

    private static final String GITHUB_REPO = "GitHubRepo";

    @Inject
    RepoDetailsView view;

    @Inject
    RepoDetailsPresenter presenter;

    @Inject
    GitHubEndpoint endpoint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repo_details_layout);

        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(ProjectApplication.get(this).getComponent())
                .build();
        activityComponent.inject(this);

        GitHubRepo repo = getIntent().getParcelableExtra(GITHUB_REPO);
        if (repo == null) {
            //TODO error message
        }

        view.onCreate(this);
        presenter.showView(view);
        presenter.showRepoData(repo);

        handleNetworkCall(repo);
    }

    private void handleNetworkCall(GitHubRepo repo) {
        Call<List<GitCommit>> call = endpoint.getCommits(repo.getOwner().getLogin(), repo.getName());
        call.enqueue(new Callback<List<GitCommit>>() {
            @Override
            public void onResponse(Call<List<GitCommit>> call, Response<List<GitCommit>> response) {
                if (response.isSuccessful()) {
                    presenter.showCommits(response.body());
                } else {
                    //TODO error message
                }
            }

            @Override
            public void onFailure(Call<List<GitCommit>> call, Throwable t) {
                //TODO Error message
            }
        });
    }

    public static Intent launchDetailsActivity(Context context, GitHubRepo repo) {
        Intent intent = new Intent(context, RepoDetailsActivity.class);
        intent.putExtra(GITHUB_REPO, repo);
        return intent;
    }
}
