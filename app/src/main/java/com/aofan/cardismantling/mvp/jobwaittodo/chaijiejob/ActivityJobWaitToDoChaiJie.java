package com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForJobWaitToDoChaiJie;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.JobWaitToDoChaiJieTaskItem;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob.detail.jingchai.ActivityWaitToDoChaiJieJobDetailForJingChai;
import com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob.detail.cuchai.ActivityWaitToDoChaiJieJobDetailForCuChai;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 待办事项（拆解任务）
 * Created by Administrator on 2016/11/23.
 */

public class ActivityJobWaitToDoChaiJie extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, JobWaitToDoChaiJieContract.View {


    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;


    List<JobWaitToDoChaiJieTaskItem> mJobChaiJieList = new ArrayList<>();
    ListAdapterForJobWaitToDoChaiJie mAdapter;


    boolean isRefresh = false;
    boolean canLoad = false;
    Integer mPageIndex;

    JobWaitToDoChaiJieContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_refresh_lv);
        initTitleWithRightTvOrIv(null, null, "待拆解任务", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobWaitToDoChaiJiePresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getOriginalList();
    }

    @Override
    public void initData() {
       /* mJobChaiJieList.add(new Object());
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

        mAdapter = new ListAdapterForJobWaitToDoChaiJie(mJobChaiJieList, this);
        originalLv.setAdapter(mAdapter);
        originalLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (TextUtils.isEmpty(mJobChaiJieList.get(i-1).getPartnamelist()) == false)
                {
                    Intent toChaiJieLingJianJingChai = new Intent(ActivityJobWaitToDoChaiJie.this, ActivityWaitToDoChaiJieJobDetailForJingChai.class);
                    toChaiJieLingJianJingChai.putExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID, mJobChaiJieList.get(i - 1).getOid());
                    toChaiJieLingJianJingChai.putExtra(IntentKey.INTENT_KEY_CAR_NUM, mJobChaiJieList.get(i - 1).getVehiclenumber());
                    startActivity(toChaiJieLingJianJingChai);
                }else {
                    Intent toChaiJieLingJianCuChai = new Intent(ActivityJobWaitToDoChaiJie.this, ActivityWaitToDoChaiJieJobDetailForCuChai.class);
                    toChaiJieLingJianCuChai.putExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID, mJobChaiJieList.get(i - 1).getOid());
                    toChaiJieLingJianCuChai.putExtra(IntentKey.INTENT_KEY_CAR_NUM, mJobChaiJieList.get(i - 1).getVehiclenumber());
                    toChaiJieLingJianCuChai.putExtra(IntentKey.INTENT_KEY_CAR_CHAIJIE_TYPE, Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI);
                    startActivity(toChaiJieLingJianCuChai);

                }


            }
        });

    }


    private void getOriginalList() {
        mPageIndex = 1;
        isRefresh = true;
        getWaitToDoChaiJieJob();
    }

    private void getMoreList() {
        if (canLoad) {
            mPageIndex++;
            getWaitToDoChaiJieJob();
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
    public void getWaitToDoChaiJieJob() {
        mPresenter.getWaitToDoChaiJieJob((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""), "待拆解任务", mPageIndex.toString(), Config.DEFAULT_PAGE_SIZE.toString());
    }

    @Override
    public void showWaitToDoChaiJieJob(List<JobWaitToDoChaiJieTaskItem> chaiJieTaskList) {
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
    public void setPresenter(JobWaitToDoChaiJieContract.Presenter presenter) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        if(isHidden)
        {
            getOriginalList();
            isHidden = false;
        }
    }
}
