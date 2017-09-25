package com.aofan.cardismantling.mvp.datatongji.vheicletongji;

import com.aofan.cardismantling.bean.TongJiDataOfCar;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class CarTongJiContract {

    public interface View extends BaseView<Presenter>
    {
        void showLoading();

        void dismissLoading();

        void getCarTongJiData();

        void onGetCarTongJiDataSuccess(List<TongJiDataOfCar> carTongJiList);

        void onGetCarTongJiDataError(String tip);
    }

    public interface Presenter extends BasePresenter
    {
        void getCarTongJiData(String userId,String pageIndex,String pageSize);
    }
}
