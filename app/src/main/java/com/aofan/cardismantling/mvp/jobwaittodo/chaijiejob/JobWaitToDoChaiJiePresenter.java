package com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.JobWaitToDoChaiJieTaskItem;
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
 * Created by Administrator on 2016/11/23.
 */

public class JobWaitToDoChaiJiePresenter implements JobWaitToDoChaiJieContract.Presenter {


    @NonNull
    JobWaitToDoChaiJieContract.View jobToDoChaiJieView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public JobWaitToDoChaiJiePresenter(@NonNull JobWaitToDoChaiJieContract.View jobToDoChaiJieView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.jobToDoChaiJieView = jobToDoChaiJieView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.jobToDoChaiJieView.setPresenter(this);
    }


    @Override
    public void getWaitToDoChaiJieJob(String userId,String jobKind,String pageIndex,String pageSize) {
        jobToDoChaiJieView.showLoading();

        Subscription subscription = mHttpService.getUserWaitToDoJobList(userId,jobKind,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("userWaitToDoJobResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            jobToDoChaiJieView.dismissLoading();
                            jobToDoChaiJieView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        jobToDoChaiJieView.dismissLoading();
                        jobToDoChaiJieView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<JobWaitToDoChaiJieTaskItem> mChaiJieTaskList = new ArrayList<>();

                        if (jsonObject.get("data").isJsonNull()==false)
                        {
                            mChaiJieTaskList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<JobWaitToDoChaiJieTaskItem>>(){});
                        }
                        jobToDoChaiJieView.dismissLoading();
                        jobToDoChaiJieView.showWaitToDoChaiJieJob(mChaiJieTaskList);

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
