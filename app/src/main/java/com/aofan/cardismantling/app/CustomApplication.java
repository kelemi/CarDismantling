package com.aofan.cardismantling.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.aofan.cardismantling.BuildConfig;
import com.aofan.cardismantling.R;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.Res;
import com.aofan.cardismantling.util.viewutil.imageloader.PicassoImageLoader;
import com.aofan.cardismantling.util.viewutil.imageloader.PicassoPauseOnScrollListener;
import com.karumi.dexter.Dexter;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;


/**
 * Created by Administrator on 2016/10/25.
 */
public class CustomApplication extends Application {




    public static ContextWrapper globalContext;
    private static CustomApplication application;


    HttpService httpService;



    public HttpService getHttpService() {
        if (httpService == null) {
            httpService = HttpService.Factory.create(Config.URL_BASE_IN_USE);
        }
        return httpService;
    }




    public static CustomApplication getInstance() {
        return application;
    }





    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = this;
        application = this;
        Dexter.initialize(this);
        initLog();
        initGalleyFinal();
    }


    private void initLog() {
        LogUtil.isDebug = BuildConfig.DEBUG;
    }

    private void initGalleyFinal()
    {
        //设置主题
//ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Res.getColor(R.color.color_main))
                .setTitleBarTextColor(Res.getColor(R.color.white))
                .build();


        //配置功能
       /* FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();*/
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnablePreview(true)
                .build();

        PicassoImageLoader picassoImageLoader = new PicassoImageLoader();

        CoreConfig coreConfig = new CoreConfig.Builder(this, new PicassoImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(new PicassoPauseOnScrollListener(false, true))
                .build();
        GalleryFinal.init(coreConfig);

    }
}