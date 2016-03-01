package com.lybeat.huaban.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lybeat.huaban.R;

/**
 * Author: lybeat
 * Date: 2016/2/28
 */
public class NavigationActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        DrawBoardFragment drawBoardFragment = new DrawBoardFragment();
        ft.add(R.id.fragment_container, drawBoardFragment).commit();
    }
}
