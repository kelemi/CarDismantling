package com.aofan.cardismantling.mvp.kucun.lingjiankucun;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.vp.ViewPagerFragmentAdapterWithTab;
import com.aofan.cardismantling.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public class ActivityLingJianKuCunMainView extends BaseActivity {

    private TabLayout layoutTab;
    private ViewPager vp;

    List<Fragment> mTabFragmentList = new ArrayList<>();
    List<String> mTabTitleList = new ArrayList<>();
    ViewPagerFragmentAdapterWithTab mTabFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lingjian_kucun_new);
        initTitleWithRightTvOrIv(null,null,"零件库存",true,null,null);
        initData();
        assignView();
        initView();

    }

    @Override
    public void initData() {
        mTabTitleList.add("待销售");
        mTabTitleList.add("已销售");
        mTabTitleList.add("转大宗商品");
    }

    @Override
    public void assignView() {
        layoutTab = (TabLayout) findViewById(R.id.layout_tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    public void initView() {
        initTabAndFrag();
    }

    private void initTabAndFrag()
    {
        mTabFragmentList.add(FragmentLingJianKuCun.getInstance("待销售"));
        mTabFragmentList.add(FragmentLingJianKuCun.getInstance("已销售"));
        mTabFragmentList.add(FragmentLingJianKuCun.getInstance("大宗商品"));

        mTabFragmentAdapter = new ViewPagerFragmentAdapterWithTab(getSupportFragmentManager(), mTabFragmentList, mTabTitleList, this);


        layoutTab.addTab(layoutTab.newTab().setText("待销售"));
        layoutTab.addTab(layoutTab.newTab().setText("已销售"));
        layoutTab.addTab(layoutTab.newTab().setText("转大宗商品"));


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
