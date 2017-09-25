package com.aofan.cardismantling.mvp.datatongji.vheicletongji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForVheicleTongJi;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.TongJiDataOfCar;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseFragment;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class FragmentVheicleTongJi extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, CarTongJiContract.View {

    public static final String ARGUMENT = "argument";

    public String mArgument;

    public static FragmentVheicleTongJi getInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        FragmentVheicleTongJi fragmentVheicleTongJi = new FragmentVheicleTongJi();
        fragmentVheicleTongJi.setArguments(bundle);
        return fragmentVheicleTongJi;
    }


    CarTongJiContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mArgument = bundle.getString(ARGUMENT);
        }

        mPresenter = new CarTongJiPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }


    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;


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


    List<TongJiDataOfCar> mVheicleTongJiData = new ArrayList<>();
    ListAdapterForVheicleTongJi mAdapter;

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
        getCarTongJiData();
    }

    private void getMoreData() {
        if (canLoad) {
            mPageIndex++;
            //加载数据的具体代码
            getCarTongJiData();
        }
    }

    @Override
    public void initData() {
     /*   mVheicleTongJiData.add(new Object());
        mVheicleTongJiData.add(new Object());
        mVheicleTongJiData.add(new Object());
        mVheicleTongJiData.add(new Object());
        mVheicleTongJiData.add(new Object());
        mVheicleTongJiData.add(new Object());
*/
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


        mAdapter = new ListAdapterForVheicleTongJi(mVheicleTongJiData, getActivity());
        originalLv.setAdapter(mAdapter);


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
        getMoreData();
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
    public void getCarTongJiData() {
        mPresenter.getCarTongJiData((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""), mPageIndex.toString(), Config.DEFAULT_PAGE_SIZE.toString());
    }

    @Override
    public void onGetCarTongJiDataSuccess(List<TongJiDataOfCar> carTongJiList) {

        lv.onRefreshComplete();

        if (isRefresh) {
            mVheicleTongJiData.clear();
            isRefresh = false;
        }
        if (carTongJiList.size() > 0) {
            if (carTongJiList.size() >= Config.DEFAULT_PAGE_SIZE) {
                lv.setMode(PullToRefreshBase.Mode.BOTH);
                canLoad = true;
            } else {
                canLoad = false;
                lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            mVheicleTongJiData.addAll(carTongJiList);

        } else {
            canLoad = false;
            lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            if (mVheicleTongJiData.size() == 0) {
                lv.setEmptyView(emptylayout);
            } else {
                ToastUtil.show(getActivity(), "已经获取全部数据", 2000);
            }
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetCarTongJiDataError(String tip) {
        lv.onRefreshComplete();
        ToastUtil.showShort(getActivity(), tip);
        if (isRefresh) {
            mVheicleTongJiData.clear();
            lv.setEmptyView(emptylayout);

        }

        isRefresh = false;
        canLoad = false;

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(CarTongJiContract.Presenter presenter) {

    }
}
