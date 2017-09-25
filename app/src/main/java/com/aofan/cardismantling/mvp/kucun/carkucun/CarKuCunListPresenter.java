package com.aofan.cardismantling.mvp.kucun.carkucun;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.CarKuCunListItem;
import com.aofan.cardismantling.bean.UserToDoJobKind;
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

public class CarKuCunListPresenter implements CarKuCunListContract.Presenter {


    @NonNull
    CarKuCunListContract.View mCarKuCunListView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public CarKuCunListPresenter(@NonNull CarKuCunListContract.View mCarKuCunListView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mCarKuCunListView = mCarKuCunListView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mCarKuCunListView.setPresenter(this);
    }

    @Override
    public void getCarKuCunList(String userId, String chaiJieState, String pageIndex, String pageSize) {
        mCarKuCunListView.showLoading();

        Subscription subscription = mHttpService.getCarKuCunList(userId,chaiJieState,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("CarkuCunResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mCarKuCunListView.dismissLoading();
                            mCarKuCunListView.onGetCarKuCunListError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mCarKuCunListView.dismissLoading();
                        mCarKuCunListView.onGetCarKuCunListError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        List<CarKuCunListItem> carKuCunList = new ArrayList<CarKuCunListItem>();

                        if (jsonObject.get("data").isJsonNull() == false)
                        {
                            carKuCunList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<CarKuCunListItem>>(){});
                        }

                        mCarKuCunListView.dismissLoading();
                        mCarKuCunListView.onGetCarKuCunListSuccess(carKuCunList);
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
