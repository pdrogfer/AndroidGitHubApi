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
import com.paginate.Paginate;
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
    private int page;
    private String searchText;
    private boolean loading;

    public MainActivityView(MainActivity activity, Actions actions) {

        this.activity = activity;
        this.actions = actions;
        loading = true;
    }

    public void initUi() {

        repoList = new ArrayList<>();
        rvRepositoriesList = activity.findViewById(R.id.rv_repositories_list);

        searchBar = activity.findViewById(R.id.searchBar);
        searchBar.setHint("Custom hint");
        searchBar.setSpeechMode(false);
        searchBar.setCardViewElevation(10);
        //enable searchbar callbacks
        searchBar.setOnSearchActionListener(this);

        page = 1;
    }

    public void paintData(final boolean isSearchResult, RepoListResponse repoListResponse) {

        loading = false;
        repoList.addAll(repoListResponse.getItems()) ;
        repositoriesAdapter = new RepositoriesAdapter(repoList, this);

        rvRepositoriesList.setLayoutManager(new LinearLayoutManager(activity));
        rvRepositoriesList.setAdapter(repositoriesAdapter);
        rvRepositoriesList.addItemDecoration(new DividerItemDecoration(activity.getApplicationContext(), VERTICAL));
        if (repoList.size() > 20) {
            rvRepositoriesList.scrollToPosition(repoList.size() - 19);
        }

        Paginate.with(rvRepositoriesList, new Paginate.Callbacks() {

            @Override
            public void onLoadMore() {

                page++;

                if (isSearchResult) {
                    startSearch(searchText, page);
                }
                else {
                    getData(page);
                }
            }

            @Override
            public boolean isLoading() {
                return loading;
            }

            @Override
            public boolean hasLoadedAllItems() {
                // not implemented
                // solution: get total pages parameter from data and compare to present page
                return false;
            }
        })
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .build();

        Toast.makeText(activity, "loaded page " + page, Toast.LENGTH_SHORT).show();
    }

//    public void paintSearchResults(RepoListResponse repoListResponse) {
//
//        loading = false;
//        repoList.addAll(repoListResponse.getItems()) ;
//        repositoriesAdapter = new RepositoriesAdapter(repoList, this);
//
//        rvRepositoriesList.setLayoutManager(new LinearLayoutManager(activity));
//        rvRepositoriesList.setAdapter(repositoriesAdapter);
//        rvRepositoriesList.addItemDecoration(new DividerItemDecoration(activity.getApplicationContext(), VERTICAL));
//
//        Paginate.with(rvRepositoriesList, new Paginate.Callbacks() {
//
//            @Override
//            public void onLoadMore() {
//
//                page++;
//                startSearch(searchText, page);
//            }
//
//            @Override
//            public boolean isLoading() {
//                return loading;
//            }
//
//            @Override
//            public boolean hasLoadedAllItems() {
//                return false;
//            }
//        })
//                .setLoadingTriggerThreshold(2)
//                .addLoadingListItem(true)
//                .build();
//
//        Toast.makeText(activity, "loaded page " + page, Toast.LENGTH_SHORT).show();
//    }

    public void showMessage(String message) {

        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
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

        repoList.clear();
        repositoriesAdapter.notifyDataSetChanged();
        searchText = text.toString();
        page = 1;

        if (!searchText.isEmpty()) {
            startSearch(searchText, page);
        }
        else {
            getData(page);
        }
    }

    private void startSearch(String textSearch, int page) {

        loading = true;
        actions.startSearch(textSearch, page);

        Log.i(TAG, "startSearch: " + textSearch);
    }

    private void getData(int page) {

        loading = true;
        actions.getData(page);
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

    public interface Actions {

        void startSearch(String textSearch, int page);

        void getData(int page);
    }
}
