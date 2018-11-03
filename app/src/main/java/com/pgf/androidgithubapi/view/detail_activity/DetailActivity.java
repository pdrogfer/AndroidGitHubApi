package com.pgf.androidgithubapi.view.detail_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pgf.androidgithubapi.R;
import com.pgf.androidgithubapi.model.ItemsItem;
import com.pgf.androidgithubapi.view.list_activity.MainActivityView;

public class DetailActivity extends AppCompatActivity {

    private DetailActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        ItemsItem repoItem = getIntent().getParcelableExtra(MainActivityView.REPO_INTENT_KEY);

        presenter = new DetailActivityPresenter(this);

        presenter.onCreate();
        presenter.setData(repoItem);
    }
}
