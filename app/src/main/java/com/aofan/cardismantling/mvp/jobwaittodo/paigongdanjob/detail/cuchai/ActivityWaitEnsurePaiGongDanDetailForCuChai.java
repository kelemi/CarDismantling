package com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob.detail.cuchai;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.grid.GridAdapterForCuChaiLingJianPic;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.ChaiJieDetailInfoForCuChai;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.picshow.ActivityPicShow;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.aofan.cardismantling.widget.GridViewForScrollView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ActivityWaitEnsurePaiGongDanDetailForCuChai extends BaseActivity implements WaitEnsurePaiGongDanForCuChaiContract.View {


    private LinearLayout layoutEnsureChaijieFinish;
    private TextView tvEnsureChaijieFinish;
    private TextView tvCarnum;
    private TextView tvPaigongTime;
    private TextView tvChaijieWorkers;
    private TextView tvChaijieFinishTime;
    private TextView tvChaijieRequest;
    private GridViewForScrollView gvChoosedChaijiePic;







    GridAdapterForCuChaiLingJianPic mCuChaiLingJianPicAdapter;
    //粗拆新选择的图片列表
    List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> mCuChaiNewChoosePicList = new ArrayList<>();


    /*List<ChaiJieLingJianInfoItem> mChaiJieLingJianList;
    ListAdapterForChaiJieLingJianHasFinished mLingJianChaiJieInfoAdapter;*/

    String mOId;
    String mCarNum;

    //拆解详情信息
    ChaiJieDetailInfoForCuChai mChaiJieDetailInfo;


    /*//拆解零件信息的adapter
    ListAdapterForChaiJieLingJianSubmitInfo mChaiJieLingJianInfoAdapter;
    //待拆解的零件信息list
    List<ChaiJieDetailInfo.PartlistBean> mChaiJieLingJianInfoList = new ArrayList<>();*/

    WaitEnsurePaiGongDanForCuChaiPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_ensure_paigongdan_detail_for_cuchai);
        initTitleWithRightTvOrIv(null, null, "待确认派工单", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new WaitEnsurePaiGongDanForCuChaiPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getChaiJiePaiGongDanDetail();
    }

    @Override
    public void initData() {

        mOId = getIntent().getStringExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID);
        mCarNum = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_NUM);

    }

    @Override
    public void assignView() {

        layoutEnsureChaijieFinish = (LinearLayout) findViewById(R.id.layout_ensure_chaijie_finish);
        tvEnsureChaijieFinish = (TextView) findViewById(R.id.tv_ensure_chaijie_finish);
        tvCarnum = (TextView) findViewById(R.id.tv_carnum);
        tvPaigongTime = (TextView) findViewById(R.id.tv_paigong_time);
        tvChaijieWorkers = (TextView) findViewById(R.id.tv_chaijie_workers);
        tvChaijieFinishTime = (TextView) findViewById(R.id.tv_chaijie_finish_time);
        tvChaijieRequest = (TextView) findViewById(R.id.tv_chaijie_request);
        gvChoosedChaijiePic = (GridViewForScrollView) findViewById(R.id.gv_choosed_chaijie_pic);


    }

    private void initAdapter() {



        mCuChaiLingJianPicAdapter = new GridAdapterForCuChaiLingJianPic(mCuChaiNewChoosePicList,this,true);
        mCuChaiLingJianPicAdapter.setGridPicClickListener(new CuChaiPicClickListenerImpl());
        gvChoosedChaijiePic.setAdapter(mCuChaiLingJianPicAdapter);
        /*mChaiJieLingJianInfoAdapter = new ListAdapterForChaiJieLingJianSubmitInfo(mChaiJieLingJianInfoList, this);
        mChaiJieLingJianInfoAdapter.setLingJianPicClickListener(new LingJianPicClickListenerImpl());

        lv.setAdapter(mChaiJieLingJianInfoAdapter);*/


    }

    @Override
    public void initView() {
        /*mLingJianChaiJieInfoAdapter = new ListAdapterForChaiJieLingJianHasFinished(mChaiJieLingJianList,this);
        lv.setAdapter(mLingJianChaiJieInfoAdapter);*/
        tvCarnum.setText(mCarNum);
        tvEnsureChaijieFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ensurePaiGongDanFinished();
            }
        });
        /*RxView.clicks(tvEnsureChaijieFinish)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });*/
        initAdapter();
    }


    @Override
    public void ensurePaiGongDanFinished() {
        mPresenter.ensurePaiGongDanFinished(mOId,
                (String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, "")
        );

    }

    @Override
    public void showEnsurePaiGongDanFinishedSuccess() {
        ToastUtil.showShort(this, "确认拆解派工单成功");
        finish();
    }

    @Override
    public void showEnsurePaiGongDanFinishedError(String tip) {
        ToastUtil.showShort(this, tip);
    }

    @Override
    public void getChaiJiePaiGongDanDetail() {

        mPresenter.getChaiJiePaiGongDanDetail(Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI , mOId);

    }

    @Override
    public void showChaiJiePaiGongDanDetail(ChaiJieDetailInfoForCuChai chaiJieDetail) {
        mChaiJieDetailInfo = chaiJieDetail;

        mCuChaiNewChoosePicList.clear();
        mCuChaiNewChoosePicList.addAll(chaiJieDetail.getImgattachlist());
        mCuChaiLingJianPicAdapter.notifyDataSetChanged();

        fillChaiJieDetailInfoToView(chaiJieDetail);

        /*mChaiJieLingJianInfoList.clear();
        mChaiJieLingJianInfoList.addAll(mChaiJieDetailInfo.getPartlist());*/

        /*mChaiJieLingJianInfoAdapter = new ListAdapterForChaiJieLingJianSubmitInfo(mChaiJieLingJianInfoList,this);
        lv.setAdapter(mChaiJieLingJianInfoAdapter);*/

        initAdapter();

    }

    private void fillChaiJieDetailInfoToView(ChaiJieDetailInfoForCuChai chaiJieDetail) {
        // tvCarnum.setText(chaiJieDetail.get);
        tvPaigongTime.setText("派工时间:" + chaiJieDetail.getCreatetime());
        tvChaijieWorkers.setText("拆解人员:" + chaiJieDetail.getDispatchperson());
        tvChaijieRequest.setText(chaiJieDetail.getRequirement());
        tvChaijieFinishTime.setText("拆解完成时间:" + chaiJieDetail.getComletetime());
    }

    @Override
    public void showChaiJiePaiGongDanDetailError(String tip) {
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
    public void setPresenter(WaitEnsurePaiGongDanForCuChaiContract.Presenter presenter) {

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

            Intent toShowPics = new Intent(ActivityWaitEnsurePaiGongDanDetailForCuChai.this, ActivityPicShow.class);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION, position);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_URLS, (Serializable) picUrls);
            //
            startActivity(toShowPics);
        }
    }

    /*private class LingJianPicClickListenerImpl implements GridAdapterForJingChaiLingJianPic.GridPicClickListener {

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

            Intent toShowPics = new Intent(ActivityWaitEnsurePaiGongDanDetailForCuChai.this, ActivityPicShow.class);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION, position);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_URLS, (Serializable) picUrls);
            //
            startActivity(toShowPics);

        }
    }*/




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
