package com.lybeat.huaban.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lybeat.huaban.R;
import com.lybeat.huaban.adapter.TabAdapter;
import com.lybeat.huaban.util.PictureUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String NAVIGATION_FRAGMENT = "navigation_fragment";

    private List<Fragment> fragments;
    private List<String> titles;
    private TabAdapter tabAdapter;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        HomeFragment homeFragment = new HomeFragment();
        FindFragment findFragment = new FindFragment();
        WeeklyFragment weeklyFragment = new WeeklyFragment();
        BlogFragment blogFragment = new BlogFragment();
        fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(findFragment);
        fragments.add(weeklyFragment);
        fragments.add(blogFragment);

        Resources res = getResources();
        titles = new ArrayList<>();
        titles.add(res.getString(R.string.home));
        titles.add(res.getString(R.string.find));
        titles.add(res.getString(R.string.weekly));
        titles.add(res.getString(R.string.blog));

        tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments, titles);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Bitmap bmp = PictureUtil.getScaledBitmapFromResource(this, R.drawable.avatar, 36, 36);
        bmp = PictureUtil.getRoundBitmap(bmp);
        Drawable drawable = new BitmapDrawable(getResources(), bmp);
        toolbar.setNavigationIcon(drawable);
        toolbar.setTitle("幻想乡，");
        setSupportActionBar(toolbar);
        // 设置Navigation监听器必须在setSupportActionBar()之后
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "NavigationOnClickListener");
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setCheckedItem(R.id.menu_home);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigationView.setCheckedItem(R.id.menu_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.setClass(this, NavigationActivity.class);
        switch (item.getItemId()) {
            case R.id.menu_home :
                intent.putExtra(NAVIGATION_FRAGMENT, 0);
                break;
            case R.id.menu_draw_board :
                intent.putExtra(NAVIGATION_FRAGMENT, 1);
                break;
            case R.id.menu_follow :
                break;
            case R.id.menu_fans :
                break;
            case R.id.menu_theme:
                break;
            case R.id.menu_setting:
                break;
        }
        startActivity(intent);
        drawerLayout.closeDrawers();
        return true;
    }
}
