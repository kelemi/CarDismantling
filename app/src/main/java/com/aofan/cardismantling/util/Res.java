package com.aofan.cardismantling.util;

import android.graphics.drawable.Drawable;

import com.aofan.cardismantling.app.CustomApplication;


/**
 * Created by Administrator on 2015/7/1.
 */
public class Res {

    public static int getDrawableIdFromName(String paramString) {
        return CustomApplication.globalContext.getResources().getIdentifier(paramString, "drawable", CustomApplication.globalContext.getPackageName());
    }

    public static int getMipmapIdFromName(String paramString) {
        return CustomApplication.globalContext.getResources().getIdentifier(paramString, "mipmap", CustomApplication.globalContext.getPackageName());
    }

    public static int getColorIdFromName(String paramString) {
        return CustomApplication.globalContext.getResources().getIdentifier(paramString, "color", CustomApplication.globalContext.getPackageName());
    }



    public static Drawable getDrawable(int paramInt) {
        return CustomApplication.getInstance().getResources().getDrawable(paramInt);
    }

    public static String getString(int paramInt){
        return CustomApplication.getInstance().getResources().getString(paramInt);
    }


    public static  int getColor(int paramInt)
    {
        return CustomApplication.getInstance().getResources().getColor(paramInt);
    }

    public static  int getDimen(int paramInt)
    {
        return CustomApplication.getInstance().getResources().getDimensionPixelSize(paramInt);
    }

}
