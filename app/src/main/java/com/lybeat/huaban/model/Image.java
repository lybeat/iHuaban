package com.lybeat.huaban.model;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class Image {

    private String url;
    private String coverUrl;

    public Image(String url, String coverUrl) {
        this.url = url;
        this.coverUrl = coverUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
