package com.pgf.androidgithubapi.api;

interface AppDataSource {

    void getGitHubRepositories(int page, AppApiListener appApiListener);

    void searchRepositoriesByName(String textToSearch, int page, AppApiListener appApiListener);
}
