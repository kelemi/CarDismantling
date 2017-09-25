package com.aofan.cardismantling.mvp.takephotostorecordinfo.uploadxingshizhenginfo;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.MenuItem;
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
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/24.
 */

public class UploadXingShiZhengInfoPresenter implements UploadXingShiZhengInfoContract.Presenter {


    @NonNull
    UploadXingShiZhengInfoContract.View mUploadXingShiZhengInfoView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public UploadXingShiZhengInfoPresenter(@NonNull UploadXingShiZhengInfoContract.View mUploadXingShiZhengInfoView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mUploadXingShiZhengInfoView = mUploadXingShiZhengInfoView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mUploadXingShiZhengInfoView.setPresenter(this);
    }


    @Override
    public void uploadXingShiZhengInfo(String haoPaiNum,
                                       String carLeiXing,
                                       String owner,
                                       String address,
                                       String shiYongXingZhi,
                                       String pinPaiXingHao,
                                       String carIdCode,
                                       String faDongJiNum,
                                       String registerDate,
                                       String faZhengDate,
                                       String userId,
                                       String picType,
                                       File file) {


        mUploadXingShiZhengInfoView.showLoading();

        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filBody =
                MultipartBody.Part.createFormData("image", "xingShiZheng_" + file.getName(), requestFile);


        RequestBody haoPaiNumRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), haoPaiNum);
        RequestBody carLeiXingRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), carLeiXing);

        RequestBody ownerRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), owner);
        RequestBody addressRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), address);
        RequestBody shiYongXingZhiRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), shiYongXingZhi);
        RequestBody pinPaiXingHaoRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), pinPaiXingHao);
        RequestBody carIdCodeRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), carIdCode);
        RequestBody faDongJiNumRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), faDongJiNum);
        RequestBody registerDateRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), registerDate);
        RequestBody faZhengDateRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), faZhengDate);
        RequestBody userIdRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), userId);
        RequestBody picTypeRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), picType);


        Subscription subscription = mHttpService.uploadXingShiZhengInfo(haoPaiNumRequestBody, carLeiXingRequestBody,ownerRequestBody, addressRequestBody, shiYongXingZhiRequestBody, pinPaiXingHaoRequestBody, carIdCodeRequestBody, faDongJiNumRequestBody, registerDateRequestBody, faZhengDateRequestBody, userIdRequestBody,picTypeRequestBody, filBody)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("uploadXingShiZhengResponse:" + jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS)) {

                            mUploadXingShiZhengInfoView.dismissLoading();
                            mUploadXingShiZhengInfoView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        LogUtil.e(e.getMessage());
                        LogUtil.e(e.getLocalizedMessage());
                        mUploadXingShiZhengInfoView.dismissLoading();
                        mUploadXingShiZhengInfoView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        mUploadXingShiZhengInfoView.dismissLoading();
                        mUploadXingShiZhengInfoView.showUploadXingShiZhengSuccess();
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
