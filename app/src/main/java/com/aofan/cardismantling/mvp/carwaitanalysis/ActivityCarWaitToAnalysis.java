package com.aofan.cardismantling.mvp.carwaitanalysis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForCarWaitToAnalysis;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.CarInfo;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.carwaitanalysis.carwaitanalysisdetail.ActivityCarWaitAnalysisDetail;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 待分析车辆列表
 * Created by Administrator on 2016/11/21.
 */

public class ActivityCarWaitToAnalysis extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, WaitToAnalysisCarListContract.View {

    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;


    List<CarInfo> mCarInfoList = new ArrayList<>();
    ListAdapterForCarWaitToAnalysis mAdapter;

    WaitToAnalysisCarListContract.Presenter mPresenter;

    Integer mPageIndex;
    boolean isRefresh;
    boolean canLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_refresh_lv);
        initTitleWithRightTvOrIv(null, null, "汽车分析", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new WaitToAnalysisCarListPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
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

        mAdapter = new ListAdapterForCarWaitToAnalysis(mCarInfoList, this);
        originalLv.setAdapter(mAdapter);
        originalLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent toAnalysisDetail = new Intent(ActivityCarWaitToAnalysis.this, ActivityCarWaitAnalysisDetail.class);
                toAnalysisDetail.putExtra(IntentKey.INTENT_KEY_CAR_INFO,mCarInfoList.get(i-1));
                startActivity(toAnalysisDetail);
            }
        });
    }

    @Override
    public void getWaitToAnalysisCar() {
        mPresenter.getWaitToAnalysisCar((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),"待分析", mPageIndex.toString(), Config.DEFAULT_PAGE_SIZE.toString());
    }

    @Override
    public void showWaitToAnalysisCar(List<CarInfo> carInfoList) {
        lv.onRefreshComplete();

        if (isRefresh) {
            mCarInfoList.clear();
            isRefresh = false;
        }
        if (carInfoList.size() > 0) {
            if (carInfoList.size() >= Config.DEFAULT_PAGE_SIZE) {
                lv.setMode(PullToRefreshBase.Mode.BOTH);
                canLoad = true;
            } else {
                canLoad = false;
                lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            mCarInfoList.addAll(carInfoList);

        } else {
            canLoad = false;
            lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            if (mCarInfoList.size() == 0) {
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
            mCarInfoList.clear();
            lv.setEmptyView(emptylayout);

        }

        isRefresh = false;
        canLoad = false;

        mAdapter.notifyDataSetChanged();
    }

    private void getOriginalList() {
        isRefresh = true;
        mPageIndex = 1;
        getWaitToAnalysisCar();
    }


    private void getMoreList() {
        if (canLoad) {
            mPageIndex++;
            getWaitToAnalysisCar();
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
    public void showLoading() {
        showProgressDialog(false,null);
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }




    @Override
    public void setPresenter(WaitToAnalysisCarListContract.Presenter presenter) {

    }

    boolean isHidden =false;
    @Override
    protected void onPause() {
        super.onPause();
        isHidden = true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isHidden)
        {
            getOriginalList();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
