package com.aofan.cardismantling.mvp.carwaittochaijie.carchaijiepaigongdanlist;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForPaiGongDanCuChai;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.CarOfPaiGongDan;
import com.aofan.cardismantling.bean.LingJIanOfCanChooseChaiJie;
import com.aofan.cardismantling.bean.PaiGongDanitem;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.base.common.fragment.DialogFragmentTip;
import com.aofan.cardismantling.mvp.carwaittochaijie.addnewpaigongdan.ActivityAddNewPaiGongDanForCuChai;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**粗拆派工单详情列表
 * Created by Administrator on 2017/2/7.
 */

public class ActivityOneCarChaiJiePaiGongDanListForCuChai extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, PaiGongDanListContract.View {

   /* private LinearLayout layoutBottomControl;
    private TextView tvFinishedPaidan;
    private TextView tvAddNewPaidan;*/

    private LinearLayout layoutBottomControl;
    private TextView tvAddNewPaidan;
    private TextView tvCarCompleteChaijie;
    private LinearLayout layoutTopBar;
    private ImageView ivCarBrandPic;
    private TextView tvCarNum;
    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;



    List<PaiGongDanitem> mPaiGongDanList = new ArrayList<>();
    ListAdapterForPaiGongDanCuChai mAdapter;

    Integer mPageIndex;

    boolean isRefresh, canLoad;

    PaiGongDanListContract.Presenter mPresenter;

