package com.aofan.cardismantling.mvp.modifypass;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ModifyPassContract {

    public interface View extends BaseView<Presenter>
    {

        void showLoading();

        void dismissLoading();

        void modifyPass();

        void showModifyPassSuccess();

        void showModifyPassError(String tip);

    }

    public interface Presenter extends BasePresenter
    {
        void modifyPass(String userId,String oldPass,String newPass);
    }
}
