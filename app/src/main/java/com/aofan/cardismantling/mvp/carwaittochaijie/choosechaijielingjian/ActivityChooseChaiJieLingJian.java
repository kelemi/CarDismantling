/*
package com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijielingjian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.expandlist.ExpandAdapterForLingJianChoose;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.LingJIanOfCanChooseChaiJie;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.RequestAndResultCode;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.view.RxView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.functions.Func1;

import static com.aofan.cardismantling.R.id.lv;

*/
/**
 * Created by Administrator on 2016/11/21.
 *//*


public class ActivityChooseChaiJieLingJian extends BaseActivity implements ChooseChaiJieLingJianContract.View {

    */
/*private LinearLayout layoutSubmit;
    private TextView tvSubmit;
    private ExpandableListView lv;
    private TextView tvEmptyTip;*//*


    private RadioGroup rgPartAnalysisstateChoose;
    private RadioButton rbHasAnalysisPart;
    private RadioButton rbNotAnalysisPart;
    private LinearLayout layoutSubmit;
    private TextView tvSubmit;
    private ExpandableListView lvOfHasAnalysisPart;
    private ExpandableListView lvOfNotAnalysisPart;
    private TextView tvEmptyTip;




    ExpandAdapterForLingJianChoose mHasAnalysisLingJianAdapter;
    ExpandAdapterForLingJianChoose mNotAnalysisLingJianAdapter;

    List<String> mParentPartInfoList = new ArrayList<>();
    //已经分析的零件
    List<List<LingJIanOfCanChooseChaiJie>> mHasAnalysisLingJianList = new ArrayList<>();
    //未分析的零件
    List<List<LingJIanOfCanChooseChaiJie>> mNotAnalysisLingJianList = new ArrayList<>();
    //选择的去派工的零件
    List<LingJIanOfCanChooseChaiJie> mChoosedLingJianList = new ArrayList<>();

    String mCarDid;
    //当前选择的零件分析状态
    String mCurChooseLingJianAnalysisState;

    ChooseChaiJieLingJianContract.Presenter mChooseChaiJieLingPresenter;

    //是否已经
    boolean isHasAnalysisPartGetData;
    boolean isNotAnalysisPartGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_chaijie_lingjian);
        mChooseChaiJieLingPresenter = new ChooseChaiJieLingJianPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        initTitleWithRightTvOrIv(null, null, "选择拆解零件", true, null, null);
        initData();
        assignView();
        initView();
        getChaiJieLingJian();
    }

    @Override
    public void initData() {
        mCarDid = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_DID);
        mCurChooseLingJianAnalysisState = Constant.STATE_OF_HAS_ANALYSIS;

        mParentPartInfoList.add("发动机总成");
        mParentPartInfoList.add("底盘总成");
        mParentPartInfoList.add("驾驶室总成");
        mParentPartInfoList.add("车箱总成");
    }

    @Override
    public void assignView() {
        rgPartAnalysisstateChoose = (RadioGroup) findViewById(R.id.rg_part_analysisstate_choose);
        rbHasAnalysisPart = (RadioButton) findViewById(R.id.rb_has_analysis_part);
        rbNotAnalysisPart = (RadioButton) findViewById(R.id.rb_not_analysis_part);
        layoutSubmit = (LinearLayout) findViewById(R.id.layout_submit);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        lvOfHasAnalysisPart = (ExpandableListView) findViewById(R.id.lv_of_has_analysis_part);
        lvOfNotAnalysisPart = (ExpandableListView) findViewById(R.id.lv_of_not_analysis_part);
        tvEmptyTip = (TextView) findViewById(R.id.tv_empty_tip);

    }

    private void initAdapter() {
        mHasAnalysisLingJianAdapter = new ExpandAdapterForLingJianChoose(this, mParentPartInfoList, mHasAnalysisLingJianList);
        mHasAnalysisLingJianAdapter.setLingJianItemClickListener(new LingJianItemClickListenerImpl());
        lvOfHasAnalysisPart.setGroupIndicator(null);
        lvOfHasAnalysisPart.setChildIndicator(null);
        lvOfHasAnalysisPart.setAdapter(mHasAnalysisLingJianAdapter);


        mNotAnalysisLingJianAdapter = new ExpandAdapterForLingJianChoose(this, mParentPartInfoList, mNotAnalysisLingJianList);
        mNotAnalysisLingJianAdapter.setLingJianItemClickListener(new LingJianItemClickListenerImpl());
        lvOfNotAnalysisPart.setGroupIndicator(null);
        lvOfNotAnalysisPart.setChildIndicator(null);
        lvOfNotAnalysisPart.setAdapter(mNotAnalysisLingJianAdapter);

    }

    @Override
    public void initView() {
       */
