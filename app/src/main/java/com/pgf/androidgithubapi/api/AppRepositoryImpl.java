package com.pgf.androidgithubapi.api;

import com.pgf.androidgithubapi.api.AppRepository;
import com.pgf.androidgithubapi.model.RepoListResponse;

import retrofit2.Response;

public class AppRepositoryImpl implements AppRepository {

    AppDataSource appDataSource;

    public AppRepositoryImpl(AppDataSource appDataSource) {
        this.appDataSource = appDataSource;
    }

    @Override
    public void getGitHubRepositories(final AppRepositoryListener listener) {

        appDataSource.getGitHubRepositories(new AppApiListener() {

            @Override
            public void onSuccess(Response<RepoListResponse> response) {

                listener.onSuccess(response);
            }

            @Override
            public void onFail(String message) {

                listener.onFail(message);
            }
        });
    }

    @Override
    public void searchRepositoriesByName(String textToSearch, final AppRepositoryListener listener) {

        appDataSource.searchRepositoriesByName(textToSearch, new AppApiListener() {

            @Override
            public void onSuccess(Response<RepoListResponse> response) {

                listener.onSuccess(response);
            }

            @Override
            public void onFail(String message) {

                listener.onFail(message);
            }
        });
    }
}
