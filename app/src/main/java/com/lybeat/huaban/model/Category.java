package com.lybeat.huaban.model;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class Category {

    private int imgId;
    private String txt;
    private String url;

    public Category(int imgId, String txtId, String url) {
        this.imgId = imgId;
        this.txt = txtId;
        this.url = url;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
