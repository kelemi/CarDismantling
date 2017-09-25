package com.aofan.cardismantling.mvp.carwaittochaijie.paigongdan;

import com.aofan.cardismantling.bean.CarOfPaiGongDan;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/27.
 */

public class PaiGongCarListContract {


    public interface View extends BaseView<Presenter>
    {
        void getPaiGongCarList();

        void showPaiGongCarList(List<CarOfPaiGongDan> carOfPaiGongDanList);

        void showError(String tip);

        void  showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter
    {
        /**
         * 获取派工的车辆
         * @param userId  用户id
         * @param carPaiGongState 车派工状态
         */
        void getPaiGongCarList(String userId, String carPaiGongState,String pageIndex,String pageSize);
    }

}
