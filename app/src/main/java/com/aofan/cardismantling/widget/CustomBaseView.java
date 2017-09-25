package com.aofan.cardismantling.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/2/19.
 */
public abstract class CustomBaseView extends View {
    public CustomBaseView(Context context) {
        this(context,null);
    }

    public CustomBaseView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CustomBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomBaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //初始化属性
    public abstract void initAttrs(AttributeSet attrs);
    //初始化操作
    public abstract void  init();
}
