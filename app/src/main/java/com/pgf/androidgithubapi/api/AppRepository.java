package com.pgf.androidgithubapi.api;

public interface AppRepository {

    void getGitHubRepositories(AppRepositoryListener listener);

    void searchRepositoriesByName(String textToSearch, AppRepositoryListener listener);
}
