package com.lybeat.huaban.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lybeat.huaban.R;
import com.lybeat.huaban.adapter.BaseAdapter;
import com.lybeat.huaban.adapter.CategoryAdapter;
import com.lybeat.huaban.model.Category;
import com.lybeat.huaban.model.CategoryLoader;
import com.lybeat.huaban.widget.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class CategoryFragment extends BaseFragment
        implements BaseAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Category> categories;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.category_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                GridLayoutManager.VERTICAL);
        dividerItemDecoration.setSpace(getResources().getDimensionPixelSize(R.dimen.grid_space));
        recyclerView.addItemDecoration(dividerItemDecoration);

        CategoryLoader categoryLoader = new CategoryLoader(getActivity());
         categories = categoryLoader.loadCategory();

        CategoryAdapter adapter = new CategoryAdapter(getActivity(), categories);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        String url = categories.get(position).getUrl();
        Toast.makeText(getActivity(), "url: " + url, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(getActivity(), "position: " + position, Toast.LENGTH_LONG).show();
    }
}
