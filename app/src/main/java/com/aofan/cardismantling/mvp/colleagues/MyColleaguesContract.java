package com.aofan.cardismantling.mvp.colleagues;

import com.aofan.cardismantling.bean.ColleagueItem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public class MyColleaguesContract {

    public interface View extends BaseView<Presenter>
    {
        void getMyColleagues();

        void onGetMyColleaguesSuccess( List<ColleagueItem> colleagueItemList);

        void onGetMyColleaguesError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter
    {
        void getMyColleagues(String userId);
    }
}
