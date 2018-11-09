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
    public void getGitHubRepositories(int page, final AppRepositoryListener listener) {

        appDataSource.getGitHubRepositories(page, new AppApiListener() {

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
    public void searchRepositoriesByName(String textToSearch, int page, final AppRepositoryListener listener) {

        appDataSource.searchRepositoriesByName(textToSearch, page, new AppApiListener() {

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
