package com.pgf.androidgithubapi.view.list_activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pgf.androidgithubapi.R;
import com.pgf.androidgithubapi.model.ItemsItem;
import com.pgf.androidgithubapi.model.RepoListResponse;
import com.pgf.androidgithubapi.view.detail_activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivityView implements OnRepoItemClickListener,
        MaterialSearchBar.OnSearchActionListener {

    private static final String TAG = "MainActivityView";
    public static final String REPO_INTENT_KEY = "REPO_INTENT_KEY";

    MainActivity activity;
    ArrayList<ItemsItem> repoList;

    RecyclerView rvRepositoriesList;
    RepositoriesAdapter repositoriesAdapter;

    private List<String> lastSearches;
    private MaterialSearchBar searchBar;

    private Actions actions;

    public MainActivityView(MainActivity activity, Actions actions) {

        this.activity = activity;
        this.actions = actions;
    }

    public void paintData(RepoListResponse repoListResponse) {

        repoList = (ArrayList<ItemsItem>) repoListResponse.getItems();
        repositoriesAdapter = new RepositoriesAdapter(repoList, this);

        rvRepositoriesList.setLayoutManager(new LinearLayoutManager(activity));
        rvRepositoriesList.setAdapter(repositoriesAdapter);
        rvRepositoriesList.addItemDecoration(new DividerItemDecoration(activity.getApplicationContext(), VERTICAL));

        Toast.makeText(activity, "first repo name " + repoList.get(0).getName(), Toast.LENGTH_SHORT).show();
    }

    public void paintSearchResults(RepoListResponse repoListResponse) {

        repoList = (ArrayList<ItemsItem>) repoListResponse.getItems();
        repositoriesAdapter = new RepositoriesAdapter(repoList, this);

        rvRepositoriesList.setLayoutManager(new LinearLayoutManager(activity));
        rvRepositoriesList.setAdapter(repositoriesAdapter);
        rvRepositoriesList.addItemDecoration(new DividerItemDecoration(activity.getApplicationContext(), VERTICAL));

        Toast.makeText(activity, "first search repo name " + repoList.get(0).getName(), Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String message) {

        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void initUi() {

        rvRepositoriesList = activity.findViewById(R.id.rv_repositories_list);

        searchBar = activity.findViewById(R.id.searchBar);
        searchBar.setHint("Custom hint");
        searchBar.setSpeechMode(false);
        searchBar.setCardViewElevation(10);
        //enable searchbar callbacks
        searchBar.setOnSearchActionListener(this);
    }

    @Override
    public void onItemClick(int position) {

        Intent gotoDetailIntent = new Intent(activity, DetailActivity.class);
        gotoDetailIntent.putExtra(REPO_INTENT_KEY, repoList.get(position));
        activity.startActivity(gotoDetailIntent);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        
        startSearch(text.toString());
    }

    private void startSearch(String textSearch) {

        actions.startSearch(textSearch);

        Log.i(TAG, "startSearch: " + textSearch);
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

    public interface Actions {

        void startSearch(String textSearch);

    }
}
