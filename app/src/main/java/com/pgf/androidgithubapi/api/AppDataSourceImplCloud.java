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

    private RetrofitApiService retrofitApiService;

    @Override
    public void getGitHubRepositories(final AppApiListener listener) {

        // for testing in real device
        // String url = "http://localhost:1337/epg/";

        // for testing in emulator
        // String url = "http://10.0.2.2:1337/epg/";
        String urlBase = "https://api.github.com/search/";

        Retrofit retrofitInstance = new Retrofit
                .Builder()
                .baseUrl(urlBase)
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
}
