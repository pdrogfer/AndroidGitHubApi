package com.pgf.androidgithubapi;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.pgf.androidgithubapi.api.AppDataSourceImplCloud;
import com.pgf.androidgithubapi.api.AppRepository;
import com.pgf.androidgithubapi.api.AppRepositoryImpl;
import com.pgf.androidgithubapi.api.AppRepositoryListener;
import com.pgf.androidgithubapi.model.RepoListResponse;

import retrofit2.Response;

public class MainActivityPresenter {

    private static final String TAG = "MainActivityPresenter";

    private MainActivity mainActivity;
    private MainActivityView view;

    private AppRepository repository;

    public MainActivityPresenter(AppCompatActivity activity) {

        mainActivity = (MainActivity) activity;
        view = new MainActivityView(mainActivity);
        repository = new AppRepositoryImpl(new AppDataSourceImplCloud());

        initUi();
        loadData();
    }

    public void onCreate() {

    }

    private void initUi() {

    }

    private void loadData() {

        repository.getGitHubRepositories(new AppRepositoryListener() {

            @Override
            public void onSuccess(Response<RepoListResponse> response) {

                Log.i(TAG, "onSuccess: " + response.body());
                view.paintData(response.body());
            }

            @Override
            public void onFail(String message) {

                view.showMessage(message);
            }
        });
    }
}
