package com.lybeat.huaban.model;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/21
 */
public class Board {

    private String name;
    private String boardUrl;
    private String pinCount;
    private List<String> covers;

    public Board(){}

    public Board(String name, String boardUrl, String pinCount, List<String> covers) {
        this.name = name;
        this.boardUrl = boardUrl;
        this.pinCount = pinCount;
        this.covers = covers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinCount() {
        return pinCount;
    }

    public void setPinCount(String pinCount) {
        this.pinCount = pinCount;
    }

    public String getBoardUrl() {
        return boardUrl;
    }

    public void setBoardUrl(String boardUrl) {
        this.boardUrl = boardUrl;
    }

    public List<String> getCovers() {
        return covers;
    }

    public void setCovers(List<String> covers) {
        this.covers = covers;
    }
}
