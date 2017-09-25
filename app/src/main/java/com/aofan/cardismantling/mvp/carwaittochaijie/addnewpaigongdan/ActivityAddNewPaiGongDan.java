package com.aofan.cardismantling.mvp.carwaittochaijie.addnewpaigongdan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.CarOfPaiGongDan;
import com.aofan.cardismantling.bean.ChaiJieWorkerInfo;
import com.aofan.cardismantling.bean.LingJIanOfCanChooseChaiJie;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.RequestAndResultCode;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijielingjian.ActivityChooseChaiJieLingJianNew;
import com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijieworker.ActivityChooseChaiJieWorker;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 新增派工单
 * Created by Administrator on 2016/11/21.
 */

public class ActivityAddNewPaiGongDan extends BaseActivity implements AddPaiGongDanContract.View {

    private TextView tvSubmit;
    private ImageView ivCarBrandPic;
    private TextView tvCarNum;
    private TextView tvChooseLingjian;
    private TextView tvChoosedLingjian;
    private EditText etChaijieRequest;
    private TextView tvChooseChaijieWorker;
    private TextView tvChoosedChaijieWorker;


    CarOfPaiGongDan mCarOfPaiGongDan;

    AddPaiGongDanContract.Presenter mPresenter;

    //选择拆解的零件list
    List<LingJIanOfCanChooseChaiJie> mChoosedChaiJieLingJianList;
    //选择的拆解零件的names str
    String mChaiJieLingJianNamesStr;
    //选择的拆解零件的id和name拼接str
    String mChaiJieLingJianIdsAndNamesStr;


    //选择的拆解worker
    List<ChaiJieWorkerInfo> mChoosedChaiJieWorkerList;
    //选择的拆解工人的names str
    String mChaiJieWorkerNamesStr;
    //选择的拆解工人的id和name的str
    String mChaiJieWorkerIdsAndNamesStr;

