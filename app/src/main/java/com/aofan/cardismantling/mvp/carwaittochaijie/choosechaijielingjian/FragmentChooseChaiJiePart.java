package com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijielingjian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.expandlist.ExpandAdapterForLingJianChoose;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.LingJIanOfCanChooseChaiJie;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.RequestAndResultCode;
import com.aofan.cardismantling.mvp.base.BaseFragment;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.view.RxView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 选择拆解零件frag
 * Created by Administrator on 2017/1/3.
 */

public class FragmentChooseChaiJiePart extends BaseFragment implements ChooseChaiJieLingJianContract.View {

    public static final String CAR_ID = "carid";
    public static final String ANALYSIS_STATE = "analysisstate";

    public String mCarId;
    public String mAnalysisState;

    ChooseChaiJieLingJianContract.Presenter mPresenter;

    public static FragmentChooseChaiJiePart getInstance(String carId, String analysisState) {
        Bundle bundle = new Bundle();
        bundle.putString(CAR_ID, carId);
        bundle.putString(ANALYSIS_STATE, analysisState);
        FragmentChooseChaiJiePart fragment = new FragmentChooseChaiJiePart();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCarId = bundle.getString(CAR_ID);
            mAnalysisState = bundle.getString(ANALYSIS_STATE);
        }

        mPresenter = new ChooseChaiJieLingJianPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    private LinearLayout layoutSubmit;
    private TextView tvSubmit;
    private ExpandableListView lvOfPart;
    private TextView tvEmptyTip;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_choose_chaijie_part, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        assignView(view);
        initView();
    }

    //默认的是isFirstVisibles true ,刚进来的时候分两种情况
    //1.如果切过来的时候 setVisibleHint在OnCreate之前调用，那isFirstVisible为true，这个时候不进行任何操作；
    //等方法执行到onActivityCreated的时候，在执行一次setUserVisibleHint 这个时候把默认的是isFirstVisibles 设为false
    //然后这个时候调用setUserVISIBLEhINT为true  去获取数据
    //2.如果是后面再次切过来，那isFirstVisible已经为false，那就可以直接通过setUserVisibleHint获取数据了

    //代表这个界面是不是第一次被使用
    boolean isFirstUsed = true;
    boolean isGetData = false;

    Integer mPageIndex;


    ExpandAdapterForLingJianChoose mLingJianAdapter;


    List<String> mParentPartInfoList = new ArrayList<>();
    //子零件列表
    List<List<LingJIanOfCanChooseChaiJie>> mChildPartList = new ArrayList<>();

    //选择的去派工的零件
    List<LingJIanOfCanChooseChaiJie> mChoosedLingJianList = new ArrayList<>();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //fragment 可见的情况下 进行加载数据。不可见则不加载
        if (getUserVisibleHint() == true) {
            if (isFirstUsed == true) {
                isFirstUsed = false;
                setUserVisibleHint(true);
            }
            //不可见，说明fragment已经初始话完成,就直接把isFirstVisible改为false 再次切过来的时候就直接可以加载数据了
        } else {
            isFirstUsed = false;
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // LogUtil.e("CartVisible");
        if (getUserVisibleHint()) {
            LogUtil.e("fragVisible");
            //如果不是firstvisible才去进行下面的操作  因为第一次很可能出现各种问题
            if (isFirstUsed == false) {

                if (isGetData == false) {
                    getChaiJieLingJian();
                }

            }
        } else {

        }
    }


    @Override
    public void initData() {
        mParentPartInfoList.add("发动机总成");
        mParentPartInfoList.add("底盘总成");
        mParentPartInfoList.add("驾驶室总成");
        mParentPartInfoList.add("车箱总成");
        mParentPartInfoList.add("危险废弃物");
    }

    @Override
    public void assignView(View view) {
        layoutSubmit = (LinearLayout) view.findViewById(R.id.layout_submit);
        tvSubmit = (TextView) view.findViewById(R.id.tv_submit);
        lvOfPart = (ExpandableListView) view.findViewById(R.id.lv_of_part);
        tvEmptyTip = (TextView) view.findViewById(R.id.tv_empty_tip);
    }

    @Override
    public void initView() {

        initAdapter();

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
                        getActivity().setResult(RequestAndResultCode.RESULT_CODE_TO_CHOOSE_CHAIJIE_LINGJIAN_SUCCESS, backLingJian);
                        getActivity().finish();
                    }
                });
    }

    private void initAdapter()
    {
        mLingJianAdapter = new ExpandAdapterForLingJianChoose(getActivity(), mParentPartInfoList, mChildPartList);
        mLingJianAdapter.setLingJianItemClickListener(new LingJianItemClickListenerImpl());
        lvOfPart.setGroupIndicator(null);
        lvOfPart.setChildIndicator(null);
        lvOfPart.setAdapter(mLingJianAdapter);
    }

    @Override
    public void getData() {

    }

    @Override
    public void getChaiJieLingJian() {
        mPresenter.getChaiJieLingJian(mCarId, mAnalysisState);
    }

    @Override
    public void showChaiJieLingJian(List<LingJIanOfCanChooseChaiJie> allCanChooseChaiJieLingJianList) {
        if (CommonUtil.isListNullOrEmpty(allCanChooseChaiJieLingJianList) == true) {

            tvEmptyTip.setText("没有可以选择去拆解的零件");
            tvEmptyTip.setVisibility(View.VISIBLE);

            layoutSubmit.setVisibility(View.GONE);
            lvOfPart.setVisibility(View.GONE);

        } else {

            tvEmptyTip.setVisibility(View.GONE);

            layoutSubmit.setVisibility(View.VISIBLE);
            lvOfPart.setVisibility(View.VISIBLE);

            splitChaiJieLingJianList(allCanChooseChaiJieLingJianList);
            initAdapter();
            for (int i = 0; i < mParentPartInfoList.size(); i++) {
                //lvOfPart.collapseGroup(i);
                lvOfPart.expandGroup(i);
            }
        }
    }

    @Override
    public void showGetChaiJieLingJianError(String tip) {
        ToastUtil.showShort(getActivity(), tip);
        tvEmptyTip.setText("没有可以选择去拆解的零件");
        tvEmptyTip.setVisibility(View.VISIBLE);

        layoutSubmit.setVisibility(View.GONE);
        lvOfPart.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void setPresenter(ChooseChaiJieLingJianContract.Presenter presenter) {

    }

    private void splitChaiJieLingJianList(List<LingJIanOfCanChooseChaiJie> allChaiJieLingJianList) {
        List<LingJIanOfCanChooseChaiJie> faDongJiChaiJieLingJianList = new ArrayList<>();
        List<LingJIanOfCanChooseChaiJie> diPanChaiJieLingJianList = new ArrayList<>();
        List<LingJIanOfCanChooseChaiJie> jiaShiShiChaiJieLingJianList = new ArrayList<>();
        List<LingJIanOfCanChooseChaiJie> cheXiangChaiJieLingJianList = new ArrayList<>();
        List<LingJIanOfCanChooseChaiJie> feiQiWuChaiJieLingJianList = new ArrayList<>();

        for (LingJIanOfCanChooseChaiJie lingJianOfChaiJie : allChaiJieLingJianList) {
            if (lingJianOfChaiJie.getType().equals(Config.TYPE_STR_OF_LINGJIAN_COMPONENT_FADONGJI)) {
                faDongJiChaiJieLingJianList.add(lingJianOfChaiJie);
            } else if (lingJianOfChaiJie.getType().equals(Config.TYPE_STR_OF_LINGJIAN_COMPONENT_DIPAN)) {
                diPanChaiJieLingJianList.add(lingJianOfChaiJie);
            } else if (lingJianOfChaiJie.getType().equals(Config.TYPE_STR_OF_LINGJIAN_COMPONENT_JIASHISHI)) {
                jiaShiShiChaiJieLingJianList.add(lingJianOfChaiJie);
            } else if (lingJianOfChaiJie.getType().equals(Config.TYPE_STR_OF_LINGJIAN_COMPONENT_CHEXIANG)) {
                cheXiangChaiJieLingJianList.add(lingJianOfChaiJie);
            }else if (lingJianOfChaiJie.getType().equals(Config.TYPE_STR_OF_LINGJIAN_DANGEROUS_FEIQIWU)) {
                feiQiWuChaiJieLingJianList.add(lingJianOfChaiJie);
            }
        }


        mChildPartList.add(faDongJiChaiJieLingJianList);
        mChildPartList.add(diPanChaiJieLingJianList);
        mChildPartList.add(jiaShiShiChaiJieLingJianList);
        mChildPartList.add(cheXiangChaiJieLingJianList);
        mChildPartList.add(feiQiWuChaiJieLingJianList);

    }

    //零件item点击监听
    private class LingJianItemClickListenerImpl implements ExpandAdapterForLingJianChoose.LingJianItemClickListener {

        @Override
        public void onLingJianItemClick(int groupPos, int childPos) {
            if (mChildPartList.get(groupPos).get(childPos).isCheck()) {
                mChoosedLingJianList.remove(mChildPartList.get(groupPos).get(childPos));

                mChildPartList.get(groupPos).get(childPos).setCheck(false);

            } else {
                mChoosedLingJianList.add(mChildPartList.get(groupPos).get(childPos));

                mChildPartList.get(groupPos).get(childPos).setCheck(true);
            }

            /*mChaiJieLingJianAdapter = new ExpandAdapterForLingJianChoose(ActivityChooseChaiJieLingJian.this,mParentPartInfoList,mCanChooseChaiJieLingJianList);
            lv.setAdapter(mChaiJieLingJianAdapter);*/
            //initAdapter();
            mLingJianAdapter.refresh();
            for (int i = 0; i < mParentPartInfoList.size(); i++) {
                lvOfPart.collapseGroup(i);
                lvOfPart.expandGroup(i);
            }
        }
    }

    private boolean validateIsChooseLingJian() {

        return mChoosedLingJianList.size() > 0;
    }
}
