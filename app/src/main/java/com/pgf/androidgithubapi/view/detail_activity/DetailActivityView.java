package com.pgf.androidgithubapi.view.detail_activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.pgf.androidgithubapi.R;
import com.pgf.androidgithubapi.model.ItemsItem;

public class DetailActivityView {

    private DetailActivity detailActivity;
    private ItemsItem gitHubRepoItem;

    private TextView tvDetailTitle;
    private TextView tvDetailAuthor;

    public DetailActivityView(AppCompatActivity activity) {

        detailActivity = (DetailActivity) activity;
    }

    public void initUi() {

        tvDetailTitle = detailActivity.findViewById(R.id.tv_detail_title);
        tvDetailAuthor = detailActivity.findViewById(R.id.tv_detail_author);

    }

    public void paintData(ItemsItem repoItem) {

        gitHubRepoItem = repoItem;

        tvDetailTitle.setText(gitHubRepoItem.getName());
        tvDetailAuthor.setText(gitHubRepoItem.getOwner().getLogin());
    }
}
