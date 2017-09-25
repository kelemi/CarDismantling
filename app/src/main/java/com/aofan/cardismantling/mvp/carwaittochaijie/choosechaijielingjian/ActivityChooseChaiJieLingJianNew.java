package com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijielingjian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.vp.HomeFragmentPagerAdapter;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.widget.NoSlideViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ActivityChooseChaiJieLingJianNew extends BaseActivity implements ViewPager.OnPageChangeListener{

    private RadioGroup rgPartAnalysisstateChoose;
    private RadioButton rbHasAnalysisPart;
    private RadioButton rbNotAnalysisPart;
    private NoSlideViewPager vp;


    List<Fragment> mFragList = new ArrayList<>();
    HomeFragmentPagerAdapter mFragAdapter;

    String mCarDid;

    String mChaijieType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_chaijie_part_new);
        initTitleWithRightTvOrIv(null, null, "选择拆解零件", true, null, null);
        initData();
        assignView();
        initView();
    }

    @Override
    public void initData() {
        mCarDid = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_DID);
        mChaijieType = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_CHAIJIE_TYPE);
    }

    @Override
    public void assignView() {
        rgPartAnalysisstateChoose = (RadioGroup) findViewById(R.id.rg_part_analysisstate_choose);
        rbHasAnalysisPart = (RadioButton) findViewById(R.id.rb_has_analysis_part);
        rbNotAnalysisPart = (RadioButton) findViewById(R.id.rb_not_analysis_part);
        vp = (NoSlideViewPager) findViewById(R.id.vp);
    }

    @Override
    public void initView() {

        initFrag();

        if (mChaijieType.equals(Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI))
        {
           rbNotAnalysisPart.setVisibility(View.VISIBLE);
        }else if(mChaijieType.equals(Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI)){
            rbNotAnalysisPart.setVisibility(View.GONE);
        }

        rgPartAnalysisstateChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.rb_has_analysis_part:

                        vp.setCurrentItem(0,false);

                        break;
                    case R.id.rb_not_analysis_part:

                        vp.setCurrentItem(1,false);

                        break;
                }
            }
        });
    }


    private void initFrag()
    {

        if (mChaijieType.equals(Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI))
        {
            FragmentChooseChaiJiePart fragmentChooseHasAnalysisPart = FragmentChooseChaiJiePart.getInstance(mCarDid, Constant.STATE_OF_HAS_ANALYSIS);
            FragmentChooseChaiJiePart fragmentChooseNotAnalysisPart = FragmentChooseChaiJiePart.getInstance(mCarDid, Constant.STATE_OF_NOT_ANALYSIS);

            mFragList.add(fragmentChooseHasAnalysisPart);
            mFragList.add(fragmentChooseNotAnalysisPart);

            mFragAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),mFragList);
            vp.setOffscreenPageLimit(2);
            vp.setAdapter(mFragAdapter);
            vp.setCurrentItem(0);
            vp.addOnPageChangeListener(this);
            vp.setLocked(true);
        }else if(mChaijieType.equals(Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI)){
            FragmentChooseChaiJiePart fragmentChooseHasAnalysisPart = FragmentChooseChaiJiePart.getInstance(mCarDid, Constant.STATE_OF_HAS_ANALYSIS);
            //FragmentChooseChaiJiePart fragmentChooseNotAnalysisPart = FragmentChooseChaiJiePart.getInstance(mCarDid, Constant.STATE_OF_NOT_ANALYSIS);

            mFragList.add(fragmentChooseHasAnalysisPart);
            //mFragList.add(fragmentChooseNotAnalysisPart);

            mFragAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),mFragList);
            vp.setOffscreenPageLimit(1);
            vp.setAdapter(mFragAdapter);
            vp.setCurrentItem(0);
            vp.addOnPageChangeListener(this);
            vp.setLocked(true);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
