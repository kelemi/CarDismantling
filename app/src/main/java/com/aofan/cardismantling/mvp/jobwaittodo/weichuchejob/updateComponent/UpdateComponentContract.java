package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.updateComponent;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class UpdateComponentContract {

    public interface View extends BaseView<Presenter> {

        void saveUnSignCaijieFail(String tip);

        void saveUnSignCaijieSuccess(String tip);

        void netError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter {

        void saveUnSignCaijie(String cjtype, String userid, String partid, String partName,
                              String itemcount, String DId, List<String> picPathList);

    }
}
