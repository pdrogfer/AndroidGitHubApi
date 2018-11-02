package com.pgf.androidgithubapi.view;

import android.content.ClipData;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.pgf.androidgithubapi.R;
import com.pgf.androidgithubapi.model.ItemsItem;
import com.pgf.androidgithubapi.model.RepoListResponse;

import java.util.ArrayList;

public class MainActivityView implements OnRepoItemClickListener {

    MainActivity activity;
    ArrayList<ItemsItem> repoList;

    RecyclerView rvRepositoriesList;
    RepositoriesAdapter repositoriesAdapter;

    public MainActivityView(MainActivity activity) {

        this.activity = activity;
    }

    public void paintData(RepoListResponse repoListResponse) {

        repoList = (ArrayList<ItemsItem>) repoListResponse.getItems();
        repositoriesAdapter = new RepositoriesAdapter(repoList, this);

        rvRepositoriesList.setLayoutManager(new LinearLayoutManager(activity));
        rvRepositoriesList.setAdapter(repositoriesAdapter);

        Toast.makeText(activity, "first repo name " + repoList.get(0).getName(), Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String message) {

        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void initUi() {

        rvRepositoriesList = activity.findViewById(R.id.rv_repositories_list);
    }

    @Override
    public void onItemClick(int position) {

        Toast.makeText(activity, "click " + position, Toast.LENGTH_SHORT).show();
    }
}
