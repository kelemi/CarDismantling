package com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob.detail.cuchai;

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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/2/7.
 */

public class JobWaitToDoChaiJieDetailCuChaiPresenter implements JobWaitToDoChaiJieDetailCuChaiContract.Presenter {


    @NonNull
    JobWaitToDoChaiJieDetailCuChaiContract.View mJobWaitToDoChaiJieDetailCuChaiView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public JobWaitToDoChaiJieDetailCuChaiPresenter(@NonNull JobWaitToDoChaiJieDetailCuChaiContract.View mJobWaitToDoChaiJieDetailCuChaiView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mJobWaitToDoChaiJieDetailCuChaiView = mJobWaitToDoChaiJieDetailCuChaiView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mJobWaitToDoChaiJieDetailCuChaiView.setPresenter(this);
    }


    @Override
    public void getChaiJiePaiGongDanDetail(String oid,String chaiJieType) {
        mJobWaitToDoChaiJieDetailCuChaiView.showLoading();

        Subscription subscription = mHttpService.getChaiJiePaiGongDanDetailNew(oid,chaiJieType)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("chaiJiePaiGongDanDetailCuChaiResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mJobWaitToDoChaiJieDetailCuChaiView.dismissLoading();
                            mJobWaitToDoChaiJieDetailCuChaiView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mJobWaitToDoChaiJieDetailCuChaiView.dismissLoading();
                        mJobWaitToDoChaiJieDetailCuChaiView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        ChaiJieDetailInfoForCuChai chaiJieDetail = (ChaiJieDetailInfoForCuChai) CommonUtil.formatJsonToObject(jsonObject,"data",new TypeToken<ChaiJieDetailInfoForCuChai>(){});

                        mJobWaitToDoChaiJieDetailCuChaiView.dismissLoading();
                        mJobWaitToDoChaiJieDetailCuChaiView.showChaiJiePaiGongDanDetail(chaiJieDetail);

                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void submitLingJianChaiJieInfo(String chaiJieType,String userId, String oId,  List<File> lingJianChaiJiePics) {

        //mJobWaitToDoChaiJieDetailView.showLoading();

        RequestBody chaiJieTypeRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), chaiJieType);

        RequestBody userIdRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), userId);
        RequestBody oIdRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), oId);
       /* RequestBody partIdRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), partId);
        RequestBody partNameRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), partName);
        RequestBody lingJianCountRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), lingJianCount);*/

        Map<String, RequestBody> picInfoMap = new HashMap<>();
        for (int i = 0 ;i<lingJianChaiJiePics.size();i++)
        {
            RequestBody picFile =
                    RequestBody.create(MediaType.parse(CommonUtil.guessMimeType(lingJianChaiJiePics.get(i).getName())), lingJianChaiJiePics.get(i));
            picInfoMap.put("file"+i+";"+"\";filename=\""+lingJianChaiJiePics.get(i).getName(),picFile);
        }



        Subscription subscription = mHttpService.submitCuChaiChaiJieInfo(chaiJieTypeRequestBody,userIdRequestBody,oIdRequestBody,picInfoMap)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("submitLingJianChaiJieInfoResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mJobWaitToDoChaiJieDetailCuChaiView.dismissLoading();
                            mJobWaitToDoChaiJieDetailCuChaiView.onSubmitLingJianChaiJieInfoError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mJobWaitToDoChaiJieDetailCuChaiView.dismissLoading();
                        mJobWaitToDoChaiJieDetailCuChaiView.onSubmitLingJianChaiJieInfoError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {


                        mJobWaitToDoChaiJieDetailCuChaiView.dismissLoading();
                        mJobWaitToDoChaiJieDetailCuChaiView.onSubmitLingJianChaiJieInfoSuccess();

                    }
                });
        mSubscriptions.add(subscription);

    }

    @Override
    public void finishChaiJie(String userId, String oId) {
        mJobWaitToDoChaiJieDetailCuChaiView.showLoading();

        Subscription subscription = mHttpService.completeChaiJie(userId,oId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("completeChaiJieResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mJobWaitToDoChaiJieDetailCuChaiView.dismissLoading();
                            mJobWaitToDoChaiJieDetailCuChaiView.onFinishChaiJieError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mJobWaitToDoChaiJieDetailCuChaiView.dismissLoading();
                        mJobWaitToDoChaiJieDetailCuChaiView.onFinishChaiJieError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {


                        mJobWaitToDoChaiJieDetailCuChaiView.dismissLoading();
                        mJobWaitToDoChaiJieDetailCuChaiView.onFinishChaiJieSuccess();

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
