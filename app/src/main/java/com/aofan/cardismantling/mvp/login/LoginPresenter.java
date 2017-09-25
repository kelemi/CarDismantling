package com.aofan.cardismantling.mvp.login;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.LoginUser;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.ShareKey;
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
 * Created by Administrator on 2016/10/26.
 */
public class LoginPresenter implements LoginContract.Presenter {

    @NonNull
    LoginContract.View mLoginView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public LoginPresenter(@NonNull LoginContract.View mLoginView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mLoginView = mLoginView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mLoginView.setPresenter(this);
    }


    @Override
    public void login(String userName, String passWord) {

        mLoginView.showLoading();

        Subscription subscription = mHttpService.login(userName,passWord)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("loginResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mLoginView.dismissLoading();
                            mLoginView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mLoginView.dismissLoading();
                        mLoginView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        LoginUser  loginUser = (LoginUser) CommonUtil.formatJsonToObject(jsonObject,"data",new TypeToken<LoginUser>(){});

                        CommonUtil.putShareValue(ShareKey.SHARE_KEY_USER_ID,loginUser.getUid());
                        CommonUtil.putShareValue(ShareKey.SHARE_KEY_USER_NAME,loginUser.getUsername());
                        CommonUtil.putShareValue(ShareKey.SHARE_KEY_IS_LOGIN,true);
                        mLoginView.dismissLoading();
                        mLoginView.showLoginResult();
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
