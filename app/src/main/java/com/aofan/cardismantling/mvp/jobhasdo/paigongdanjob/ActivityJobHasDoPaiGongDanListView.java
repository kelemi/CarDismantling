package com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForJobHasDoPaiGongDanEnsure;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.HasDoPaiGongDanListItem;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob.detail.ActivityHasDoPaiGongDanDetail;
import com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob.detail.cuchai.ActivityHasEnsurePaiGongDanDetailForCuChai;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 已办事项已经确认的派工单
 * Created by Administrator on 2016/11/23.
 */

public class ActivityJobHasDoPaiGongDanListView extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, JobHasDoPaiGongDanContract.View {


    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;


    List<HasDoPaiGongDanListItem> mJobList = new ArrayList<>();
    ListAdapterForJobHasDoPaiGongDanEnsure mAdapter;

    boolean isRefresh, canLoad;
    Integer mPageIndex;

    JobHasDoPaiGongDanContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_refresh_lv);
        initTitleWithRightTvOrIv(null, null, "已确认派工单", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobHasDoPaiGongDanPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
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

        mAdapter = new ListAdapterForJobHasDoPaiGongDanEnsure(mJobList, this);
        originalLv.setAdapter(mAdapter);
        originalLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (TextUtils.isEmpty(mJobList.get(i - 1).getPartnamelist()) == false)
                {
                    Intent toHasPaiGongDanDetail = new Intent(ActivityJobHasDoPaiGongDanListView.this, ActivityHasDoPaiGongDanDetail.class);
                    toHasPaiGongDanDetail.putExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID, mJobList.get(i - 1).getOid());
                    toHasPaiGongDanDetail.putExtra(IntentKey.INTENT_KEY_CAR_NUM, mJobList.get(i - 1).getVehiclenumber());
                    startActivity(toHasPaiGongDanDetail);
                }else {
                    Intent toHasPaiGongDanDetail = new Intent(ActivityJobHasDoPaiGongDanListView.this, ActivityHasEnsurePaiGongDanDetailForCuChai.class);
                    toHasPaiGongDanDetail.putExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID, mJobList.get(i - 1).getOid());
                    toHasPaiGongDanDetail.putExtra(IntentKey.INTENT_KEY_CAR_NUM, mJobList.get(i - 1).getVehiclenumber());
                    startActivity(toHasPaiGongDanDetail);
                }


            }
        });
    }

    private void getOriginalList() {
        isRefresh = true;
        mPageIndex = 1;
        getHasDoEnsurePaiGongDanList();
    }


    private void getMoreList() {
        if (canLoad) {
            mPageIndex++;
            getHasDoEnsurePaiGongDanList();
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getHasDoEnsurePaiGongDanList() {
        mPresenter.getHasDoEnsurePaiGongDanList((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""), "已确认派工单", mPageIndex.toString(), Config.DEFAULT_PAGE_SIZE.toString());

    }

    @Override
    public void showHasDoEnsurePaiGongDanList(List<HasDoPaiGongDanListItem> hasDoPaiGongDanList) {
        lv.onRefreshComplete();

        if (isRefresh) {
            mJobList.clear();
            isRefresh = false;
        }
        if (hasDoPaiGongDanList.size() > 0) {
            if (hasDoPaiGongDanList.size() >= Config.DEFAULT_PAGE_SIZE) {
                lv.setMode(PullToRefreshBase.Mode.BOTH);
                canLoad = true;
            } else {
                canLoad = false;
                lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            mJobList.addAll(hasDoPaiGongDanList);

        } else {
            canLoad = false;
            lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            if (mJobList.size() == 0) {
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
            mJobList.clear();
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
    public void setPresenter(JobHasDoPaiGongDanContract.Presenter presenter) {

    }
}


