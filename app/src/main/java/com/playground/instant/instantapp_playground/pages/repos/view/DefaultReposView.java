package com.playground.instant.instantapp_playground.pages.repos.view;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.playground.instant.instantapp_playground.R;
import com.playground.instant.instantapp_playground.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public class DefaultReposView implements ReposView {

    private ProgressBar progress;

    private RepoAdapter adapter;

    public DefaultReposView(RepoAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onCreate(Activity activity) {
        progress = (ProgressBar) activity.findViewById(R.id.progress);
        RecyclerView reposList = (RecyclerView) activity.findViewById(R.id.repo_list);
        reposList.addItemDecoration(new SpaceItemDecor(16));
        reposList.setAdapter(adapter);
        reposList.setLayoutManager(new GridLayoutManager(activity, 1));
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showRepoList(List<GitHubRepo> repoList) {
        adapter.setRepoList(repoList);
        adapter.notifyDataSetChanged();
    }
}
