package com.aofan.cardismantling.mvp.home;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.vp.HomeFragmentPagerAdapter;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.home.index.FragmentIndex;
import com.aofan.cardismantling.mvp.home.personalcenter.FragmentPersonalCenter;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.widget.NoSlideViewPager;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ActivityHome extends BaseActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{


    private RadioGroup rgChooseTab;
    private RadioButton rbIndexPage;
    private RadioButton rbPersonalCenter;
    private NoSlideViewPager vp;


    List<Fragment> mFragList = new ArrayList<>();
    HomeFragmentPagerAdapter mFragAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        requestPermission();
        initData();
        assignView();
        initView();
    }

    private void requestPermission() {
        Dexter.checkPermissions(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted())
                {

                }else {
                    ToastUtil.showShort(ActivityHome.this,"我们需要相机和本地存储权限用来保证app的正常运行,请到系统设置里面进行相关设置");
                }
            }
            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }, Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE);
       }

    @Override
    public void initData() {

        /*String JSON_TEST = "{'name':'wang','sex':'man'}";
        String gsonString = new Gson().fromJson(JSON_TEST, new TypeToken<String>(){}.getType());

        LogUtil.e("gsonString:"+gsonString);*/
    }

    @Override
    public void assignView() {
        rgChooseTab = (RadioGroup) findViewById(R.id.rg_choose_tab);
        rbIndexPage = (RadioButton) findViewById(R.id.rb_index_page);
        rbPersonalCenter = (RadioButton) findViewById(R.id.rb_personal_center);
        vp = (NoSlideViewPager) findViewById(R.id.vp);
    }

    @Override
    public void initView() {
        initRadioGroup();
        initFrag();
    }

    private void initRadioGroup()
    {
        rgChooseTab.setOnCheckedChangeListener(this);
    }

    private void initFrag()
    {
        FragmentIndex fragmentIndex = FragmentIndex.getInstance(null);
        FragmentPersonalCenter fragmentPersonalCenter = FragmentPersonalCenter.getInstance(null);

        mFragList.add(fragmentIndex);
        mFragList.add(fragmentPersonalCenter);

        mFragAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),mFragList);
        vp.setOffscreenPageLimit(5);
        vp.setAdapter(mFragAdapter);
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(this);
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
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i)
        {
            case R.id.rb_index_page:
                vp.setCurrentItem(0,false);
                break;
            case R.id.rb_personal_center:
                vp.setCurrentItem(1,false);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
