package com.playground.instant.instantapp_playground.pages.details.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.playground.instant.instantapp_playground.R;
import com.playground.instant.instantapp_playground.data.GitCommit;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 26..
 */

public class CommitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GitCommit> commitList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.commit_card_layout, viewGroup, false);
        return new CommitAdapter.CommitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        GitCommit commit = commitList.get(position);
        ((CommitViewHolder) viewHolder).commiterName.setText(commit.getCommit().getCommitter().getName());
        ((CommitViewHolder) viewHolder).commitMessage.setText(commit.getCommit().getMessage());
        ((CommitViewHolder) viewHolder).date.setText(commit.getCommit().getCommitter().getDate());
    }

    @Override
    public int getItemCount() {
        return commitList != null ? commitList.size() : 0;
    }

    public void setCommits(List<GitCommit> commitList) {
        this.commitList = commitList;
    }

    public class CommitViewHolder extends RecyclerView.ViewHolder {

        private TextView commiterName;

        private TextView commitMessage;

        private TextView date;

        public CommitViewHolder(View view) {
            super(view);
            commiterName = (TextView) view.findViewById(R.id.committer_name);
            commitMessage = (TextView) view.findViewById(R.id.commit_message);
            date = (TextView) view.findViewById(R.id.commit_date);
        }
    }
}
