package com.aofan.cardismantling.mvp.takephotostorecordinfo.uploadphoto;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.aofan.cardismantling.util.viewutil.BitmapTransformation;
import com.baoyz.actionsheet.NewActionSheet;
import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import rx.functions.Action1;

/**
 * 显示拍摄的图片的界面
 * Created by Administrator on 2016/11/24.
 */

public class ActivityShowTakePhoto extends BaseActivity implements UploadPhotoContract.View {

    private TextView tvTakePhotoAgain;
    private TextView tvSubmitPhoto;
    private ImageView ivShowTakePhoto;


    private List<PhotoInfo> mPhotoList = new ArrayList<>();
    private List<File> fileList = new ArrayList<>();

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

    String mTitle;
    int mPhotoType;
    String mTakePhotoLocalPath;


    UploadPhotoContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_take_photo);
        initData();
        assignView();
        initView();
        mPresenter = new UploadPhotoPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

   /* public final static int TAKE_PHOTO_TYPE_XINGSHIZHENG = 1;
    public final static int TAKE_PHOTO_TYPE_CHELIANG = 2;
    public final static int TAKE_PHOTO_TYPE_CHEZHU_IDCARD_FRONT = 3;
    public final static int TAKE_PHOTO_TYPE_CHEZHU_IDCARD_BACK = 4;
    public final static int TAKE_PHOTO_TYPE_DAILI_IDCARD_FRONT = 5;
    public final static int TAKE_PHOTO_TYPE_DAILI_IDCARD_BACK = 6;
    public final static int TAKE_PHOTO_TYPE_CARSALER_IDCARD_FRONT = 7;
    public final static int TAKE_PHOTO_TYPE_CARSALER_IDCARD_BACK = 8;*/

    @Override
    public void initData() {
        //mTitle = getIntent().getStringExtra(IntentKey.INTENT_KEY_SHOW_PHOTO_TITLE);
        mPhotoType = getIntent().getIntExtra(IntentKey.INTENT_KEY_TAKE_PHOTO_KIND, 0);
        mTakePhotoLocalPath = getIntent().getStringExtra(IntentKey.INTENT_KEY_TAKE_PHOTO_LOCAL_PATH);
        switch (mPhotoType) {

            case Config.TAKE_PHOTO_TYPE_CHEZHU_IDCARD_FRONT:
                mTitle = "车主身份证正面";
                break;
            case Config.TAKE_PHOTO_TYPE_CHEZHU_IDCARD_BACK:
                mTitle = "车主身份证反面";
                break;
            case Config.TAKE_PHOTO_TYPE_DAILI_IDCARD_FRONT:
                mTitle = "代理人身份证正面";
                break;
            case Config.TAKE_PHOTO_TYPE_DAILI_IDCARD_BACK:
                mTitle = "代理人身份证反面";
                break;
            case Config.TAKE_PHOTO_TYPE_CARSALER_IDCARD_FRONT:
                mTitle = "收车人身份证正面";
                break;
            case Config.TAKE_PHOTO_TYPE_CARSALER_IDCARD_BACK:
                mTitle = "收车人身份证反面";
                break;
            case Config.TAKE_PHOTO_TYPE_BUSINESS_LICENCE:
                mTitle = "企业营业执照";
                break;
        }
    }

    @Override
    public void assignView() {
        tvTakePhotoAgain = (TextView) findViewById(R.id.tv_take_photo_again);
        tvSubmitPhoto = (TextView) findViewById(R.id.tv_submit_photo);
        ivShowTakePhoto = (ImageView) findViewById(R.id.iv_show_take_photo);
    }

    @Override
    public void initView() {

        initTitleWithRightTvOrIv(null, null, mTitle, true, null, null);

        RxView.clicks(tvTakePhotoAgain)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showChoosePicSheet();
                    }
                });

        RxView.clicks(tvSubmitPhoto)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        uploadPhoto();
                    }
                });

        if (TextUtils.isEmpty(mTakePhotoLocalPath) == false) {
            showChooseOrTakePhoto(mTakePhotoLocalPath);
        }

    }

    //显示选择照片的actionSheet
    private void showChoosePicSheet() {


        NewActionSheet.createBuilder(ActivityShowTakePhoto.this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("从相册选一张", "拍一张照片")
                .setCancelableOnTouchOutside(true)
                .setListener(new NewActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(NewActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(NewActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, initGalleyFunctionConfig(), mOnHanlderResultCallback);

                                break;
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, initGalleyFunctionConfig(), mOnHanlderResultCallback);

                                break;

                            default:
                                break;
                        }
                    }
                })
                .show();
    }


    //初始化图片选择functionconfig
    private FunctionConfig initGalleyFunctionConfig() {
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setMutiSelectMaxSize(1)
                .build();
        return functionConfig;
    }


    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int requestCode, List<PhotoInfo> resultList) {

            if (requestCode == REQUEST_CODE_GALLERY) {
                if (resultList != null) {
                    mPhotoList.clear();
                    mPhotoList.addAll(resultList);
                    mTakePhotoLocalPath = mPhotoList.get(0).getPhotoPath();
                    showChooseOrTakePhoto(mTakePhotoLocalPath);

                }
            }
            //如果是拍照的话，则直接添加
            if (requestCode == REQUEST_CODE_CAMERA) {
                if (resultList != null) {
                    mPhotoList.clear();
                    mPhotoList.addAll(resultList);
                    mTakePhotoLocalPath = mPhotoList.get(0).getPhotoPath();
                    showChooseOrTakePhoto(mTakePhotoLocalPath);

                }
            }


        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(ActivityShowTakePhoto.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    //显示选择或者拍摄的照片
    private void showChooseOrTakePhoto(String picLocalPath) {
        int targetWidth = ((WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() - 10;
        int targetHeight = ((WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight() - 10;

        Picasso.with(this).load(new File(picLocalPath)).
                placeholder(R.drawable.default_pic_big)
                .error(R.drawable.default_pic_big)
                .transform(new BitmapTransformation(this, targetWidth, targetHeight))
                .into(ivShowTakePhoto);/*, new Callback() {


                    @Override
                    public void onSuccess() {
                        loadingiv.setVisibility(View.INVISIBLE);
                        picIv.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {
                        loadingiv.setVisibility(View.VISIBLE);
                        picIv.setVisibility(View.INVISIBLE);
                    }
                });*/
    }

    @Override
    public void uploadPhoto() {

        mPresenter.uploadOtherPic("",
                "123",
                "123",
                "123",
                "132",
                "123",
                "123",
                "132",
                "132",
                (String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),
                mTitle,
                new File(mTakePhotoLocalPath));
    }

    @Override
    public void showUploadPhotoSuccess() {
        ToastUtil.showShort(this, "图片上传成功");
        finish();
    }

    @Override
    public void showError(String tip) {
        ToastUtil.showShort(this, tip);
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
    public void setPresenter(UploadPhotoContract.Presenter presenter) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
