package com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob.detail.cuchai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.grid.GridAdapterForCuChaiLingJianPic;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.bean.ChaiJieDetailInfoForCuChai;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.picshow.ActivityPicShow;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ImageCompressUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.baoyz.actionsheet.NewActionSheet;
import com.jakewharton.rxbinding.view.RxView;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;

/**粗拆的待拆解任务
 * Created by Administrator on 2017/2/7.
 */

public class ActivityWaitToDoChaiJieJobDetailForCuChai extends BaseActivity implements JobWaitToDoChaiJieDetailCuChaiContract.View {


    private LinearLayout layoutFinishChaijie;

    private TextView tvCarnum;
    private TextView tvPaigongTime;
    private TextView tvChaijieWorkers;
    private TextView tvChaijieRequest;
    private TextView tvAddChaijiePic;
    private GridView gvChoosedChaijiePic;

    private TextView tvSubmitCurChaijieInfo;
    private TextView tvFinishChaijie;





    //拆解详情信息
    ChaiJieDetailInfoForCuChai mChaiJieDetailInfo;
    //粗拆已经有的图片信息
    List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> mCuChaiDetailHasedPicList = new ArrayList<>();

    /*//拆解零件信息的adapter
    ListAdapterForChaiJieLingJianSubmitInfo mChaiJieLingJianInfoAdapter;
    //待拆解的零件信息list
    List<ChaiJieDetailInfo.PartlistBean> mChaiJieLingJianInfoList = new ArrayList<>();*/

    GridAdapterForCuChaiLingJianPic mCuChaiLingJianPicAdapter;
    //粗拆新选择的图片列表
    List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> mCuChaiNewChoosePicList = new ArrayList<>();

    //private List<List<PhotoInfo>> mLingJianPhotoList = new ArrayList<>();

    //选择的零件的图片列表
    private List<PhotoInfo> mChooseLingJianPhotoList = new ArrayList<>();
    private List<File> fileList = new ArrayList<>();

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

    String mOid;
    String mCarNum;
    //拆解类型
    String mChaiJieType;


