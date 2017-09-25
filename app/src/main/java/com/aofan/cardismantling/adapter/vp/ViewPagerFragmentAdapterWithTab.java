package com.aofan.cardismantling.adapter.vp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**viewpager的fragment adatper 同时带有string
 * Created by Administrator on 2016/7/19.
 */
public class ViewPagerFragmentAdapterWithTab extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private List<String> mTitles;
    Context mContext;


    public ViewPagerFragmentAdapterWithTab(FragmentManager fm, List<Fragment> mFragments, List<String> mTitles, Context mContext) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    //保证tablayout和vp绑定的时候可以有title显示出来
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }



}
