package com.aofan.cardismantling.mvp.datatongji.selltongji;

import com.aofan.cardismantling.bean.SellTongJiListItem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface SellTongJiContract {

    public interface View extends BaseView<Presenter>
    {
        void showLoading();

        void dismissLoading();

        void getSellTongJiList();

        void onGetSellTongJiListSuccess(List<SellTongJiListItem> sellTongJiList,String sellCount,String sellValue);

        void onGetSellTongJiListError(String tip);
    }

    public interface Presenter extends BasePresenter
    {
        void getSellTongJi(String userId,String startTime,String endTime,String pageIndex,String pageSize);
    }

}
