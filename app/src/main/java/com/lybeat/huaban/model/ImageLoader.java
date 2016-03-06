package com.lybeat.huaban.model;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class ImageLoader extends AsyncTask<String, Void, List<Image>>{

    public interface onCompleteListener {
        void onSuccess(List<Image> images);
        void onFailed();
    }

    private onCompleteListener listener;

    @Override
    protected List<Image> doInBackground(String... params) {
        String url = params[0];
        return parseImage(url);
    }

    @Override
    protected void onPostExecute(List<Image> images) {
        super.onPostExecute(images);

        if (images != null) {
            listener.onSuccess(images);
        } else {
            listener.onFailed();
        }
    }

    private List<Image> parseImage(String url) {
        try {
            Document document = Jsoup.connect(url).timeout(5000).get();
            List<Image> images = new ArrayList<>();
            Element imgElement = document.select("a.img").get(0);
            String imageUrl = imgElement.attr("href");
            String coverUrl = imgElement.select("img").attr("src");
            Log.i("MainActivity", "imageUrl: " + imageUrl);
            Log.i("MainActivity", "coverUrl: " + coverUrl);

            return images;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
