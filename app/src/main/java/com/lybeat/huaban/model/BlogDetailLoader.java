package com.lybeat.huaban.model;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Author: lybeat
 * Date: 2016/3/9
 */
public class BlogDetailLoader extends AsyncTask<String, Void, Blog> {

    public interface OnCompleteListener {
        void onSuccess(Blog blog);
        void onFailed();
    }

    private OnCompleteListener listener;

    public BlogDetailLoader(OnCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected Blog doInBackground(String... params) {
        String url = params[0];
        return parseBlog(url);
    }

    @Override
    protected void onPostExecute(Blog blog) {
        super.onPostExecute(blog);

        if (blog != null) {
            listener.onSuccess(blog);
        } else {
            listener.onFailed();
        }
    }

    private Blog parseBlog(String url) {
        try {
            Document document = Jsoup.connect(url).timeout(5000).get();

            String title = document.select("header.entry-header h1").text();
            String time = document.select("time").text();
            String tag = "";
            for (int i=0; i<document.select("span.tags a").size(); i++) {
                tag += document.select("span.tags a").get(i).text() + " ";
            }
            String content = document.select("div.entry-content p").text();
            String imgUrl = "";
            for (int i=0; i<document.select("div.entry-content img").size(); i++) {
                imgUrl += document.select("div.entry-content img").get(i).attr("src") + " ";
            }

//            Log.i("MainActivity", "title: " + title);
//            Log.i("MainActivity", "time: " + time);
//            Log.i("MainActivity", "tag: " + tag);
//            Log.i("MainActivity", "content: " + content);
//            Log.i("MainActivity", "imgUrl: " + imgUrl);

            return new Blog("*", title, content, time, tag, imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
