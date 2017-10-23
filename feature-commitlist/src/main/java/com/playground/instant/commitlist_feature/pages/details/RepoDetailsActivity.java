package com.playground.instant.commitlist_feature.pages.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.playground.instant.base_module.ProjectApplication;
import com.playground.instant.base_module.data.GitCommit;
import com.playground.instant.base_module.data.GitHubOwner;
import com.playground.instant.base_module.data.GitHubRepo;
import com.playground.instant.base_module.dialog.ErrorDialog;
import com.playground.instant.base_module.network.GitHubEndpoint;
import com.playground.instant.base_module.util.InstantAppUtil;
import com.playground.instant.commitlist_feature.R;
import com.playground.instant.commitlist_feature.pages.details.injection.components.ActivityComponent;
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

    //private static final String GITHUB_REPO = "GitHubRepo";

    private static final String TAG = RepoDetailsActivity.class.getSimpleName();

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

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        presenter.showSnackBar(InstantAppUtil.getAppType(this));
    }

    private GitHubRepo handleIntent(Intent intent) {
        String action = intent.getAction();
        Uri appLinkData = intent.getData();
        GitHubRepo repo;
        if (action == null && appLinkData != null) {
            Log.d(TAG, "InstantApp");
            Log.d(TAG, appLinkData.toString());
            repo = createGitHubRepo(appLinkData);
        } else if (Intent.ACTION_VIEW.equals(action) && appLinkData != null) {
            Log.d(TAG, "AppLink");
            Log.d(TAG, action);
            Log.d(TAG, appLinkData.toString());
            repo = createGitHubRepo(appLinkData);
        } else {
            Log.d(TAG, "It is not possible");
            throw new IllegalArgumentException("It is not possible!");
        }
        return repo;
    }

    private GitHubRepo createGitHubRepo(Uri appLinkData) {
        GitHubRepo repo = new GitHubRepo();
        repo.setName(appLinkData.getQueryParameter("repo"));
        GitHubOwner owner = new GitHubOwner();
        owner.setLogin(appLinkData.getQueryParameter("user"));
        String language = appLinkData.getQueryParameter("language");
        if (language != null && !language.isEmpty()) {
            repo.setLanguage(language);
        }
        repo.setOwner(owner);
        return repo;
    }

    private void handleNetworkCall(GitHubRepo repo) {
        Call<List<GitCommit>> call = endpoint.getCommits(repo.getOwner().getLogin(), repo.getName());
        call.enqueue(new Callback<List<GitCommit>>() {
            @Override
            public void onResponse(@NonNull Call<List<GitCommit>> call, @NonNull Response<List<GitCommit>> response) {
                if (response.isSuccessful()) {
                    presenter.showCommits(response.body());
                } else {
                    ErrorDialog.newInstance("Response was not Successful!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GitCommit>> call, @NonNull Throwable throwable) {
                ErrorDialog.newInstance("NetworkError " + throwable.getMessage());
            }
        });
    }

//    public static Intent launchDetailsActivity(Context context, GitHubRepo repo) {
//        Intent intent = new Intent(context, RepoDetailsActivity.class);
//        intent.putExtra(GITHUB_REPO, repo);
//        return intent;
//    }
}
