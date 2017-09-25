package com.aofan.cardismantling.mvp.lingjiandetail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.vp.ViewPagerAdapterForLingJianPic;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.LingJianInfo;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ActivityShowLingJianDetail extends BaseActivity implements ShowLingJianDetailContract.View {

    private TextView tagLingjianBianhao;
    private TextView tvLingjianBianhao;
    private TextView tagLingjianName;
    private TextView tvLingjianName;
    private TextView tagSourceCarBrand;
    private TextView tvSourceCarBrand;
    private TextView tagSourceCarType;
    private TextView tvSourceCarType;
    private TextView tagPailiang;
    private TextView tvPailiang;
    private TextView tagWeight;
    private TextView tvWeight;
    private TextView tagColor;
    private TextView tvColor;
    private TextView tagGoodsPlace;
    private TextView tvGoodsPlace;
    private TextView tagProductiveYear;
    private TextView tvProductiveYear;
    private TextView tagPresalePrice;
    private TextView tvPresalePrice;
    private TextView tagFromCarNum;
    private TextView tvFromCarNum;
    private TextView tvTagChaijieTime;
    private TextView tvChaijieTime;
    private TextView tvTagChaijieWorker;
    private TextView tvChaijieWorker;


    private RelativeLayout layoutGoodsPlace;

    private ViewPager vpLingjianPic;
    //private PageIndicatorView indicatorLingjianPic;






    String mLingJianId;

    ShowLingJianDetailContract.Presenter mPresenter;


    List<View> mBannerViewList = new ArrayList<>();
    List<LingJianInfo.ImgattachlistBean> mBannerItemList = new ArrayList<>();
    int bannerCurPos;

    ViewPagerAdapterForLingJianPic mLingJianPicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lingjian_detail);
        initTitleWithRightTvOrIv(null, null, "零件拆解信息", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new ShowLingJianDetailPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getLingJianDetail();
    }

    @Override
    public void initData() {
        mLingJianId = getIntent().getStringExtra(IntentKey.INTENT_KEY_LINGJIAN_ID);
    }

    @Override
    public void assignView() {
        tagLingjianBianhao = (TextView) findViewById(R.id.tag_lingjian_bianhao);
        tvLingjianBianhao = (TextView) findViewById(R.id.tv_lingjian_bianhao);
        tagLingjianName = (TextView) findViewById(R.id.tag_lingjian_name);
        tvLingjianName = (TextView) findViewById(R.id.tv_lingjian_name);
        tagSourceCarBrand = (TextView) findViewById(R.id.tag_source_car_brand);
        tvSourceCarBrand = (TextView) findViewById(R.id.tv_source_car_brand);
        tagSourceCarType = (TextView) findViewById(R.id.tag_source_car_type);
        tvSourceCarType = (TextView) findViewById(R.id.tv_source_car_type);
        tagPailiang = (TextView) findViewById(R.id.tag_pailiang);
        tvPailiang = (TextView) findViewById(R.id.tv_pailiang);
        tagWeight = (TextView) findViewById(R.id.tag_weight);
        tvWeight = (TextView) findViewById(R.id.tv_weight);
        tagColor = (TextView) findViewById(R.id.tag_color);
        tvColor = (TextView) findViewById(R.id.tv_color);
        tagGoodsPlace = (TextView) findViewById(R.id.tag_goods_place);
        tvGoodsPlace = (TextView) findViewById(R.id.tv_goods_place);
        tagProductiveYear = (TextView) findViewById(R.id.tag_productive_year);
        tvProductiveYear = (TextView) findViewById(R.id.tv_productive_year);
        tagPresalePrice = (TextView) findViewById(R.id.tag_presale_price);
        tvPresalePrice = (TextView) findViewById(R.id.tv_presale_price);
        tagFromCarNum = (TextView) findViewById(R.id.tag_from_car_num);
        tvFromCarNum = (TextView) findViewById(R.id.tv_from_car_num);
        tvTagChaijieTime = (TextView) findViewById(R.id.tv_tag_chaijie_time);
        tvChaijieTime = (TextView) findViewById(R.id.tv_chaijie_time);
        tvTagChaijieWorker = (TextView) findViewById(R.id.tv_tag_chaijie_worker);
        tvChaijieWorker = (TextView) findViewById(R.id.tv_chaijie_worker);

        layoutGoodsPlace = (RelativeLayout) findViewById(R.id.layout_goods_place);


        vpLingjianPic = (ViewPager) findViewById(R.id.vp_lingjian_pic);
        //indicatorLingjianPic = (PageIndicatorView) findViewById(R.id.indicator_lingjian_pic);
    }

    @Override
    public void initView() {
        initLingJianPicViewPager();
    }


    private void initLingJianPicViewPager()
    {

        createBannerViewList();
        /*indicatorLingjianPic.setCount(mBannerViewList.size());
        indicatorLingjianPic.setDynamicCount(true);*/
        /*adsindicator.setSelection(0);
        adsindicator.setCount(mBannerViewList.size());
        adsindicator.setDynamicCount(true);
        adsindicator.setAnimationDuration(1000);
        adsindicator.setAnimationType(AnimationType.THIN_WORM);
        adsindicator.setInteractiveAnimation(true);*/

        mLingJianPicAdapter = new ViewPagerAdapterForLingJianPic(mBannerViewList,mBannerItemList,ActivityShowLingJianDetail.this);
        /*createBannerViewList();
        mBannerViewAdapterNew.setData(mBannerViewList);*/
        vpLingjianPic.setAdapter(mLingJianPicAdapter);

        vpLingjianPic.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               /* Log.e("selectedPosition", "" + position);*/
                bannerCurPos = position;
                //adsindicator.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*adviewpager.setPageTransformer(true, new DefaultTransformer());
        try {

            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(adviewpager.getContext(),
                    new AccelerateInterpolator());
            field.set(adviewpager, scroller);
            scroller.setmDuration(1000);
        } catch (Exception e) {
            // LogUtils.e(TAG, "", e);Log.e("fixedspeedscrollerwrong", "true");
            e.printStackTrace();
        }*/

    }


    //创建bannerview list
    private void createBannerViewList()
    {
        if (mBannerItemList.size() > 0) {

            mBannerViewList.clear();
            for (int i = 0; i < mBannerItemList.size(); i++) {
                View bannerView = getLayoutInflater().inflate(R.layout.vp_item_banner, null);
                mBannerViewList.add(bannerView);
            }
        }
    }


    @Override
    public void getLingJianDetail() {
        mPresenter.getLingJianDetail(mLingJianId);
    }

    @Override
    public void showLingJianDetail(LingJianInfo lingJianInfo) {

        mBannerItemList.addAll(lingJianInfo.getImgattachlist());
        initLingJianPicViewPager();

        tvLingjianBianhao.setText(lingJianInfo.getPartinfo().getBiannumber());

        tvLingjianName.setText(lingJianInfo.getPartinfo().getPartname());

        String carBrand = lingJianInfo.getBaseinfo().getBrandmodel().substring(0,(lingJianInfo.getBaseinfo().getBrandmodel().length()-lingJianInfo.getBaseinfo().getVehiclemodel().length()));
        tvSourceCarBrand.setText(carBrand);
        tvSourceCarType.setText(lingJianInfo.getBaseinfo().getVehiclemodel());
        //???是所有的零件都有排量么？
        tvPailiang.setText(lingJianInfo.getPartinfo().getPailiang());
        tvWeight.setText(lingJianInfo.getPartinfo().getWeight()+"kg");
        tvColor.setText(lingJianInfo.getPartinfo().getColor());
        if(TextUtils.isEmpty(lingJianInfo.getPartinfo().getGoodplace()) == false)
        {
            tvGoodsPlace.setText(lingJianInfo.getPartinfo().getGoodplace());
            layoutGoodsPlace.setVisibility(View.VISIBLE);
        }else {
            layoutGoodsPlace.setVisibility(View.GONE);
        }

        tvProductiveYear.setText(lingJianInfo.getPartinfo().getScnf());
        tvPresalePrice.setText(lingJianInfo.getPartinfo().getPresellprice());
        tvFromCarNum.setText(lingJianInfo.getPartinfo().getVehiclenumber());
        tvChaijieTime.setText(lingJianInfo.getPartinfo().getCreatetime());
        tvChaijieWorker.setText(lingJianInfo.getPartorderinfo().getDealpersonname());
        //
    }

    @Override
    public void showError(String tip) {
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
    public void setPresenter(ShowLingJianDetailContract.Presenter presenter) {

    }
}
