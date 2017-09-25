package com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob.detail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.grid.GridAdapterForJingChaiLingJianPic;
import com.aofan.cardismantling.adapter.list.ListAdapterForChaiJieLingJianSubmitInfo;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.ChaiJieDetailInfo;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.picshow.ActivityPicShow;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.aofan.cardismantling.widget.ListViewForScrollView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class ActivityHasDoPaiGongDanDetail extends BaseActivity implements JobHasDoPaiGongDanDetailContract.View {


    private TextView tvChaijieRequest;
    private ListViewForScrollView lv;
    private TextView tvChaijieWorker;
    private TextView tvFinishedChaijieTime;
    private TextView tvEnsureChaijieWorker;
    private TextView tvEnsureChaijieTime;
    private TextView tvCarNum;


    //拆解详情信息
    ChaiJieDetailInfo mChaiJieDetailInfo;


    //拆解零件信息的adapter
    ListAdapterForChaiJieLingJianSubmitInfo mChaiJieLingJianInfoAdapter;
    //待拆解的零件信息list
    List<ChaiJieDetailInfo.PartlistBean> mChaiJieLingJianInfoList = new ArrayList<>();


    String mOId;
    String mCarNum;

    JobHasDoPaiGongDanDetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_hasdo_chaijie_detail);
        initTitleWithRightTvOrIv(null, null, "已确认派工单", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobHasDoPaiGongDanDetailPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getJobHasDoPaiGongDanDetail();
    }

    @Override
    public void initData() {
        mOId = getIntent().getStringExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID);
        mCarNum = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_NUM);
    }

    @Override
    public void assignView() {
        tvChaijieRequest = (TextView) findViewById(R.id.tv_chaijie_request);
        lv = (ListViewForScrollView) findViewById(R.id.lv);
        tvChaijieWorker = (TextView) findViewById(R.id.tv_chaijie_worker);
        tvFinishedChaijieTime = (TextView) findViewById(R.id.tv_finished_chaijie_time);
        tvEnsureChaijieWorker = (TextView) findViewById(R.id.tv_ensure_chaijie_worker);
        tvEnsureChaijieTime = (TextView) findViewById(R.id.tv_ensure_chaijie_time);
        tvCarNum = (TextView) findViewById(R.id.tv_car_num);

    }

    @Override
    public void initView() {
        tvEnsureChaijieWorker.setVisibility(View.VISIBLE);
        tvEnsureChaijieTime.setVisibility(View.GONE);
        tvCarNum.setText("车牌号:"+mCarNum);
        initAdapter();
    }

    private void initAdapter() {
        mChaiJieLingJianInfoAdapter = new ListAdapterForChaiJieLingJianSubmitInfo(mChaiJieLingJianInfoList, this);
        mChaiJieLingJianInfoAdapter.setLingJianPicClickListener(new LingJianPicClickListenerImpl());

        lv.setAdapter(mChaiJieLingJianInfoAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getJobHasDoPaiGongDanDetail() {
        mPresenter.getJobHasDoPaiGongDanDetail(mOId);
    }

    @Override
    public void onGetJobHasDoPaiGongDanDetailSuccess(ChaiJieDetailInfo chaiJieDetail) {
        mChaiJieDetailInfo = chaiJieDetail;

        fillChaiJieDetailInfoToView(chaiJieDetail);

        mChaiJieLingJianInfoList.clear();
        mChaiJieLingJianInfoList.addAll(mChaiJieDetailInfo.getPartlist());

        initAdapter();
    }

    private void fillChaiJieDetailInfoToView(ChaiJieDetailInfo chaiJieDetail) {

        tvChaijieRequest.setText(chaiJieDetail.getRequirement());
        tvChaijieWorker.setText("拆解人:" + chaiJieDetail.getDispatchperson());
        tvFinishedChaijieTime.setText("完成拆解时间:" + chaiJieDetail.getComletetime());
        tvEnsureChaijieWorker.setText("确认拆解人:" + chaiJieDetail.getSurecompletepersonname());
        tvEnsureChaijieTime.setText("确认拆解时间:" + chaiJieDetail.getSurecompetetime());
    }

    @Override
    public void onGetJobHasDoPaiGongDanDetailError(String tip) {
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
    public void setPresenter(JobHasDoPaiGongDanDetailContract.Presenter presenter) {

    }


    private class LingJianPicClickListenerImpl implements GridAdapterForJingChaiLingJianPic.GridPicClickListener {

        @Override
        public void onGridPicItemClick(int position, List<ChaiJieDetailInfo.PartlistBean.ImgattachlistBean> imageAttachList) {

            List<ChaiJieDetailInfo.PartlistBean.ImgattachlistBean> newImgAttachList = imageAttachList;

            List<String> picUrls = new ArrayList<>();

            for (ChaiJieDetailInfo.PartlistBean.ImgattachlistBean imgattachlistBean : newImgAttachList) {
                if (TextUtils.isEmpty(imgattachlistBean.getPath()) == false) {
                    picUrls.add(Config.URL_TO_LOAD_PIC_IN_USE + imgattachlistBean.getPath());
                } else {
                    picUrls.add(imgattachlistBean.getLoaclPicPath());
                }
            }

            Intent toShowPics = new Intent(ActivityHasDoPaiGongDanDetail.this, ActivityPicShow.class);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION, position);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_URLS, (Serializable) picUrls);
            //
            startActivity(toShowPics);

        }
    }
}
