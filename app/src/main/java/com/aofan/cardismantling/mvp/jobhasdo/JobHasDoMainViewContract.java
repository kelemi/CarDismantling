package com.aofan.cardismantling.mvp.jobhasdo;

import com.aofan.cardismantling.bean.UserToDoJobKind;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class JobHasDoMainViewContract {

    public interface View extends BaseView<Presenter>
    {
        void getHasDoJobKind();

        void onGetHasDoJobKindSuccess(List<UserToDoJobKind> userToDoJobKindList);

        void onGetHasDoJobKindError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getHasDoJobKind(String userId);
    }
}
