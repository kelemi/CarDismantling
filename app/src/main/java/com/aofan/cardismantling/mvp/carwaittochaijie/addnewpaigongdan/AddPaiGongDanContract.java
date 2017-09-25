package com.aofan.cardismantling.mvp.carwaittochaijie.addnewpaigongdan;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/24.
 */

public class AddPaiGongDanContract {

    public interface View extends BaseView<Presenter>
    {
        void addPaiGongDan();

        void showAddPaiGongDanSuccess();

        void showError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void addPaiGongDan(
                             String chaijieType,
                             String userId,              //用户id
                             String analysisCarId,          //分析车辆的id
                             String chaiJieRequire, //拆解要求
                             String chaiJieWorkerNames,// 名称,名称
                             String chaiJieWorkIdAndNames,//64^1000demo,65^颜荐轩
                             String chaiJieLingJianNames,//反光镜，轮子
                             String chaiJieLingJianIdAndNames //PartId^PartName,artId^PartName
                           );
    }
}
