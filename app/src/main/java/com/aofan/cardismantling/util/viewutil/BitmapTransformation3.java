package com.aofan.cardismantling.util.viewutil;

import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 2015/11/5.
 */
public class BitmapTransformation3 implements Transformation {


    private Context mContext;

    private int targetWidth;

    public BitmapTransformation3(Context mContext, int targetWidth) {
        this.targetWidth = targetWidth;
        this.mContext = mContext;
    }


    @Override
    public Bitmap transform(Bitmap source) {


        return BitMapUtil.getBannerPicBitmap(mContext, source,targetWidth );


    }

    @Override
    public String key() {
        return "square";
    }
}