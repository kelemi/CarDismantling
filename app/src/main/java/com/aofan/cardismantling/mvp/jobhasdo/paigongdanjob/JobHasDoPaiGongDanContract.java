package com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob;

import com.aofan.cardismantling.bean.HasDoPaiGongDanListItem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;
import com.aofan.cardismantling.mvp.jobhasdo.chaijiejob.JobHasDoChaiJieContract;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class JobHasDoPaiGongDanContract {

    public interface View extends BaseView<Presenter>
    {
        void getHasDoEnsurePaiGongDanList();

        void showHasDoEnsurePaiGongDanList(List<HasDoPaiGongDanListItem> mHasDoPaiGongDanList);

        void showError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {

        void getHasDoEnsurePaiGongDanList(String userId,String jobKind,String pageIndex,String pageSize);
    }

}
