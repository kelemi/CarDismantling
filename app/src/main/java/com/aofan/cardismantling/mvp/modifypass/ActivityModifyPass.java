package com.aofan.cardismantling.mvp.modifypass;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 修改密码界面
 * Created by Administrator on 2016/11/28.
 */

public class ActivityModifyPass extends BaseActivity implements ModifyPassContract.View {


    private EditText etOldPass;
    private EditText etNewPass;
    private EditText etNewPassAgain;
    private TextView tvSubmit;


    ModifyPassContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pass);
        initTitleWithRightTvOrIv(null, null, "修改密码", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new ModifyPassPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Override
    public void initData() {

    }

    @Override
    public void assignView() {
        etOldPass = (EditText) findViewById(R.id.et_old_pass);
        etNewPass = (EditText) findViewById(R.id.et_new_pass);
        etNewPassAgain = (EditText) findViewById(R.id.et_new_pass_again);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
    }

    @Override
    public void initView() {
        RxView.clicks(tvSubmit)
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        return validateDataUserFul();
                    }
                }).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                modifyPass();
            }
        });
    }


    boolean validateDataUserFul() {

        boolean result = false;
        if (!CommonUtil.isEditTextEmpty(etOldPass, "请输入旧密码")
                && !CommonUtil.isEditTextEmpty(etNewPass, "请输入新密码")
                && !CommonUtil.isEditTextEmpty(etNewPassAgain, "请输入新密码")
                && CommonUtil.isTwoStrSame(etNewPassAgain.getText().toString(), etNewPass.getText().toString(), "新密码必须一致") == true) {
            result = true;
        }

        return result;
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
    public void modifyPass() {
        mPresenter.modifyPass((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),
                etOldPass.getText().toString(), etNewPass.getText().toString());
    }

    @Override
    public void showModifyPassSuccess() {
        ToastUtil.showShort(this, "密码修改成功");
        finish();
    }

    @Override
    public void showModifyPassError(String tip) {
        ToastUtil.showShort(this, tip);
    }

    @Override
    public void setPresenter(ModifyPassContract.Presenter presenter) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
