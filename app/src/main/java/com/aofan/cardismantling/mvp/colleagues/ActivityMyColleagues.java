package com.aofan.cardismantling.mvp.colleagues;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForMyColleagues;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.ColleagueItem;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的同事界面
 * Created by Administrator on 2016/11/30.
 */

public class ActivityMyColleagues extends BaseActivity implements MyColleaguesContract.View, PullToRefreshBase.OnRefreshListener {


    private PullToRefreshListView lv;
    private ListView originalLv;

    private RelativeLayout emptylayout;
    private TextView emptytiptv;

    MyColleaguesContract.Presenter mPresenter;

    ListAdapterForMyColleagues mAdapter;
    List<ColleagueItem> mColleagueItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_refresh_lv);
        initTitleWithRightTvOrIv(null, null, "我的同事", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new MyColleaguesPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getMyColleagues();
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

        mAdapter = new ListAdapterForMyColleagues(mColleagueItemList, this);

        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lv.setOnRefreshListener(this);

        originalLv.setAdapter(mAdapter);
    }

    @Override
    public void getMyColleagues() {
        mPresenter.getMyColleagues((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""));
    }

    @Override
    public void onGetMyColleaguesSuccess(List<ColleagueItem> colleagueItemList) {

        lv.onRefreshComplete();

        mColleagueItemList.clear();

        if (colleagueItemList.size() > 0) {

            mColleagueItemList.addAll(colleagueItemList);

        }

        if (mColleagueItemList.size() == 0) {
            lv.setEmptyView(emptylayout);
        }

        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void onGetMyColleaguesError(String tip) {
        lv.onRefreshComplete();
        ToastUtil.showShort(this, tip);

        mColleagueItemList.clear();
        lv.setEmptyView(emptylayout);

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
    public void setPresenter(MyColleaguesContract.Presenter presenter) {

    }

    @Override
    public void onRefresh(PullToRefreshBase pullToRefreshBase) {
        getMyColleagues();
    }
}
