package com.aofan.cardismantling.mvp.splash;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.home.ActivityHome;
import com.aofan.cardismantling.mvp.login.ActivityLogin;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.IntentUtil;
import com.aofan.cardismantling.util.LogUtil;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ActivitySplash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        TimeCount timeCount = new TimeCount(Config.DEFAULT_WELCOME_PAGE_TIME, 1000);
        timeCount.start();
    }

    @Override
    public void initData() {

    }

    @Override
    public void assignView() {

    }

    @Override
    public void initView() {

    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            if ((Boolean) CommonUtil.getShareValue(ShareKey.SHARE_KEY_IS_LOGIN,false))
            {
                IntentUtil.startNewActivity(ActivitySplash.this,ActivityHome.class);
                finish();
            }else {
                IntentUtil.startNewActivity(ActivitySplash.this,ActivityLogin.class);
                finish();
            }

        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            LogUtil.e(millisUntilFinished / 1000 + "秒");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
