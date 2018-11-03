package com.pgf.androidgithubapi.api;

import android.util.Log;

import com.pgf.androidgithubapi.model.RepoListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppDataSourceImplCloud implements AppDataSource {

    private static final String TAG = "AppDataSourceImplCloud";
    private static final String URL_BASE = "https://api.github.com/search/";

    private RetrofitApiService retrofitApiService;

    @Override
    public void getGitHubRepositories(final AppApiListener listener) {

        Retrofit retrofitInstance = new Retrofit
                .Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApiService = retrofitInstance.create(RetrofitApiService.class);

        // q=is:public&sort=created&order=desc
        Call<RepoListResponse> dataCall = retrofitApiService.getGitHubRepositories(
                "is:public",
                "created",
                "desc",
                1,
                20
        );
        dataCall.enqueue(new Callback<RepoListResponse>() {

            @Override
            public void onResponse(Call<RepoListResponse> call, Response<RepoListResponse> response) {

                Log.i(TAG, "onResponse: " + response.body().toString());
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<RepoListResponse> call, Throwable t) {

                Log.i(TAG, "onFailure: " + t.getMessage());
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void searchRepositoriesByName(String textToSearch, final AppApiListener listener) {

        Retrofit retrofitInstance = new Retrofit
                .Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApiService = retrofitInstance.create(RetrofitApiService.class);

        String searchParameter = String.format("%s in:name", textToSearch);

        Call<RepoListResponse> dataCall = retrofitApiService.searchReposByName(
                searchParameter,
                "created",
                "desc",
                1,
                20
        );
        dataCall.enqueue(new Callback<RepoListResponse>() {

            @Override
            public void onResponse(Call<RepoListResponse> call, Response<RepoListResponse> response) {

                Log.i(TAG, "onResponse: " + response.body().toString());
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(Call<RepoListResponse> call, Throwable t) {

                Log.i(TAG, "onFailure: " + t.getMessage());
                listener.onFail(t.getMessage());
            }
        });
    }
}
