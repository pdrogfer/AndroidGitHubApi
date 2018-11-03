package com.pgf.androidgithubapi.view.list_activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pgf.androidgithubapi.R;
import com.pgf.androidgithubapi.model.ItemsItem;
import com.pgf.androidgithubapi.model.RepoListResponse;
import com.pgf.androidgithubapi.view.detail_activity.DetailActivity;

import java.util.ArrayList;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivityView implements OnRepoItemClickListener {

    public static final String REPO_INTENT_KEY = "REPO_INTENT_KEY";

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
        rvRepositoriesList.addItemDecoration(new DividerItemDecoration(activity.getApplicationContext(), VERTICAL));

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

        Intent gotoDetailIntent = new Intent(activity, DetailActivity.class);
        gotoDetailIntent.putExtra(REPO_INTENT_KEY, repoList.get(position));
        activity.startActivity(gotoDetailIntent);
    }
}
