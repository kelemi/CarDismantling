package com.aofan.cardismantling.mvp.home.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.recycle.RecycleAdapterForIndexMenu;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.MenuItem;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.mvp.base.BaseFragment;
import com.aofan.cardismantling.mvp.carwaitanalysis.ActivityCarWaitToAnalysis;
import com.aofan.cardismantling.mvp.carwaittochaijie.paigongdan.ActivityPaiGongDanMainView;
import com.aofan.cardismantling.mvp.datatongji.ActivityDataTongJiHome;
import com.aofan.cardismantling.mvp.jobhasdo.ActivityJobHasDoMainView;
import com.aofan.cardismantling.mvp.jobwaittodo.ActivityJobWaitToDoMainView;
import com.aofan.cardismantling.mvp.kucun.ActivityKuCunMainView;
import com.aofan.cardismantling.mvp.lingjiandetail.ActivityShowLingJianDetail;
import com.aofan.cardismantling.mvp.takephotostorecordinfo.ActivityTakePhotoManage;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.aofan.cardismantling.widget.ListViewForScrollView;
import com.dyw.myerweimalib.android.CaptureActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/21.
 */

public class FragmentIndex extends BaseFragment implements IndexPageContract.View, PullToRefreshBase.OnRefreshListener {


    public static final String ARGUMENT = "argument";

    public String mArgument;

    IndexPageContract.Presenter mIndexPresenter;

