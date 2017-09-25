package com.aofan.cardismantling.util.viewutil;

import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * 根据占位符的大小去显示图片
 * Created by Administrator on 2015/10/26.
 */
public class BitmapTransformation2 implements Transformation {


    private Context mContext;

    private int defaultResId;

    public BitmapTransformation2(Context mContext, int defaultResId) {
        this.defaultResId = defaultResId;
        this.mContext = mContext;
    }


    @Override
    public Bitmap transform(Bitmap source) {

        return BitMapUtil.getTargetBitmapByDefaultDrawable(mContext, source, defaultResId);
    }

    @Override
    public String key() {
        return "square";
    }
}