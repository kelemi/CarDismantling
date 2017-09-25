package com.aofan.cardismantling.mvp.takephotostorecordinfo.uploadphoto;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.JsonObject;

import java.io.File;

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

public class UploadPhotoPresenter implements UploadPhotoContract.Presenter {

    @NonNull
    UploadPhotoContract.View mUploadPhotoView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public UploadPhotoPresenter(@NonNull UploadPhotoContract.View mUploadPhotoView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mUploadPhotoView = mUploadPhotoView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mUploadPhotoView.setPresenter(this);
    }

    @Override
    public void uploadOtherPic(String haoPaiNum,
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


        mUploadPhotoView.showLoading();

        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filBody =
                MultipartBody.Part.createFormData("image", "otherpic_" + file.getName(), requestFile);

        RequestBody userIdRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), userId);
        RequestBody picTypeRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), picType);


        //Subscription subscription = mHttpService.uploadXingShiZhengInfo(haoPaiNumRequestBody, ownerRequestBody, addressRequestBody, shiYongXingZhiRequestBody, pinPaiXingHaoRequestBody, carIdCodeRequestBody, faDongJiNumRequestBody, registerDateRequestBody, faZhengDateRequestBody, userIdRequestBody,picTypeRequestBody, filBody)
        Subscription subscription = mHttpService.uploadOtherBasePic(userIdRequestBody,picTypeRequestBody, filBody)

                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("uploadOtherPicResponse:" + jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS)) {
                            mUploadPhotoView.dismissLoading();
                            mUploadPhotoView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mUploadPhotoView.dismissLoading();
                        mUploadPhotoView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        mUploadPhotoView.dismissLoading();
                        mUploadPhotoView.showUploadPhotoSuccess();
                    }
                });
        mSubscriptions.add(subscription);


    }

    /*@Override
    public void uploadPhoto(String fileType,String userId,File file){
        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filBody =
                MultipartBody.Part.createFormData("image", "xingShiZheng_" + file.getName(), requestFile);


        RequestBody haoPaiNumRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");
        RequestBody ownerRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");
        RequestBody addressRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");
        RequestBody shiYongXingZhiRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");
        RequestBody pinPaiXingHaoRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");
        RequestBody carIdCodeRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");
        RequestBody faDongJiNumRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");
        RequestBody registerDateRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");
        RequestBody faZhengDateRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");
        RequestBody userIdRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), userId);
        RequestBody picTypeRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1231231");


        Subscription subscription = mHttpService.uploadXingShiZhengInfo(haoPaiNumRequestBody, ownerRequestBody, addressRequestBody, shiYongXingZhiRequestBody, pinPaiXingHaoRequestBody, carIdCodeRequestBody, faDongJiNumRequestBody, registerDateRequestBody, faZhengDateRequestBody, userIdRequestBody,picTypeRequestBody, filBody)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("uploadPhotoResponse:" + jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS)) {

                            mUploadPhotoView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mUploadPhotoView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        mUploadPhotoView.showUploadPhotoSuccess();
                    }
                });
        mSubscriptions.add(subscription);
    }*/

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.unsubscribe();
        mSubscriptions = null;
    }
}
