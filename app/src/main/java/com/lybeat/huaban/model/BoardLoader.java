package com.lybeat.huaban.model;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/21
 */
public class BoardLoader extends AsyncTask<String, Void, List<Board>> {

    public interface OnCompleteListener {
        void onSuccess(List<Board> boards);
        void onFailed();
    }

    private OnCompleteListener listener;

    public BoardLoader(OnCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<Board> doInBackground(String... params) {
        String url = params[0];
        return parseBoard(url);
    }

    @Override
    protected void onPostExecute(List<Board> boards) {
        super.onPostExecute(boards);

        if (boards != null) {
            listener.onSuccess(boards);
        } else {
            listener.onFailed();
        }
    }

    private List<Board> parseBoard(String url) {
        try {
            Document document = Jsoup.connect(url).timeout(5000).get();
            List<Board> boards = new ArrayList<>();
            int size = document.select("div.wfc").size();
            Log.i("MainActivity", "board size: " + size);
            for (int i=0; i<size; i++) {
                Element boardLinkElement = document.select("div.wfc a").get(i);
                String boardUrl = boardLinkElement.attr("href");
                List<String> covers = new ArrayList<>();
                Elements coverElements = boardLinkElement.select("img");
                for (int count=0; count<coverElements.size(); count++) {
                    covers.add(coverElements.get(count).attr("src"));
//                    Log.i("MainActivity", "boardUrl: " + coverElements.get(count).attr("src"));
                }
                String boardName = boardLinkElement.select("div.over h3").text();
                String pinCount = boardLinkElement.select("div.pin-count").text();

//                Log.i("MainActivity", "boardUrl: " + boardUrl);
//                Log.i("MainActivity", "boardName: " + boardName);
//                Log.i("MainActivity", "pinCount: " + pinCount);

                Board board = new Board(boardName, boardUrl, pinCount, covers);
                boards.add(board);
            }
            return boards;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
