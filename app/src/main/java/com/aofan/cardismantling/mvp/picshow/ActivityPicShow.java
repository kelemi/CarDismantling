package com.aofan.cardismantling.mvp.picshow;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.vp.PicViewPagerAdapter;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.widget.NoSlideViewPager;
import com.aofan.cardismantling.widget.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class ActivityPicShow extends BaseActivity implements View.OnClickListener{


    private ImageView leftbackiv;
    private NoSlideViewPager picvp;
    private CirclePageIndicator vpindicator;


    List<View> mViewList = new ArrayList<>();
    List<String> mPicUrls = new ArrayList<>();
    PicViewPagerAdapter mAdapter;

    int mCurShowPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_show);
        initData();
        assignView();
        initView();
    }

    @Override
    public void initData() {
        /*mPicUrls = (List<String>) getIntent().getSerializableExtra(Config.INTENT_KEY_PIC_URLS);
        mPicFromType = getIntent().getStringExtra(Config.INTENT_KEY_PIC_FROM_TYPE);*/
        mCurShowPosition = getIntent().getIntExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION,0);

        mPicUrls = (List<String>) getIntent().getSerializableExtra(IntentKey.INTENT_KEY_PIC_URLS);

        mViewList.clear();

        if (mPicUrls.size()>0)
        {
            for (int i=0;i<mPicUrls.size();i++)
            {
                View view = getLayoutInflater().inflate(R.layout.vp_item_pic_show,null);
                mViewList.add(view);
            }
        }
    }

    @Override
    public void assignView() {
        leftbackiv = (ImageView) findViewById(R.id.leftbackiv);
        picvp = (NoSlideViewPager) findViewById(R.id.picvp);
        vpindicator = (CirclePageIndicator) findViewById(R.id.vpindicator);
    }

    @Override
    public void initView() {
        leftbackiv.setOnClickListener(this);
        mAdapter = new PicViewPagerAdapter(this,mPicUrls,mViewList);
        picvp.setAdapter(mAdapter);
        picvp.setCurrentItem(mCurShowPosition);
        vpindicator.setViewPager(picvp);
        vpindicator.setCurrentItem(mCurShowPosition);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.leftbackiv:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
