package com.aofan.cardismantling.mvp.carwaitanalysis.carwaitanalysisdetail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.grid.GridAdapterForCarComponentDetail;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.CarInfo;
import com.aofan.cardismantling.bean.CarLingJianInfo;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.Res;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.aofan.cardismantling.widget.GridViewForScrollView;
import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 待分析车辆分析详情界面
 * Created by Administrator on 2016/11/21.
 */

public class ActivityCarWaitAnalysisDetail extends BaseActivity implements WaitToAnalysisCarDetailContract.View {

    private TextView tvCarBrand;
    private TextView tvCarKind;
    private TextView tvCarRegisterTime;
    private ImageView ivCarBrandPic;
    private TextView tvCarNum;

    private TextView tvFeiqiwuChoose;


    private TextView tvJingchaiChoose;
    private TextView tvCuchaiChoose;



   /* private CheckBox cbJingchaiChoose;
    private CheckBox cbCuchaiChoose;*/




    /* private RadioGroup rgChooseChaijieWay;
    private RadioButton rbJingchai;
    private RadioButton rbCuchai;*/
    private LinearLayout layoutJingcaiCarLingjianChoose;
    private RadioGroup rgComponentChoose;
    private RadioButton rbFadongjiComponent;
    private RadioButton rbDipanComponent;
    private RadioButton rbJiashishiComponent;
    private RadioButton rbChexiangComponent;
    private GridView gvComponentDetail;
    private LinearLayout layoutFeiqiwuLiangjianchoose;
    private GridViewForScrollView gvNeedChaijieDianchiFeiqiwu;
    private GridViewForScrollView gvNeedChaijieOtherFeiqiwu;
    private GridViewForScrollView gvQueshiDianchiFeiqiwu;
    private GridViewForScrollView gvQueshiOtherFeiqiwu;





    List<CarLingJianInfo> mFaDongJiLingJianList = new ArrayList<>();
    List<CarLingJianInfo> mDiPanLingJianList = new ArrayList<>();
    List<CarLingJianInfo> mJiaShiShiLingJianList = new ArrayList<>();
    List<CarLingJianInfo> mCheXiangLingJianList = new ArrayList<>();


    //需要拆解的废弃物
    //电池废弃物零件
    List<CarLingJianInfo> mNeedChaijieDianChiFeiQiWuParts = new ArrayList<>();
    //其他废弃物零件
    List<CarLingJianInfo> mNeedChaijieOtherFeiQiWuParts = new ArrayList<>();

    //缺失的废弃物
    //电池废弃物零件
    List<CarLingJianInfo> mQueShiDianChiFeiQiWuParts = new ArrayList<>();
    //其他废弃物零件
    List<CarLingJianInfo> mQueShiOtherFeiQiWuParts = new ArrayList<>();

    //需要拆解的废弃物的adapter
    GridAdapterForCarComponentDetail mNeedChaiJieDianChiFeiQiWuAdapter;
    GridAdapterForCarComponentDetail mNeedChaiJieOtherFeiQiWuAdapter;
    //缺失的废弃物的adapter
    GridAdapterForCarComponentDetail mQueShiDianChiFeiQiWuAdapter;
    GridAdapterForCarComponentDetail mQueShiOtherFeiQiWuAdapter;


    GridAdapterForCarComponentDetail mLingJianAdapter;


    WaitToAnalysisCarDetailPresenter mPresenter;


    CarInfo mCarInfo;

    String mChaiJieWay;
    //选择拆解的零件的提交信息
    String mAnalysisLingJianSubmitStr;
    //没有分析的零件提交信息
    String mNotAnalysisLingJianSubmitStr;


