package com.lybeat.huaban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lybeat.huaban.R;
import com.lybeat.huaban.model.Image;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/6
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<Image> images;

    public ImageAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public BaseHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);
        return new ImageHolder(view);
    }

    @Override
    public void onBindHolder(BaseHolder holder, int position) {
        if (holder instanceof ImageHolder) {
            Picasso.with(context).load(images.get(position).getCoverUrl())
                    .placeholder(R.drawable.avatar).error(R.drawable.avatar)
                    .into(((ImageHolder) holder).imageImg);
        }
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public class ImageHolder extends BaseHolder {

        private ImageView imageImg;

        public ImageHolder(View itemView) {
            super(itemView);
            imageImg = (ImageView) itemView.findViewById(R.id.image_img);
        }
    }
}
