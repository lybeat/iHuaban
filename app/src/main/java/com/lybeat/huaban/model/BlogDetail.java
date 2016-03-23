package com.lybeat.huaban.model;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/22
 */
public class BlogDetail {

    private String title;
    private String content;
    private String time;
    private String tag;
    private List<Image> images;

    public BlogDetail(){}

    public BlogDetail(String title, String content, String time, String tag, List<Image> images) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.tag = tag;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
