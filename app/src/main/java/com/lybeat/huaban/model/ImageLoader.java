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

    public interface OnCompleteListener {
        void onSuccess(List<Image> images);
        void onFailed();
    }

    private OnCompleteListener listener;

    public ImageLoader(OnCompleteListener listener) {
        this.listener = listener;
    }

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
//            Log.i("MainActivity", "doc: " + document.toString());
            List<Image> images = new ArrayList<>();
            int size = document.select("div.wfc a").size();
            Log.i("MainActivity", "size: " + size);
            for (int i=0; i<size; i++) {
                Element imgElement = document.select("div.wfc a").get(i);
                String imageUrl = imgElement.attr("href");
                String imageWidth = imgElement.select("img").attr("width");
                String imageHeight = imgElement.select("img").attr("height");
                if (imageWidth != null && !imageWidth.equals("")
                        && imageHeight != null && !imageHeight.equals("")) {
                    Image image = new Image("http://huaban.com" + imageUrl,
                            Integer.valueOf(imageWidth),
                            Integer.valueOf(imageHeight));
                    images.add(image);
                }
//                Log.i("MainActivity", "imageUrl: " + imageUrl);
//                Log.i("MainActivity", "imageWidth: " + imageWidth);
//                Log.i("MainActivity", "imageHeight: " + imageHeight);
            }
//
            return images;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
