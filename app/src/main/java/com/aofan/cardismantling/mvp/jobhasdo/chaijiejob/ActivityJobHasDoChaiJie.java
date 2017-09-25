package com.aofan.cardismantling.mvp.jobhasdo.chaijiejob;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForJobHasDoChaiJie;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.HasDoChaiJieTaskItem;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.jobhasdo.chaijiejob.detail.cuchai.ActivityJobHasDoChaiJieDetailForCuChai;
import com.aofan.cardismantling.mvp.jobhasdo.chaijiejob.detail.jingchai.ActivityJobHasDoChaiJieDetail;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class ActivityJobHasDoChaiJie extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, JobHasDoChaiJieContract.View {


    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;

    boolean isRefresh, canLoad;
    Integer mPageIndex;


    List<HasDoChaiJieTaskItem> mJobChaiJieList = new ArrayList<>();
    ListAdapterForJobHasDoChaiJie mAdapter;

    JobHasDoChaiJieContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_refresh_lv);
        initTitleWithRightTvOrIv(null, null, "已完成拆解任务", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobHasDoChaiJiePresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getOriginalList();
    }

    @Override
    public void initData() {
      /*  mJobChaiJieList.add(new Object());
        mJobChaiJieList.add(new Object());
        mJobChaiJieList.add(new Object());
        mJobChaiJieList.add(new Object());
        mJobChaiJieList.add(new Object());*/
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

        mAdapter = new ListAdapterForJobHasDoChaiJie(mJobChaiJieList, this);
        originalLv.setAdapter(mAdapter);
        originalLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (TextUtils.isEmpty(mJobChaiJieList.get(i -1).getPartnamelist()) == false)
                {


                    Intent toHasDoChaiJieDetail = new Intent(ActivityJobHasDoChaiJie.this, ActivityJobHasDoChaiJieDetail.class);
                    toHasDoChaiJieDetail.putExtra(IntentKey.INTENT_KEY_CAR_NUM, mJobChaiJieList.get(i - 1).getVehiclenumber());
                    toHasDoChaiJieDetail.putExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID, mJobChaiJieList.get(i - 1).getOid());
                    startActivity(toHasDoChaiJieDetail);
                }else {
                    Intent toHasDoChaiJieDetail = new Intent(ActivityJobHasDoChaiJie.this, ActivityJobHasDoChaiJieDetailForCuChai.class);
                    toHasDoChaiJieDetail.putExtra(IntentKey.INTENT_KEY_CAR_NUM, mJobChaiJieList.get(i - 1).getVehiclenumber());

                    toHasDoChaiJieDetail.putExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID, mJobChaiJieList.get(i - 1).getOid());
                    startActivity(toHasDoChaiJieDetail);
                }

            }
        });
    }

    private void getOriginalList() {
        isRefresh = true;
        mPageIndex = 1;
        getHasDoChaiJieJob();
    }


    private void getMoreList() {
        if (canLoad) {
            mPageIndex++;
            getHasDoChaiJieJob();
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
    public void getHasDoChaiJieJob() {
        mPresenter.getHasDoChaiJieJob((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""), "已完成拆解任务", mPageIndex.toString(), Config.DEFAULT_PAGE_SIZE.toString());
    }

    @Override
    public void showHasDoChaiJieJob(List<HasDoChaiJieTaskItem> chaiJieTaskList) {
        lv.onRefreshComplete();

        if (isRefresh) {
            mJobChaiJieList.clear();
            isRefresh = false;
        }
        if (chaiJieTaskList.size() > 0) {
            if (chaiJieTaskList.size() >= Config.DEFAULT_PAGE_SIZE) {
                lv.setMode(PullToRefreshBase.Mode.BOTH);
                canLoad = true;
            } else {
                canLoad = false;
                lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            mJobChaiJieList.addAll(chaiJieTaskList);

        } else {
            canLoad = false;
            lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            if (mJobChaiJieList.size() == 0) {
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
            mJobChaiJieList.clear();
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
    public void setPresenter(JobHasDoChaiJieContract.Presenter presenter) {

    }
}
