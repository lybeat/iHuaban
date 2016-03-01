package com.lybeat.huaban.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lybeat.huaban.R;
import com.lybeat.huaban.model.Image;
import com.lybeat.huaban.util.PictureUtil;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/2/28
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.StaggeredView> {

    private Context context;
    private List<Image> images;

    public StaggeredAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public StaggeredView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, null);
        return new StaggeredView(view);
    }

    @Override
    public void onBindViewHolder(StaggeredView holder, int position) {
        Image image = images.get(position);
        Bitmap thumbnail = PictureUtil.getScaledBitmapFromPath(image.getPath(), 360, 480);
        holder.img.setImageBitmap(thumbnail);
        holder.txt.setText(image.getName());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class StaggeredView extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txt;

        public StaggeredView(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.card_img);
            txt = (TextView) itemView.findViewById(R.id.card_txt);
        }
    }
}
