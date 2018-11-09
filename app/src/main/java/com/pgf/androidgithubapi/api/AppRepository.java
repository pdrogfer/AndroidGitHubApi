package com.pgf.androidgithubapi.api;

public interface AppRepository {

    void getGitHubRepositories(int page, AppRepositoryListener listener);

    void searchRepositoriesByName(String textToSearch, int page, AppRepositoryListener listener);
}
