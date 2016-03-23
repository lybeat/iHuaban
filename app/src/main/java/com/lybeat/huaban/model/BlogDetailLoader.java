package com.lybeat.huaban.model;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/9
 */
public class BlogDetailLoader extends AsyncTask<String, Void, BlogDetail> {

    public interface OnCompleteListener {
        void onSuccess(BlogDetail blog);
        void onFailed();
    }

    private OnCompleteListener listener;

    public BlogDetailLoader(OnCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected BlogDetail doInBackground(String... params) {
        String url = params[0];
        return parseBlog(url);
    }

    @Override
    protected void onPostExecute(BlogDetail blogDetail) {
        super.onPostExecute(blogDetail);

        if (blogDetail != null) {
            listener.onSuccess(blogDetail);
        } else {
            listener.onFailed();
        }
    }

    private BlogDetail parseBlog(String url) {
        try {
            Document document = Jsoup.connect(url).timeout(5000).get();

            String title = document.select("header.entry-header h1").text();
            String time = document.select("time").text();
            String tag = "";
            for (int i=0; i<document.select("span.tags a").size(); i++) {
                tag += document.select("span.tags a").get(i).text() + " ";
            }
            String content = document.select("div.entry-content p").text();
            List<Image> images = new ArrayList<>();
            for (int i=0; i<document.select("div.entry-content img").size(); i++) {
                String imgUrl = document.select("div.entry-content img").get(i).attr("src");
                String width = document.select("div.entry-content img").get(i).attr("width");
                String height = document.select("div.entry-content img").get(i).attr("height");
                Image image = null;
                if (width != null && !width.equals("")
                        && height != null && !height.equals("")) {
                    image = new Image(imgUrl, Integer.valueOf(width), Integer.valueOf(height));
                }
                images.add(image);
            }
            return new BlogDetail(title, content, time, tag, images);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
