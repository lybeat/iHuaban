package com.lybeat.huaban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lybeat.huaban.R;
import com.lybeat.huaban.model.Image;
import com.lybeat.huaban.widget.FlowImageView;
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
            try {
                Image image = images.get(position);
                ((ImageHolder) holder).imageImg.setOriginalSize(image.getWidth(), image.getHeight());
                Picasso.with(context).load(images.get(position).getUrl())
                        .resize(500, 500 * image.getHeight() / image.getWidth())
                        .placeholder(R.mipmap.huaban_icon_64px)
                        .error(R.mipmap.huaban_icon_64px)
                        .into(((ImageHolder) holder).imageImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public class ImageHolder extends BaseHolder {

        private FlowImageView imageImg;

        public ImageHolder(View itemView) {
            super(itemView);
            imageImg = (FlowImageView) itemView.findViewById(R.id.image_img);
        }
    }
}
