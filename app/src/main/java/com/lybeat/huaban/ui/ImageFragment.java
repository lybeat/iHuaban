package com.lybeat.huaban.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lybeat.huaban.R;
import com.lybeat.huaban.adapter.ImageAdapter;
import com.lybeat.huaban.model.Image;
import com.lybeat.huaban.model.ImageLoader;
import com.lybeat.huaban.widget.DividerItemDecoration;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class ImageFragment extends BaseFragment implements ImageLoader.OnCompleteListener {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.image_recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                OrientationHelper.VERTICAL);
        dividerItemDecoration.setSpace(getResources().getDimensionPixelSize(R.dimen.item_space));
        recyclerView.addItemDecoration(dividerItemDecoration);

        ImageLoader imageLoader = new ImageLoader(this);
        imageLoader.execute("http://huaban.com/favorite/anime/");

        return view;
    }

    @Override
    public void onSuccess(List<Image> images) {
        ImageAdapter adapter = new ImageAdapter(getActivity(), images);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed() {

    }
}
