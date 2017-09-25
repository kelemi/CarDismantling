package com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.HasDoPaiGongDanListItem;
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
 * Created by Administrator on 2016/11/23.
 */

public class JobHasDoPaiGongDanPresenter implements JobHasDoPaiGongDanContract.Presenter {


    @NonNull
    JobHasDoPaiGongDanContract.View jobHasDoPaiGongDanListView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public JobHasDoPaiGongDanPresenter(@NonNull JobHasDoPaiGongDanContract.View jobHasDoPaiGongDanListView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.jobHasDoPaiGongDanListView = jobHasDoPaiGongDanListView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.jobHasDoPaiGongDanListView.setPresenter(this);
    }

    @Override
    public void getHasDoEnsurePaiGongDanList(String userId,String jobKind,String pageIndex,String pageSize) {
        jobHasDoPaiGongDanListView.showLoading();

        Subscription subscription = mHttpService.getHasDoJobList(userId,jobKind,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("userHasEnsurePaiGongDanResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            jobHasDoPaiGongDanListView.dismissLoading();
                            jobHasDoPaiGongDanListView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        jobHasDoPaiGongDanListView.dismissLoading();
                        jobHasDoPaiGongDanListView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                       List<HasDoPaiGongDanListItem> mHasDoPaiGongDanList = new ArrayList<>();

                        if (jsonObject.get("data").isJsonNull()==false)
                        {
                            mHasDoPaiGongDanList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<HasDoPaiGongDanListItem>>(){});
                        }
                        jobHasDoPaiGongDanListView.dismissLoading();
                        jobHasDoPaiGongDanListView.showHasDoEnsurePaiGongDanList(mHasDoPaiGongDanList);

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
