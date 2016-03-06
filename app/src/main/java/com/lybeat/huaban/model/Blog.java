package com.lybeat.huaban.model;

/**
 * Author: lybeat
 * Date: 2016/3/3
 */
public class Blog {

    private String url;
    private String title;
    private String summary;
    private String date;
    private String tags;
    private String thumbUrl;

    public Blog(){}

    public Blog(String url, String title, String summary,
                String date, String tags, String thumbUrl) {
        this.url = url;
        this.title = title;
        this.summary = summary;
        this.date = date;
        this.tags = tags;
        this.thumbUrl = thumbUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
