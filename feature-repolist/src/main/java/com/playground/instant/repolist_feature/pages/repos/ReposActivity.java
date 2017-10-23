package com.playground.instant.repolist_feature.pages.repos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.playground.instant.base_module.ProjectApplication;
import com.playground.instant.base_module.data.GitHubRepo;
import com.playground.instant.base_module.dialog.ErrorDialog;
import com.playground.instant.repolist_feature.pages.repos.injection.components.ActivityComponent;
import com.playground.instant.base_module.network.GitHubEndpoint;
import com.playground.instant.repolist_feature.R;
import com.playground.instant.repolist_feature.pages.repos.injection.components.DaggerActivityComponent;
import com.playground.instant.repolist_feature.pages.repos.injection.modules.RepoListActivityModule;
import com.playground.instant.repolist_feature.pages.repos.presenter.ReposPresenter;
import com.playground.instant.repolist_feature.pages.repos.view.RepoCardClickCallback;
import com.playground.instant.repolist_feature.pages.repos.view.ReposView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by petnagy on 2017. 05. 24..
 */

public class ReposActivity extends AppCompatActivity implements RepoCardClickCallback {

    private static final String USER_NAME_EXTRA = "userName";

    private static final String TAG = ReposActivity.class.getSimpleName();

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
                .repoListActivityModule(new RepoListActivityModule(this))
                .applicationComponent(ProjectApplication.get(this).getComponent())
                .build();
        activityComponent.inject(this);

        Log.d(TAG, "onCreate");

        Intent intent = getIntent();
        String userName = handleIntent(intent);

        view.onCreate(this);
        presenter.showView(view);

        if (userName == null) {
            ErrorDialog.newInstance("UserName Parameter was null!!!");
        } else {
            handleNetworkCall(userName);
        }
    }

    private String handleIntent(Intent intent) {
        Log.d("ReposActivity", intent.toString());
        String action = intent.getAction();
        Uri appLinkData = intent.getData();
        String userName;
        if (appLinkData != null && action == null) {
            Log.d(TAG, "InstantApp");
            userName = appLinkData.getQueryParameter("user");
            Log.d(TAG, appLinkData.toString());
        } else if (Intent.ACTION_VIEW.equals(action) && appLinkData != null) {
            Log.d(TAG, "AppLink");
            userName = appLinkData.getQueryParameter("user");
            Log.d(TAG, action);
            Log.d(TAG, appLinkData.toString());
        } else {
            userName = getUserName(intent);
        }
        return userName;
    }

    @Override
    public void onCardClick(GitHubRepo repo) {
        //startActivity(RepoDetailsActivity.launchDetailsActivity(this, repo));
        invokeDeepLink(this, getString(R.string.next_page, repo.getOwner().getLogin(), repo.getName(), repo.getLanguage()));
    }

    private void invokeDeepLink(Context context, String deepLink) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink));
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setPackage(context.getPackageName());
        context.startActivity(intent);
    }

    private void handleNetworkCall(String userName) {
        Call<List<GitHubRepo>> call = endpoint.getRepos(userName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(@NonNull Call<List<GitHubRepo>> call, @NonNull Response<List<GitHubRepo>> response) {
                Log.d(TAG, "Search is HTTP200");
                if (response.isSuccessful()) {
                    presenter.showData(response.body());
                } else {
                    ErrorDialog.newInstance("Response was not successful!");
                    Log.d(TAG, "There was an error in Search");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GitHubRepo>> call, @NonNull Throwable throwable) {
                ErrorDialog.newInstance("NetworkError " + throwable.getMessage());
                Log.e(TAG, "NetworkError");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    private String getUserName(Intent intent) {
        return intent.getStringExtra(USER_NAME_EXTRA);
    }

    public static Intent launchIntent(Context context, String userName) {
        Intent intent = new Intent(context, ReposActivity.class);
        intent.putExtra(USER_NAME_EXTRA, userName);
        return intent;
    }

}
