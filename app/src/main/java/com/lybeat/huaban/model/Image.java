package com.lybeat.huaban.model;

/**
 * Author: lybeat
 * Date: 2016/2/19
 */
public class Image {

    private String path;
    private String name;
    private String format;
    private long size;

    public Image(String path,
                 String name,
                 String format,
                 long size) {
        this.path = path;
        this.name = name;
        this.format = format;
        this.size = size;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
