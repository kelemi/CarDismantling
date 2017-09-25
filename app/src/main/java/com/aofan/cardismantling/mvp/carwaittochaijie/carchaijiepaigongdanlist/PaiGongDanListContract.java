package com.aofan.cardismantling.mvp.carwaittochaijie.carchaijiepaigongdanlist;

import com.aofan.cardismantling.bean.LingJIanOfCanChooseChaiJie;
import com.aofan.cardismantling.bean.PaiGongDanitem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */

public class PaiGongDanListContract {

    public interface View extends BaseView<Presenter>
    {

        void getCarPaiGongDanList();

        void showCarPaiGongDanList(List<PaiGongDanitem> paiGongDanList);

        void showError(String tip);

        void completePaiGong();

        void showCompletePaiGongSuccess();

        void showCompletePaiGongError(String tip);


        //获取剩余的未派工的零件
        void getLeftNotPaiGongLingJian();

        void showLeftNotPaiGongLingJian( List<LingJIanOfCanChooseChaiJie> canChooseChaiJieLingJianList);

        void showGetLeftNotPaiGongLingJianError(String tip);


        void checkCarHasNotFinishedPaiGongDan();

        void onCheckCarHasNotFinishedPaiGongDanSuccess(boolean hasNotFinishedPaiGongDan);

        void onCheckCarHasNotFinishedPaiGongDanError(String tip);

        void submitCarFinishChaiJie();

        void onSubmitCarFinishChaiJieSuccess();

        void onSubmitCarFinishChaiJieError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        /**
         * 获取一辆车的派工单列表
         * @param did         派工单车辆item的did
         * @param pageIndex
         * @param pageSize
         */
        void getCarPaiGongDanList(String did,String pageIndex,String pageSize);

        /**
         * 完成派工
         * @param did
         */
        void completePaiGong(String did);

        /**
         * 获取没有派工的零件
         * @param did
         */
        void getLeftNotPaiGongLingJian(String did);

        /**
         * 检查车子是否有没有完成拆解的派工单
         * @param baseId
         */
        void checkCarHasLeftNotFinishPaiGongDan(String baseId);

        /**
         * 汽车完成拆解
         * @param userId
         * @param oId
         */
        void submitCarFinishChaiJie(String userId,String oId,String chaiJieType);
    }
}
