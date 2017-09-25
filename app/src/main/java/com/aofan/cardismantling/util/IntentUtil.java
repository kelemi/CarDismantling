package com.aofan.cardismantling.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


/**
 * Created by Administrator on 2015/8/20.
 */
public class IntentUtil {

    public static void startNewActivity(Context context,Class newActivityClass){
        Intent intent = new Intent(context, newActivityClass);
        context.startActivity(intent);
    }


    public static void startNewActivityForResult(Context context,Class newActivityClass,int requestCode){
        Intent intent = new Intent(context, newActivityClass);
        ((Activity)context).startActivityForResult(intent,requestCode);
    }

}
