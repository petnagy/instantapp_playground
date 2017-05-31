package com.playground.instant.commitlist_feature.pages.details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.playground.instant.base_module.ProjectApplication;
import com.playground.instant.commitlist_feature.R;
import com.playground.instant.commitlist_feature.pages.details.injection.components.ActivityComponent;
import com.playground.instant.base_module.data.GitCommit;
import com.playground.instant.base_module.data.GitHubOwner;
import com.playground.instant.base_module.data.GitHubRepo;
import com.playground.instant.base_module.network.GitHubEndpoint;
import com.playground.instant.commitlist_feature.pages.details.injection.components.DaggerActivityComponent;
import com.playground.instant.commitlist_feature.pages.details.injection.modules.CommitListActivityModule;
import com.playground.instant.commitlist_feature.pages.details.presenter.RepoDetailsPresenter;
import com.playground.instant.commitlist_feature.pages.details.view.RepoDetailsView;

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
                .commitListActivityModule(new CommitListActivityModule(this))
                .applicationComponent(ProjectApplication.get(this).getComponent())
                .build();
        activityComponent.inject(this);

        GitHubRepo repo = handleIntent(getIntent());

        view.onCreate(this);
        presenter.showView(view);
        presenter.showRepoData(repo);

        handleNetworkCall(repo);
    }


    private GitHubRepo handleIntent(Intent intent) {
        String appLinkAction = intent.getAction();
        Uri appLinkData = intent.getData();
        GitHubRepo repo;
        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null){
            Log.d("Applink", appLinkAction);
            Log.d("Applink", appLinkData.toString());
            repo = new GitHubRepo();
            repo.setName(appLinkData.getQueryParameter("repo"));
            GitHubOwner owner = new GitHubOwner();
            owner.setLogin(appLinkData.getQueryParameter("user"));
            repo.setOwner(owner);
        } else {
            repo = getIntent().getParcelableExtra(GITHUB_REPO);
            if (repo == null) {
                //TODO error message
            }
        }
        return repo;
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