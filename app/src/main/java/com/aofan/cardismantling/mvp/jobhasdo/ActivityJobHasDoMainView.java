package com.aofan.cardismantling.mvp.jobhasdo;

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
import com.aofan.cardismantling.mvp.jobhasdo.chaijiejob.ActivityJobHasDoChaiJie;
import com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob.ActivityJobHasDoPaiGongDanListView;
import com.aofan.cardismantling.mvp.jobwaittodo.ActivityJobWaitToDoMainView;
import com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob.ActivityJobWaitToDoChaiJie;
import com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob.ActivityJobWaitToDoPaiGongDan;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxAdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ActivityJobHasDoMainView extends BaseActivity implements JobHasDoMainViewContract.View {

    private ListView lvHasDoJobKind;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;

    List<UserToDoJobKind> mUserToDoJobKindList = new ArrayList<>();
    ListAdapterForUserToDoJobKind mAdapter;

    JobHasDoMainViewContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_has_do_mainview);
        initTitleWithRightTvOrIv(null, null, "已完成事项", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobHasDoMainViewPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getHasDoJobKind();
    }

    @Override
    public void initData() {

    }

    @Override
    public void assignView() {
        lvHasDoJobKind = (ListView) findViewById(R.id.lv_hasdo_job_kind);
        emptylayout = (RelativeLayout) findViewById(R.id.emptylayout);
        emptytiptv = (TextView) findViewById(R.id.emptytiptv);
       /* tvHasdoChaijieTask = (TextView) findViewById(R.id.tv_hasdo_chaijie_task);
        tvHasEnsurePaigongdan = (TextView) findViewById(R.id.tv_has_ensure_paigongdan);*/
    }

    @Override
    public void initView() {

        mAdapter = new ListAdapterForUserToDoJobKind(mUserToDoJobKindList, this);
        lvHasDoJobKind.setAdapter(mAdapter);

        RxAdapterView.itemClicks(lvHasDoJobKind)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer position) {
                        if (mUserToDoJobKindList.get(position).getName().equals("已完成拆解任务")) {
                            Intent toWaitChaiJieTaskList = new Intent(ActivityJobHasDoMainView.this, ActivityJobHasDoChaiJie.class);
                            startActivity(toWaitChaiJieTaskList);

                            /*Intent toWaitChaiJieTaskList = new Intent(ActivityJobWaitToDoMainView.this, ActivityJobWaitToDoChaiJie.class);
                            startActivity(toWaitChaiJieTaskList);
                        */
                        } else if (mUserToDoJobKindList.get(position).getName().equals("已确认派工单")) {

                            Intent toHasEnsurePaiGongDanList = new Intent(ActivityJobHasDoMainView.this, ActivityJobHasDoPaiGongDanListView.class);
                            startActivity(toHasEnsurePaiGongDanList);

                            /*Intent toWaitEnsurePaiGongDanList = new Intent(ActivityJobWaitToDoMainView.this, ActivityJobWaitToDoPaiGongDan.class);
                            startActivity(toWaitEnsurePaiGongDanList);*/
                        }
                    }
                });

        /*RxView.clicks(tvHasdoChaijieTask)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent toWaitChaiJieTaskList = new Intent(ActivityJobHasDoMainView.this, ActivityJobHasDoChaiJie.class);
                        startActivity(toWaitChaiJieTaskList);
                    }
                });

        RxView.clicks(tvHasEnsurePaigongdan)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent toWaitEnsurePaiGongDanList = new Intent(ActivityJobHasDoMainView.this, ActivityJobHasDoPaiGongDanListView.class);
                        startActivity(toWaitEnsurePaiGongDanList);
                    }
                });*/
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getHasDoJobKind() {
        mPresenter.getHasDoJobKind((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""));
    }

    @Override
    public void onGetHasDoJobKindSuccess(List<UserToDoJobKind> userToDoJobKindList) {
        mUserToDoJobKindList.clear();
        if (CommonUtil.isListNullOrEmpty(userToDoJobKindList) == false) {

            mUserToDoJobKindList.addAll(userToDoJobKindList);
        } else {
            lvHasDoJobKind.setEmptyView(emptylayout);
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetHasDoJobKindError(String tip) {
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
    public void setPresenter(JobHasDoMainViewContract.Presenter presenter) {

    }
}
