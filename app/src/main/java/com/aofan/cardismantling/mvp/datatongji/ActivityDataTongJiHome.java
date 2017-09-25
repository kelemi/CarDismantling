package com.aofan.cardismantling.mvp.datatongji;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.vp.ViewPagerFragmentAdapterWithTab;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.datatongji.selltongji.FragmentSellTongJi;
import com.aofan.cardismantling.mvp.datatongji.vheicletongji.FragmentVheicleTongJi;
import com.aofan.cardismantling.mvp.datatongji.workertongji.FragmentWorkerTongJi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class ActivityDataTongJiHome extends BaseActivity {

    private TabLayout layoutTab;
    private ViewPager vp;

    List<Fragment> mTabFragmentList = new ArrayList<>();
    List<String> mTabTitleList = new ArrayList<>();
    ViewPagerFragmentAdapterWithTab mTabFragmentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tongji_home);
        initTitleWithRightTvOrIv(null,null,"数据统计",true,null,null);
        initData();
        assignView();
        initView();
    }

    @Override
    public void initData() {
        mTabTitleList.add("车辆统计");
        mTabTitleList.add("人员统计");
        mTabTitleList.add("销售统计");

    }

    @Override
    public void assignView() {

        layoutTab = (TabLayout) findViewById(R.id.layout_tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    public void initView() {
        initTabAndFragment();
    }

    //初始化tab和fragment
    private void initTabAndFragment() {
        mTabFragmentList.add(FragmentVheicleTongJi.getInstance(null));
        mTabFragmentList.add(FragmentWorkerTongJi.getInstance(null));
        mTabFragmentList.add(FragmentSellTongJi.getInstance(null));

        mTabFragmentAdapter = new ViewPagerFragmentAdapterWithTab(getSupportFragmentManager(), mTabFragmentList, mTabTitleList, this);


        //tabvp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        layoutTab.addTab(layoutTab.newTab().setText("车辆统计"));
        layoutTab.addTab(layoutTab.newTab().setText("人员统计"));
        layoutTab.addTab(layoutTab.newTab().setText("销售统计"));



        //tablayout.setupWithViewPager(tabvp);

        vp.setOffscreenPageLimit(4);
        vp.setAdapter(mTabFragmentAdapter);
        vp.setCurrentItem(0);

        layoutTab.setupWithViewPager(vp);
        layoutTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