/* mChaiJieLingJianAdapter = new ExpandAdapterForLingJianChoose(this,mParentPartInfoList,mCanChooseChaiJieLingJianList);
        mChaiJieLingJianAdapter.setLingJianItemClickListener(new LingJianItemClickListenerImpl());
        lv.setGroupIndicator(null);
        lv.setChildIndicator(null);
        lv.setAdapter(mChaiJieLingJianAdapter);*//*

        initAdapter();

        rgPartAnalysisstateChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.rb_has_analysis_part:

                        lvOfNotAnalysisPart.setVisibility(View.GONE);

                        break;
                    case R.id.rb_not_analysis_part:

                        lvOfHasAnalysisPart.setVisibility(View.GONE);

                        break;
                }
            }
        });

        RxView.clicks(tvSubmit)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        return CommonUtil.validateAndToast(validateIsChooseLingJian(), "请选择要拆解的零件");
                    }
                })
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent backLingJian = new Intent();
                        backLingJian.putExtra(IntentKey.INTENT_KEY_CHOOSED_CHAIJIE_LINGJIAN, (Serializable) mChoosedLingJianList);
                        setResult(RequestAndResultCode.RESULT_CODE_TO_CHOOSE_CHAIJIE_LINGJIAN_SUCCESS, backLingJian);
                        finish();
                    }
                });

    }

    private boolean validateIsChooseLingJian() {

        return mChoosedLingJianList.size() > 0;
    }

    @Override
    public void getChaiJieLingJian() {
        mChooseChaiJieLingPresenter.getChaiJieLingJian(mCarDid, mCurChooseLingJianAnalysisState);
    }

    @Override
    public void showChaiJieLingJian(List<LingJIanOfCanChooseChaiJie> allCanChooseChaiJieLingJianList) {

        if (CommonUtil.isListNullOrEmpty(allCanChooseChaiJieLingJianList) == true) {

            tvEmptyTip.setText("没有可以选择去拆解的零件");
            tvEmptyTip.setVisibility(View.VISIBLE);

            layoutSubmit.setVisibility(View.GONE);
            lv.setVisibility(View.GONE);

        } else {

            tvEmptyTip.setVisibility(View.GONE);

            layoutSubmit.setVisibility(View.VISIBLE);
            lv.setVisibility(View.VISIBLE);

            splitChaiJieLingJianList(allCanChooseChaiJieLingJianList);
            for (int i = 0; i < mParentPartInfoList.size(); i++) {
                lv.expandGroup(i);
            }
        }

        //mAllLingJian = allCanChooseChaiJieLingJianList;

    }

    @Override
    public void showGetChaiJieLingJianError(String tip) {
        ToastUtil.showShort(this,tip);
        tvEmptyTip.setText("没有可以选择去拆解的零件");
        tvEmptyTip.setVisibility(View.VISIBLE);

        layoutSubmit.setVisibility(View.GONE);
        lv.setVisibility(View.GONE);

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
    public void setPresenter(ChooseChaiJieLingJianContract.Presenter presenter) {

    }


    private void splitChaiJieLingJianList(List<LingJIanOfCanChooseChaiJie> allChaiJieLingJianList) {
        List<LingJIanOfCanChooseChaiJie> faDongJiChaiJieLingJianList = new ArrayList<>();
        List<LingJIanOfCanChooseChaiJie> diPanChaiJieLingJianList = new ArrayList<>();
        List<LingJIanOfCanChooseChaiJie> jiaShiShiChaiJieLingJianList = new ArrayList<>();
        List<LingJIanOfCanChooseChaiJie> cheXiangChaiJieLingJianList = new ArrayList<>();

        for (LingJIanOfCanChooseChaiJie lingJianOfChaiJie : allChaiJieLingJianList) {
            if (lingJianOfChaiJie.getType().equals(Config.TYPE_STR_OF_LINGJIAN_COMPONENT_FADONGJI)) {
                faDongJiChaiJieLingJianList.add(lingJianOfChaiJie);
            } else if (lingJianOfChaiJie.getType().equals(Config.TYPE_STR_OF_LINGJIAN_COMPONENT_DIPAN)) {
                diPanChaiJieLingJianList.add(lingJianOfChaiJie);
            } else if (lingJianOfChaiJie.getType().equals(Config.TYPE_STR_OF_LINGJIAN_COMPONENT_JIASHISHI)) {
                jiaShiShiChaiJieLingJianList.add(lingJianOfChaiJie);
            } else if (lingJianOfChaiJie.getType().equals(Config.TYPE_STR_OF_LINGJIAN_COMPONENT_CHEXIANG)) {
                cheXiangChaiJieLingJianList.add(lingJianOfChaiJie);
            }
        }


        mCanChooseChaiJieLingJianList.add(faDongJiChaiJieLingJianList);
        mCanChooseChaiJieLingJianList.add(diPanChaiJieLingJianList);
        mCanChooseChaiJieLingJianList.add(jiaShiShiChaiJieLingJianList);
        mCanChooseChaiJieLingJianList.add(cheXiangChaiJieLingJianList);

    }

    //零件item点击监听
    private class LingJianItemClickListenerImpl implements ExpandAdapterForLingJianChoose.LingJianItemClickListener {

        @Override
        public void onLingJianItemClick(int groupPos, int childPos) {
            if (mCanChooseChaiJieLingJianList.get(groupPos).get(childPos).isCheck()) {
                mChoosedLingJianList.remove(mCanChooseChaiJieLingJianList.get(groupPos).get(childPos));

                mCanChooseChaiJieLingJianList.get(groupPos).get(childPos).setCheck(false);

            } else {
                mChoosedLingJianList.add(mCanChooseChaiJieLingJianList.get(groupPos).get(childPos));

                mCanChooseChaiJieLingJianList.get(groupPos).get(childPos).setCheck(true);
            }

            */
/*mChaiJieLingJianAdapter = new ExpandAdapterForLingJianChoose(ActivityChooseChaiJieLingJian.this,mParentPartInfoList,mCanChooseChaiJieLingJianList);
            lv.setAdapter(mChaiJieLingJianAdapter);*//*

            //initAdapter();
            mChaiJieLingJianAdapter.refresh();
            for (int i = 0; i < mParentPartInfoList.size(); i++) {
                lv.collapseGroup(i);
                lv.expandGroup(i);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
*/
