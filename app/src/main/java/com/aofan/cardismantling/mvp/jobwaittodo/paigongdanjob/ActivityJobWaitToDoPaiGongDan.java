package com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForJobWaitToDoPaiGongDanEnsure;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.PaiGongDanOfEnsure;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob.detail.cuchai.ActivityWaitEnsurePaiGongDanDetailForCuChai;
import com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob.detail.jingchai.ActivityWaitEnsurePaiGongDanDetail;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 待办事项派工单（待确认的派出去的工单）
 * Created by Administrator on 2016/11/23.
 */

public class ActivityJobWaitToDoPaiGongDan extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, JobWaitToDoPaiGongDanEnsureListContract.View {


    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;


    List<PaiGongDanOfEnsure> mPaiGongDanList = new ArrayList<>();
    ListAdapterForJobWaitToDoPaiGongDanEnsure mAdapter;


    boolean isRefresh, canLoad;

    Integer mPageIndex;


    JobWaitToDoPaiGongDanEnsureListContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_refresh_lv);
        initTitleWithRightTvOrIv(null, null, "待确认派工单", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobWaitToDoPaiGongDanEnsureListPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getOriginalList();
    }

    @Override
    public void initData() {

    }

    @Override
    public void assignView() {
        lv = (PullToRefreshListView) findViewById(R.id.lv);
        originalLv = lv.getRefreshableView();
        emptylayout = (RelativeLayout) findViewById(R.id.emptylayout);
        emptytiptv = (TextView) findViewById(R.id.emptytiptv);
    }

    @Override
    public void initView() {
        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lv.setOnRefreshListener(this);

        mAdapter = new ListAdapterForJobWaitToDoPaiGongDanEnsure(mPaiGongDanList, this);
        originalLv.setAdapter(mAdapter);
        originalLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if (TextUtils.isEmpty(mPaiGongDanList.get(i-1).getPartnamelist()) == false)
                {

                    Intent toWaitEnsurePaiGongDanDetail = new Intent(ActivityJobWaitToDoPaiGongDan.this, ActivityWaitEnsurePaiGongDanDetail.class);
                    toWaitEnsurePaiGongDanDetail.putExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID, mPaiGongDanList.get(i - 1).getOid());
                    toWaitEnsurePaiGongDanDetail.putExtra(IntentKey.INTENT_KEY_CAR_NUM, mPaiGongDanList.get(i - 1).getVehiclenumber());
                    startActivity(toWaitEnsurePaiGongDanDetail);
                }else {
                    Intent toWaitEnsurePaiGongDanDetail = new Intent(ActivityJobWaitToDoPaiGongDan.this, ActivityWaitEnsurePaiGongDanDetailForCuChai.class);
                    toWaitEnsurePaiGongDanDetail.putExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID, mPaiGongDanList.get(i - 1).getOid());
                    toWaitEnsurePaiGongDanDetail.putExtra(IntentKey.INTENT_KEY_CAR_NUM, mPaiGongDanList.get(i - 1).getVehiclenumber());
                    startActivity(toWaitEnsurePaiGongDanDetail);

                }


            }
        });
    }


    private void getOriginalList() {
        mPageIndex = 1;
        isRefresh = true;
        getWaitToEnsurePaiGongDanInfoList();
    }

    private void getMoreList() {
        if (canLoad) {
            mPageIndex++;
            getWaitToEnsurePaiGongDanInfoList();
        }
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
        getOriginalList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
        getMoreList();
    }


    @Override
    public void getWaitToEnsurePaiGongDanInfoList() {
        mPresenter.getWaitToEnsurePaiGongDanInfoList((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""), "待确认派工单", mPageIndex.toString(), Config.DEFAULT_PAGE_SIZE.toString());

    }

    @Override
    public void showWaitToEnsurePaiGongDanList(List<PaiGongDanOfEnsure> paiGongDanList) {

        lv.onRefreshComplete();

        if (isRefresh) {
            mPaiGongDanList.clear();
            isRefresh = false;
        }
        if (paiGongDanList.size() > 0) {
            if (paiGongDanList.size() >= Config.DEFAULT_PAGE_SIZE) {
                lv.setMode(PullToRefreshBase.Mode.BOTH);
                canLoad = true;
            } else {
                canLoad = false;
                lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            mPaiGongDanList.addAll(paiGongDanList);

        } else {
            canLoad = false;
            lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            if (mPaiGongDanList.size() == 0) {
                lv.setEmptyView(emptylayout);
            } else {
                ToastUtil.show(this, "已经获取全部数据", 2000);
            }
        }

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError(String tip) {
        lv.onRefreshComplete();
        ToastUtil.showShort(this, tip);
        if (isRefresh) {
            mPaiGongDanList.clear();
            lv.setEmptyView(emptylayout);

        }

        isRefresh = false;
        canLoad = false;

        mAdapter.notifyDataSetChanged();
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
    public void setPresenter(JobWaitToDoPaiGongDanEnsureListContract.Presenter presenter) {

    }


    boolean isHidden = false;

    @Override
    protected void onPause() {
        super.onPause();

        isHidden = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isHidden) {
            getOriginalList();
            isHidden = false;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}