    //拆解方式
    String mChaiJieWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_paigongdan);
        initTitleWithRightTvOrIv(null, null, "派工单新增", true, null, null);
        initData();
        assignView();
        initView();

        mPresenter = new AddPaiGongDanPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Override
    public void initData() {
        mChaiJieWay = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_CHAIJIE_TYPE);

        mCarOfPaiGongDan = (CarOfPaiGongDan) getIntent().getSerializableExtra(IntentKey.INTENT_KEY_CAR_INFO_OF_PAIGONGDAN);
    }

    @Override
    public void assignView() {
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        ivCarBrandPic = (ImageView) findViewById(R.id.iv_car_brand_pic);
        tvCarNum = (TextView) findViewById(R.id.tv_car_num);
        tvChooseLingjian = (TextView) findViewById(R.id.tv_choose_lingjian);
        tvChoosedLingjian = (TextView) findViewById(R.id.tv_choosed_lingjian);
        etChaijieRequest = (EditText) findViewById(R.id.et_chaijie_request);
        tvChooseChaijieWorker = (TextView) findViewById(R.id.tv_choose_chaijie_worker);
        tvChoosedChaijieWorker = (TextView) findViewById(R.id.tv_choosed_chaijie_worker);
    }

    @Override
    public void initView() {
        tvCarNum.setText(mCarOfPaiGongDan.getVehiclenumber());
        if (TextUtils.isEmpty(mCarOfPaiGongDan.getCaricon()) == false) {
            Picasso.with(this)
                    .load(mCarOfPaiGongDan.getCaricon())
                    .resize(100, 100)
                    .tag(this)
                    .placeholder(R.drawable.ico_car_default_mid)
                    .error(R.drawable.ico_car_default_mid)
                    .into(ivCarBrandPic);
        }

        RxView.clicks(tvSubmit)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        boolean result = false;
                        if (!CommonUtil.isTextViewEmpty(tvChoosedChaijieWorker, "请选择拆解工人") &&
                                !CommonUtil.isTextViewEmpty(tvChoosedLingjian, "请选择要拆解的零件") &&
                                !CommonUtil.isEditTextEmpty(etChaijieRequest, "请输入拆解要求")
                                ) {
                            result = true;
                        }
                        return result;

                    }
                }).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                addPaiGongDan();
            }
        });

        RxView.clicks(tvChooseLingjian)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent toChooseLingJian = new Intent(ActivityAddNewPaiGongDan.this, ActivityChooseChaiJieLingJianNew.class);
                        toChooseLingJian.putExtra(IntentKey.INTENT_KEY_CAR_DID, mCarOfPaiGongDan.getDid());
                        toChooseLingJian.putExtra(IntentKey.INTENT_KEY_CAR_CHAIJIE_TYPE, mChaiJieWay);
                        startActivityForResult(toChooseLingJian, RequestAndResultCode.REQUEST_CODE_TO_CHOOSE_CHAIJIE_LINGJIAN);
                    }
                });

        RxView.clicks(tvChooseChaijieWorker)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent toChooseWorker = new Intent(ActivityAddNewPaiGongDan.this, ActivityChooseChaiJieWorker.class);
                        startActivityForResult(toChooseWorker, RequestAndResultCode.REQUEST_CODE_TO_CHOOSE_CHAIJIE_WORKER);
                    }
                });
    }

    @Override
    public void addPaiGongDan() {
        mPresenter.addPaiGongDan(mChaiJieWay,(String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),
                mCarOfPaiGongDan.getDid(),
                etChaijieRequest.getText().toString(),
                mChaiJieWorkerNamesStr,
                mChaiJieWorkerIdsAndNamesStr,
                mChaiJieLingJianNamesStr,
                mChaiJieLingJianIdsAndNamesStr);
    }

    @Override
    public void showAddPaiGongDanSuccess() {
        ToastUtil.showShort(this, "添加派工单成功");
        finish();
    }

    @Override
    public void showError(String tip) {
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
    public void setPresenter(AddPaiGongDanContract.Presenter presenter) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestAndResultCode.REQUEST_CODE_TO_CHOOSE_CHAIJIE_LINGJIAN) {
            if (resultCode == RequestAndResultCode.RESULT_CODE_TO_CHOOSE_CHAIJIE_LINGJIAN_SUCCESS) {
                mChoosedChaiJieLingJianList = (List<LingJIanOfCanChooseChaiJie>) data.getSerializableExtra(IntentKey.INTENT_KEY_CHOOSED_CHAIJIE_LINGJIAN);
                makeChaiJieLingJianSubmitInfo(mChoosedChaiJieLingJianList);
                tvChoosedLingjian.setText(mChaiJieLingJianNamesStr);
            }


        } else if (requestCode == RequestAndResultCode.REQUEST_CODE_TO_CHOOSE_CHAIJIE_WORKER) {
            if (resultCode == RequestAndResultCode.RESULT_CODE_TO_CHOOSE_CHAIJIE_WORKER_SUCCESS) {
                mChoosedChaiJieWorkerList = (List<ChaiJieWorkerInfo>) data.getSerializableExtra(IntentKey.INTENT_KEY_CHOOSED_CHAIJIE_WORKER);
                LogUtil.e("chooseChaiJieWorkerSize:" + mChoosedChaiJieWorkerList.size());
                makeChaiJieWorkerSubmitInfo(mChoosedChaiJieWorkerList);
                tvChoosedChaijieWorker.setText(mChaiJieWorkerNamesStr);
            }
        }


    }

    //构建拆解的零件的提交信息
    public void makeChaiJieLingJianSubmitInfo(List<LingJIanOfCanChooseChaiJie> chaiJieLingJianList) {
        StringBuilder chaiJieLingJianNameSB = new StringBuilder();
        StringBuilder chaiJieLingJianIdAndNameSB = new StringBuilder();

        for (LingJIanOfCanChooseChaiJie lingJIanOfCanChooseChaiJie : chaiJieLingJianList) {
            chaiJieLingJianNameSB.append(lingJIanOfCanChooseChaiJie.getPartname());
            chaiJieLingJianNameSB.append(",");

            chaiJieLingJianIdAndNameSB.append(lingJIanOfCanChooseChaiJie.getPartid());
            chaiJieLingJianIdAndNameSB.append("^");
            chaiJieLingJianIdAndNameSB.append(lingJIanOfCanChooseChaiJie.getPartname());
            chaiJieLingJianIdAndNameSB.append("^");
            chaiJieLingJianIdAndNameSB.append(lingJIanOfCanChooseChaiJie.getPid());
            chaiJieLingJianIdAndNameSB.append(",");
        }

        mChaiJieLingJianNamesStr = chaiJieLingJianNameSB.substring(0, chaiJieLingJianNameSB.length() - 1);
        mChaiJieLingJianIdsAndNamesStr = chaiJieLingJianIdAndNameSB.substring(0, chaiJieLingJianIdAndNameSB.length() - 1);

    }

    public void makeChaiJieWorkerSubmitInfo(List<ChaiJieWorkerInfo> chaiJieWorkerList) {
        StringBuilder chaiJieWorkerNameSB = new StringBuilder();
        StringBuilder chaiJieWorkerIdAndNameSB = new StringBuilder();

        for (ChaiJieWorkerInfo chaiJieWorker : chaiJieWorkerList) {
            chaiJieWorkerNameSB.append(chaiJieWorker.getUsername());
            chaiJieWorkerNameSB.append(",");

            chaiJieWorkerIdAndNameSB.append(chaiJieWorker.getId());
            chaiJieWorkerIdAndNameSB.append("^");
            chaiJieWorkerIdAndNameSB.append(chaiJieWorker.getUsername());
            chaiJieWorkerIdAndNameSB.append(",");
        }

        mChaiJieWorkerNamesStr = chaiJieWorkerNameSB.substring(0, chaiJieWorkerNameSB.length() - 1);
        mChaiJieWorkerIdsAndNamesStr = chaiJieWorkerIdAndNameSB.substring(0, chaiJieWorkerIdAndNameSB.length() - 1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
