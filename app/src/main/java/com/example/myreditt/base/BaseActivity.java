package com.example.myreditt.base;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.myreditt.R;


/**
 * Created by Sanoop
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Nullable
    protected Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    protected void setToolbar() {
        setSupportActionBar(toolbar);
    }

    protected void setNavigationToolbar() {
        setToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> navigateBack());
        }
    }

    public void setToolbar(Toolbar customToolbar) {
        setSupportActionBar(customToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (customToolbar != null) {
            customToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    protected void navigateBack() {
        onBackPressed();
    }
}