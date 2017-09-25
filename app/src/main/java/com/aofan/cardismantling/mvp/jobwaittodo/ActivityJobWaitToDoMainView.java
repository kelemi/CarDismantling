package com.aofan.cardismantling.mvp.jobwaittodo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForUserToDoJobKind;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.UserToDoJobKind;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob.ActivityJobWaitToDoChaiJie;
import com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob.ActivityJobWaitToDoPaiGongDan;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.ActivityToWaitWeiChuCar;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.widget.RxAdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * 待办事项主界面
 * Created by Administrator on 2016/11/28.
 */

public class ActivityJobWaitToDoMainView extends BaseActivity implements JobWaitToDoMainViewContract.View {

    private ListView lvWaittodoJobKind;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;


    List<UserToDoJobKind> mUserToDoJobKindList = new ArrayList<>();
    ListAdapterForUserToDoJobKind mAdapter;

    JobWaitToDoMainViewContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_waittodo_mainview);
        initTitleWithRightTvOrIv(null, null, "待办事项", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobWaitToDoMainViewPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getWaitToDoJobKind();
    }

    @Override
    public void initData() {

    }

    @Override
    public void assignView() {
        lvWaittodoJobKind = (ListView) findViewById(R.id.lv_waittodo_job_kind);
        emptylayout = (RelativeLayout) findViewById(R.id.emptylayout);
        emptytiptv = (TextView) findViewById(R.id.emptytiptv);
    }

    @Override
    public void initView() {

        mAdapter = new ListAdapterForUserToDoJobKind(mUserToDoJobKindList, this);
        lvWaittodoJobKind.setAdapter(mAdapter);

        RxAdapterView.itemClicks(lvWaittodoJobKind)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer position) {
                        if (mUserToDoJobKindList.get(position).getName().equals("待拆解任务")) {
                            Intent toWaitChaiJieTaskList = new Intent(ActivityJobWaitToDoMainView.this, ActivityJobWaitToDoChaiJie.class);
                            startActivity(toWaitChaiJieTaskList);
                        } else if (mUserToDoJobKindList.get(position).getName().equals("待确认派工单")) {
                            Intent toWaitEnsurePaiGongDanList = new Intent(ActivityJobWaitToDoMainView.this, ActivityJobWaitToDoPaiGongDan.class);
                            startActivity(toWaitEnsurePaiGongDanList);
                        } else if (mUserToDoJobKindList.get(position).getName().equals("未出手续车辆")) {
                            Intent toNoDispatchCarList = new Intent(ActivityJobWaitToDoMainView.this, ActivityToWaitWeiChuCar.class);
                            startActivity(toNoDispatchCarList);
                        }
                    }
                });

    }

    @Override
    public void getWaitToDoJobKind() {
        mPresenter.getUserWaitToDoJobKind((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""));
    }

    @Override
    public void onGetWaitToDoJobKindSuccess(List<UserToDoJobKind> userToDoJobKindList) {
        mUserToDoJobKindList.clear();
        if (CommonUtil.isListNullOrEmpty(userToDoJobKindList) == false) {

            mUserToDoJobKindList.addAll(userToDoJobKindList);
        } else {
            lvWaittodoJobKind.setEmptyView(emptylayout);
        }

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onGetWaitToDoJobKindError(String tip) {
        ToastUtil.showShort(this, tip);
        lvWaittodoJobKind.setEmptyView(emptylayout);
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
    public void setPresenter(JobWaitToDoMainViewContract.Presenter presenter) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