    //废弃物提交信息str
    String mFeiQiWuLingJianSubmitStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_analysis_detail);
        initTitleWithRightTvOrIv(null, null, "汽车分析", true, "提交", null);
        initData();
        assignView();
        initView();
        mPresenter = new WaitToAnalysisCarDetailPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getWaitToAnalysisCarLingJianInfo();
        getFeiQiWuInfo();
    }

    @Override
    public void initData() {
        mCarInfo = (CarInfo) getIntent().getSerializableExtra(IntentKey.INTENT_KEY_CAR_INFO);


    }

    @Override
    public void assignView() {
        tvCarBrand = (TextView) findViewById(R.id.tv_car_brand);
        tvCarKind = (TextView) findViewById(R.id.tv_car_kind);
        tvCarRegisterTime = (TextView) findViewById(R.id.tv_car_register_time);
        ivCarBrandPic = (ImageView) findViewById(R.id.iv_car_brand_pic);
        tvCarNum = (TextView) findViewById(R.id.tv_car_num);

        tvJingchaiChoose = (TextView) findViewById(R.id.tv_jingchai_choose);
        tvCuchaiChoose = (TextView) findViewById(R.id.tv_cuchai_choose);

        /*cbJingchaiChoose = (CheckBox) findViewById(R.id.cb_jingchai_choose);
        cbCuchaiChoose = (CheckBox) findViewById(R.id.cb_cuchai_choose);*/

       /* rgChooseChaijieWay = (RadioGroup) findViewById(R.id.rg_choose_chaijie_way);
        rbJingchai = (RadioButton) findViewById(R.id.rb_jingchai);
        rbCuchai = (RadioButton) findViewById(R.id.rb_cuchai);*/
        layoutJingcaiCarLingjianChoose = (LinearLayout) findViewById(R.id.layout_jingcai_car_lingjian_choose);
        rgComponentChoose = (RadioGroup) findViewById(R.id.rg_component_choose);
        rbFadongjiComponent = (RadioButton) findViewById(R.id.rb_fadongji_component);
        rbDipanComponent = (RadioButton) findViewById(R.id.rb_dipan_component);
        rbJiashishiComponent = (RadioButton) findViewById(R.id.rb_jiashishi_component);
        rbChexiangComponent = (RadioButton) findViewById(R.id.rb_chexiang_component);
        gvComponentDetail = (GridView) findViewById(R.id.gv_component_detail);

        tvFeiqiwuChoose = (TextView) findViewById(R.id.tv_feiqiwu_choose);


        layoutFeiqiwuLiangjianchoose = (LinearLayout) findViewById(R.id.layout_feiqiwu_liangjianchoose);
        gvNeedChaijieDianchiFeiqiwu = (GridViewForScrollView) findViewById(R.id.gv_need_chaijie_dianchi_feiqiwu);
        gvNeedChaijieOtherFeiqiwu = (GridViewForScrollView) findViewById(R.id.gv_need_chaijie_other_feiqiwu);
        gvQueshiDianchiFeiqiwu = (GridViewForScrollView) findViewById(R.id.gv_queshi_dianchi_feiqiwu);
        gvQueshiOtherFeiqiwu = (GridViewForScrollView) findViewById(R.id.gv_queshi_other_feiqiwu);

    }

    @Override
    public void initView() {

        RxView.clicks(tvRight)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        boolean result = false;
                        if (TextUtils.isEmpty(mChaiJieWay)==true)
                        {
                            ToastUtil.showShort(ActivityCarWaitAnalysisDetail.this,"请选择拆解方式和要拆解的零件");

                        }else {
                            if (mChaiJieWay.equals(Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI)) {
                                result = checkHasChoosedLingJianToChaiJie();
                                if (result==false)
                                {
                                    ToastUtil.showShort(ActivityCarWaitAnalysisDetail.this,"请选择要拆解的零件");
                                }

                            } else if (mChaiJieWay.equals(Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI)) {
                                result = true;
                            }
                        }


                        return result;
                    }
                }).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

                submitCarAnalysisInfo();
            }
        });

        tvCarBrand.setText(mCarInfo.getBrandmodel());
        tvCarKind.setText(mCarInfo.getVehicletype());
        tvCarRegisterTime.setText(mCarInfo.getRegisterdate());
        tvCarNum.setText(mCarInfo.getVehiclenumber());
        Picasso.with(this)
                .load(mCarInfo.getCaricon())
                .resize(100, 100)
                .placeholder(R.drawable.ico_car_default_mid_white)
                .error(R.drawable.ico_car_default_mid_white)
                .into(ivCarBrandPic);

        tvFeiqiwuChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutFeiqiwuLiangjianchoose.setVisibility(View.VISIBLE);
                layoutJingcaiCarLingjianChoose.setVisibility(View.GONE);
            }
        });

        tvJingchaiChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvJingchaiChoose.setCompoundDrawablesWithIntrinsicBounds( Res.getDrawable(R.drawable.ico_choosed_green),null,null,null);
                tvCuchaiChoose.setCompoundDrawablesWithIntrinsicBounds( Res.getDrawable(R.drawable.ico_not_choosed_black),null,null,null);

                layoutFeiqiwuLiangjianchoose.setVisibility(View.GONE);
                layoutJingcaiCarLingjianChoose.setVisibility(View.VISIBLE);

                mChaiJieWay = Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI;
            }
        });


        tvCuchaiChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCuchaiChoose.setCompoundDrawablesWithIntrinsicBounds( Res.getDrawable(R.drawable.ico_choosed_green),null,null,null);
                tvJingchaiChoose.setCompoundDrawablesWithIntrinsicBounds( Res.getDrawable(R.drawable.ico_not_choosed_black),null,null,null);

                layoutFeiqiwuLiangjianchoose.setVisibility(View.GONE);
                layoutJingcaiCarLingjianChoose.setVisibility(View.GONE);

                mChaiJieWay = Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI;
            }
        });



        /*rgChooseChaijieWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_jingchai:
                        layoutFeiqiwuLiangjianchoose.setVisibility(View.GONE);
                        layoutJingcaiCarLingjianChoose.setVisibility(View.VISIBLE);

                        mChaiJieWay = Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI;
                        break;
                    case R.id.rb_cuchai:
                        layoutFeiqiwuLiangjianchoose.setVisibility(View.GONE);
                        layoutJingcaiCarLingjianChoose.setVisibility(View.GONE);

                        mChaiJieWay = Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI;
                        break;
                }
            }
        });*/


        mLingJianAdapter = new GridAdapterForCarComponentDetail(mFaDongJiLingJianList, this, Config.TYPE_OF_LINGJIAN_FADONGJI_COMPONENT);
        mLingJianAdapter.setLingJianLayoutClickListener(new LingJianLayoutClickListenerImpl());
        /*mLingJianAdapter.setLingJianCheckBoxCheckListener(new LingJianCheckBoxCheckListenerImpl());*/

        gvComponentDetail.setAdapter(mLingJianAdapter);

        rgComponentChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_fadongji_component:
                        mLingJianAdapter.setLingJianData(mFaDongJiLingJianList);
                        mLingJianAdapter.setLingJianType(Config.TYPE_OF_LINGJIAN_FADONGJI_COMPONENT);

                        break;
                    case R.id.rb_dipan_component:
                        mLingJianAdapter.setLingJianData(mDiPanLingJianList);
                        mLingJianAdapter.setLingJianType(Config.TYPE_OF_LINGJIAN_DIPAN_COMPONENT);

                        break;
                    case R.id.rb_jiashishi_component:
                        mLingJianAdapter.setLingJianData(mJiaShiShiLingJianList);
                        mLingJianAdapter.setLingJianType(Config.TYPE_OF_LINGJIAN_JIASHISHI_COMPONENT);

                        break;
                    case R.id.rb_chexiang_component:
                        mLingJianAdapter.setLingJianData(mCheXiangLingJianList);
                        mLingJianAdapter.setLingJianType(Config.TYPE_OF_LINGJIAN_CHEXIANG_COMPONENT);

                        break;
                }

                /*gvComponentDetail.setAdapter(mLingJianAdapter);*/
                mLingJianAdapter.notifyDataSetChanged();
            }
        });


        initFeiQiWuView();
    }


    //初始化废弃物view
    private void initFeiQiWuView()
    {
        mNeedChaiJieDianChiFeiQiWuAdapter = new GridAdapterForCarComponentDetail(mNeedChaijieDianChiFeiQiWuParts,this,Config.TYPE_OF_LINGJIAN_NEED_CHAIJIE_DIANCHI_FEIQIWU);
        mNeedChaiJieDianChiFeiQiWuAdapter.setLingJianLayoutClickListener(new FeiQiWuLingJianClickListener());
        gvNeedChaijieDianchiFeiqiwu.setAdapter(mNeedChaiJieDianChiFeiQiWuAdapter);

        mNeedChaiJieOtherFeiQiWuAdapter = new GridAdapterForCarComponentDetail(mNeedChaijieOtherFeiQiWuParts,this,Config.TYPE_OF_LINGJIAN_NEED_CHAIJIE_OTHER_FEIQIWU);
        mNeedChaiJieOtherFeiQiWuAdapter.setLingJianLayoutClickListener(new FeiQiWuLingJianClickListener());
        gvNeedChaijieOtherFeiqiwu.setAdapter(mNeedChaiJieOtherFeiQiWuAdapter);

        mQueShiDianChiFeiQiWuAdapter = new GridAdapterForCarComponentDetail(mQueShiDianChiFeiQiWuParts,this,Config.TYPE_OF_LINGJIAN_QUESHI_DIANCHI_FEIQIWU);
        mQueShiDianChiFeiQiWuAdapter.setLingJianLayoutClickListener(new FeiQiWuLingJianClickListener());
        gvQueshiDianchiFeiqiwu.setAdapter(mQueShiDianChiFeiQiWuAdapter);

        mQueShiOtherFeiQiWuAdapter = new GridAdapterForCarComponentDetail(mQueShiOtherFeiQiWuParts,this,Config.TYPE_OF_LINGJIAN_QUESHI_OTHER_FEIQIWU);
        mQueShiOtherFeiQiWuAdapter.setLingJianLayoutClickListener(new FeiQiWuLingJianClickListener());
        gvQueshiOtherFeiqiwu.setAdapter(mQueShiOtherFeiQiWuAdapter);
    }

    @Override
    public void getFeiQiWuInfo() {
        mPresenter.getFeiQiWuInfo((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID,""));
    }

    @Override
    public void showGetFeiQiWuInfoSuccess(List<CarLingJianInfo> dianChiFeiQiWuParts,List<CarLingJianInfo> otherFeiQiWuParts,List<CarLingJianInfo> queShiDianChiFeiQiWuParts,List<CarLingJianInfo> queShiOtherFeiQiWuParts) {
        mNeedChaijieDianChiFeiQiWuParts.addAll(dianChiFeiQiWuParts);
        mNeedChaijieDianChiFeiQiWuParts.get(0).setCheck(true);

        mNeedChaijieOtherFeiQiWuParts.addAll(otherFeiQiWuParts);

        for (int i = 0;i<mNeedChaijieOtherFeiQiWuParts.size();i++)
        {
            mNeedChaijieOtherFeiQiWuParts.get(i).setCheck(true);
        }


        mQueShiDianChiFeiQiWuParts.addAll(queShiDianChiFeiQiWuParts);
        mQueShiOtherFeiQiWuParts.addAll(queShiOtherFeiQiWuParts);

        mNeedChaiJieDianChiFeiQiWuAdapter.notifyDataSetChanged();
        mNeedChaiJieOtherFeiQiWuAdapter.notifyDataSetChanged();

        mQueShiDianChiFeiQiWuAdapter.notifyDataSetChanged();
        mQueShiOtherFeiQiWuAdapter.notifyDataSetChanged();
    }

    @Override
    public void showGetFeiQiWuInfoError(String tip) {
        ToastUtil.showShort(this,tip);
    }

    @Override
    public void getWaitToAnalysisCarLingJianInfo() {
        mPresenter.getWaitToAnalysisCarLingJianInfo((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID,""));
    }


    @Override
    public void showWaitToAnalysisCarLingJianInfo(List<CarLingJianInfo> faDongJiLingJianList, List<CarLingJianInfo> diPanLingJianInfo, List<CarLingJianInfo> jiaShiShiLingJianInfo, List<CarLingJianInfo> cheXiangLingJianInfo) {
        mFaDongJiLingJianList.clear();
        mFaDongJiLingJianList.addAll(faDongJiLingJianList);

        mDiPanLingJianList.clear();
        mDiPanLingJianList.addAll(diPanLingJianInfo);

        mJiaShiShiLingJianList.clear();
        mJiaShiShiLingJianList.addAll(jiaShiShiLingJianInfo);

        mCheXiangLingJianList.clear();
        mCheXiangLingJianList.addAll(cheXiangLingJianInfo);

        mLingJianAdapter.notifyDataSetChanged();
    }


    @Override
    public void showGetWaitToAnalysisCarLingJianInfoError(String tip) {
        ToastUtil.showShort(this, tip);
    }

    @Override
    public void submitCarAnalysisInfo() {


        if (mChaiJieWay.equals(Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI)) {
            mAnalysisLingJianSubmitStr = makeChaiJieLingJianSubmitInfo()[0];
            mNotAnalysisLingJianSubmitStr = makeChaiJieLingJianSubmitInfo()[1];

            LogUtil.e("analysisLingJianInfo:"+mAnalysisLingJianSubmitStr);
            LogUtil.e("notAnalysisLingJianInfo:"+mNotAnalysisLingJianSubmitStr);

            mPresenter.submitCarAnalysisInfo(mCarInfo.getBaseid(), mChaiJieWay, (String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""), mAnalysisLingJianSubmitStr,mNotAnalysisLingJianSubmitStr);
        } else if (mChaiJieWay.equals(Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI)) {
            mFeiQiWuLingJianSubmitStr = makeFeiQiWuChaiJieInfo();
            mPresenter.submitCarAnalysisInfoCuChai(mCarInfo.getBaseid(), mChaiJieWay, (String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),mFeiQiWuLingJianSubmitStr);
        }

    }

    @Override
    public void showSubmitCarAnalysisInfoSuccess() {
        ToastUtil.showShort(this, "车辆分析信息提交成功");
        finish();
    }

    @Override
    public void showSubmitCarAnalysisInfoError(String tip) {
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
    public void setPresenter(WaitToAnalysisCarDetailContract.Presenter presenter) {

    }


    private class LingJianLayoutClickListenerImpl implements GridAdapterForCarComponentDetail.LingJianLayoutClickListener {

        @Override
        public void onLingJianLayoutClick(int position, int lingJianType) {
            switch (lingJianType) {
                case Config.TYPE_OF_LINGJIAN_FADONGJI_COMPONENT:

                    mFaDongJiLingJianList.get(position).setCheck(!mFaDongJiLingJianList.get(position).isCheck());
                    break;
                case Config.TYPE_OF_LINGJIAN_DIPAN_COMPONENT:

                    mDiPanLingJianList.get(position).setCheck(!mDiPanLingJianList.get(position).isCheck());
                    break;
                case Config.TYPE_OF_LINGJIAN_JIASHISHI_COMPONENT:

                    mJiaShiShiLingJianList.get(position).setCheck(!mJiaShiShiLingJianList.get(position).isCheck());
                    break;
                case Config.TYPE_OF_LINGJIAN_CHEXIANG_COMPONENT:

                    mCheXiangLingJianList.get(position).setCheck(!mCheXiangLingJianList.get(position).isCheck());
                    break;
            }
            mLingJianAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 废弃物零件点击时间
     */
    private class FeiQiWuLingJianClickListener implements GridAdapterForCarComponentDetail.LingJianLayoutClickListener {

        @Override
        public void onLingJianLayoutClick(int position, int lingJianType) {
            switch (lingJianType) {
                case Config.TYPE_OF_LINGJIAN_NEED_CHAIJIE_DIANCHI_FEIQIWU:
                    if (mNeedChaijieDianChiFeiQiWuParts.get(position).isCheck()==true)
                    {
                        return;
                    }else {

                        mNeedChaijieDianChiFeiQiWuParts.get(position).setCheck(true);
                        for (int i=0;i<mNeedChaijieDianChiFeiQiWuParts.size();i++)
                        {
                            if (!mNeedChaijieDianChiFeiQiWuParts.get(i).getPartname().equals(mNeedChaijieDianChiFeiQiWuParts.get(position).getPartname()))
                            {
                                mNeedChaijieDianChiFeiQiWuParts.get(i).setCheck(false);
                            }

                            mQueShiDianChiFeiQiWuParts.get(i).setCheck(false);
                        }
                    }

                    mNeedChaiJieDianChiFeiQiWuAdapter.notifyDataSetChanged();
                    mQueShiDianChiFeiQiWuAdapter.notifyDataSetChanged();

                    break;
                case Config.TYPE_OF_LINGJIAN_NEED_CHAIJIE_OTHER_FEIQIWU:

                    if (mNeedChaijieOtherFeiQiWuParts.get(position).isCheck()==true)
                    {
                        return;
                    }else {

                        mNeedChaijieOtherFeiQiWuParts.get(position).setCheck(true);
                        mQueShiOtherFeiQiWuParts.get(position).setCheck(false);
                    }

                    mNeedChaiJieOtherFeiQiWuAdapter.notifyDataSetChanged();
                    mQueShiOtherFeiQiWuAdapter.notifyDataSetChanged();


                    break;
                case Config.TYPE_OF_LINGJIAN_QUESHI_DIANCHI_FEIQIWU:

                    if (mQueShiDianChiFeiQiWuParts.get(position).isCheck()==true)
                    {
                        return;
                    }else {

                        mQueShiDianChiFeiQiWuParts.get(position).setCheck(true);
                        for (int i=0;i<mQueShiDianChiFeiQiWuParts.size();i++)
                        {
                            if (!mQueShiDianChiFeiQiWuParts.get(i).getPartname().equals(mQueShiDianChiFeiQiWuParts.get(position).getPartname()))
                            {
                                mQueShiDianChiFeiQiWuParts.get(i).setCheck(false);
                            }

                            mNeedChaijieDianChiFeiQiWuParts.get(i).setCheck(false);
                        }
                    }

                    mNeedChaiJieDianChiFeiQiWuAdapter.notifyDataSetChanged();
                    mQueShiDianChiFeiQiWuAdapter.notifyDataSetChanged();


                    break;
                case Config.TYPE_OF_LINGJIAN_QUESHI_OTHER_FEIQIWU:

                    if (mQueShiOtherFeiQiWuParts.get(position).isCheck()==true)
                    {
                        return;
                    }else {
                        mQueShiOtherFeiQiWuParts.get(position).setCheck(true);
                        mNeedChaijieOtherFeiQiWuParts.get(position).setCheck(false);
                    }

                    mNeedChaiJieOtherFeiQiWuAdapter.notifyDataSetChanged();
                    mQueShiOtherFeiQiWuAdapter.notifyDataSetChanged();

                    break;
            }

        }
    }



    /*private class LingJianCheckBoxCheckListenerImpl implements GridAdapterForCarComponentDetail.LingJianCheckBoxCheckListener{

        @Override
        public void onLingJianCheckBoxCheck(int position,int lingJianType) {

        }
    }*/


    //判断是否选择拆解零件
    private boolean checkHasChoosedLingJianToChaiJie() {
        boolean result = false;

        List<CarLingJianInfo> allLingJianInfo = new ArrayList<>();
        allLingJianInfo.addAll(mFaDongJiLingJianList);
        allLingJianInfo.addAll(mDiPanLingJianList);
        allLingJianInfo.addAll(mJiaShiShiLingJianList);
        allLingJianInfo.addAll(mCheXiangLingJianList);

        for (CarLingJianInfo carLingJianInfo : allLingJianInfo) {
            if (carLingJianInfo.isCheck()) {
                result = true;
                break;
            }
        }

        return result;
    }

    //构建拆解零件的选择信息
    private String[] makeChaiJieLingJianSubmitInfo() {
        StringBuilder analysisLingJianSubmitInfoSB = new StringBuilder();

        StringBuilder notAnalysisLingJianSubmitInfoSB = new StringBuilder();

        for (int i = 0; i < mFaDongJiLingJianList.size(); i++) {
            if (mFaDongJiLingJianList.get(i).isCheck()) {
                analysisLingJianSubmitInfoSB.append(mFaDongJiLingJianList.get(i).getPartname());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("发动机总成");
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append(mFaDongJiLingJianList.get(i).getId());
                analysisLingJianSubmitInfoSB.append(",");
            }

            if (!mFaDongJiLingJianList.get(i).isCheck()) {
                notAnalysisLingJianSubmitInfoSB.append(mFaDongJiLingJianList.get(i).getPartname());
                notAnalysisLingJianSubmitInfoSB.append("^");
                notAnalysisLingJianSubmitInfoSB.append("发动机总成");
                notAnalysisLingJianSubmitInfoSB.append("^");
                notAnalysisLingJianSubmitInfoSB.append(mFaDongJiLingJianList.get(i).getId());
                notAnalysisLingJianSubmitInfoSB.append(",");
            }
        }

        for (int i = 0; i < mDiPanLingJianList.size(); i++) {
            if (mDiPanLingJianList.get(i).isCheck()) {
                analysisLingJianSubmitInfoSB.append(mDiPanLingJianList.get(i).getPartname());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("底盘总成");
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append(mDiPanLingJianList.get(i).getId());
                analysisLingJianSubmitInfoSB.append(",");
            }


            if (!mDiPanLingJianList.get(i).isCheck()) {
                notAnalysisLingJianSubmitInfoSB.append(mDiPanLingJianList.get(i).getPartname());
                notAnalysisLingJianSubmitInfoSB.append("^");
                notAnalysisLingJianSubmitInfoSB.append("底盘总成");
                notAnalysisLingJianSubmitInfoSB.append("^");
                notAnalysisLingJianSubmitInfoSB.append(mDiPanLingJianList.get(i).getId());
                notAnalysisLingJianSubmitInfoSB.append(",");
            }



        }


        for (int i = 0; i < mJiaShiShiLingJianList.size(); i++) {
            if (mJiaShiShiLingJianList.get(i).isCheck()) {
                analysisLingJianSubmitInfoSB.append(mJiaShiShiLingJianList.get(i).getPartname());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("驾驶室总成");
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append(mJiaShiShiLingJianList.get(i).getId());
                analysisLingJianSubmitInfoSB.append(",");
            }

            if (!mJiaShiShiLingJianList.get(i).isCheck()) {
                notAnalysisLingJianSubmitInfoSB.append(mJiaShiShiLingJianList.get(i).getPartname());
                notAnalysisLingJianSubmitInfoSB.append("^");
                notAnalysisLingJianSubmitInfoSB.append("驾驶室总成");
                notAnalysisLingJianSubmitInfoSB.append("^");
                notAnalysisLingJianSubmitInfoSB.append(mJiaShiShiLingJianList.get(i).getId());
                notAnalysisLingJianSubmitInfoSB.append(",");
            }
        }


        for (int i = 0; i < mCheXiangLingJianList.size(); i++) {
            if (mCheXiangLingJianList.get(i).isCheck()) {
                analysisLingJianSubmitInfoSB.append(mCheXiangLingJianList.get(i).getPartname());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("车箱总成");
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append(mCheXiangLingJianList.get(i).getId());
                analysisLingJianSubmitInfoSB.append(",");
            }

            if (!mCheXiangLingJianList.get(i).isCheck()) {
                notAnalysisLingJianSubmitInfoSB.append(mCheXiangLingJianList.get(i).getPartname());
                notAnalysisLingJianSubmitInfoSB.append("^");
                notAnalysisLingJianSubmitInfoSB.append("车箱总成");
                notAnalysisLingJianSubmitInfoSB.append("^");
                notAnalysisLingJianSubmitInfoSB.append(mCheXiangLingJianList.get(i).getId());
                notAnalysisLingJianSubmitInfoSB.append(",");
            }
        }



        //添加废弃物拆解信息
        for (int i = 0; i < mNeedChaijieDianChiFeiQiWuParts.size(); i++) {
            if (mNeedChaijieDianChiFeiQiWuParts.get(i).isCheck()) {
                analysisLingJianSubmitInfoSB.append(mNeedChaijieDianChiFeiQiWuParts.get(i).getPartname());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("危险废弃物");
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append(mNeedChaijieDianChiFeiQiWuParts.get(i).getId());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("否");
                analysisLingJianSubmitInfoSB.append(",");
            }else {
                analysisLingJianSubmitInfoSB.append(mNeedChaijieDianChiFeiQiWuParts.get(i).getPartname());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("危险废弃物");
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append(mNeedChaijieDianChiFeiQiWuParts.get(i).getId());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("是");
                analysisLingJianSubmitInfoSB.append(",");
            }
        }


        for (int i = 0; i < mNeedChaijieOtherFeiQiWuParts.size(); i++) {
            if (mNeedChaijieOtherFeiQiWuParts.get(i).isCheck()) {
                analysisLingJianSubmitInfoSB.append(mNeedChaijieOtherFeiQiWuParts.get(i).getPartname());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("危险废弃物");
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append(mNeedChaijieOtherFeiQiWuParts.get(i).getId());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("否");
                analysisLingJianSubmitInfoSB.append(",");
            }else {
                analysisLingJianSubmitInfoSB.append(mNeedChaijieOtherFeiQiWuParts.get(i).getPartname());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("危险废弃物");
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append(mNeedChaijieOtherFeiQiWuParts.get(i).getId());
                analysisLingJianSubmitInfoSB.append("^");
                analysisLingJianSubmitInfoSB.append("是");
                analysisLingJianSubmitInfoSB.append(",");
            }
        }


        String[] analysisLingJianAndNotAnalysisLingJianInfo = {analysisLingJianSubmitInfoSB.substring(0, analysisLingJianSubmitInfoSB.length() - 1),
                                                                notAnalysisLingJianSubmitInfoSB.substring(0, notAnalysisLingJianSubmitInfoSB.length() - 1)};

        return analysisLingJianAndNotAnalysisLingJianInfo;
    }


    //构建拆解废弃物提交字符串
    private String makeFeiQiWuChaiJieInfo()
    {
        StringBuilder feiQiWuLingJianSubmitInfoSB = new StringBuilder();

        //添加废弃物拆解信息
        for (int i = 0; i < mNeedChaijieDianChiFeiQiWuParts.size(); i++) {
            if (mNeedChaijieDianChiFeiQiWuParts.get(i).isCheck()) {
                feiQiWuLingJianSubmitInfoSB.append(mNeedChaijieDianChiFeiQiWuParts.get(i).getPartname());
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append("危险废弃物");
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append(mNeedChaijieDianChiFeiQiWuParts.get(i).getId());
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append("否");
                feiQiWuLingJianSubmitInfoSB.append(",");
            }else {
                feiQiWuLingJianSubmitInfoSB.append(mNeedChaijieDianChiFeiQiWuParts.get(i).getPartname());
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append("危险废弃物");
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append(mNeedChaijieDianChiFeiQiWuParts.get(i).getId());
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append("是");
                feiQiWuLingJianSubmitInfoSB.append(",");
            }
        }


        for (int i = 0; i < mNeedChaijieOtherFeiQiWuParts.size(); i++) {
            if (mNeedChaijieOtherFeiQiWuParts.get(i).isCheck()) {
                feiQiWuLingJianSubmitInfoSB.append(mNeedChaijieOtherFeiQiWuParts.get(i).getPartname());
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append("危险废弃物");
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append(mNeedChaijieOtherFeiQiWuParts.get(i).getId());
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append("否");
                feiQiWuLingJianSubmitInfoSB.append(",");
            }else {
                feiQiWuLingJianSubmitInfoSB.append(mNeedChaijieOtherFeiQiWuParts.get(i).getPartname());
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append("危险废弃物");
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append(mNeedChaijieOtherFeiQiWuParts.get(i).getId());
                feiQiWuLingJianSubmitInfoSB.append("^");
                feiQiWuLingJianSubmitInfoSB.append("是");
                feiQiWuLingJianSubmitInfoSB.append(",");
            }
        }

        //如果废弃物拆解内容存在，则返回废弃物拆解内容
        if (feiQiWuLingJianSubmitInfoSB.length()>0)
        {
            return feiQiWuLingJianSubmitInfoSB.substring(0, feiQiWuLingJianSubmitInfoSB.length() - 1);
        //否则返回空字符串
        }else {
            return "";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
