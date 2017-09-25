package com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijieworker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForChaiJieWorker;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.ChaiJieWorkerInfo;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.RequestAndResultCode;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.view.RxView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 选择拆解工人界面
 * Created by Administrator on 2016/11/21.
 */

public class ActivityChooseChaiJieWorker extends BaseActivity implements ChooseChaiJieWorkerContract.View {


    private LinearLayout layoutSubmit;
    private TextView tvSubmit;
    private ListView lv;

    List<ChaiJieWorkerInfo> mChaiJieWorkerList = new ArrayList<>();
    List<ChaiJieWorkerInfo> mChoosedChaiJieWorkerList = new ArrayList<>();

    ListAdapterForChaiJieWorker mAdapter;

    ChooseChaiJieWorkerContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_chaijie_worker);
        initTitleWithRightTvOrIv(null, null, "选择拆解工人", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new ChooseChaiJieWorkerPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getChaiJieWorkers();
    }

    @Override
    public void initData() {
     /*   mChaiJieWorkerList.add(new Object());
        mChaiJieWorkerList.add(new Object());
        mChaiJieWorkerList.add(new Object());
        mChaiJieWorkerList.add(new Object());
        mChaiJieWorkerList.add(new Object());
        mChaiJieWorkerList.add(new Object());
        mChaiJieWorkerList.add(new Object());*/
    }

    @Override
    public void assignView() {
        layoutSubmit = (LinearLayout) findViewById(R.id.layout_submit);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        lv = (ListView) findViewById(R.id.lv);

    }

    @Override
    public void initView() {
        mAdapter = new ListAdapterForChaiJieWorker(mChaiJieWorkerList, this);
        mAdapter.setWorkerClickListener(new WorkerClickListenerImpl());
        lv.setAdapter(mAdapter);
        RxView.clicks(tvSubmit)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        return CommonUtil.validateAndToast(validateHasChooseWorker(), "请选择拆解工人");
                    }
                }).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Intent backChaiJieWorker = new Intent();
                backChaiJieWorker.putExtra(IntentKey.INTENT_KEY_CHOOSED_CHAIJIE_WORKER, (Serializable) mChoosedChaiJieWorkerList);
                setResult(RequestAndResultCode.RESULT_CODE_TO_CHOOSE_CHAIJIE_WORKER_SUCCESS, backChaiJieWorker);
                finish();
            }
        });
    }

    private boolean validateHasChooseWorker() {
        boolean result = false;
        for (int i = 0; i < mChaiJieWorkerList.size(); i++) {
            if (mChaiJieWorkerList.get(i).isCheck()) {
                result = true;
                mChoosedChaiJieWorkerList.add(mChaiJieWorkerList.get(i));

            }
        }

        return result;
    }

    @Override
    public void getChaiJieWorkers() {
        mPresenter.getChaiJieWorkers((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""));
    }

    @Override
    public void showChaiJieWorkers(List<ChaiJieWorkerInfo> chaiJieWorkerInfoList) {
        mChaiJieWorkerList.clear();
        mChaiJieWorkerList.addAll(chaiJieWorkerInfoList);
        mAdapter.notifyDataSetChanged();
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
    public void setPresenter(ChooseChaiJieWorkerContract.Presenter presenter) {

    }


    class WorkerClickListenerImpl implements ListAdapterForChaiJieWorker.WorkerClickListener {

        @Override
        public void onWorkerClick(int position) {
            ChaiJieWorkerInfo chaiJieWorkerInfo = mChaiJieWorkerList.get(position);
            if (chaiJieWorkerInfo.isCheck()) {
                chaiJieWorkerInfo.setCheck(false);
            } else {
                chaiJieWorkerInfo.setCheck(true);
            }

            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
