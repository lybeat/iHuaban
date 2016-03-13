package com.lybeat.huaban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lybeat.huaban.R;
import com.lybeat.huaban.model.Category;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public BaseHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, null);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindHolder(BaseHolder holder, int position) {
        if (holder instanceof CategoryHolder) {
            Category category = categories.get(position);
            ((CategoryHolder) holder).categoryImg.setImageResource(category.getImgId());
            ((CategoryHolder) holder).categoryTxt.setText(category.getTxt());
        }
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    public class CategoryHolder extends BaseHolder {

        ImageView categoryImg;
        TextView categoryTxt;

        public CategoryHolder(View itemView) {
            super(itemView);
            categoryImg = (ImageView) itemView.findViewById(R.id.category_img);
            categoryTxt = (TextView) itemView.findViewById(R.id.category_txt);
        }
    }
}
