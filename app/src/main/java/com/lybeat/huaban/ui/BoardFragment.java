package com.lybeat.huaban.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lybeat.huaban.R;
import com.lybeat.huaban.adapter.BoardAdapter;
import com.lybeat.huaban.model.Board;
import com.lybeat.huaban.model.BoardLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/2/28
 */
public class BoardFragment extends BaseFragment implements
        BoardLoader.OnCompleteListener, BoardAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private BoardAdapter adapter;
    private List<Board> boards = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.board_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.board_refresh);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
            }
        });
        initData();

        return view;
    }

    private void initData() {
        adapter = new BoardAdapter(getActivity(), this.boards);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        BoardLoader boardLoader = new BoardLoader(this);
        boardLoader.execute("http://huaban.com/xa1zmhzt2u/");
    }

    @Override
    public void onSuccess(List<Board> boards) {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);
            }
        });
        Log.i("MainActivity", "onSuccess");
        this.boards.addAll(boards);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed() {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void onRefresh() {
        BoardLoader boardLoader = new BoardLoader(this);
        boardLoader.execute("http://huaban.com/xa1zmhzt2u/");
    }
}
