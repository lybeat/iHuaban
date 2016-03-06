package com.lybeat.huaban.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lybeat.huaban.R;
import com.lybeat.huaban.util.PictureUtil;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BaseFragment.LoadTabLayout {

    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private BaseFragment currentFragment;
    private HomeFragment homeFragment;
    private BoardFragment boardFragment;
    private FollowFragment followFragment;
    private FansFragment fansFragment;
    private ThemeFragment themeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, homeFragment).commit();
        currentFragment = homeFragment;
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
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setCheckedItem(R.id.menu_home);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
    }

    @Override
    protected void onStart() {
        super.onStart();
//        navigationView.setCheckedItem(R.id.menu_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home :
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                switchFragment(homeFragment);
                break;
            case R.id.menu_board:
                if (boardFragment == null) {
                    boardFragment = new BoardFragment();
                }
                switchFragment(boardFragment);
                break;
            case R.id.menu_follow :
                if (followFragment == null) {
                    followFragment = new FollowFragment();
                }
                switchFragment(followFragment);
                break;
            case R.id.menu_fans :
                if (fansFragment == null) {
                    fansFragment = new FansFragment();
                }
                switchFragment(fansFragment);
                break;
            case R.id.menu_theme :
                if (themeFragment == null) {
                    themeFragment = new ThemeFragment();
                }
                switchFragment(themeFragment);
                break;
            case R.id.menu_setting :
                break;
        }
        tabLayout.setVisibility(currentFragment.isHasTabLayout() ? View.VISIBLE : View.GONE);
        drawerLayout.closeDrawers();
        return true;
    }

    private void switchFragment(BaseFragment toFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (currentFragment != toFragment) {
            if (toFragment.isAdded()) {
                ft.hide(currentFragment).show(toFragment).commit();
            } else {
                ft.hide(currentFragment).add(R.id.fragment_container, toFragment).commit();
            }
            currentFragment = toFragment;
        }
    }

    @Override
    public void loadTabLayout(View view) {
        if (view instanceof ViewPager) {
            tabLayout.setVisibility(View.VISIBLE);
            tabLayout.setupWithViewPager((ViewPager) view);
        } else {
            tabLayout.setVisibility(View.GONE);
        }
    }
}
