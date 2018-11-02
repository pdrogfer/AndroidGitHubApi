package com.pgf.androidgithubapi.view.list_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pgf.androidgithubapi.R;

public class MainActivity extends AppCompatActivity {

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);

        presenter.onCreate();
    }
}
