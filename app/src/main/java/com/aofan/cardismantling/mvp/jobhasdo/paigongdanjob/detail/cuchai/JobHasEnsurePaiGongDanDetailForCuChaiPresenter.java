package com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob.detail.cuchai;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.ChaiJieDetailInfoForCuChai;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/29.
 */

public class JobHasEnsurePaiGongDanDetailForCuChaiPresenter implements JobHasEnsurePaiGongDanDetailForCuChaiContract.Presenter {


    @NonNull
    JobHasEnsurePaiGongDanDetailForCuChaiContract.View mJobHasDoPaiGongDanDetailView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public JobHasEnsurePaiGongDanDetailForCuChaiPresenter(@NonNull JobHasEnsurePaiGongDanDetailForCuChaiContract.View mJobHasDoPaiGongDanDetailView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mJobHasDoPaiGongDanDetailView = mJobHasDoPaiGongDanDetailView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mJobHasDoPaiGongDanDetailView.setPresenter(this);
    }


    @Override
    public void getJobHasDoPaiGongDanDetail(String chaiJieType,String oid) {
        mJobHasDoPaiGongDanDetailView.showLoading();


        Subscription subscription = mHttpService.getChaiJiePaiGongDanDetailNew(oid,chaiJieType)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("hasDoPaiGongDanDetailResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mJobHasDoPaiGongDanDetailView.dismissLoading();
                            mJobHasDoPaiGongDanDetailView.onGetJobHasDoPaiGongDanDetailError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mJobHasDoPaiGongDanDetailView.dismissLoading();
                        mJobHasDoPaiGongDanDetailView.onGetJobHasDoPaiGongDanDetailError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        ChaiJieDetailInfoForCuChai chaiJieDetail = (ChaiJieDetailInfoForCuChai) CommonUtil.formatJsonToObject(jsonObject,"data",new TypeToken<ChaiJieDetailInfoForCuChai>(){});

                        mJobHasDoPaiGongDanDetailView.dismissLoading();
                        mJobHasDoPaiGongDanDetailView.onGetJobHasDoPaiGongDanDetailSuccess(chaiJieDetail);

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
