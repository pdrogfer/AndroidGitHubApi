package com.pgf.androidgithubapi;

import android.widget.Toast;

import com.pgf.androidgithubapi.model.RepoListResponse;

public class MainActivityView {

    MainActivity activity;

    RepoListResponse repoList;

    public MainActivityView(MainActivity activity) {

        this.activity = activity;
    }

    public void paintData(RepoListResponse repoList) {

        this.repoList = repoList;

        Toast.makeText(activity, "first repo name " + repoList.getItems().get(0).getName() , Toast.LENGTH_SHORT).show();

    }

    public void showMessage(String message) {

        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
