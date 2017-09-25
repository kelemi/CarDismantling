package com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijielingjian;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.LingJIanOfCanChooseChaiJie;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ChooseChaiJieLingJianPresenter implements ChooseChaiJieLingJianContract.Presenter{



    @NonNull
    ChooseChaiJieLingJianContract.View mChooseChaiJieLingJianView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public ChooseChaiJieLingJianPresenter(@NonNull ChooseChaiJieLingJianContract.View mChooseChaiJieLingJianView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mChooseChaiJieLingJianView = mChooseChaiJieLingJianView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mChooseChaiJieLingJianView.setPresenter(this);
    }


    @Override
    public void getChaiJieLingJian(String hasAnalysisCarId,String analysisState) {
        mChooseChaiJieLingJianView.showLoading();

        Subscription subscription = mHttpService.getCanChooseChaiJieLingJian(hasAnalysisCarId,analysisState)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("canChooseChaiJieLingJian:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mChooseChaiJieLingJianView.dismissLoading();
                            mChooseChaiJieLingJianView.showGetChaiJieLingJianError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mChooseChaiJieLingJianView.dismissLoading();
                        mChooseChaiJieLingJianView.showGetChaiJieLingJianError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        if (jsonObject.get("data") == null)
                        {
                            mChooseChaiJieLingJianView.dismissLoading();
                            mChooseChaiJieLingJianView.showChaiJieLingJian( null);

                        }else {
                            List<LingJIanOfCanChooseChaiJie> allCanChooseChaiJieLingJianList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<LingJIanOfCanChooseChaiJie>>(){});
                            mChooseChaiJieLingJianView.dismissLoading();
                            mChooseChaiJieLingJianView.showChaiJieLingJian( allCanChooseChaiJieLingJianList);
                        }


                    }
                });
        mSubscriptions.add(subscription);
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
