package com.playground.instant.repolist_feature.pages.repos.view;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.playground.instant.base_module.common.SpaceItemDecor;
import com.playground.instant.base_module.data.GitHubRepo;
import com.playground.instant.repolist_feature.R;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public class DefaultReposView implements ReposView {

    private ProgressBar progress;

    private RepoAdapter adapter;

    private ConstraintLayout rootView;

    public DefaultReposView(RepoAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onCreate(Activity activity) {
        progress = activity.findViewById(R.id.progress);
        RecyclerView reposList = activity.findViewById(R.id.repo_list);
        rootView = activity.findViewById(R.id.details_root_view);
        reposList.addItemDecoration(new SpaceItemDecor(16));
        reposList.setAdapter(adapter);
        reposList.setLayoutManager(new GridLayoutManager(activity, 1));
    }

    @Override
    public View getRootView() {
        return rootView;
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
