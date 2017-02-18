package com.lanou.yindongge.music.pineapple.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 17/2/18.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initData();
    }
    public abstract int getLayoutId();
    public abstract void initData();

    public <V extends View> V byView(int resId) {
        return (V) findViewById(resId);
    }
}
