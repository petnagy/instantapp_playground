package com.playground.instant.commitlist_feature.pages.details.view;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.playground.instant.base_module.common.SpaceItemDecor;
import com.playground.instant.base_module.data.GitCommit;
import com.playground.instant.base_module.data.GitHubRepo;
import com.playground.instant.commitlist_feature.R;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public class DefaultRepoDetailsView implements RepoDetailsView {

    private TextView repoName;

    private TextView repoOwner;

    private TextView language;

    private ProgressBar progress;

    private CommitAdapter adapter;

    private ConstraintLayout rootView;

    public DefaultRepoDetailsView(CommitAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onCreate(Activity activity) {
        repoName = activity.findViewById(R.id.repo_name);
        repoOwner = activity.findViewById(R.id.repo_owner);
        language = activity.findViewById(R.id.repo_language);
        progress = activity.findViewById(R.id.commit_progress);
        rootView = activity.findViewById(R.id.details_root_view);
        RecyclerView commitList = activity.findViewById(R.id.commit_list);
        commitList.setLayoutManager(new GridLayoutManager(activity, 1));
        commitList.addItemDecoration(new SpaceItemDecor(8));
        commitList.setAdapter(adapter);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void showRepoData(GitHubRepo repo) {
        repoName.setText(repo.getName());
        repoOwner.setText(repo.getOwner().getLogin());
        language.setText(repo.getLanguage());
    }

    @Override
    public void showCommits(List<GitCommit> commitList) {
        adapter.setCommits(commitList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }
}
