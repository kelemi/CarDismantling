package com.aofan.cardismantling.mvp.carwaitanalysis.carwaitanalysisdetail;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.CarLingJianInfo;
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
 * Created by Administrator on 2016/11/24.
 */

public class WaitToAnalysisCarDetailPresenter implements WaitToAnalysisCarDetailContract.Presenter {


    @NonNull
    WaitToAnalysisCarDetailContract.View mWaitToAnalysisCarDetailView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public WaitToAnalysisCarDetailPresenter(@NonNull WaitToAnalysisCarDetailContract.View mWaitToAnalysisCarDetailView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mWaitToAnalysisCarDetailView = mWaitToAnalysisCarDetailView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mWaitToAnalysisCarDetailView.setPresenter(this);
    }


    @Override
    public void getFeiQiWuInfo(String userId) {

        Subscription subscription = mHttpService.getAllFeiQiWuInfo(userId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("allFeiQiWuInfo:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mWaitToAnalysisCarDetailView.dismissLoading();
                            mWaitToAnalysisCarDetailView.showGetFeiQiWuInfoError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mWaitToAnalysisCarDetailView.dismissLoading();
                        mWaitToAnalysisCarDetailView.showGetFeiQiWuInfoError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        JsonObject dataObject = jsonObject.get("data").getAsJsonObject();

                        List<CarLingJianInfo> dianChiFeiQiWuParts = CommonUtil.formatJsonToList(dataObject,"datakother",new TypeToken<List<CarLingJianInfo>>(){});
                        List<CarLingJianInfo> otherFeiQiWuParts = CommonUtil.formatJsonToList(dataObject,"datakone",new TypeToken<List<CarLingJianInfo>>(){});


                        List<CarLingJianInfo> queShiDianChiFeiQiWuParts = CommonUtil.formatJsonToList(dataObject,"datakother",new TypeToken<List<CarLingJianInfo>>(){});
                        List<CarLingJianInfo> queShiOtherFeiQiWuParts = CommonUtil.formatJsonToList(dataObject,"datakone",new TypeToken<List<CarLingJianInfo>>(){});

                        /*List<CarLingJianInfo> carFaDongJiInfoList = CommonUtil.formatJsonToList(dataObject,"发动机总成",new TypeToken<List<CarLingJianInfo>>(){});
                        List<CarLingJianInfo> carDiPanInfoList = CommonUtil.formatJsonToList(dataObject,"底盘总成",new TypeToken<List<CarLingJianInfo>>(){});
                        List<CarLingJianInfo> carJiaShiShiInfoList = CommonUtil.formatJsonToList(dataObject,"驾驶室总成",new TypeToken<List<CarLingJianInfo>>(){});
                        List<CarLingJianInfo> carCheXiangInfoList = CommonUtil.formatJsonToList(dataObject,"车箱总成",new TypeToken<List<CarLingJianInfo>>(){});
*/
                        mWaitToAnalysisCarDetailView.dismissLoading();
                        mWaitToAnalysisCarDetailView.showGetFeiQiWuInfoSuccess(dianChiFeiQiWuParts,otherFeiQiWuParts,queShiDianChiFeiQiWuParts,queShiOtherFeiQiWuParts);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getWaitToAnalysisCarLingJianInfo(String userId) {
        mWaitToAnalysisCarDetailView.showLoading();

        Subscription subscription = mHttpService.getCarAllToChaiJieLingJianInfo(userId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("allAnalysisLingJianinfo:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mWaitToAnalysisCarDetailView.dismissLoading();
                            mWaitToAnalysisCarDetailView.showGetWaitToAnalysisCarLingJianInfoError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mWaitToAnalysisCarDetailView.dismissLoading();
                        mWaitToAnalysisCarDetailView.showGetWaitToAnalysisCarLingJianInfoError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        JsonObject dataObject = jsonObject.get("data").getAsJsonObject();

                        List<CarLingJianInfo> carFaDongJiInfoList = CommonUtil.formatJsonToList(dataObject,"发动机总成",new TypeToken<List<CarLingJianInfo>>(){});
                        List<CarLingJianInfo> carDiPanInfoList = CommonUtil.formatJsonToList(dataObject,"底盘总成",new TypeToken<List<CarLingJianInfo>>(){});
                        List<CarLingJianInfo> carJiaShiShiInfoList = CommonUtil.formatJsonToList(dataObject,"驾驶室总成",new TypeToken<List<CarLingJianInfo>>(){});
                        List<CarLingJianInfo> carCheXiangInfoList = CommonUtil.formatJsonToList(dataObject,"车箱总成",new TypeToken<List<CarLingJianInfo>>(){});

                        mWaitToAnalysisCarDetailView.dismissLoading();
                        mWaitToAnalysisCarDetailView.showWaitToAnalysisCarLingJianInfo(carFaDongJiInfoList,carDiPanInfoList,carJiaShiShiInfoList,carCheXiangInfoList);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void submitCarAnalysisInfo(String carId, String chaiJieWay, String userId, String analysisLingJianInfo,String unAnalysisLingJianInfo) {
        mWaitToAnalysisCarDetailView.showLoading();

        Subscription subscription = mHttpService.submitCarAnalysisInfo(carId,chaiJieWay,userId,analysisLingJianInfo,unAnalysisLingJianInfo)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("submitCarAnalysisInfoResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mWaitToAnalysisCarDetailView.dismissLoading();
                            mWaitToAnalysisCarDetailView.showSubmitCarAnalysisInfoError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mWaitToAnalysisCarDetailView.dismissLoading();
                        mWaitToAnalysisCarDetailView.showSubmitCarAnalysisInfoError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {


                        mWaitToAnalysisCarDetailView.dismissLoading();
                        mWaitToAnalysisCarDetailView.showSubmitCarAnalysisInfoSuccess();
                    }
                });
        mSubscriptions.add(subscription);
    }




    public void submitCarAnalysisInfoCuChai(String carId, String chaiJieWay, String userId,String feiQiWuChaiJieInfo) {
        mWaitToAnalysisCarDetailView.showLoading();

        Subscription subscription = mHttpService.submitCarAnalysisInfoCuChai(carId,chaiJieWay,userId,feiQiWuChaiJieInfo)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("submitCarAnalysisCuChaiInfoResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mWaitToAnalysisCarDetailView.dismissLoading();
                            mWaitToAnalysisCarDetailView.showSubmitCarAnalysisInfoError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mWaitToAnalysisCarDetailView.dismissLoading();
                        mWaitToAnalysisCarDetailView.showSubmitCarAnalysisInfoError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {


                        mWaitToAnalysisCarDetailView.dismissLoading();
                        mWaitToAnalysisCarDetailView.showSubmitCarAnalysisInfoSuccess();
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
