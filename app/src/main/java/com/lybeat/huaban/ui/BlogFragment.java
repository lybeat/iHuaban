package com.lybeat.huaban.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lybeat.huaban.R;
import com.lybeat.huaban.adapter.BlogAdapter;
import com.lybeat.huaban.model.Blog;
import com.lybeat.huaban.model.BlogLoader;
import com.lybeat.huaban.widget.DividerItemDecoration;
import com.lybeat.huaban.widget.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/2/28
 */
public class BlogFragment extends BaseFragment implements
        BlogLoader.OnCompleteListener, RefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadMoreListener, BlogAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private RefreshLayout swipeRefresh;
    private View footer;
    private TextView footerInfoTxt;

    private BlogAdapter adapter;
    private List<Blog> blogs = new ArrayList<>();
    private String nextPageUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.blog_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setSpace(getResources().getDimensionPixelSize(R.dimen.item_space));
        recyclerView.addItemDecoration(dividerItemDecoration);
        swipeRefresh = (RefreshLayout) view.findViewById(R.id.blog_refresh);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setOnLoadMoreListener(this);
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
            }
        });
        initData();

        return view;
    }

    private void initData() {
        adapter = new BlogAdapter(getActivity(), this.blogs);
        adapter.setOnItemClickListener(this);
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.refresh_footer, null);
        footerInfoTxt = (TextView) footer.findViewById(R.id.refresh_txt);
        footerInfoTxt.setText("正在加载更多数据");
        footer.setVisibility(View.GONE);
        adapter.addFooterView(footer);
        recyclerView.setAdapter(adapter);
        BlogLoader blogLoader = new BlogLoader(this);
        blogLoader.execute("http://blog.huaban.com/");
    }

    @Override
    public void onSuccess(List<Blog> blogs, String nextPageUrl) {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);
                swipeRefresh.setLoading(false);
            }
        });
        if (this.blogs.size() > 0 &&
                blogs.get(0).getTitle().equals(this.blogs.get(0).getTitle())) {
            Toast.makeText(getActivity(), "没有新内容啦", Toast.LENGTH_SHORT).show();
            return;
        }
        this.nextPageUrl = nextPageUrl;
        this.blogs.addAll(blogs);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed() {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);
                swipeRefresh.setLoading(false);
            }
        });
        Toast.makeText(getActivity(), "加载博客列表失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        BlogLoader blogLoader = new BlogLoader(this);
        blogLoader.execute("http://blog.huaban.com/");
    }

    @Override
    public void onLoadMore() {
        footer.setVisibility(View.VISIBLE);
        BlogLoader blogLoader = new BlogLoader(this);
        blogLoader.execute(nextPageUrl);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("MainActivity", "onItemClick position: " + position);
        Log.i("MainActivity", "blog url: " + blogs.get(position).getUrl());
        Intent intent = new Intent();
        intent.putExtra("blog_url", blogs.get(position).getUrl());
        intent.setClass(getActivity(), BlogDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Log.i("MainActivity", "onItemLongClick position: " + position);
    }
}
