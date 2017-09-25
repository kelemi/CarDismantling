package com.aofan.cardismantling.mvp.datatongji.selltongji;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForSellTongJi;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.SellTongJiListItem;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseFragment;
import com.aofan.cardismantling.util.CalendarUtil;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/1/5.
 */

public class FragmentSellTongJi extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, SellTongJiContract.View {


    public static final String ARGUMENT = "argument";

    public String mArgument;

    public static FragmentSellTongJi getInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        FragmentSellTongJi fragment = new FragmentSellTongJi();
        fragment.setArguments(bundle);
        return fragment;
    }

    SellTongJiContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mArgument = bundle.getString(ARGUMENT);
        }

        mPresenter = new SellTongJiPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    private TextView tvStarttimeChoose;
    private TextView tvEndtimeChoose;
    private TextView tvSellTongjiValue;
    private PullToRefreshListView lv;
    private ListView originalLv;
    private RelativeLayout emptylayout;
    private TextView emptytiptv;


    List<SellTongJiListItem> mSellTongJiList = new ArrayList<>();
    ListAdapterForSellTongJi mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_sell_tongji, null);
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
    boolean isRefresh = false;
    boolean canLoad = false;


    String mStartTime;
    String mEndTime;

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
                    loadOriginalList();
                }


            }
        } else {

        }
    }


    private void loadOriginalList() {
        isRefresh = true;
        mPageIndex = 1;
        //加载数据的具体代码
        getSellTongJiList();
    }

    private void loadMoreData() {
        if (canLoad) {
            mPageIndex++;
            //加载数据的具体代码
            loadMoreData();

        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void assignView(View view) {
        tvStarttimeChoose = (TextView) view.findViewById(R.id.tv_starttime_choose);
        tvEndtimeChoose = (TextView) view.findViewById(R.id.tv_endtime_choose);
        tvSellTongjiValue = (TextView) view.findViewById(R.id.tv_sell_tongji_value);
        lv = (PullToRefreshListView) view.findViewById(R.id.lv);
        originalLv = lv.getRefreshableView();
        emptylayout = (RelativeLayout) view.findViewById(R.id.emptylayout);
        emptytiptv = (TextView) view.findViewById(R.id.emptytiptv);

    }

    @Override
    public void initView() {
        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lv.setOnRefreshListener(this);

        mAdapter = new ListAdapterForSellTongJi(getActivity(), mSellTongJiList);
        originalLv.setAdapter(mAdapter);


        tvStarttimeChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int startTimeYear = Calendar.getInstance().get(Calendar.YEAR);
                int startTimeMonth = Calendar.getInstance().get(Calendar.MONTH);
                int startTimeDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog mDialog = new DatePickerDialog(getActivity(), null,
                        startTimeYear, startTimeMonth, startTimeDate);

                //手动设置按钮
                mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //通过mDialog.getDatePicker()获得dialog上的DatePicker组件，然后可以获取日期信息
                        DatePicker datePicker = mDialog.getDatePicker();
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();
                        mStartTime = CalendarUtil
                                .setYearMonthDayString(year,
                                        month, day);

                        tvStarttimeChoose.setText(mStartTime);
                        loadOriginalList();

                    }
                });
//取消按钮，如果不需要直接不设置即可
                mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                mDialog.show();

                /*new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            boolean isGetDate = false;
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // TODO Auto-generated method stub


                                if (isGetDate == true)
                                {
                                    return;
                                }else {
                                    mStartTime = CalendarUtil
                                            .setYearMonthDayString(year,
                                                    monthOfYear, dayOfMonth);

                                    tvStarttimeChoose.setText(mStartTime);
                                    loadOriginalList();
                                }

                            }
                        }, startTimeYear, startTimeMonth, startTimeDate).show();*/
            }
        });
        RxView.clicks(tvEndtimeChoose)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        int endTimeYear = Calendar.getInstance().get(Calendar.YEAR);
                        int endTimeMonth = Calendar.getInstance().get(Calendar.MONTH);
                        int endTimeDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                        final DatePickerDialog mDialog = new DatePickerDialog(getActivity(), null,
                                endTimeYear, endTimeMonth, endTimeDate);

                        //手动设置按钮
                        mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "完成", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //通过mDialog.getDatePicker()获得dialog上的DatePicker组件，然后可以获取日期信息
                                DatePicker datePicker = mDialog.getDatePicker();
                                int year = datePicker.getYear();
                                int month = datePicker.getMonth();
                                int day = datePicker.getDayOfMonth();
                                mEndTime = CalendarUtil
                                        .setYearMonthDayString(year,
                                                month, day);

                                tvEndtimeChoose.setText(mEndTime);
                                loadOriginalList();

                            }
                        });
//取消按钮，如果不需要直接不设置即可
                        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        mDialog.show();
                    }
                });
    }

    @Override
    public void getData() {

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
    public void getSellTongJiList() {
        mPresenter.getSellTongJi((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""), mStartTime, mEndTime, mPageIndex.toString(), Config.DEFAULT_PAGE_SIZE.toString());
    }

    @Override
    public void onGetSellTongJiListSuccess(List<SellTongJiListItem> sellTongJiList, String sellCount, String sellValue) {

        tvSellTongjiValue.setText("共销售零件" + sellCount + "件，共" + sellValue + "元");

        lv.onRefreshComplete();

        if (isRefresh) {
            mSellTongJiList.clear();
            isRefresh = false;
        }
        if (sellTongJiList.size() > 0) {
            if (sellTongJiList.size() >= Config.DEFAULT_PAGE_SIZE) {
                lv.setMode(PullToRefreshBase.Mode.BOTH);
                canLoad = true;
            } else {
                canLoad = false;
                lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }
            mSellTongJiList.addAll(sellTongJiList);

        } else {
            canLoad = false;
            lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            if (mSellTongJiList.size() == 0) {
                lv.setEmptyView(emptylayout);
            } else {
                ToastUtil.show(getActivity(), "已经获取全部数据", 2000);
            }
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetSellTongJiListError(String tip) {
        lv.onRefreshComplete();
        ToastUtil.showShort(getActivity(), tip);
        if (isRefresh) {
            mSellTongJiList.clear();
            lv.setEmptyView(emptylayout);

        }

        isRefresh = false;
        canLoad = false;

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(SellTongJiContract.Presenter presenter) {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
        loadOriginalList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
        loadMoreData();
    }

    private void showDateChooseDialog() {

    }
}
