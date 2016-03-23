package com.lybeat.huaban.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Author: lybeat
 * Date: 2016/3/21
 */
public class FlowImageView extends ImageView {

    private int originalWidth;

    private int originalHeight;

    public FlowImageView(Context context) {
        super(context);
    }

    public FlowImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            float ratio = (float) originalWidth / originalHeight;
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * ratio);
            setMeasuredDimension(width, height);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
