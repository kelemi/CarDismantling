package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForJobWaitWeiChuCar;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.JobWaitWeiChuItem;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.addcar.ActivityAddWeiChuCar;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.detail.ActivityWeiChuDetail;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityToWaitWeiChuCar extends BaseActivity implements JobWaitWeiChuContract.View, PullToRefreshBase.OnRefreshListener2 {


    public static final int REQUEST_TO_DETAIL = 0x01;
    private PullToRefreshListView lvWeichuCars;
    private ImageView ivRight;
    private RelativeLayout noticeEmpty;

    private JobWaitWeiChuContract.Presenter presenter;

    private List<JobWaitWeiChuItem> jobWaitWeiChuItemList = new ArrayList<>();
    public static final int REQUEST_ADD_CAR = 0x12;
    private String userId;
    private int pageIndex;
    private ListAdapterForJobWaitWeiChuCar listAdapterForJobWaitWeiChuCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_wait_wei_chu_car);
        initTitleWithRightTvOrIv(null, null, "未出手续车", true, null, R.drawable.ico_add_white);
        initData();
        initView();
        assignView();

        pageIndex = 1;
        //获取列表
        presenter.getUnSignCarList(userId, Config.DEFAULT_PAGE_SIZE.toString(), pageIndex + "");
    }

    @Override
    public void initData() {

        userId = (String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, "");
        presenter = new JonWaitWeiChuPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Override
    public void assignView() {
        //添加车辆
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAddCar = new Intent(ActivityToWaitWeiChuCar.this, ActivityAddWeiChuCar.class);
                startActivityForResult(toAddCar, REQUEST_ADD_CAR);
            }
        });

        //点击列表进行处理
        lvWeichuCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toDetail = new Intent(ActivityToWaitWeiChuCar.this, ActivityWeiChuDetail.class);

                toDetail.putExtra("did", jobWaitWeiChuItemList.get(position - 1).getDid());
                toDetail.putExtra("baseid", jobWaitWeiChuItemList.get(position - 1).getBaseid());
                toDetail.putExtra("tstype", jobWaitWeiChuItemList.get(position - 1).getType());
                toDetail.putExtra("carbrand", jobWaitWeiChuItemList.get(position - 1).getVehiclenumber());
                startActivityForResult(toDetail, REQUEST_TO_DETAIL);
            }
        });

    }

    @Override
    public void initView() {

        ivRight = (ImageView) findViewById(R.id.iv_right);
        lvWeichuCars = (PullToRefreshListView) findViewById(R.id.lv_weichu_cars);
        noticeEmpty = (RelativeLayout) findViewById(R.id.notice_empty);

        listAdapterForJobWaitWeiChuCar = new ListAdapterForJobWaitWeiChuCar(jobWaitWeiChuItemList, this);
        lvWeichuCars.setEmptyView(noticeEmpty);
        lvWeichuCars.setAdapter(listAdapterForJobWaitWeiChuCar);
        lvWeichuCars.setMode(PullToRefreshBase.Mode.BOTH);
        lvWeichuCars.setOnRefreshListener(this);
    }


    @Override
    public void getUnSignCarListFail(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUnSignCarListSuccess(List<JobWaitWeiChuItem> mJobWaitWeiChus) {
        lvWeichuCars.onRefreshComplete();
        if (pageIndex == 1) {
            jobWaitWeiChuItemList.clear();
        }
        //表明还有数据
        if (mJobWaitWeiChus.size() > 0) {
            if (mJobWaitWeiChus.size() >= Config.DEFAULT_PAGE_SIZE) {
                lvWeichuCars.setMode(PullToRefreshBase.Mode.BOTH);
            } else {
                lvWeichuCars.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            jobWaitWeiChuItemList.addAll(mJobWaitWeiChus);
        } else {
            lvWeichuCars.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            if (jobWaitWeiChuItemList.size() == 0) {
                lvWeichuCars.setEmptyView(noticeEmpty);
            } else {
                Toast.makeText(this, "已加载全部数据", Toast.LENGTH_SHORT).show();
            }
        }
        //this.businessDetailList = newsListModelList;
        //提醒adapter去更新
        listAdapterForJobWaitWeiChuCar.notifyDataSetChanged();

    }

    @Override
    public void netError(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

        showProgressDialog(true, null);
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }

    @Override
    public void setPresenter(JobWaitWeiChuContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
        pageIndex = 1;
        presenter.getUnSignCarList(userId, Config.DEFAULT_PAGE_SIZE.toString(), pageIndex + "");
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
        pageIndex++;
        presenter.getUnSignCarList(userId, Config.DEFAULT_PAGE_SIZE.toString(), pageIndex + "");
    }

    //返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        //成功添加之后返回
        if (requestCode == REQUEST_ADD_CAR && resultCode == RESULT_OK) {
            pageIndex = 1;
            presenter.getUnSignCarList(userId, Config.DEFAULT_PAGE_SIZE.toString(), pageIndex + "");
        }

        if (requestCode == REQUEST_TO_DETAIL && resultCode == RESULT_OK) {
            pageIndex = 1;
            presenter.getUnSignCarList(userId, Config.DEFAULT_PAGE_SIZE.toString(), pageIndex + "");
        }
    }
}
