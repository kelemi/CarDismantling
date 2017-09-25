package com.aofan.cardismantling.mvp.carwaittochaijie.paigongdan.hasfinishedpaigongcar;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/24.
 */

public class HasFinishedPaiGongCarListPresenter implements HasFinishedPaiGongCarListContract.Presenter {

    @NonNull
    HasFinishedPaiGongCarListContract.View mNotFinishedPaiGongCarListView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public HasFinishedPaiGongCarListPresenter(@NonNull HasFinishedPaiGongCarListContract.View mNotFinishedPaiGongCarListView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mNotFinishedPaiGongCarListView = mNotFinishedPaiGongCarListView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mNotFinishedPaiGongCarListView.setPresenter(this);
    }

    @Override
    public void getHasFinishedPaiGongCarList() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


}
