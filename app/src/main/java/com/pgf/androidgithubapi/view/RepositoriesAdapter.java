package com.pgf.androidgithubapi.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pgf.androidgithubapi.R;
import com.pgf.androidgithubapi.model.ItemsItem;

import java.util.ArrayList;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder> {

    private ArrayList<ItemsItem> reposList;

    public RepositoriesAdapter(ArrayList<ItemsItem> reposList) {
        this.reposList = reposList;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_repo_list_item, parent, false);
        return new RepositoryViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder repositoryViewHolder, int position) {

        ItemsItem repo = reposList.get(position);

        repositoryViewHolder.tvRepoTitle.setText(repo.getName());
    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvRepoTitle;

        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRepoTitle = itemView.findViewById(R.id.item_repo_title);
        }


    }
}
