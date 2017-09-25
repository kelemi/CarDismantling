package com.aofan.cardismantling.mvp.carwaittochaijie.carchaijiepaigongdanlist;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.LingJIanOfCanChooseChaiJie;
import com.aofan.cardismantling.bean.PaiGongDanitem;
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
 * Created by Administrator on 2016/11/24.
 */

public class PaiGongDanListPresenter implements PaiGongDanListContract.Presenter {

    @NonNull
    PaiGongDanListContract.View mCarPaiGongDanListView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public PaiGongDanListPresenter(@NonNull PaiGongDanListContract.View mCarPaiGongDanListView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mCarPaiGongDanListView = mCarPaiGongDanListView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mCarPaiGongDanListView.setPresenter(this);
    }

    @Override
    public void  getCarPaiGongDanList(String did,String pageIndex,String pageSize) {
        mCarPaiGongDanListView.showLoading();

        Subscription subscription = mHttpService.getOneCarPaiGongDanList(did,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("oneCarPaiGongDanListResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mCarPaiGongDanListView.dismissLoading();
                            mCarPaiGongDanListView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mCarPaiGongDanListView.dismissLoading();
                        mCarPaiGongDanListView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<PaiGongDanitem> paiGongDanList = new ArrayList<PaiGongDanitem>();

                        if (jsonObject.get("data").isJsonNull())
                        {
                            mCarPaiGongDanListView.dismissLoading();
                            mCarPaiGongDanListView.showCarPaiGongDanList(paiGongDanList);

                        }else {
                            paiGongDanList.addAll(CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<PaiGongDanitem>>(){}));
                            mCarPaiGongDanListView.dismissLoading();
                            mCarPaiGongDanListView.showCarPaiGongDanList(paiGongDanList);
                        }


                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void completePaiGong(String did) {
        mCarPaiGongDanListView.showLoading();

        Subscription subscription = mHttpService.completePaiGong(did)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("completePaiGongResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mCarPaiGongDanListView.dismissLoading();
                            mCarPaiGongDanListView.showCompletePaiGongError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mCarPaiGongDanListView.dismissLoading();
                        mCarPaiGongDanListView.showCompletePaiGongError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {


                            mCarPaiGongDanListView.dismissLoading();
                            mCarPaiGongDanListView.showCompletePaiGongSuccess();

                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getLeftNotPaiGongLingJian(String did) {
        mCarPaiGongDanListView.showLoading();

        Subscription subscription = mHttpService.getCanChooseChaiJieLingJian(did,Constant.STATE_OF_HAS_ANALYSIS)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("canChooseChaiJieLingJian:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mCarPaiGongDanListView.dismissLoading();
                            mCarPaiGongDanListView.showGetLeftNotPaiGongLingJianError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mCarPaiGongDanListView.dismissLoading();
                        mCarPaiGongDanListView.showGetLeftNotPaiGongLingJianError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<LingJIanOfCanChooseChaiJie> allCanChooseChaiJieLingJianList = new ArrayList<LingJIanOfCanChooseChaiJie>();

                        if (!jsonObject.get("data").isJsonNull())
                        {
                             allCanChooseChaiJieLingJianList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<LingJIanOfCanChooseChaiJie>>(){});

                        }

                        mCarPaiGongDanListView.dismissLoading();
                        mCarPaiGongDanListView.showLeftNotPaiGongLingJian(allCanChooseChaiJieLingJianList);

                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void checkCarHasLeftNotFinishPaiGongDan(String baseId) {
        mCarPaiGongDanListView.showLoading();

        Subscription subscription = mHttpService.checkCarHasNotFinishedPaiGongDan(baseId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("chaHasNotFinishedPaiGongDanResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mCarPaiGongDanListView.dismissLoading();
                            mCarPaiGongDanListView.onCheckCarHasNotFinishedPaiGongDanError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mCarPaiGongDanListView.dismissLoading();
                        mCarPaiGongDanListView.onCheckCarHasNotFinishedPaiGongDanError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                       /* List<LingJIanOfCanChooseChaiJie> allCanChooseChaiJieLingJianList = new ArrayList<LingJIanOfCanChooseChaiJie>();

                        if (!jsonObject.get("data").isJsonNull())
                        {
                            allCanChooseChaiJieLingJianList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<LingJIanOfCanChooseChaiJie>>(){});

                        }*/

                        mCarPaiGongDanListView.dismissLoading();
                        if (jsonObject.get(Constant.RESPONSE_KEY_DATA).getAsInt()>0)
                        {
                            mCarPaiGongDanListView.onCheckCarHasNotFinishedPaiGongDanSuccess(true);
                        }else {
                            mCarPaiGongDanListView.onCheckCarHasNotFinishedPaiGongDanSuccess(false);

                        }


                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void submitCarFinishChaiJie(String userId, String carId,String chaiJieType) {
        mCarPaiGongDanListView.showLoading();

        Subscription subscription = mHttpService.carCompleteChaiJie(userId,carId,chaiJieType)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("carCompleteChaiJieResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mCarPaiGongDanListView.dismissLoading();
                            mCarPaiGongDanListView.onSubmitCarFinishChaiJieError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mCarPaiGongDanListView.dismissLoading();
                        mCarPaiGongDanListView.onSubmitCarFinishChaiJieError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        mCarPaiGongDanListView.dismissLoading();
                        mCarPaiGongDanListView.onSubmitCarFinishChaiJieSuccess();

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
