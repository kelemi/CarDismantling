package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.addcar;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/23.
 */

public class AddWeiChuContract {

    public interface View extends BaseView<Presenter> {

        void addUnSignCarFail(String tip);

        void addUnSignCarSuccess(String tip);

        void netError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter {

        //VehicleNumber车牌号码,CustomInFactoryNumber自定义入场编号
        void addUnSignCar(String userid, String VehicleNumber, String CustomInFactoryNumber,String cjtype);

    }
}
