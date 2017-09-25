package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.addcomponent;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.bean.WeiChuComponent;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class AddComponentContract {

    public interface View extends BaseView<Presenter> {

        void getUnSignPartByCarFail(String tip);

        void getUnSignPartByCarSuccess(List<WeiChuComponent> weiChuComponents);

        void netError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter {

        //baseid 厂家ID
        void getUnSignPartByCar(String baseid);

    }
}
