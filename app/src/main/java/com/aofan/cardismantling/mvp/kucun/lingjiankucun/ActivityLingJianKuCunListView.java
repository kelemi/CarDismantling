package com.aofan.cardismantling.mvp.kucun.lingjiankucun;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForLingJianKuCun;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.LingJianKuCunListItem;
import com.aofan.cardismantling.common.Config;
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
 * 零件库存主界面
 * Created by Administrator on 2016/11/29.
 */

public class ActivityLingJianKuCunListView extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, LingJianKuCunListContract.View {

    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;


    List<LingJianKuCunListItem> mLingJianKuCunList = new ArrayList<>();
    ListAdapterForLingJianKuCun mAdapter;

    Integer mPageIndex;
    boolean isRefresh, canLoad;

    LingJianKuCunListContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_refresh_lv);
        initTitleWithRightTvOrIv(null, null, "零件库存", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new LingJianKuCunListPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getOriginalList();
    }

    @Override
    public void initData() {
       /* mLingJianKuCunList.add(new Object());
        mLingJianKuCunList.add(new Object());
        mLingJianKuCunList.add(new Object());
        mLingJianKuCunList.add(new Object());
        mLingJianKuCunList.add(new Object());
        mLingJianKuCunList.add(new Object());*/

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

        mAdapter = new ListAdapterForLingJianKuCun(mLingJianKuCunList, this);
        originalLv.setAdapter(mAdapter);
    }


    private void getOriginalList() {
        isRefresh = true;
        mPageIndex = 1;
        getLingJianKuCunList();
    }


    private void getMoreList() {
        if (canLoad) {
            mPageIndex++;
            getLingJianKuCunList();
        }
    }

    @Override
    public void getLingJianKuCunList() {
        mPresenter.getLingJianKuCunList((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),
                mPageIndex.toString(),
                Config.DEFAULT_PAGE_SIZE.toString(),"");
    }

    @Override
    public void onGetLingJianKuCunListSuccess(List<LingJianKuCunListItem> lingJianKuCunList) {
        lv.onRefreshComplete();

        if (isRefresh) {
            mLingJianKuCunList.clear();
            isRefresh = false;
        }
        if (lingJianKuCunList.size() > 0) {
            if (lingJianKuCunList.size() >= Config.DEFAULT_PAGE_SIZE) {
                lv.setMode(PullToRefreshBase.Mode.BOTH);
                canLoad = true;
            } else {
                canLoad = false;
                lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            mLingJianKuCunList.addAll(lingJianKuCunList);

        } else {
            canLoad = false;
            lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            if (mLingJianKuCunList.size() == 0) {
                lv.setEmptyView(emptylayout);
            } else {
                ToastUtil.show(this, "已经获取全部数据", 2000);
            }
        }

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onGetLingJianKuCunListError(String tip) {
        lv.onRefreshComplete();
        ToastUtil.showShort(this, tip);
        if (isRefresh) {
            mLingJianKuCunList.clear();
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
    public void setPresenter(LingJianKuCunListContract.Presenter presenter) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
        getOriginalList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
        getMoreList();
    }

}