    public static FragmentIndex getInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        FragmentIndex fragmentIndex = new FragmentIndex();
        fragmentIndex.setArguments(bundle);
        return fragmentIndex;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mArgument = bundle.getString(ARGUMENT);
        }

        mIndexPresenter = new IndexPagePresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_index, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleWithRightTvOrIv(view, null, null, "再生家", false, null, null);
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

    private ImageView ivLeftBack;
    private TextView tvTitle;
    private ImageView ivTakePhoto;
    private ImageView ivEwmSacn;
    private PullToRefreshScrollView scRefresh;
    private ViewPager vpBanner;
    private RecyclerView rvFuncion;
    private TextView tvTagTodoList;
    private TextView tvToMoreTodoJob;
    private ListViewForScrollView lvJobWaitTodo;


    RecycleAdapterForIndexMenu mMenuAdapter;
    List<MenuItem> mMenuList = new ArrayList<>();
    //是否可以拍照上传
    boolean mCanTakePhoto;


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

                    getData();
                }
            }
        } else {

        }
    }


    @Override
    public void initData() {
        MenuItem defaultMenuItem1 = new MenuItem();
        defaultMenuItem1.setName("车辆分析");
        defaultMenuItem1.setState(false);
        defaultMenuItem1.setLocalIconResId(R.drawable.ico_menu_one);
        defaultMenuItem1.setIcon(null);

        MenuItem defaultMenuItem2 = new MenuItem();
        defaultMenuItem2.setName("派工单");
        defaultMenuItem2.setState(false);
        defaultMenuItem2.setLocalIconResId(R.drawable.ico_menu_second);
        defaultMenuItem2.setIcon(null);


        MenuItem defaultMenuItem3 = new MenuItem();
        defaultMenuItem3.setName("查看库存");
        defaultMenuItem3.setState(false);
        defaultMenuItem3.setLocalIconResId(R.drawable.ico_menu_third);
        defaultMenuItem3.setIcon(null);


        MenuItem defaultMenuItem4 = new MenuItem();
        defaultMenuItem4.setName("数据报表");
        defaultMenuItem4.setState(false);
        defaultMenuItem4.setLocalIconResId(R.drawable.ico_menu_forth);
        defaultMenuItem4.setIcon(null);


        MenuItem defaultMenuItem5 = new MenuItem();
        defaultMenuItem5.setName("待办事项");
        defaultMenuItem5.setState(false);
        defaultMenuItem5.setLocalIconResId(R.drawable.ico_menu_fiveth);
        defaultMenuItem5.setIcon(null);


        MenuItem defaultMenuItem6 = new MenuItem();
        defaultMenuItem6.setName("已办事项");
        defaultMenuItem6.setState(false);
        defaultMenuItem6.setLocalIconResId(R.drawable.ico_menu_sixth);
        defaultMenuItem6.setIcon(null);


        mMenuList.add(defaultMenuItem1);
        mMenuList.add(defaultMenuItem2);
        mMenuList.add(defaultMenuItem3);
        mMenuList.add(defaultMenuItem4);
        mMenuList.add(defaultMenuItem5);
        mMenuList.add(defaultMenuItem6);
    }

    @Override
    public void assignView(View view) {
        //scRefresh = (PullToRefreshScrollView) view.findViewById(R.id.sc_refresh);
        vpBanner = (ViewPager) view.findViewById(R.id.vp_banner);
        rvFuncion = (RecyclerView) view.findViewById(R.id.rv_funcion);
        /*tvTagTodoList = (TextView) view.findViewById(R.id.tv_tag_todo_list);
        tvToMoreTodoJob = (TextView) view.findViewById(R.id.tv_to_more_todo_job);
        lvJobWaitTodo = (ListViewForScrollView) view.findViewById(R.id.lv_job_wait_todo);*/


        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        ivTakePhoto = (ImageView) view.findViewById(R.id.iv_take_photo);
        ivEwmSacn = (ImageView) view.findViewById(R.id.iv_ewm_sacn);

    }

    @Override
    public void initView() {
        // scRefresh.setOnRefreshListener(this);

        RxView.clicks(ivTakePhoto)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (mCanTakePhoto) {
                            startActivity(new Intent(getActivity(), ActivityTakePhotoManage.class));
                        } else {
                            ToastUtil.showShort(getActivity(), TipStr.TIP_NOT_HAVE_TAKE_PHOTO_AUTH);
                        }

                    }
                });

        RxView.clicks(ivEwmSacn)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent scanErWeiMa = new Intent(getActivity(), CaptureActivity.class);
                        startActivityForResult(scanErWeiMa, CaptureActivity.REQUEST_CODE_SCAN_ERWEIMA);
                    }
                });


        initMenu();

    }

    private void initMenu() {

        mMenuAdapter = new RecycleAdapterForIndexMenu(mMenuList, getActivity());
        mMenuAdapter.setMenuItemClickListener(new MenuItemClickListenerImpl());

        rvFuncion.setAdapter(mMenuAdapter);
        rvFuncion.setLayoutManager(new GridLayoutManager(getActivity(), 3));

    }


    @Override
    public void getData() {
        getMenuList();
    }


    private void loadOriginalList() {
        isRefresh = true;
        mPageIndex = 1;
        //加载数据的具体代码
    }

    private void loadMoreData() {
        if (canLoad) {
            mPageIndex++;
            //加载数据的具体代码

        }
    }


    @Override
    public void getMenuList() {
        mIndexPresenter.getMenuList((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""));
    }

    @Override
    public void showMenuList(List<MenuItem> menuList) {
        //scRefresh.onRefreshComplete();

        isGetData = true;

        resetMenulList(menuList);
        mMenuAdapter.notifyDataSetChanged();

    }

    private void resetMenulList(List<MenuItem> menuList) {
        for (int i = 0; i < mMenuList.size(); i++) {
            MenuItem menuItem = mMenuList.get(i);
            menuItem.setState(menuList.get(i).isState());
            menuItem.setName(menuList.get(i).getName());
            menuItem.setId(menuList.get(i).getId());
        }

        mCanTakePhoto = menuList.get(menuList.size() - 1).isState();
    }

    @Override
    public void showGetMenuListError(String tip) {
        //scRefresh.onRefreshComplete();
        ToastUtil.showShort(getActivity(), tip);
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
    public void setPresenter(IndexPageContract.Presenter presenter) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CaptureActivity.REQUEST_CODE_SCAN_ERWEIMA) {
            if (resultCode == CaptureActivity.RESULT_CODE_SCAN_ERWEIMA_SUCCESS) {
                String scanResult = data.getStringExtra(CaptureActivity.INTENT_KEY_ER_WEI_MA_SCAN_RESULT);
                LogUtil.e("scanresult:" + scanResult);
                Intent toShowLingJianInfo = new Intent(getActivity(), ActivityShowLingJianDetail.class);
                toShowLingJianInfo.putExtra(IntentKey.INTENT_KEY_LINGJIAN_ID,scanResult);
                startActivity(toShowLingJianInfo);
                /*LogUtil.e("scanresult:"+scanResult);
                ToastUtil.showShort(getActivity(),"scanResult:"+scanResult);
                String paramStr = scanResult.substring(scanResult.lastIndexOf("?")+1,scanResult.length());
                ToastUtil.showShort(getActivity(),"paramStr:"+paramStr);*/

            }
        }
    }

    @Override
    public void onRefresh(PullToRefreshBase pullToRefreshBase) {
        getData();
    }


    private class MenuItemClickListenerImpl implements RecycleAdapterForIndexMenu.MenuItemClickListener {

        @Override
        public void onMenuItemClick(int position) {
            MenuItem menuItem = mMenuList.get(position);

            if (menuItem.isState() == true) {
                switch (menuItem.getName()) {
                    case Config.MENU_TYPE_CAR_ANALYSIS:
                        Intent toAnalysisCar = new Intent(getActivity(), ActivityCarWaitToAnalysis.class);
                        startActivity(toAnalysisCar);
                        break;
                    case Config.MENU_TYPE_PAIGONGDAN:
                        Intent toPaiGongDanView = new Intent(getActivity(), ActivityPaiGongDanMainView.class);
                        startActivity(toPaiGongDanView);
                        break;
                    case Config.MENU_TYPE_CHECK_KUCUN:

                        Intent toKuCun = new Intent(getActivity(), ActivityKuCunMainView.class);
                        startActivity(toKuCun);

                        break;
                    case Config.MENU_TYPE_DATA_TONGJI:
                        Intent toDataTongJi = new Intent(getActivity(), ActivityDataTongJiHome.class);
                        startActivity(toDataTongJi);

                        break;
                    case Config.MENU_TYPE_JOB_WAIT_TODO:
                        Intent toJobWaitToDo = new Intent(getActivity(), ActivityJobWaitToDoMainView.class);
                        startActivity(toJobWaitToDo);
                        break;
                    case Config.MENU_TYPE_JOB_HAS_DO:
                        Intent toJobHasDo = new Intent(getActivity(), ActivityJobHasDoMainView.class);
                        startActivity(toJobHasDo);
                        break;

                }

            } else {
                ToastUtil.showShort(getActivity(), TipStr.TIP_NOT_HAVE_THIS_AUTH);
            }
        }
    }
}
