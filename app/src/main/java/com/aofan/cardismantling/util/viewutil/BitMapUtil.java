package com.aofan.cardismantling.util.viewutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.aofan.cardismantling.util.LogUtil;


/**
 * Created by Administrator on 2015/9/21.
 */
public class BitMapUtil {


    /**
     * 获取target宽高信息
     *
     * @param resId
     * @param context
     * @return
     */
    public static TargetDisplayInfo getTargetDisplayInfo(int resId, Context context, int targetWidth) {


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 通过这个bitmap获取图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options);

        int realWidth = options.outWidth;
        int realHeight = options.outHeight;


        int targetHeight = targetWidth / realWidth * realHeight;
        Log.e("targetWidth", "" + targetWidth);
        Log.e("targetHeight", "" + targetHeight);

        return new TargetDisplayInfo(targetWidth, targetHeight);
    }


    /**
     * 获取banner图片的bitmap
     * @param context
     * @param bitmap
     * @param targetWidth
     * @return
     */
    public static Bitmap getBannerPicBitmap(Context context,Bitmap bitmap, int targetWidth) {


        float widthScaleValue = ((float)targetWidth/bitmap.getWidth());
        int targetHeight =(int)( bitmap.getHeight()*widthScaleValue);
        LogUtil.e("targetWidth:" + targetWidth);
        LogUtil.e("targetHeight:"+targetHeight);
        Bitmap resizeBmp = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false);
        bitmap.recycle();
        return resizeBmp;

    }


    //根据默认的图片  获取目的bitmap
    public static Bitmap getTargetBitmapByDefaultDrawable(Context context,Bitmap bitmap, int drawableId)
    {
        Drawable defaultDrawable = context.getResources().getDrawable(drawableId);
        BitmapDrawable bitmapDrawable = (BitmapDrawable)defaultDrawable;
        Bitmap defaultBitmap = bitmapDrawable.getBitmap();

        int defaultBitmapWidth = defaultBitmap.getWidth();
        int defaultBitmapHeight = defaultBitmap.getHeight();
        Log.e("defaultBitmapWidth", defaultBitmapWidth + "");
        Log.e("defaultBitmapHeight", defaultBitmapHeight + "");

        int originalWidth = bitmap.getWidth();
        Log.e("originalWidth", originalWidth + "");
        int originalHeight = bitmap.getHeight();

        float widthScaleValue = ((float)defaultBitmapWidth/originalWidth);
        float heightScaleValue =  ((float)defaultBitmapHeight/originalHeight);
        Log.e("widthScaleValue", widthScaleValue + "");
        Log.e("heightScaleValue", heightScaleValue + "");
        Matrix matrix = new Matrix();
        matrix.postScale(widthScaleValue, heightScaleValue); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

        bitmap.recycle();

        return resizeBmp;

    }


    public static Bitmap getTargetBitmap(Bitmap bitmap,Context context,Integer targetWidth,Integer targetHeight)
    {
        /*TargetDisplayInfo displayInfo = null;

        Bitmap resizeBmp = null;

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
       LogUtil.e("originalWidth", originalWidth + "");
        LogUtil.e("originalHeight",originalHeight+"");

        LogUtil.e("targetWidth",targetWidth+"");

        if (null != targetWidth)
        {

            float scaleValue = ((float)targetWidth/originalWidth);

            LogUtil.e("scaleValue", scaleValue + "");
            Matrix matrix = new Matrix();
            matrix.postScale(scaleValue, scaleValue); //长和宽放大缩小的比例
            resizeBmp = Bitmap.createBitmap(bitmap,0,0,originalWidth,originalHeight,matrix,true);

            bitmap.recycle();


        }


        if (null != targetHeight)
        {


            float scaleValue = ((float)targetHeight/originalHeight);
            Log.e("scaleValue", scaleValue + "");
            Matrix matrix = new Matrix();
            matrix.postScale(scaleValue, scaleValue); //长和宽放大缩小的比例
            resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

            bitmap.recycle();

        }

        return resizeBmp;*/

        Bitmap resizeBmp = null;

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();


        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        float scaleValue = 1;//be=1表示不缩放

        float widthScale = (float)targetWidth/(float)originalWidth;
        float heightScale = (float)targetHeight/(float)originalHeight;


        if (widthScale<heightScale)
        {
            scaleValue = widthScale;
        }else {
            scaleValue = heightScale;
        }


        LogUtil.e("scalevalue:"+scaleValue);

        int scaleWidht = (int) (originalWidth*scaleValue);
        int scaleHeight = (int) (originalHeight*scaleValue);
        Matrix matrix = new Matrix();
        matrix.postScale(scaleValue, scaleValue); //长和宽放大缩小的比例
        resizeBmp = Bitmap.createBitmap(bitmap,0,0,originalWidth,originalHeight,matrix,true);

        bitmap.recycle();


        return resizeBmp;
    }


}

