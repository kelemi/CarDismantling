package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.updateComponent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.aofan.cardismantling.widget.PhotoUtil;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * Created by Administrator on 2016/11/23.
 */

public class UpdateComponentPresenter implements UpdateComponentContract.Presenter {


    @NonNull
    UpdateComponentContract.View mMainView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public UpdateComponentPresenter(@NonNull UpdateComponentContract.View mMainView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mMainView = mMainView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mMainView.setPresenter(this);
    }

    @Override
    public void saveUnSignCaijie(String cjtype, String userid, String partid, String partName, String itemcount, String DId, List<String> picPathList) {
        mMainView.showLoading();
        RequestBody cjtypeRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), cjtype);

        RequestBody userIdRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), userid);
        RequestBody partIdRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), partid);
        RequestBody partNameRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), partName);
        RequestBody itemcountRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), itemcount);
        RequestBody DIdRequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), DId);

        Map<String, RequestBody> picInfoMap = new HashMap<>();
        for (int i = 0; i < picPathList.size(); i++) {
            File image = new File(picPathList.get(i));
            Uri uri = Uri.fromFile(image);
            File file = scal(uri);
            RequestBody picFile =
                    RequestBody.create(MediaType.parse(CommonUtil.guessMimeType(file.getName())), file);
            picInfoMap.put("file" + i + ";" + "\";filename=\"" + file.getName(), picFile);
        }

        Subscription subscription = mHttpService.saveUnSignCaijie(cjtypeRequestBody, userIdRequestBody, partIdRequestBody, partNameRequestBody, itemcountRequestBody, DIdRequestBody, picInfoMap)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("saveUnSignCaijie" + jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS)) {
                            mMainView.dismissLoading();
                            mMainView.saveUnSignCaijieFail(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mMainView.dismissLoading();
                        mMainView.netError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        mMainView.dismissLoading();

                        mMainView.saveUnSignCaijieSuccess("上传成功");

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

    //*********************************压缩上传图片*************************************
    public static File scal(Uri fileUri) {
        String path = fileUri.getPath();
        File outputFile = new File(path);
        long fileSize = outputFile.length();
        final long fileMaxSize = 200 * 1024;
        if (fileSize >= fileMaxSize) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int height = options.outHeight;
            int width = options.outWidth;

            double scale = Math.sqrt((float) fileSize / fileMaxSize);
            options.outHeight = (int) (height / scale);
            options.outWidth = (int) (width / scale);
            options.inSampleSize = (int) (scale + 0.5);
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            outputFile = new File(PhotoUtil.createImageFile().getPath());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(outputFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Log.d("sss ok ", "" + outputFile.length());
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            } else {
                File tempFile = outputFile;
                outputFile = new File(PhotoUtil.createImageFile().getPath());
                PhotoUtil.copyFileUsingFileChannels(tempFile, outputFile);
            }

        }
        return outputFile;

    }
}
