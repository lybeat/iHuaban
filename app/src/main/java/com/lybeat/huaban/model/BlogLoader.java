package com.lybeat.huaban.model;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/3
 */
public class BlogLoader extends AsyncTask<String, Integer, List<Blog>>  {

    public interface OnCompleteListener {
        void onSuccess(List<Blog> blogs, String nextPageUrl);
        void onFailed();
    }

    private OnCompleteListener listener;
    private String nextPageUrl;

    public BlogLoader(OnCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<Blog> doInBackground(String... params) {
        String url = params[0];
        if (url != null) {
            return parseBlog(url);
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Blog> blogs) {
        super.onPostExecute(blogs);

        if (blogs != null) {
            listener.onSuccess(blogs, nextPageUrl);
        } else {
            listener.onFailed();
        }
//        Log.i("MainActivity", "onPostExecute");
    }

    private List<Blog> parseBlog(String url) {
        try {
            Document document = Jsoup.connect(url).timeout(5000).get();

            Element maxPageElement = document.select("a.next").get(0);
            nextPageUrl = maxPageElement.attr("href");

            int size = document.select("header.entry-header").size();

            List<Blog> blogs = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Element imgHrefElement = document.select("div.entry-thumbnail img").get(i);
                Element titleElement = document.select("header.entry-header").get(i);
                Element summaryElement = document.select("div.entry-content").get(i);
                Element tagElement = document.select("footer.entry-meta").get(i);
                Element dateElement = document.select("div.entry-content").get(i);

                String imgHref = imgHrefElement.attr("src");
                String title = titleElement.select("h1 a").text();
                String href = titleElement.select("h1 a").attr("href");
                String summary = summaryElement.select("p").text();
                String tag = tagElement.select("span.categories a").text();
                String date = dateElement.select("div.entry-date").text();

//                Log.i("MainActivity", "blog size: " + document.select("header.entry-header").size());
//
//                Log.i("MainActivity", "href: " + imgHref);
//                Log.i("MainActivity", "title: " + title);
//                Log.i("MainActivity", "href: " + href);
//                Log.i("MainActivity", "summary: " + summary);
//                Log.i("MainActivity", "tag: " + tag);
//                Log.i("MainActivity", "date: " + date);

                Blog blog = new Blog(href, title, summary, date, tag, imgHref);
                blogs.add(blog);
            }
            return blogs;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