    JobWaitToDoChaiJieDetailCuChaiContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_todo_chaijie_job_detail_for_cuchai);
        initTitleWithRightTvOrIv(null, null, "拆解", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new JobWaitToDoChaiJieDetailCuChaiPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
        getChaiJiePaiGongDanDetail();
    }

    @Override
    public void initData() {

        mOid = getIntent().getStringExtra(IntentKey.INTENT_KEY_PAIGONGDAN_OID);
        mCarNum = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_NUM);
        mChaiJieType = getIntent().getStringExtra(IntentKey.INTENT_KEY_CAR_CHAIJIE_TYPE);
    }

    @Override
    public void assignView() {
        layoutFinishChaijie = (LinearLayout) findViewById(R.id.layout_finish_chaijie);
        tvFinishChaijie = (TextView) findViewById(R.id.tv_finish_chaijie);
        tvSubmitCurChaijieInfo = (TextView) findViewById(R.id.tv_submit_cur_chaijie_info);

        tvCarnum = (TextView) findViewById(R.id.tv_carnum);
        tvPaigongTime = (TextView) findViewById(R.id.tv_paigong_time);
        tvChaijieWorkers = (TextView) findViewById(R.id.tv_chaijie_workers);
        tvChaijieRequest = (TextView) findViewById(R.id.tv_chaijie_request);
        tvAddChaijiePic = (TextView) findViewById(R.id.tv_add_chaijie_pic);
        gvChoosedChaijiePic = (GridView) findViewById(R.id.gv_choosed_chaijie_pic);
    }

    @Override
    public void initView() {
        tvCarnum.setText(mCarNum);

        RxView.clicks(tvFinishChaijie)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        finishChaiJie();
                    }
                });

        RxView.clicks(tvSubmitCurChaijieInfo)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (mChooseLingJianPhotoList.size()>0)
                        {
                            compressedPicAndUpload(mChooseLingJianPhotoList);
                        }else {
                            ToastUtil.showShort(ActivityWaitToDoChaiJieJobDetailForCuChai.this,"请选择要提交的拆解图片");
                        }

                    }
                });




        RxView.clicks(tvAddChaijiePic)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showChoosePicSheet();
                    }
                });
        initAdapter();
    }


    private void initAdapter() {

        mCuChaiNewChoosePicList = new ArrayList<>();

        mCuChaiLingJianPicAdapter = new GridAdapterForCuChaiLingJianPic(mCuChaiDetailHasedPicList,this,true);
        mCuChaiLingJianPicAdapter.setGridPicClickListener(new CuChaiPicClickListenerImpl());
        mCuChaiLingJianPicAdapter.setGridPicDeleteClickListener(new CuChaiPicDeleteClickListenerImpl());
        gvChoosedChaijiePic.setAdapter(mCuChaiLingJianPicAdapter);

        //mCuChaiLingJianPicAdapter.setGridPicClickListener();



       /* mChaiJieLingJianInfoAdapter = new ListAdapterForChaiJieLingJianSubmitInfo(mChaiJieLingJianInfoList, this);
        mChaiJieLingJianInfoAdapter.setToTakeLingJianPhotoTvClickListener(new ActivityWaitToDoChaiJieJobDetailForCuChai.ToTakeLingJianPhotoTvClickListenerImpl());
        mChaiJieLingJianInfoAdapter.setToSubmitLingJianChaiJieInfoTvClickListener(new ActivityWaitToDoChaiJieJobDetailForCuChai.ToSubmitLingJianChaiJieInfoTvClickListenerImpl());
        mChaiJieLingJianInfoAdapter.setLingJianPicClickListener(new ActivityWaitToDoChaiJieJobDetailForCuChai.LingJianPicClickListenerImpl());
        mChaiJieLingJianInfoAdapter.setLingJianPicDeleteClickListener(new ActivityWaitToDoChaiJieJobDetailForCuChai.LingJianPicDeleteClickListenerImpl());

        mChaiJieLingJianInfoAdapter.setAddLingJianNumCliclListener(new ActivityWaitToDoChaiJieJobDetailForCuChai.AddLingJianNumCliclListenerImpl());
        mChaiJieLingJianInfoAdapter.setSubLingJianNumClickListener(new ActivityWaitToDoChaiJieJobDetailForCuChai.SubLingJianNumClickListenerImpl());
*/
        //lv.setAdapter(mChaiJieLingJianInfoAdapter);
    }


    @Override
    public void showLoading() {
        showProgressDialog(false, null);
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }

    @Override
    public void getChaiJiePaiGongDanDetail() {
        mPresenter.getChaiJiePaiGongDanDetail(mOid,mChaiJieType);
    }

    @Override
    public void showChaiJiePaiGongDanDetail(ChaiJieDetailInfoForCuChai chaiJieDetail) {

        mChaiJieDetailInfo = chaiJieDetail;
        mCuChaiDetailHasedPicList = chaiJieDetail.getImgattachlist();

        fillChaiJieDetailInfoToView(chaiJieDetail);

        /*mLingJianPhotoList.clear();
        mChaiJieLingJianInfoList.clear();
        mChaiJieLingJianInfoList.addAll(mChaiJieDetailInfo.getPartlist());

        if (mChaiJieDetailInfo.getPartlist().size() > 0) {
            for (int i = 0; i < mChaiJieDetailInfo.getPartlist().size(); i++) {
                mLingJianPhotoList.add(new ArrayList<PhotoInfo>());
            }

        }*/



        initAdapter();

        //mChaiJieLingJianInfoAdapter.notifyDataSetChanged();
    }

    private void fillChaiJieDetailInfoToView(ChaiJieDetailInfoForCuChai chaiJieDetail) {
        // tvCarnum.setText(chaiJieDetail.get);
        tvPaigongTime.setText("派工时间:" + chaiJieDetail.getCreatetime());
        tvChaijieWorkers.setText("拆解人员:" + chaiJieDetail.getDispatchperson());
        tvChaijieRequest.setText(chaiJieDetail.getRequirement());
    }

    @Override
    public void showError(String tip) {
        ToastUtil.showShort(this, tip);
    }


    private static final int HANDLER_TAG_COMPRESSED_PIC_SUCCESS = 1000;

    //处理图片压缩的handler
    Handler mCompressedFileHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case HANDLER_TAG_COMPRESSED_PIC_SUCCESS:

                    List<File> compressedPicList = (List<File>) msg.obj;
                    submitLingJianChaiJieInfo(compressedPicList);

                    break;
            }
        }
    };

    @Override
    public void submitLingJianChaiJieInfo(List<File> picFileList) {

        mPresenter.submitLingJianChaiJieInfo("粗拆",(String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""), mOid,
                picFileList);

    }

    //压缩选择的图片
    public void compressChooseToSubmitFile(final List<PhotoInfo> toCompressedPhotoList)
    {

        final List<File> compressedFileList = new ArrayList<>();

        for (int i = 0; i < toCompressedPhotoList.size(); i++) {

            LogUtil.e("beforeCompressFileSize:"+new File(toCompressedPhotoList.get(i).getPhotoPath()).length() / 1024 + "k");

            Luban.get(this)
                    .load(new File(toCompressedPhotoList.get(i).getPhotoPath()))
                    .putGear(5)
                    .asObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    })
                    .onErrorResumeNext(new Func1<Throwable, Observable<? extends File>>() {
                        @Override
                        public Observable<? extends File> call(Throwable throwable) {
                            return Observable.empty();
                        }
                    })
                    .subscribe(new Action1<File>() {
                        @Override
                        public void call(File file) {

                            LogUtil.e("afterCompressFileSize:"+file.length() / 1024 + "k");


                            compressedFileList.add(file);

                            if (compressedFileList.size() == toCompressedPhotoList.size())
                            {
                                Message message = new Message();
                                message.what = HANDLER_TAG_COMPRESSED_PIC_SUCCESS;
                                message.obj = compressedFileList;
                                mCompressedFileHandler.sendMessage(message);
                            }
                        }
                    });



        }




    }

    @Override
    public void onSubmitLingJianChaiJieInfoSuccess() {
        ToastUtil.showShort(this, "零件拆解信息上传成功");

        mCuChaiNewChoosePicList.clear();
        mChooseLingJianPhotoList.clear();

        getChaiJiePaiGongDanDetail();
    }

    @Override
    public void onSubmitLingJianChaiJieInfoError(String tip) {

    }

    @Override
    public void finishChaiJie() {
        mPresenter.finishChaiJie((String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),
                mOid);
    }

    @Override
    public void onFinishChaiJieSuccess() {
        ToastUtil.showShort(this, "完成拆解信息提交成功");
        finish();
    }

    @Override
    public void onFinishChaiJieError(String tip) {
        ToastUtil.showShort(this, tip);
    }

    @Override
    public void setPresenter(JobWaitToDoChaiJieDetailCuChaiContract.Presenter presenter) {

    }



    /*private class ToSubmitLingJianChaiJieInfoTvClickListenerImpl implements ListAdapterForChaiJieLingJianSubmitInfo.ToSubmitLingJianChaiJieInfoTvClickListener {


        @Override
        public void onToSubmitLingJianChaiJieInfoTvClick(int position) {
            mToSubmitChaiJieInfoPart = mChaiJieLingJianInfoList.get(position);
            mToSubmitChaiJieInfoLingJianPhotoList = mLingJianPhotoList.get(position);

            if (Integer.valueOf(mToSubmitChaiJieInfoPart.getOutputcount()) <= 0) {
                ToastUtil.showShort(ActivityWaitToDoChaiJieJobDetailForCuChai.this, "请设置拆解出来的零件数量");
                return;
            }

            if (Integer.valueOf(mToSubmitChaiJieInfoLingJianPhotoList.size()) <= 0) {
                ToastUtil.showShort(ActivityWaitToDoChaiJieJobDetailForCuChai.this, "请选择拆解零件的图片");
                return;
            }
            *//*submitLingJianChaiJieInfo();*//*
            //compressChooseToSubmitFile(mToSubmitChaiJieInfoLingJianPhotoList);
            compressedPicAndUpload(mToSubmitChaiJieInfoLingJianPhotoList);
        }
    }*/

    //压缩图片并且上传
    private void compressedPicAndUpload(final List<PhotoInfo> choosedPhotoInfo){

        showProgressDialog(false,null);

        final List<File> compressedFileList = new ArrayList<>();

        Observable.from(choosedPhotoInfo)

                .map(new Func1<PhotoInfo, File>() {
                    @Override
                    public File call(PhotoInfo photoInfo) {
                        LogUtil.e("BeforeCompressFileSize:"+new File(photoInfo.getPhotoPath()).length() / 1024 + "k");

                        return ImageCompressUtil.compressLocalPic(photoInfo.getPhotoPath());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        LogUtil.e("afterCompressFileSize:"+file.length() / 1024 + "k");

                        compressedFileList.add(file);

                        if (compressedFileList.size() == choosedPhotoInfo.size())
                        {
                            Message message = new Message();
                            message.what = HANDLER_TAG_COMPRESSED_PIC_SUCCESS;
                            message.obj = compressedFileList;
                            mCompressedFileHandler.sendMessage(message);
                        }
                    }
                });
    }

    private class CuChaiPicClickListenerImpl implements GridAdapterForCuChaiLingJianPic.GridPicClickListener {
        @Override
        public void onGridPicItemClick(int position, List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> cuChaiPicList) {


            List<String> picUrls = new ArrayList<>();

            for (ChaiJieDetailInfoForCuChai.ImgattachlistBean picItem : cuChaiPicList) {
                if (TextUtils.isEmpty(picItem.getPath()) == false) {
                    picUrls.add(Config.URL_TO_LOAD_PIC_IN_USE + picItem.getPath());
                } else {
                    picUrls.add(picItem.getLocalPicPath());
                }
            }

            Intent toShowPics = new Intent(ActivityWaitToDoChaiJieJobDetailForCuChai.this, ActivityPicShow.class);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION, position);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_URLS, (Serializable) picUrls);
            //
            startActivity(toShowPics);
        }

        /*@Override
        public void onGridPicItemClick(int position, List<ChaiJieDetailInfo.PartlistBean.ImgattachlistBean> imageAttachList) {

            List<ChaiJieDetailInfo.PartlistBean.ImgattachlistBean> newImgAttachList = imageAttachList;

            List<String> picUrls = new ArrayList<>();

            for (ChaiJieDetailInfo.PartlistBean.ImgattachlistBean imgattachlistBean : newImgAttachList) {
                if (TextUtils.isEmpty(imgattachlistBean.getPath()) == false) {
                    picUrls.add(Config.URL_TO_LOAD_PIC_IN_USE + imgattachlistBean.getPath());
                } else {
                    picUrls.add(imgattachlistBean.getLoaclPicPath());
                }
            }

            Intent toShowPics = new Intent(ActivityWaitToDoChaiJieJobDetailForCuChai.this, ActivityPicShow.class);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION, position);

            toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_URLS, (Serializable) picUrls);
            //
            startActivity(toShowPics);

        }*/
    }


    private class CuChaiPicDeleteClickListenerImpl implements GridAdapterForCuChaiLingJianPic.GridPicDeleteClickListener {
        @Override
        public void onGridPicDelete(int position) {





            ChaiJieDetailInfoForCuChai.ImgattachlistBean toDeletePic = mCuChaiDetailHasedPicList.get(position);



            //获取删除图片的pos
            int toDeletePhotoPos = -1;
            for (int i = 0 ;i <mChooseLingJianPhotoList.size();i++)
            {
                if (mChooseLingJianPhotoList.get(i).getPhotoPath().equals(toDeletePic.getLocalPicPath()))
                {
                    toDeletePhotoPos = i;
                }
            }
            mChooseLingJianPhotoList.remove(toDeletePhotoPos);


            mCuChaiNewChoosePicList.remove(toDeletePic);
            mCuChaiDetailHasedPicList.remove(toDeletePic);

            mCuChaiLingJianPicAdapter.notifyDataSetChanged();
        }



        /*@Override
        public void onGridPicDelete(int lingJianPositon, int position) {
            //删除本地已经选择图片
            mLingJianPhotoList.get(lingJianPositon).remove(position);
            //删除零件信息中的对应图片
            mChaiJieLingJianInfoList.get(lingJianPositon).getImgattachlist().remove(position);
            mChaiJieLingJianInfoAdapter.notifyDataSetChanged();
        }*/
    }

    /*//当前处理的零件的位置
    int mNowChooseDealLingJianPosition;
    ChaiJieDetailInfo.PartlistBean mNowDealChaiJieLingJianInfoItem;
    List<PhotoInfo> mNowDealLingJianPhotoInfo;*/

    //显示选择照片的actionSheet
    private void showChoosePicSheet(/*int nowChooseLingJianPosition*/) {

        /*mNowChooseDealLingJianPosition = nowChooseLingJianPosition;

        mNowDealChaiJieLingJianInfoItem = mChaiJieLingJianInfoList.get(nowChooseLingJianPosition);
        mNowDealLingJianPhotoInfo = mLingJianPhotoList.get(nowChooseLingJianPosition);*/


        NewActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("从相册选", "拍照")
                .setCancelableOnTouchOutside(true)
                .setListener(new NewActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(NewActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(NewActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                               /* int leftCanChoosePicNum = 4 - (CommonUtil.isListNullOrEmpty(mNowDealWaitChaiJieLingJianInfoItem.getLingJianPicList())==false?mNowDealWaitChaiJieLingJianInfoItem.getLingJianPicList().size():0);*/

                                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, initGalleyFunctionConfig(4, mChooseLingJianPhotoList), mOnHanlderResultCallback);

                                break;
                            case 1:
                                //int leftCanChoosePicNum2 = 4 - (CommonUtil.isListNullOrEmpty(mNowDealWaitChaiJieLingJianInfoItem.getLingJianPicList())==false?mNowDealWaitChaiJieLingJianInfoItem.getLingJianPicList().size():0);
                                if (mChooseLingJianPhotoList.size() < 4) {
                                    GalleryFinal.openCamera(REQUEST_CODE_CAMERA, initGalleyFunctionConfig(4, mChooseLingJianPhotoList), mOnHanlderResultCallback);

                                } else {
                                    ToastUtil.showShort(ActivityWaitToDoChaiJieJobDetailForCuChai.this, "每次最多上传4张图片");
                                }

                                break;

                            default:
                                break;
                        }
                    }
                })
                .show();
    }


    //初始化图片选择functionconfig
    private FunctionConfig initGalleyFunctionConfig(int leftCanChoosePicNum, List<PhotoInfo> hasChoosedPhotoList) {
        //配置功能
       /* FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setMutiSelectMaxSize(leftCanChoosePicNum)
                .build();*/
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        functionConfigBuilder.setMutiSelectMaxSize(leftCanChoosePicNum);
        functionConfigBuilder.setEnableCamera(true);
        functionConfigBuilder.setEnablePreview(true);
        functionConfigBuilder.setSelected(hasChoosedPhotoList);//添加过滤集合
        return functionConfigBuilder.build();

    }


    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int requestCode, List<PhotoInfo> resultList) {

            if (requestCode == REQUEST_CODE_GALLERY) {
                if (resultList != null) {
                    mChooseLingJianPhotoList.clear();
                    mChooseLingJianPhotoList.addAll(resultList);

                    List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> cuChaiPicList = new ArrayList<>();

                    for (int i = 0; i < resultList.size(); i++) {

                        ChaiJieDetailInfoForCuChai.ImgattachlistBean cuChaiPicItem = new ChaiJieDetailInfoForCuChai.ImgattachlistBean();

                        cuChaiPicItem.setLocalPicPath(resultList.get(i).getPhotoPath());
                        cuChaiPicItem.setLoaclPic(true);
                        /*ChaiJieDetailInfo.PartlistBean.ImgattachlistBean imgattachlistBean = new ChaiJieDetailInfo.PartlistBean.ImgattachlistBean();
                        imgattachlistBean.setLoaclPicPath(resultList.get(i).getPhotoPath());
                        imageAttachList.add(imgattachlistBean);*/
                        cuChaiPicList.add(cuChaiPicItem);
                    }

                    mCuChaiNewChoosePicList.clear();
                    mCuChaiNewChoosePicList.addAll(cuChaiPicList);

                    mCuChaiDetailHasedPicList.addAll(cuChaiPicList);

                    mCuChaiLingJianPicAdapter.notifyDataSetChanged();

                    /*mChaiJieLingJianInfoList.get(mNowChooseDealLingJianPosition).setLocalPic(true);
                    mChaiJieLingJianInfoList.get(mNowChooseDealLingJianPosition).setImgattachlist(imageAttachList);
                    mChaiJieLingJianInfoAdapter.notifyDataSetChanged();*/
                }
            }
            //如果是拍照的话，则直接添加
            if (requestCode == REQUEST_CODE_CAMERA) {
                if (resultList != null) {
                    //拍照后就直接添加，不需要清空，因为能拍照说明，还能选择图片
                    mChooseLingJianPhotoList.addAll(resultList);
                    /*List<String> picUrl = new ArrayList<>();

                    for (int i = 0;i<resultList.size();i++)
                    {
                        picUrl.add(resultList.get(i).getPhotoPath());
                    }*/
                    /*List<ChaiJieDetailInfo.PartlistBean.ImgattachlistBean> imageAttachList = new ArrayList<>();

                    for (int i = 0; i < resultList.size(); i++) {
                        ChaiJieDetailInfo.PartlistBean.ImgattachlistBean imgattachlistBean = new ChaiJieDetailInfo.PartlistBean.ImgattachlistBean();
                        imgattachlistBean.setLoaclPicPath(resultList.get(i).getPhotoPath());
                        imageAttachList.add(imgattachlistBean);
                        //picUrl.add(resultList.get(i).getPhotoPath());
                    }*/

                    List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> cuChaiPicList = new ArrayList<>();

                    for (int i = 0; i < resultList.size(); i++) {

                        ChaiJieDetailInfoForCuChai.ImgattachlistBean cuChaiPicItem = new ChaiJieDetailInfoForCuChai.ImgattachlistBean();

                        cuChaiPicItem.setLocalPicPath(resultList.get(i).getPhotoPath());
                        cuChaiPicItem.setLoaclPic(true);
                        /*ChaiJieDetailInfo.PartlistBean.ImgattachlistBean imgattachlistBean = new ChaiJieDetailInfo.PartlistBean.ImgattachlistBean();
                        imgattachlistBean.setLoaclPicPath(resultList.get(i).getPhotoPath());
                        imageAttachList.add(imgattachlistBean);*/
                        cuChaiPicList.add(cuChaiPicItem);
                    }

                    mCuChaiNewChoosePicList.addAll(cuChaiPicList);

                    mCuChaiDetailHasedPicList.addAll(cuChaiPicList);

                    mCuChaiLingJianPicAdapter.notifyDataSetChanged();

                    /*if (CommonUtil.isListNullOrEmpty(mChaiJieLingJianInfoList.get(mNowChooseDealLingJianPosition).getImgattachlist()) == false) {


                        mChaiJieLingJianInfoList.get(mNowChooseDealLingJianPosition).setLocalPic(true);
                        mChaiJieLingJianInfoList.get(mNowChooseDealLingJianPosition).getImgattachlist().addAll(imageAttachList);

                    } else {
                        mChaiJieLingJianInfoList.get(mNowChooseDealLingJianPosition).setLocalPic(true);
                        mChaiJieLingJianInfoList.get(mNowChooseDealLingJianPosition).setImgattachlist(imageAttachList);
                    }

                    mChaiJieLingJianInfoAdapter.notifyDataSetChanged();*/

                }
            }


        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(ActivityWaitToDoChaiJieJobDetailForCuChai.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };


   /* public class AddLingJianNumCliclListenerImpl implements ListAdapterForChaiJieLingJianSubmitInfo.AddLingJianNumCliclListener {
        public void onAddLingJianNumClick(int position) {
            int nowLingJianNum = Integer.valueOf(mChaiJieLingJianInfoList.get(position).getOutputcount());
            mChaiJieLingJianInfoList.get(position).setOutputcount(String.valueOf(++nowLingJianNum));
            mChaiJieLingJianInfoAdapter.notifyDataSetChanged();
        }
    }*/

    /*public class SubLingJianNumClickListenerImpl implements ListAdapterForChaiJieLingJianSubmitInfo.SubLingJianNumClickListener {
        public void onSubLingJianNumClick(int position) {
            int nowLingJianNum = Integer.valueOf(mChaiJieLingJianInfoList.get(position).getOutputcount());

            if (Integer.valueOf(mChaiJieLingJianInfoList.get(position).getOutputcount()) > 0) {
                nowLingJianNum = nowLingJianNum - 1;
                mChaiJieLingJianInfoList.get(position).setOutputcount(String.valueOf(nowLingJianNum));
            }
            mChaiJieLingJianInfoAdapter.notifyDataSetChanged();
        }
    }*/



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
