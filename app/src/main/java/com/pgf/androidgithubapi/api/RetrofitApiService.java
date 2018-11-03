package com.pgf.androidgithubapi.api;

import com.pgf.androidgithubapi.model.RepoListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiService {

    @GET("repositories")
    Call<RepoListResponse> getGitHubRepositories(
            @Query("q") String publicRepos,
            @Query("sort") String sortParameter,
            @Query("order") String sortOrder,
            @Query("page") Integer pageNumber,
            @Query("per_page") Integer pageSize
    );

    @GET("repositories")
    Call<RepoListResponse> searchReposByName(
            @Query("q") String searchParameter,
            @Query("sort") String sortParameter,
            @Query("order") String sortOrder,
            @Query("page") Integer pageNumber,
            @Query("per_page") Integer pageSize
    );
}
