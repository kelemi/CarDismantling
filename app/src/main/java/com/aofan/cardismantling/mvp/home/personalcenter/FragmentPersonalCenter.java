package com.aofan.cardismantling.mvp.home.personalcenter;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.VersionInfo;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseFragment;
import com.aofan.cardismantling.mvp.colleagues.ActivityMyColleagues;
import com.aofan.cardismantling.mvp.modifypass.ActivityModifyPass;
import com.aofan.cardismantling.mvp.personalsetting.ActivityPersonalSetting;
import com.aofan.cardismantling.util.AutoUpdate;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.view.RxView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/22.
 */

public class FragmentPersonalCenter extends BaseFragment implements PersonalCenterContract.View {

    public static final String ARGUMENT = "argument";

    public String mArgument;

    public static FragmentPersonalCenter getInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        FragmentPersonalCenter fragment = new FragmentPersonalCenter();
        fragment.setArguments(bundle);
        return fragment;
    }

    PersonalCenterContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mArgument = bundle.getString(ARGUMENT);
        }
        mPresenter = new PersonalCenterPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    private ImageView ivHeadpic;
    private TextView tvUsername;
    private TextView tvToColleages;
    private TextView tvToSetting;
    private TextView tvToVersionUpdate;
    private TextView tvToModifyPass;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_personal_center, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleWithRightTvOrIv(view, null, null, "设置", false, null, null);
        initData();
        assignView(view);
        initView();
    }

    //默认的是isFirstVisibles true ,刚进来的时候分两种情况
    //1.如果切过来的时候 setVisibleHint在OnCreate之前调用，那isFirstVisible为true，这个时候不进行任何操作；
    //等方法执行到onActivityCreated的时候，在执行一次setUserVisibleHint 这个时候把默认的是isFirstVisibles 设为false
    //然后这个时候调用setUserVISIBLEhINT为true  去获取数据
    //2.如果是后面再次切过来，那isFirstVisible已经为false，那就可以直接通过setUserVisibleHint获取数据了

    //代表这个界面是不是第一次被使用
    boolean isFirstUsed = true;
    boolean isGetData = false;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //fragment 可见的情况下 进行加载数据。不可见则不加载
        if (getUserVisibleHint() == true) {
            if (isFirstUsed == true) {
                isFirstUsed = false;
                setUserVisibleHint(true);
            }
            //不可见，说明fragment已经初始话完成,就直接把isFirstVisible改为false 再次切过来的时候就直接可以加载数据了
        } else {
            isFirstUsed = false;
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // LogUtil.e("CartVisible");
        if (getUserVisibleHint()) {
            LogUtil.e("fragVisible");
            //如果不是firstvisible才去进行下面的操作  因为第一次很可能出现各种问题
            if (isFirstUsed == false) {

                if (isGetData == false) {

                }


            }
        } else {

        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void assignView(View view) {
        ivHeadpic = (ImageView) view.findViewById(R.id.iv_headpic);
        tvUsername = (TextView) view.findViewById(R.id.tv_username);
        tvToColleages = (TextView) view.findViewById(R.id.tv_to_colleages);
        tvToSetting = (TextView) view.findViewById(R.id.tv_to_setting);
        tvToVersionUpdate = (TextView) view.findViewById(R.id.tv_to_version_update);
        tvToModifyPass = (TextView) view.findViewById(R.id.tv_to_modify_pass);
    }

    @Override
    public void initView() {

        tvUsername.setText((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_NAME, ""));

        RxView.clicks(tvToModifyPass)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent toModifyPass = new Intent(getActivity(), ActivityModifyPass.class);
                        startActivity(toModifyPass);
                    }
                });

        RxView.clicks(tvToVersionUpdate)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        requestPermission();
                    }
                });

        RxView.clicks(tvToSetting)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent toPersonSetting = new Intent(getActivity(), ActivityPersonalSetting.class);
                        startActivity(toPersonSetting);
                    }
                });

        RxView.clicks(tvToColleages)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent toColleagues = new Intent(getActivity(), ActivityMyColleagues.class);
                        startActivity(toColleagues);
                    }
                });

    }

    //请求权限
    private void requestPermission() {
        Dexter.checkPermission(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                getVersionInfo();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                ToastUtil.showShort(getActivity(), "无法获取本地存储权限，无法存储新版本安装文件");
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void getData() {

    }

    @Override
    public void getVersionInfo() {
        mPresenter.getVersionInfo();
    }

    @Override
    public void onGetVersionInfoSuccess(VersionInfo versionInfo) {
        AutoUpdate autoUpdate = new AutoUpdate();
        autoUpdate.doupdate(getActivity(), versionInfo, true);
    }

    @Override
    public void onGetVersionInfoError(String tip) {
        ToastUtil.showShort(getActivity(), tip);
    }


    @Override
    public void showLoading() {
        showProgressDialog(false, null);
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }

    @Override
    public void setPresenter(PersonalCenterContract.Presenter presenter) {

    }
}
