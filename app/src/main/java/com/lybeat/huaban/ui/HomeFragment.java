package com.lybeat.huaban.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lybeat.huaban.R;
import com.lybeat.huaban.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/2/27
 */
public class HomeFragment extends BaseFragment {

    private List<Fragment> fragments;
    private List<String> titles;
    private TabAdapter tabAdapter;

    private ViewPager viewPager;

    private LoadTabLayout listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoadTabLayout) {
            listener = (LoadTabLayout) context;
        } else {
            throw new IllegalArgumentException("activity must implements LoadTabLayout");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        initData();
        initView(view);

        return view;
    }

    private void initData() {
        NewestFragment newestFragment = new NewestFragment();
        FindFragment findFragment = new FindFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        WeeklyFragment weeklyFragment = new WeeklyFragment();
        BlogFragment blogFragment = new BlogFragment();
        fragments = new ArrayList<>();
        fragments.add(newestFragment);
        fragments.add(findFragment);
        fragments.add(categoryFragment);
        fragments.add(weeklyFragment);
        fragments.add(blogFragment);

        Resources res = getResources();
        titles = new ArrayList<>();
        titles.add(res.getString(R.string.home));
        titles.add(res.getString(R.string.find));
        titles.add(res.getString(R.string.category));
        titles.add(res.getString(R.string.weekly));
        titles.add(res.getString(R.string.blog));

        tabAdapter = new TabAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(tabAdapter);
        listener.loadTabLayout(viewPager);
    }

    @Override
    public boolean isHasTabLayout() {
        return true;
    }
}
