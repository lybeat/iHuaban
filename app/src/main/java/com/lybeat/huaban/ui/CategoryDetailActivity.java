package com.lybeat.huaban.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lybeat.huaban.R;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class CategoryDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ImageFragment imageFragment = new ImageFragment();
        ft.add(R.id.category_container, imageFragment).commit();
    }
}
