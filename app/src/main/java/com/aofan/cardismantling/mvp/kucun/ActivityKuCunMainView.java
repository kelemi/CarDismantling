package com.aofan.cardismantling.mvp.kucun;

import android.os.Bundle;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.kucun.carkucun.ActivityCarKuCunMainView;
import com.aofan.cardismantling.mvp.kucun.lingjiankucun.ActivityLingJianKuCunMainView;
import com.aofan.cardismantling.util.IntentUtil;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**库存主界面
 * Created by Administrator on 2016/11/29.
 */

public class ActivityKuCunMainView extends BaseActivity {

    private TextView tvCarKucun;
    private TextView tvLingjianKucun;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kucun_main_view);
        initTitleWithRightTvOrIv(null,null,"库存",true,null,null);
        initData();
        assignView();
        initView();
    }

    @Override
    public void initData() {

    }

    @Override
    public void assignView() {
        tvCarKucun = (TextView) findViewById(R.id.tv_car_kucun);
        tvLingjianKucun = (TextView) findViewById(R.id.tv_lingjian_kucun);

    }

    @Override
    public void initView() {
        RxView.clicks(tvCarKucun)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        IntentUtil.startNewActivity(ActivityKuCunMainView.this, ActivityCarKuCunMainView.class);
                    }
                });

        RxView.clicks(tvLingjianKucun)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        IntentUtil.startNewActivity(ActivityKuCunMainView.this, ActivityLingJianKuCunMainView.class);
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
