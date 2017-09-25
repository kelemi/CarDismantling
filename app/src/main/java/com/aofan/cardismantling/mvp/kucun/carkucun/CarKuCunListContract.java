package com.aofan.cardismantling.mvp.kucun.carkucun;

import com.aofan.cardismantling.bean.CarKuCunListItem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class CarKuCunListContract {

    public interface View extends BaseView<Presenter>
    {
        void getCarKuCunList();

        void onGetCarKuCunListSuccess( List<CarKuCunListItem> carKuCunList);

        void onGetCarKuCunListError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getCarKuCunList(String userId,String chaiJieState,String pageIndex,String pageSize);
    }
}
