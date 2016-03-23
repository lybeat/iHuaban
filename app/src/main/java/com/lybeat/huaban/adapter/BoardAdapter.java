package com.lybeat.huaban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lybeat.huaban.R;
import com.lybeat.huaban.model.Board;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/3/21
 */
public class BoardAdapter extends BaseAdapter {

    private Context context;
    private List<Board> boards;

    public BoardAdapter(Context context, List<Board> boards) {
        this.context = context;
        this.boards = boards;
    }

    @Override
    public BaseHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_board, null);
        return new BoardHolder(view);
    }

    @Override
    public void onBindHolder(BaseHolder holder, int position) {
        if (holder instanceof BoardHolder) {
            Picasso.with(context).load(boards.get(position).getCovers().get(0))
                    .placeholder(R.mipmap.huaban_icon_64px)
                    .error(R.mipmap.huaban_icon_64px)
                    .into(((BoardHolder) holder).coverImg1);
            Picasso.with(context).load(boards.get(position).getCovers().get(1))
                    .placeholder(R.mipmap.huaban_icon_64px)
                    .error(R.mipmap.huaban_icon_64px)
                    .into(((BoardHolder) holder).coverImg2);
            Picasso.with(context).load(boards.get(position).getCovers().get(2))
                    .placeholder(R.mipmap.huaban_icon_64px)
                    .error(R.mipmap.huaban_icon_64px)
                    .into(((BoardHolder) holder).coverImg3);
            Picasso.with(context).load(boards.get(position).getCovers().get(3))
                    .placeholder(R.mipmap.huaban_icon_64px)
                    .error(R.mipmap.huaban_icon_64px)
                    .into(((BoardHolder) holder).coverImg4);
            ((BoardHolder) holder).nameTxt.setText(boards.get(position).getName());
            ((BoardHolder) holder).pinCountTxt.setText(boards.get(position).getPinCount());
        }
    }

    @Override
    public int getCount() {
        return boards.size();
    }

    public static class BoardHolder extends BaseAdapter.BaseHolder {

        private ImageView coverImg1;
        private ImageView coverImg2;
        private ImageView coverImg3;
        private ImageView coverImg4;
        private TextView nameTxt;
        private TextView pinCountTxt;

        public BoardHolder(View itemView) {
            super(itemView);
            coverImg1 = (ImageView) itemView.findViewById(R.id.board_cover_img1);
            coverImg2 = (ImageView) itemView.findViewById(R.id.board_cover_img2);
            coverImg3 = (ImageView) itemView.findViewById(R.id.board_cover_img3);
            coverImg4 = (ImageView) itemView.findViewById(R.id.board_cover_img4);
            nameTxt = (TextView) itemView.findViewById(R.id.board_name_txt);
            pinCountTxt = (TextView) itemView.findViewById(R.id.pin_count_txt);
        }
    }
}
