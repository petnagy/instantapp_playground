package com.playground.instant.instantapp_playground.pages.repos.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.playground.instant.instantapp_playground.R;
import com.playground.instant.instantapp_playground.data.GitHubRepo;

import java.util.List;

/**
 * Created by petnagy on 2017. 05. 25..
 */

public class RepoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GitHubRepo> repoList;

    private RepoCardClickCallback callback;

    public RepoAdapter(RepoCardClickCallback callback) {
        this.callback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.repo_card_layout, viewGroup, false);
        return new RepoCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        GitHubRepo repo = repoList.get(position);
        ((RepoCardViewHolder)viewHolder).repoName.setText(repo.getName());
        ((RepoCardViewHolder)viewHolder).htmlUrl.setText(repo.getHtml_url());
        ((RepoCardViewHolder)viewHolder).language.setText(repo.getLanguage());
        ((RepoCardViewHolder)viewHolder).card.setOnClickListener(new RepoCardClickListener(position));
    }

    @Override
    public int getItemCount() {
        return repoList == null ? 0 : repoList.size();
    }

    public void setRepoList(List<GitHubRepo> repoList) {
        this.repoList = repoList;
    }

    public class RepoCardViewHolder extends RecyclerView.ViewHolder {

        public CardView card;

        public TextView repoName;

        public TextView htmlUrl;

        public TextView language;

        public RepoCardViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.card_root);
            repoName = (TextView) view.findViewById(R.id.repoName);
            htmlUrl = (TextView) view.findViewById(R.id.repoUrl);
            language = (TextView) view.findViewById(R.id.repoLanguage);
        }
    }

    private class RepoCardClickListener implements View.OnClickListener {

        private final int position;

        public RepoCardClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (callback != null && repoList != null) {
                callback.onCardClick(repoList.get(position));
            }
        }
    }
}
