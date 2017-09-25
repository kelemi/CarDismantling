package com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob.detail.cuchai;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.grid.GridAdapterForCuChaiLingJianPic;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.ChaiJieDetailInfoForCuChai;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.picshow.ActivityPicShow;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.aofan.cardismantling.widget.GridViewForScrollView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ActivityHasEnsurePaiGongDanDetailForCuChai extends BaseActivity implements JobHasEnsurePaiGongDanDetailForCuChaiContract.View {


    private TextView tvCarNum;
    private TextView tvChaijieWorker;
    private TextView tvFinishedChaijieTime;
    private TextView tvEnsureChaijieWorker;
    private TextView tvEnsureChaijieTime;
    private TextView tvChaijieRequest;
    private GridViewForScrollView gvChoosedChaijiePic;


    //拆解详情信息
    ChaiJieDetailInfoForCuChai mChaiJieDetailInfo;


    GridAdapterForCuChaiLingJianPic mCuChaiLingJianPicAdapter;
    //粗拆新选择的图片列表
    List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> mCuChaiNewChoosePicList = new ArrayList<>();



    String mOId;
    String mCarNum;

    JobHasEnsurePaiGongDanDetailForCuChaiContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_hasdo_chaijie_detail_for_cuchai);
        initTitleWithRightTvOrIv(null, null, "已确认派工单", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobHasEnsurePaiGongDanDetailForCuChaiPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getJobHasDoPaiGongDanDetail();
    }

    @Override
    public void initData() {
        mOId = getIntent().getStringExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID);
        mCarNum = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_NUM);
    }

    @Override
    public void assignView() {



        tvChaijieWorker = (TextView) findViewById(R.id.tv_chaijie_worker);
        tvFinishedChaijieTime = (TextView) findViewById(R.id.tv_finished_chaijie_time);
        tvEnsureChaijieWorker = (TextView) findViewById(R.id.tv_ensure_chaijie_worker);
        tvEnsureChaijieTime = (TextView) findViewById(R.id.tv_ensure_chaijie_time);
        tvChaijieRequest = (TextView) findViewById(R.id.tv_chaijie_request);
        tvCarNum = (TextView) findViewById(R.id.tv_car_num);

        gvChoosedChaijiePic = (GridViewForScrollView) findViewById(R.id.gv_choosed_chaijie_pic);

    }

    @Override
    public void initView() {
        tvEnsureChaijieWorker.setVisibility(View.VISIBLE);
        tvEnsureChaijieTime.setVisibility(View.GONE);
        tvCarNum.setText("车牌号:"+mCarNum);
        initAdapter();
    }

    private void initAdapter() {

        mCuChaiLingJianPicAdapter = new GridAdapterForCuChaiLingJianPic(mCuChaiNewChoosePicList,this,true);
        mCuChaiLingJianPicAdapter.setGridPicClickListener(new CuChaiPicClickListenerImpl());
        gvChoosedChaijiePic.setAdapter(mCuChaiLingJianPicAdapter);

       /* mChaiJieLingJianInfoAdapter = new ListAdapterForChaiJieLingJianSubmitInfo(mChaiJieLingJianInfoList, this);
        mChaiJieLingJianInfoAdapter.setLingJianPicClickListener(new ActivityHasDoPaiGongDanDetail.LingJianPicClickListenerImpl());

        lv.setAdapter(mChaiJieLingJianInfoAdapter);*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getJobHasDoPaiGongDanDetail() {
        mPresenter.getJobHasDoPaiGongDanDetail(Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI,mOId);
    }

    @Override
    public void onGetJobHasDoPaiGongDanDetailSuccess(ChaiJieDetailInfoForCuChai chaiJieDetail) {
        mChaiJieDetailInfo = chaiJieDetail;

        fillChaiJieDetailInfoToView(chaiJieDetail);

        mCuChaiNewChoosePicList.clear();
        mCuChaiNewChoosePicList.addAll(chaiJieDetail.getImgattachlist());
        mCuChaiLingJianPicAdapter.notifyDataSetChanged();

        initAdapter();
    }

    private void fillChaiJieDetailInfoToView(ChaiJieDetailInfoForCuChai chaiJieDetail) {

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
    public void setPresenter(JobHasEnsurePaiGongDanDetailForCuChaiContract.Presenter presenter) {

    }


    private class CuChaiPicClickListenerImpl implements GridAdapterForCuChaiLingJianPic.GridPicClickListener {
        @Override
        public void onGridPicItemClick(int position, List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> cuChaiPicList) {


            List<String> picUrls = new ArrayList<>();

            for (ChaiJieDetailInfoForCuChai.ImgattachlistBean picItem : cuChaiPicList) {
                if (TextUtils.isEmpty(picItem.getPath()) == false) {
                    picUrls.add(Config.URL_TO_LOAD_PIC_IN_USE + picItem.getPath());
                } else {
                    picUrls.add(picItem.getLocalPicPath());
                }
            }

            Intent toShowPics = new Intent(ActivityHasEnsurePaiGongDanDetailForCuChai.this, ActivityPicShow.class);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION, position);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_URLS, (Serializable) picUrls);
            //
            startActivity(toShowPics);
        }
    }
}
