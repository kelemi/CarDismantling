package com.aofan.cardismantling.mvp.jobwaittodo;

import com.aofan.cardismantling.bean.UserToDoJobKind;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class JobWaitToDoMainViewContract {

    public interface View extends BaseView<Presenter>
    {
        void getWaitToDoJobKind();

        void onGetWaitToDoJobKindSuccess(List<UserToDoJobKind> userToDoJobKindList);

        void onGetWaitToDoJobKindError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter
    {
        void getUserWaitToDoJobKind(String userId);
    }
}
