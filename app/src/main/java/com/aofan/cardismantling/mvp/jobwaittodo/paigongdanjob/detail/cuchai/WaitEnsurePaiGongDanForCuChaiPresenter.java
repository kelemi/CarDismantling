package com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob.detail.cuchai;

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
 * Created by Administrator on 2016/11/28.
 */

public class WaitEnsurePaiGongDanForCuChaiPresenter implements WaitEnsurePaiGongDanForCuChaiContract.Presenter {


    @NonNull
    WaitEnsurePaiGongDanForCuChaiContract.View mEnsurePaiGongDanView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public WaitEnsurePaiGongDanForCuChaiPresenter(@NonNull WaitEnsurePaiGongDanForCuChaiContract.View mEnsurePaiGongDanView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mEnsurePaiGongDanView = mEnsurePaiGongDanView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mEnsurePaiGongDanView.setPresenter(this);
    }



    @Override
    public void getChaiJiePaiGongDanDetail(String chaijieType,String oid) {
        mEnsurePaiGongDanView.showLoading();

        Subscription subscription = mHttpService.getChaiJiePaiGongDanDetailNew(oid,chaijieType)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("chaiJiePaiGongDanForCuChaiDetailResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mEnsurePaiGongDanView.dismissLoading();
                            mEnsurePaiGongDanView.showChaiJiePaiGongDanDetailError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mEnsurePaiGongDanView.dismissLoading();
                        mEnsurePaiGongDanView.showChaiJiePaiGongDanDetailError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        ChaiJieDetailInfoForCuChai chaiJieDetail = (ChaiJieDetailInfoForCuChai) CommonUtil.formatJsonToObject(jsonObject,"data",new TypeToken<ChaiJieDetailInfoForCuChai>(){});

                        mEnsurePaiGongDanView.dismissLoading();
                        mEnsurePaiGongDanView.showChaiJiePaiGongDanDetail(chaiJieDetail);

                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void ensurePaiGongDanFinished(String oId, String userId) {
        mEnsurePaiGongDanView.showLoading();

        Subscription subscription = mHttpService.ensureChaiJiePaiGongDan(userId,oId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("ensureChaiJiePaiGongDanResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mEnsurePaiGongDanView.dismissLoading();
                            mEnsurePaiGongDanView.showEnsurePaiGongDanFinishedError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mEnsurePaiGongDanView.dismissLoading();
                        mEnsurePaiGongDanView.showEnsurePaiGongDanFinishedError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {


                        mEnsurePaiGongDanView.dismissLoading();
                        mEnsurePaiGongDanView.showEnsurePaiGongDanFinishedSuccess();

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
