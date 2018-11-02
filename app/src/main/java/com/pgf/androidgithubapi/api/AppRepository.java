package com.pgf.androidgithubapi.api;

public interface AppRepository {

    void getGitHubRepositories(AppRepositoryListener listener);
}
