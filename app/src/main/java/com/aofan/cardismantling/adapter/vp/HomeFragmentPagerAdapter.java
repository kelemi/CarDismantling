package com.aofan.cardismantling.adapter.vp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 主界面的fragment的adapter
 * Created by Administrator on 2016/1/15.
 */
public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;


    public void setmFragments(List<Fragment> mFragments) {
        this.mFragments = mFragments;
    }

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomeFragmentPagerAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }


}
