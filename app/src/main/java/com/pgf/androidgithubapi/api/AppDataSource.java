package com.pgf.androidgithubapi.api;

interface AppDataSource {

    void getGitHubRepositories(AppApiListener appApiListener);

    void searchRepositoriesByName(String textToSearch, AppApiListener appApiListener);
}
