package com.lybeat.huaban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lybeat.huaban.R;
import com.lybeat.huaban.model.Blog;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/10
 */
public class BlogAdapter extends BaseAdapter {

    private Context context;
    private List<Blog> blogs;

    public BlogAdapter(Context context, List<Blog> blogs) {
        this.context = context;
        this.blogs = blogs;
    }

    @Override
    public BaseHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_blog, null);
        return new BlogHolder(view);
    }

    @Override
    public void onBindHolder(BaseHolder holder, int position) {
        if (holder instanceof BlogHolder) {
            Blog blog = blogs.get(position);
            ((BlogHolder) holder).titleTxt.setText(blog.getTitle());
            ((BlogHolder) holder).summaryTxt.setText(blog.getSummary());
            ((BlogHolder) holder).tagsTxt.setText("分类：" + blog.getTags());
            ((BlogHolder) holder).dateTxt.setText(blog.getDate());
            Picasso.with(context).load(blog.getThumbUrl())
                    .placeholder(R.mipmap.huaban_icon_64px)
                    .error(R.mipmap.huaban_icon_64px)
                    .into(((BlogHolder) holder).thumbImg);
        }
    }

    @Override
    public int getCount() {
        return blogs.size();
    }

    public static class BlogHolder extends BaseHolder {

        ImageView thumbImg;
        TextView titleTxt;
        TextView summaryTxt;
        TextView tagsTxt;
        TextView dateTxt;

        public BlogHolder(View itemView) {
            super(itemView);
            thumbImg = (ImageView) itemView.findViewById(R.id.blog_thumb_img);
            titleTxt = (TextView) itemView.findViewById(R.id.blog_title_txt);
            summaryTxt = (TextView) itemView.findViewById(R.id.blog_summary_txt);
            tagsTxt = (TextView) itemView.findViewById(R.id.blog_tags_txt);
            dateTxt = (TextView) itemView.findViewById(R.id.blog_date_txt);
        }
    }
}
