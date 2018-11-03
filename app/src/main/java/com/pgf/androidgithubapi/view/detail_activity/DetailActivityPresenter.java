package com.pgf.androidgithubapi.view.detail_activity;

import android.support.v7.app.AppCompatActivity;

import com.pgf.androidgithubapi.model.ItemsItem;

public class DetailActivityPresenter {

    private static final String TAG = "DetailActivityPresenter";

    private DetailActivity detailActivity;
    private DetailActivityView view;

    public DetailActivityPresenter(AppCompatActivity activity) {

        detailActivity = (DetailActivity) activity;
        view = new DetailActivityView(detailActivity);

    }

    public void onCreate() {

        initUi();
    }

    private void initUi() {

        view.initUi();
    }

    public void setData(ItemsItem repoItem) {

        view.paintData(repoItem);
    }
}
