package com.lybeat.huaban.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.lybeat.huaban.R;
import com.lybeat.huaban.model.Blog;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/3
 */
public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogView>
        implements View.OnClickListener {

    private Context context;
    private List<Blog> blogs;
    private ImageLoader imageLoader;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;
    private int position;

    public BlogAdapter(Context context, List<Blog> blogs) {
        this.context = context;
        this.blogs = blogs;
        this.imageLoader = new ImageLoader(Volley.newRequestQueue(context), new BitmapCache());
    }

    @Override
    public BlogView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_blog, null);
        view.setOnClickListener(this);
        return new BlogView(view);
    }

    @Override
    public void onBindViewHolder(BlogView holder, int position) {
        this.position = position;

        Blog blog = blogs.get(position);
        holder.titleTxt.setText(blog.getTitle());
        holder.summaryTxt.setText(blog.getSummary());
        holder.tagsTxt.setText("分类：" + blog.getTags());
        holder.dateTxt.setText(blog.getDate());

        holder.thumbImg.setDefaultImageResId(R.drawable.avatar);
        holder.thumbImg.setErrorImageResId(R.drawable.avatar);
        holder.thumbImg.setImageUrl(blog.getThumbUrl(), imageLoader);
//        Log.i("MainActivity", "img url: " + blog.getThumbUrl());
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, position);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public static class BlogView extends RecyclerView.ViewHolder {

        NetworkImageView thumbImg;
        TextView titleTxt;
        TextView summaryTxt;
        TextView tagsTxt;
        TextView dateTxt;

        public BlogView(View itemView) {
            super(itemView);
            thumbImg = (NetworkImageView) itemView.findViewById(R.id.blog_thumb_img);
            titleTxt = (TextView) itemView.findViewById(R.id.blog_title_txt);
            summaryTxt = (TextView) itemView.findViewById(R.id.blog_summary_txt);
            tagsTxt = (TextView) itemView.findViewById(R.id.blog_tags_txt);
            dateTxt = (TextView) itemView.findViewById(R.id.blog_date_txt);
        }
    }

    public static class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> cache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            cache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String key) {
            return cache.get(key);
        }

        @Override
        public void putBitmap(String key, Bitmap bitmap) {
            cache.put(key, bitmap);
        }
    }
}
