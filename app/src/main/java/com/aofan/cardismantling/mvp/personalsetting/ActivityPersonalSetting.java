package com.aofan.cardismantling.mvp.personalsetting;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.AppManager;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.UserInfo;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.base.common.fragment.DialogFragmentTip;
import com.aofan.cardismantling.mvp.carwaittochaijie.carchaijiepaigongdanlist.ActivityOneCarChaiJiePaiGongDanList;
import com.aofan.cardismantling.mvp.login.ActivityLogin;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.IntentUtil;
import com.aofan.cardismantling.util.SPUtils;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/11/28.
 */


public class ActivityPersonalSetting extends BaseActivity implements PersonalSettingContract.View {


    private CircleImageView headpiciv;
    private TextView tvRealname;
    private TextView tvTelno;
    private TextView existapptv;
    private TextView tvAccount;


    PersonalSettingContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_setting);
        initTitleWithRightTvOrIv(null, null, "个人设置", true, null, null);
        initData();
        assignView();
        initView();

        getPersonalInfo();

    }

    @Override
    public void initData() {
        mPresenter = new PersonalSettingPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Override
    public void assignView() {
        headpiciv = (CircleImageView) findViewById(R.id.headpiciv);
        tvRealname = (TextView) findViewById(R.id.tv_realname);
        tvTelno = (TextView) findViewById(R.id.tv_telno);
        existapptv = (TextView) findViewById(R.id.existapptv);
        tvAccount = (TextView) findViewById(R.id.tv_account);

    }

    @Override
    public void initView() {
        RxView.clicks(existapptv)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showExistAppTipDialog();
                    }
                });
    }


    private void showExistAppTipDialog() {
        DialogFragmentTip dialogFragmentTip = DialogFragmentTip.getInstance(Config.ACTION_EXIST_APP);
        dialogFragmentTip.setPositionBtnClickListener(new ExistAppTipDialogPositionBtnClickListener());
        dialogFragmentTip.show(getSupportFragmentManager(), Config.DIALOG_FRAGMENT_TIP);
    }

    @Override
    public void getPersonalInfo() {
        mPresenter.getPersonalInfo((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""));
    }

    @Override
    public void onGetPersonalInfoSuccess(UserInfo userInfo) {

        tvAccount.setText("账号:" +( TextUtils.isEmpty(userInfo.getUsername())==false?userInfo.getUsername():""));
        tvRealname.setText("真实姓名:" +( TextUtils.isEmpty(userInfo.getMyname())==false?userInfo.getMyname():""));
        tvTelno.setText("联系号码:" + ( TextUtils.isEmpty(userInfo.getTel())==false?userInfo.getTel():"暂无"));
    }

    @Override
    public void onGetPersonalInfoError(String tip) {
        ToastUtil.showShort(this, tip);
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
    public void setPresenter(PersonalSettingContract.Presenter presenter) {

    }


    class ExistAppTipDialogPositionBtnClickListener implements DialogFragmentTip.TipDialogPositionBtnClickListener {

        @Override
        public void onPositionBtnClick(String dealType) {
            if (dealType.equals(Config.ACTION_EXIST_APP)) {
                SPUtils.clear(ActivityPersonalSetting.this);
                AppManager.getAppManager().finishAllActivity();
                IntentUtil.startNewActivity(ActivityPersonalSetting.this, ActivityLogin.class);
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
