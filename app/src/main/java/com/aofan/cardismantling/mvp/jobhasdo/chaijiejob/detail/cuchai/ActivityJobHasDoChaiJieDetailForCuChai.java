package com.aofan.cardismantling.mvp.jobhasdo.chaijiejob.detail.cuchai;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class ActivityJobHasDoChaiJieDetailForCuChai extends BaseActivity implements JobHasDoChaiJieDetailForCuChaiContract.View {


    private TextView tvCarNum;
    private TextView tvChaijieWorker;
    private TextView tvFinishedChaijieTime;
    private TextView tvEnsureChaijieWorker;
    private TextView tvEnsureChaijieTime;
    private TextView tvChaijieRequest;
    private GridViewForScrollView gvChoosedChaijiePic;




    //拆解详情信息
    ChaiJieDetailInfoForCuChai mChaiJieDetailInfo;


    /*//拆解零件信息的adapter
    ListAdapterForChaiJieLingJianSubmitInfo mChaiJieLingJianInfoAdapter;
    //待拆解的零件信息list
    List<ChaiJieDetailInfo.PartlistBean> mChaiJieLingJianInfoList = new ArrayList<>();*/

    GridAdapterForCuChaiLingJianPic mCuChaiLingJianPicAdapter;
    //粗拆新选择的图片列表
    List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> mCuChaiNewChoosePicList = new ArrayList<>();


    String mOId;
    String mCarNum;

    JobHasDoChaiJieDetailForCuChaiContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_hasdo_chaijie_detail_for_cuchai);
        initTitleWithRightTvOrIv(null, null, "已完成拆解任务", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobHasDoChaiJieDetailForCuChaiPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getHasDoChaiJieDetail();
    }

    @Override
    public void initData() {
        mOId = getIntent().getStringExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID);
        mCarNum = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_NUM);
    }

    @Override
    public void assignView() {

        tvCarNum = (TextView) findViewById(R.id.tv_car_num);

        tvChaijieRequest = (TextView) findViewById(R.id.tv_chaijie_request);

        tvChaijieWorker = (TextView) findViewById(R.id.tv_chaijie_worker);
        tvFinishedChaijieTime = (TextView) findViewById(R.id.tv_finished_chaijie_time);

        gvChoosedChaijiePic = (GridViewForScrollView) findViewById(R.id.gv_choosed_chaijie_pic);

       /* carNum = (TextView) findViewById(R.id.car_num);
        tvChaijieWorker = (TextView) findViewById(R.id.tv_chaijie_worker);
        tvFinishedChaijieTime = (TextView) findViewById(R.id.tv_finished_chaijie_time);
        tvEnsureChaijieWorker = (TextView) findViewById(R.id.tv_ensure_chaijie_worker);
        tvEnsureChaijieTime = (TextView) findViewById(R.id.tv_ensure_chaijie_time);
        tvChaijieRequest = (TextView) findViewById(R.id.tv_chaijie_request);*/


    }

    private void initAdapter() {
        /*mChaiJieLingJianInfoAdapter = new ListAdapterForChaiJieLingJianSubmitInfo(mChaiJieLingJianInfoList, this);
        mChaiJieLingJianInfoAdapter.setLingJianPicClickListener(new LingJianPicClickListenerImpl());

        lv.setAdapter(mChaiJieLingJianInfoAdapter);*/


        mCuChaiLingJianPicAdapter = new GridAdapterForCuChaiLingJianPic(mCuChaiNewChoosePicList,this,true);
        mCuChaiLingJianPicAdapter.setGridPicClickListener(new CuChaiPicClickListenerImpl());
        gvChoosedChaijiePic.setAdapter(mCuChaiLingJianPicAdapter);

    }


    @Override
    public void initView() {

        tvCarNum.setText("车牌号:"+mCarNum);

        initAdapter();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getHasDoChaiJieDetail() {
        mPresenter.getHasDoChaiJieDetail(Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI , mOId);
    }

    @Override
    public void onGetHasDoChaiJieDetailSuccess(ChaiJieDetailInfoForCuChai chaiJieDetailInfo) {
        mChaiJieDetailInfo = chaiJieDetailInfo;

        fillChaiJieDetailInfoToView(chaiJieDetailInfo);

        mCuChaiNewChoosePicList.clear();
        mCuChaiNewChoosePicList.addAll(chaiJieDetailInfo.getImgattachlist());
        mCuChaiLingJianPicAdapter.notifyDataSetChanged();

        initAdapter();

    }

    private void fillChaiJieDetailInfoToView(ChaiJieDetailInfoForCuChai chaiJieDetail) {


        tvChaijieRequest.setText(chaiJieDetail.getRequirement());

        tvChaijieWorker.setText("拆解人:" + chaiJieDetail.getDispatchperson());
        tvFinishedChaijieTime.setText("完成拆解时间:" + chaiJieDetail.getComletetime());
    }

    @Override
    public void onGetHasDoChaiJieDetailError(String tip) {
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
    public void setPresenter(JobHasDoChaiJieDetailForCuChaiContract.Presenter presenter) {

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

            Intent toShowPics = new Intent(ActivityJobHasDoChaiJieDetailForCuChai.this, ActivityPicShow.class);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION, position);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_URLS, (Serializable) picUrls);
            //
            startActivity(toShowPics);
        }
    }
}
