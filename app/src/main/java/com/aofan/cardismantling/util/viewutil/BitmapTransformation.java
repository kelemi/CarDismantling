package com.aofan.cardismantling.util.viewutil;

import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 2015/12/30.
 */
public class BitmapTransformation implements Transformation {


    private Context mContext;

    private int targetWidth;

    private int targetHeight;
    public BitmapTransformation(Context mContext, int targetWidth, int targetHeight) {
        this.mContext = mContext;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        return BitMapUtil.getTargetBitmap(source,mContext, targetWidth,targetHeight);
    }

    @Override
    public String key() {
        return "square";
    }
}