    CarOfPaiGongDan mCarOfPaiGongDan;
    String mCarPaiGongState;
    String mCarChaiJieType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_chaijie_paigongdanlist_for_cuchai);
        initTitleWithRightTvOrIv(null, null, "派工单详情", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new PaiGongDanListPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getOriginalList();
    }

    @Override
    public void initData() {

        mCarOfPaiGongDan = (CarOfPaiGongDan) getIntent().getSerializableExtra(IntentKey.INTENT_KEY_CAR_INFO_OF_PAIGONGDAN);
        mCarPaiGongState = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_PAIGONGSTATE);
        mCarChaiJieType = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_CHAIJIE_TYPE);
    }

    @Override
    public void assignView() {

        layoutBottomControl = (LinearLayout) findViewById(R.id.layout_bottom_control);
        //tvFinishedPaidan = (TextView) findViewById(R.id.tv_finished_paidan);
        tvAddNewPaidan = (TextView) findViewById(R.id.tv_add_new_paidan);
        tvCarCompleteChaijie = (TextView) findViewById(R.id.tv_car_complete_chaijie);
        layoutTopBar = (LinearLayout) findViewById(R.id.layout_top_bar);
        ivCarBrandPic = (ImageView) findViewById(R.id.iv_car_brand_pic);
        tvCarNum = (TextView) findViewById(R.id.tv_car_num);
        lv = (PullToRefreshListView) findViewById(R.id.lv);
        originalLv = lv.getRefreshableView();
        emptylayout = (RelativeLayout) findViewById(R.id.emptylayout);
        emptytiptv = (TextView) findViewById(R.id.emptytiptv);



    }

    @Override
    public void initView() {

        if (mCarPaiGongState.equals(Config.TYPE_OF_PAIGONG_STATE_HAS_PAIGONG)) {
            // layoutBottomControl.setVisibility(View.GONE);
            ivRight.setVisibility(View.GONE);
            //tvFinishedPaidan.setVisibility(View.GONE);
            tvAddNewPaidan.setVisibility(View.GONE);

            if (mCarOfPaiGongDan.getAllocat().equals("拆解中")) {
                tvCarCompleteChaijie.setVisibility(View.VISIBLE);
            } else {
                tvCarCompleteChaijie.setVisibility(View.GONE);
            }

        } else {
            tvRight.setVisibility(View.VISIBLE);
            //tvFinishedPaidan.setVisibility(View.VISIBLE);
            tvAddNewPaidan.setVisibility(View.VISIBLE);
            tvCarCompleteChaijie.setVisibility(View.GONE);

        }


        RxView.clicks(tvCarCompleteChaijie)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        /*//先获取没有完成拆解的派工单
                        checkCarHasNotFinishedPaiGongDan();*/
                        submitCarFinishChaiJie();

                    }
                });

        tvCarNum.setText(mCarOfPaiGongDan.getVehiclenumber());
        if (TextUtils.isEmpty(mCarOfPaiGongDan.getCaricon()) == false) {
            Picasso.with(this)
                    .load(mCarOfPaiGongDan.getCaricon())
                    .resize(100, 100)
                    .tag(this)
                    .placeholder(R.drawable.ico_car_default_mid)
                    .error(R.drawable.ico_car_default_mid)
                    .into(ivCarBrandPic);
        }

        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lv.setOnRefreshListener(this);

        mAdapter = new ListAdapterForPaiGongDanCuChai(mPaiGongDanList, this);
        originalLv.setAdapter(mAdapter);

        RxView.clicks(ivRight)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent toAddPaiGongDan = new Intent(ActivityOneCarChaiJiePaiGongDanListForCuChai.this, ActivityAddNewPaiGongDanForCuChai.class);
                        toAddPaiGongDan.putExtra(IntentKey.INTENT_KEY_CAR_INFO_OF_PAIGONGDAN, mCarOfPaiGongDan);
                        startActivity(toAddPaiGongDan);

                    }
                });

        RxView.clicks(tvAddNewPaidan)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent toAddPaiGongDan = new Intent(ActivityOneCarChaiJiePaiGongDanListForCuChai.this, ActivityAddNewPaiGongDanForCuChai.class);
                        toAddPaiGongDan.putExtra(IntentKey.INTENT_KEY_CAR_INFO_OF_PAIGONGDAN, mCarOfPaiGongDan);
                        startActivity(toAddPaiGongDan);

                    }
                });

        /*RxView.clicks(tvFinishedPaidan)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        getLeftNotPaiGongLingJian();
                    }
                });*/

    }


    private void getOriginalList() {
        mPageIndex = 1;
        isRefresh = true;
        getCarPaiGongDanList();
    }

    private void getMoreList() {
        if (canLoad) {
            mPageIndex++;
            getCarPaiGongDanList();
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
    public void getCarPaiGongDanList() {
        mPresenter.getCarPaiGongDanList(mCarOfPaiGongDan.getDid(), mPageIndex.toString(), Config.DEFAULT_PAGE_SIZE.toString());
    }

    @Override
    public void showCarPaiGongDanList(List<PaiGongDanitem> paiGongDanList) {
        lv.onRefreshComplete();

        if (isRefresh) {
            mPaiGongDanList.clear();
            isRefresh = false;
        }
        if (paiGongDanList.size() > 0) {
            if (paiGongDanList.size() >= Config.DEFAULT_PAGE_SIZE) {
                lv.setMode(PullToRefreshBase.Mode.BOTH);
                canLoad = true;
            } else {
                canLoad = false;
                lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            mPaiGongDanList.addAll(paiGongDanList);

        } else {
            canLoad = false;
            lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            if (mPaiGongDanList.size() == 0) {
                lv.setEmptyView(emptylayout);

                //tvFinishedPaidan.setVisibility(View.GONE);

            } else {
                ToastUtil.show(this, "已经获取全部数据", 2000);
            }
        }


        if (mPaiGongDanList.size()>0)
        {

            //ivRight.setVisibility(View.GONE);
            tvAddNewPaidan.setVisibility(View.GONE);
        }else {
            //ivRight.setVisibility(View.GONE);
            tvAddNewPaidan.setVisibility(View.VISIBLE);
        }
        //如果不存在派工单或者汽车的状态是已派工，则不显示完成派工按钮
        /*if (mCarPaiGongState.equals(Config.TYPE_OF_PAIGONG_STATE_HAS_PAIGONG) || mPaiGongDanList.size() == 0) {
            //tvFinishedPaidan.setVisibility(View.GONE);
            //如果已经存在派工单，则显示完成派工按钮
        } else {
            //tvFinishedPaidan.setVisibility(View.VISIBLE);
        }*/

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void completePaiGong() {
        mPresenter.completePaiGong(mCarOfPaiGongDan.getDid());
    }

    @Override
    public void showCompletePaiGongSuccess() {
        ToastUtil.showShort(this, "车号为:" + mCarOfPaiGongDan.getVehiclenumber() + "的车辆完成派工");
        finish();
    }

    @Override
    public void showCompletePaiGongError(String tip) {
        ToastUtil.showShort(this, tip);
    }

    @Override
    public void getLeftNotPaiGongLingJian() {
        mPresenter.getLeftNotPaiGongLingJian(mCarOfPaiGongDan.getDid());
    }

    @Override
    public void showLeftNotPaiGongLingJian(List<LingJIanOfCanChooseChaiJie> canChooseChaiJieLingJianList) {
        if (canChooseChaiJieLingJianList.size() > 0) {
            showEnsureFinishPaiGongDialog();
        } else {
            completePaiGong();
        }
    }

    @Override
    public void showGetLeftNotPaiGongLingJianError(String tip) {
        ToastUtil.showShort(this, tip);
    }

    @Override
    public void checkCarHasNotFinishedPaiGongDan() {
        mPresenter.checkCarHasLeftNotFinishPaiGongDan(mCarOfPaiGongDan.getBaseid());
    }

    @Override
    public void onCheckCarHasNotFinishedPaiGongDanSuccess(boolean hasNotFinishedPaiGongDan) {
        if (hasNotFinishedPaiGongDan == true)
        {
            ToastUtil.showShort(this,"还有未完成拆解的派工单，请先完成拆解工作");
        }else {
            submitCarFinishChaiJie();
        }



    }

    @Override
    public void onCheckCarHasNotFinishedPaiGongDanError(String tip) {
        ToastUtil.showShort(this,tip);
    }

    @Override
    public void submitCarFinishChaiJie() {
        mPresenter.submitCarFinishChaiJie((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),
                mCarOfPaiGongDan.getBaseid(),Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI);
    }

    @Override
    public void onSubmitCarFinishChaiJieSuccess() {
        ToastUtil.showShort(this, "车辆完成拆解");
        finish();
    }

    @Override
    public void onSubmitCarFinishChaiJieError(String tip) {
        ToastUtil.showShort(this, tip);
    }


    @Override
    public void showError(String tip) {
        lv.onRefreshComplete();
        ToastUtil.showShort(this, tip);
        if (isRefresh) {
            mPaiGongDanList.clear();
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
    public void setPresenter(PaiGongDanListContract.Presenter presenter) {

    }


    /*private void showEnsureFinishChaiJieDialog() {
        DialogFragmentTip dialogFragmentTip = DialogFragmentTip.getInstance(Config.ACTION_FINISH_CHAIJIE_WITH_LEFT_PAIGONGDAN);
        dialogFragmentTip.setPositionBtnClickListener(new EnsureFinishPaiGongListener());
        dialogFragmentTip.show(getSupportFragmentManager(), Config.DIALOG_FRAGMENT_TIP);

    }*/


    private void showEnsureFinishPaiGongDialog() {
        DialogFragmentTip dialogFragmentTip = DialogFragmentTip.getInstance(Config.ACTION_FINISH_PAIGONG_WITH_LEFT_LINGJIAN);
        dialogFragmentTip.setPositionBtnClickListener(new EnsureFinishPaiGongListener());
        dialogFragmentTip.show(getSupportFragmentManager(), Config.DIALOG_FRAGMENT_TIP);

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
        if (isHidden) {
            getOriginalList();
            isHidden = false;
        }
    }

    //确定完成派工
    private class EnsureFinishPaiGongListener implements DialogFragmentTip.TipDialogPositionBtnClickListener {

        @Override
        public void onPositionBtnClick(String dealType) {
            if (dealType.equals(Config.ACTION_FINISH_PAIGONG_WITH_LEFT_LINGJIAN)) {
                completePaiGong();
            }/*else if (dealType.equals(Config.ACTION_FINISH_CHAIJIE_WITH_LEFT_PAIGONGDAN))
            {
                submitCarFinishChaiJie();
            }*/
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

