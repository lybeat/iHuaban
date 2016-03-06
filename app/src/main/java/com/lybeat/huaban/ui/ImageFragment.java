package com.lybeat.huaban.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lybeat.huaban.R;
import com.lybeat.huaban.adapter.ImageAdapter;
import com.lybeat.huaban.model.Image;
import com.lybeat.huaban.model.ImageLoader;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class ImageFragment extends BaseFragment implements ImageLoader.onCompleteListener {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.image_recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        ImageLoader imageLoader = new ImageLoader();
        imageLoader.execute("http://huaban.com/favorite/anime/");

        return view;
    }

    @Override
    public void onSuccess(List<Image> images) {
        imageAdapter = new ImageAdapter(getActivity(), images);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void onFailed() {

    }
}
