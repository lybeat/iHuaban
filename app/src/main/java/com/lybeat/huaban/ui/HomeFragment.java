package com.lybeat.huaban.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lybeat.huaban.ImageLoader;
import com.lybeat.huaban.R;
import com.lybeat.huaban.adapter.StaggeredAdapter;
import com.lybeat.huaban.model.Image;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/2/27
 */
public class HomeFragment extends Fragment {

    private List<Image> images;
    private ImageLoader imageLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        imageLoader = new ImageLoader(getActivity());
        images = imageLoader.queryImage();
        StaggeredAdapter adapter = new StaggeredAdapter(getActivity(), images);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.staggered_grid);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
