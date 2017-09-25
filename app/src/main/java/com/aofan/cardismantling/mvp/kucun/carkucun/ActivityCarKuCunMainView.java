package com.aofan.cardismantling.mvp.kucun.carkucun;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.vp.ViewPagerFragmentAdapterWithTab;
import com.aofan.cardismantling.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**汽车库存主界面
 * Created by Administrator on 2016/11/29.
 */

public class ActivityCarKuCunMainView extends BaseActivity {

    private TabLayout layoutTab;
    private ViewPager vp;

    List<Fragment> mTabFragmentList = new ArrayList<>();
    List<String> mTabTitleList = new ArrayList<>();
    ViewPagerFragmentAdapterWithTab mTabFragmentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_kucun_mainview);
        initTitleWithRightTvOrIv(null,null,"汽车库存",true,null,null);
        initData();
        assignView();
        initView();
    }

    @Override
    public void initData() {
        mTabTitleList.add("已拆");
        mTabTitleList.add("拆解中");
        mTabTitleList.add("未拆");

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
        mTabFragmentList.add(FragmentCarKuCunList.getInstance("已拆"));
        mTabFragmentList.add(FragmentCarKuCunList.getInstance("拆解中"));
        mTabFragmentList.add(FragmentCarKuCunList.getInstance("未拆"));

        mTabFragmentAdapter = new ViewPagerFragmentAdapterWithTab(getSupportFragmentManager(), mTabFragmentList, mTabTitleList, this);


        layoutTab.addTab(layoutTab.newTab().setText("已拆"));
        layoutTab.addTab(layoutTab.newTab().setText("拆解中"));
        layoutTab.addTab(layoutTab.newTab().setText("未拆"));


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
