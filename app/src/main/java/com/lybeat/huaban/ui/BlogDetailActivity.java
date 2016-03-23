package com.lybeat.huaban.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lybeat.huaban.R;
import com.lybeat.huaban.adapter.ImageAdapter;
import com.lybeat.huaban.model.BlogDetail;
import com.lybeat.huaban.model.BlogDetailLoader;
import com.lybeat.huaban.model.Image;
import com.lybeat.huaban.widget.DividerItemDecoration;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/9
 */
public class BlogDetailActivity extends BaseActivity
        implements BlogDetailLoader.OnCompleteListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_blog_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.blog_detail_tool_bar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.blog_detail_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this, OrientationHelper.VERTICAL);
        dividerItemDecoration.setSpace(getResources().getDimensionPixelSize(R.dimen.grid_space));
        recyclerView.addItemDecoration(dividerItemDecoration);
        BlogDetailLoader blogLoader = new BlogDetailLoader(this);
        blogLoader.execute(getIntent().getStringExtra("blog_url"));
    }

    @Override
    public void onSuccess(BlogDetail blogDetail) {
        List<Image> images = blogDetail.getImages();
        ImageAdapter adapter = new ImageAdapter(this, images);
        View view = LayoutInflater.from(this).inflate(R.layout.item_blog_detail_header, null);
        TextView titleTxt = (TextView) view.findViewById(R.id.blog_detail_title_txt);
        titleTxt.setText(blogDetail.getTitle());
        TextView dateTxt = (TextView) view.findViewById(R.id.blog_detail_date_txt);
        dateTxt.setText(blogDetail.getTime());
        TextView tagTxt = (TextView) view.findViewById(R.id.blog_detail_tag_txt);
        tagTxt.setText("分类： " + blogDetail.getTag());
        TextView contentTxt = (TextView) view.findViewById(R.id.blog_detail_content_txt);
        contentTxt.setText(blogDetail.getContent());

        adapter.addHeaderView(view);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed() {

    }
}
