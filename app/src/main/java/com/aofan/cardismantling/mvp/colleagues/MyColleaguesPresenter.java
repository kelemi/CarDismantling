package com.aofan.cardismantling.mvp.colleagues;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.ColleagueItem;
import com.aofan.cardismantling.bean.TongJiDataOfWorker;
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
 * Created by Administrator on 2016/11/30.
 */

public class MyColleaguesPresenter implements MyColleaguesContract.Presenter {

    @NonNull
    MyColleaguesContract.View mMyColleaguesView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;


    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public MyColleaguesPresenter(@NonNull MyColleaguesContract.View mMyColleaguesView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mMyColleaguesView = mMyColleaguesView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mMyColleaguesView.setPresenter(this);
    }

    @Override
    public void getMyColleagues(String userId) {
        mMyColleaguesView.showLoading();

        Subscription subscription = mHttpService.getColleagues(userId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("colleaguesResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mMyColleaguesView.dismissLoading();
                            mMyColleaguesView.onGetMyColleaguesError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mMyColleaguesView.dismissLoading();
                        mMyColleaguesView.onGetMyColleaguesError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<ColleagueItem> colleagueItemList = new ArrayList<>();

                        if (jsonObject.get("data").isJsonNull()==false)
                        {
                            colleagueItemList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<ColleagueItem>>(){});
                        }

                        mMyColleaguesView.dismissLoading();
                        mMyColleaguesView.onGetMyColleaguesSuccess(colleagueItemList);

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
