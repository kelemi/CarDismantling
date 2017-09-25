package com.aofan.cardismantling.mvp.chaijiehasfinisheddetail;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/24.
 */

public class ChaiJieFinishedDetailPresenter implements ChaiJieFinishedDetailContract.Presenter {

    @NonNull
    ChaiJieFinishedDetailContract.View mChaiJieFinishedDetailView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public ChaiJieFinishedDetailPresenter(@NonNull ChaiJieFinishedDetailContract.View mChaiJieFinishedDetailView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mChaiJieFinishedDetailView = mChaiJieFinishedDetailView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mChaiJieFinishedDetailView.setPresenter(this);
    }


    @Override
    public void getChaiJieDetail() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.unsubscribe();
        mSubscriptions = null;
    }
}
