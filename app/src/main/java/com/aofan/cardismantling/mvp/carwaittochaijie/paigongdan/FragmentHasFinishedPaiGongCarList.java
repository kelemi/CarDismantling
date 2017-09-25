package com.aofan.cardismantling.mvp.carwaittochaijie.paigongdan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForCarOfPaiGongDan;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.CarOfPaiGongDan;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseFragment;
import com.aofan.cardismantling.mvp.carwaittochaijie.carchaijiepaigongdanlist.ActivityOneCarChaiJiePaiGongDanList;
import com.aofan.cardismantling.mvp.carwaittochaijie.carchaijiepaigongdanlist.ActivityOneCarChaiJiePaiGongDanListForCuChai;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 已经完成派工的汽车列表
 * Created by Administrator on 2016/11/24.
 */

public class FragmentHasFinishedPaiGongCarList extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, PaiGongCarListContract.View {

    public static final String ARGUMENT = "argument";

    public String mArgument;

    public static FragmentHasFinishedPaiGongCarList getInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        FragmentHasFinishedPaiGongCarList fragment = new FragmentHasFinishedPaiGongCarList();
        fragment.setArguments(bundle);
        return fragment;
    }


    PaiGongCarListContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mArgument = bundle.getString(ARGUMENT);
        }
        mPresenter = new PaiGongCarListPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }


    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;

    List<CarOfPaiGongDan> mCarOfPaiGongDanList = new ArrayList<>();
    ListAdapterForCarOfPaiGongDan mAdapter;

    //默认的是isFirstVisibles true ,刚进来的时候分两种情况
    //1.如果切过来的时候 setVisibleHint在OnCreate之前调用，那isFirstVisible为true，这个时候不进行任何操作；
    //等方法执行到onActivityCreated的时候，在执行一次setUserVisibleHint 这个时候把默认的是isFirstVisibles 设为false
    //然后这个时候调用setUserVISIBLEhINT为true  去获取数据
    //2.如果是后面再次切过来，那isFirstVisible已经为false，那就可以直接通过setUserVisibleHint获取数据了

    //代表这个界面是不是第一次被使用
    boolean isFirstUsed = true;
    boolean isGetData = false;

    Integer mPageIndex;
    boolean isRefresh = false;
    boolean canLoad = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.view_single_refresh_lv_without_titlebar, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        assignView(view);
        initView();
    }


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
                    getOriginalList();
                }


            }
        } else {

        }
    }

    private void getOriginalList() {
        isRefresh = true;
        mPageIndex = 1;
        //加载数据的具体代码
        getPaiGongCarList();

    }

    private void getMoreList() {
        if (canLoad) {
            mPageIndex++;
            //加载数据的具体代码
            getPaiGongCarList();
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void assignView(View view) {
        lv = (PullToRefreshListView) view.findViewById(R.id.lv);
        originalLv = lv.getRefreshableView();
        emptylayout = (RelativeLayout) view.findViewById(R.id.emptylayout);
        emptytiptv = (TextView) view.findViewById(R.id.emptytiptv);
    }

    @Override
    public void initView() {
        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lv.setOnRefreshListener(this);

        mAdapter = new ListAdapterForCarOfPaiGongDan(mCarOfPaiGongDanList, getActivity());
        originalLv.setAdapter(mAdapter);
        originalLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Intent toOneCarPaiGongDanList = new Intent(getActivity(), ActivityOneCarChaiJiePaiGongDanList.class);
                toOneCarPaiGongDanList.putExtra(IntentKey.INTENT_KEY_CAR_INFO_OF_PAIGONGDAN, mCarOfPaiGongDanList.get(i - 1));
                toOneCarPaiGongDanList.putExtra(IntentKey.INTENT_KEY_CAR_PAIGONGSTATE, Config.TYPE_OF_PAIGONG_STATE_HAS_PAIGONG);

                startActivity(toOneCarPaiGongDanList);*/
                if (mCarOfPaiGongDanList.get(i - 1).getCjtype().equals(Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI))
                {
                    Intent toOneCarPaiGongDanList = new Intent(getActivity(), ActivityOneCarChaiJiePaiGongDanList.class);

                    toOneCarPaiGongDanList.putExtra(IntentKey.INTENT_KEY_CAR_CHAIJIE_TYPE, mCarOfPaiGongDanList.get(i - 1).getCjtype());
                    toOneCarPaiGongDanList.putExtra(IntentKey.INTENT_KEY_CAR_INFO_OF_PAIGONGDAN, mCarOfPaiGongDanList.get(i - 1));
                    toOneCarPaiGongDanList.putExtra(IntentKey.INTENT_KEY_CAR_PAIGONGSTATE, Config.TYPE_OF_PAIGONG_STATE_HAS_PAIGONG);
                    startActivity(toOneCarPaiGongDanList);
                }else if (mCarOfPaiGongDanList.get(i - 1).getCjtype().equals(Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI)){
                    Intent toOneCarPaiGongDanList = new Intent(getActivity(), ActivityOneCarChaiJiePaiGongDanListForCuChai.class);

                    toOneCarPaiGongDanList.putExtra(IntentKey.INTENT_KEY_CAR_CHAIJIE_TYPE, mCarOfPaiGongDanList.get(i - 1).getCjtype());
                    toOneCarPaiGongDanList.putExtra(IntentKey.INTENT_KEY_CAR_INFO_OF_PAIGONGDAN, mCarOfPaiGongDanList.get(i - 1));
                    toOneCarPaiGongDanList.putExtra(IntentKey.INTENT_KEY_CAR_PAIGONGSTATE, Config.TYPE_OF_PAIGONG_STATE_HAS_PAIGONG);
                    startActivity(toOneCarPaiGongDanList);
                }
            }
        });
    }

    @Override
    public void getData() {

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
    public void getPaiGongCarList() {
        mPresenter.getPaiGongCarList((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),
                Config.TYPE_OF_PAIGONG_STATE_HAS_PAIGONG,
                mPageIndex.toString(),
                Config.DEFAULT_PAGE_SIZE.toString());
    }

    @Override
    public void showPaiGongCarList(List<CarOfPaiGongDan> carOfPaiGongDanList) {
        lv.onRefreshComplete();

        if (isRefresh) {
            mCarOfPaiGongDanList.clear();
            isRefresh = false;
        }
        if (carOfPaiGongDanList.size() > 0) {
            if (carOfPaiGongDanList.size() >= Config.DEFAULT_PAGE_SIZE) {
                lv.setMode(PullToRefreshBase.Mode.BOTH);
                canLoad = true;
            } else {
                canLoad = false;
                lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            mCarOfPaiGongDanList.addAll(carOfPaiGongDanList);

        } else {
            canLoad = false;
            lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            if (mCarOfPaiGongDanList.size() == 0) {
                lv.setEmptyView(emptylayout);
            } else {
                ToastUtil.show(getActivity(), "没有获取到更多数据", 2000);
            }
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String tip) {
        lv.onRefreshComplete();
        ToastUtil.showShort(getActivity(), tip);
        if (isRefresh) {
            mCarOfPaiGongDanList.clear();
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
    public void setPresenter(PaiGongCarListContract.Presenter presenter) {

    }

    boolean isHidden = false;
    @Override
    public void onPause() {
        super.onPause();
        isHidden = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isHidden)
        {
            if (getUserVisibleHint())
            {
                getOriginalList();
            }
            isHidden = false;
        }
    }
}
