package com.aofan.cardismantling.mvp.kucun.lingjiankucun;

import com.aofan.cardismantling.bean.LingJianKuCunListItem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class LingJianKuCunListContract {

    public interface View extends BaseView<Presenter>
    {

        void getLingJianKuCunList();

        void onGetLingJianKuCunListSuccess(List<LingJianKuCunListItem> lingJianKuCunList);

        void onGetLingJianKuCunListError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        /**
         * 获取零件库存信息
         * @param userId
         * @param pageIndex
         * @param pageSize
         * @param lingJianState  零件状态：待销售，已销售，转大宗商品
         */
        void getLingJianKuCunList(String userId,String pageIndex,String pageSize,String lingJianState);
    }

}



