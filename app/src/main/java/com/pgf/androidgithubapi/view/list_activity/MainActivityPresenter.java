package com.pgf.androidgithubapi.view.list_activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pgf.androidgithubapi.api.AppDataSourceImplCloud;
import com.pgf.androidgithubapi.api.AppRepository;
import com.pgf.androidgithubapi.api.AppRepositoryImpl;
import com.pgf.androidgithubapi.api.AppRepositoryListener;
import com.pgf.androidgithubapi.model.RepoListResponse;

import retrofit2.Response;

public class MainActivityPresenter implements MainActivityView.Actions {

    private static final String TAG = "MainActivityPresenter";

    private MainActivity mainActivity;
    private MainActivityView view;

    private AppRepository repository;

    public MainActivityPresenter(AppCompatActivity activity) {

        mainActivity = (MainActivity) activity;
        view = new MainActivityView(mainActivity, this);
        repository = new AppRepositoryImpl(new AppDataSourceImplCloud());

        loadData(1);
    }

    public void onCreate() {

        initUi();
    }

    private void initUi() {

        view.initUi();
    }

    private void loadData(int page) {

        repository.getGitHubRepositories(page, new AppRepositoryListener() {

            @Override
            public void onSuccess(Response<RepoListResponse> response) {

                Log.i(TAG, "onSuccess: " + response.body());
                view.paintData(false, response.body());
            }

            @Override
            public void onFail(String message) {

                view.showMessage(message);
            }
        });
    }

    @Override
    public void startSearch(String textSearch, int page) {

        repository.searchRepositoriesByName(textSearch, page, new AppRepositoryListener() {

            @Override
            public void onSuccess(Response<RepoListResponse> response) {

                Log.i(TAG, "onSuccess: " + response.body());
                view.paintData(true, response.body());
            }

            @Override
            public void onFail(String message) {

                view.showMessage(message);
            }
        });
    }

    @Override
    public void getData(int page) {

        loadData(page);
    }
}
