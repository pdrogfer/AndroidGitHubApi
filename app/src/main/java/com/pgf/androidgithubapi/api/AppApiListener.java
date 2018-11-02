package com.pgf.androidgithubapi.api;

import com.pgf.androidgithubapi.model.RepoListResponse;

import retrofit2.Response;

public interface AppApiListener {

    void onSuccess(Response<RepoListResponse> response);

    void onFail(String message);
}
