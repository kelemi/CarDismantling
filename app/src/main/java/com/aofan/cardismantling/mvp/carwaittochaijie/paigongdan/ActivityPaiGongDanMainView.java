package com.aofan.cardismantling.mvp.carwaittochaijie.paigongdan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.vp.ViewPagerFragmentAdapterWithTab;
import com.aofan.cardismantling.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**派工单主界面
 * Created by Administrator on 2016/11/24.
 */

public class ActivityPaiGongDanMainView extends BaseActivity{

    private TabLayout layoutTab;
    private ViewPager vp;

    List<Fragment> mTabFragmentList = new ArrayList<>();
    List<String> mTabTitleList = new ArrayList<>();
    ViewPagerFragmentAdapterWithTab mTabFragmentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paigongdan_main_view);
        initTitleWithRightTvOrIv(null,null,"派工单",true,null,null);
        initData();
        assignView();
        initView();
    }

    @Override
    public void initData() {
        mTabTitleList.add("未派工车辆");
        mTabTitleList.add("派工中车辆");
        mTabTitleList.add("已派工车辆");

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
        mTabFragmentList.add(FragmentNotFinishedPaiGongCarList.getInstance(null));
        mTabFragmentList.add(FragmentPaiGongIngCarList.getInstance(null));
        mTabFragmentList.add(FragmentHasFinishedPaiGongCarList.getInstance(null));

        mTabFragmentAdapter = new ViewPagerFragmentAdapterWithTab(getSupportFragmentManager(), mTabFragmentList, mTabTitleList, this);


        layoutTab.addTab(layoutTab.newTab().setText("未派工车辆"));
        layoutTab.addTab(layoutTab.newTab().setText("派工中车辆"));
        layoutTab.addTab(layoutTab.newTab().setText("已派工车辆"));


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
