package com.aofan.cardismantling.mvp.home.index;

import com.aofan.cardismantling.bean.MenuItem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */

public class IndexPageContract {

    public interface View extends BaseView<Presenter> {
        void getMenuList();

        void showMenuList(List<MenuItem> menuList);

        void showGetMenuListError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter {
        void getMenuList(String userId);
    }

}
