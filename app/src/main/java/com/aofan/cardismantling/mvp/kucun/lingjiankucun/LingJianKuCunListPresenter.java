package com.aofan.cardismantling.mvp.kucun.lingjiankucun;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.LingJianKuCunListItem;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/29.
 */

public class LingJianKuCunListPresenter implements LingJianKuCunListContract.Presenter {


    @NonNull
    LingJianKuCunListContract.View mLingJianKuCunListView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public LingJianKuCunListPresenter(@NonNull LingJianKuCunListContract.View mLingJianKuCunListView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mLingJianKuCunListView = mLingJianKuCunListView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mLingJianKuCunListView.setPresenter(this);
    }


    @Override
    public void getLingJianKuCunList(String userId, String pageIndex, String pageSize,String lingJianState) {
        mLingJianKuCunListView.showLoading();

        Subscription subscription = mHttpService.getLingJianKuCunList(userId,pageIndex,pageSize,lingJianState,null,null,null,null,null)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("lingJianKuCunRespose:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mLingJianKuCunListView.dismissLoading();
                            mLingJianKuCunListView.onGetLingJianKuCunListError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
                        }

                        return jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS);
                    }
                })
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mLingJianKuCunListView.dismissLoading();
                        mLingJianKuCunListView.onGetLingJianKuCunListError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<LingJianKuCunListItem> lingJianKuCunList = new ArrayList<LingJianKuCunListItem>();

                        if (jsonObject.get("data").isJsonNull()==false)
                        {
                            lingJianKuCunList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<LingJianKuCunListItem>>(){});
                        }

                        mLingJianKuCunListView.dismissLoading();
                        mLingJianKuCunListView.onGetLingJianKuCunListSuccess(lingJianKuCunList);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


}
