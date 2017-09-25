package com.aofan.cardismantling.mvp.home.personalcenter;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.MenuItem;
import com.aofan.cardismantling.bean.VersionInfo;
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
 * Created by Administrator on 2016/11/28.
 */

public class PersonalCenterPresenter implements PersonalCenterContract.Presenter {

    @NonNull
    PersonalCenterContract.View mPersonalCenterView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public PersonalCenterPresenter(@NonNull PersonalCenterContract.View mPersonalCenterView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mPersonalCenterView = mPersonalCenterView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mPersonalCenterView.setPresenter(this);
    }


    @Override
    public void getVersionInfo() {
        mPersonalCenterView.showLoading();


        Subscription subscription = mHttpService.getVersion()
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("versionInfo:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mPersonalCenterView.dismissLoading();
                            mPersonalCenterView.onGetVersionInfoError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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

                        mPersonalCenterView.dismissLoading();
                        mPersonalCenterView.onGetVersionInfoError(TipStr.TIP_SERVER_GET_DATA_WRONG);


                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        if (!jsonObject.get("data").isJsonNull())
                        {

                            List<VersionInfo> versionInfoList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<VersionInfo>>(){});

                            VersionInfo versionInfo = versionInfoList.get(0);

                            mPersonalCenterView.dismissLoading();
                            mPersonalCenterView.onGetVersionInfoSuccess(versionInfo);
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

    }
}
