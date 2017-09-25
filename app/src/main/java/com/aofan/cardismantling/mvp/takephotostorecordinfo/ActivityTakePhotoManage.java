package com.aofan.cardismantling.mvp.takephotostorecordinfo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.takephotostorecordinfo.uploadphoto.ActivityShowTakePhoto;
import com.aofan.cardismantling.mvp.takephotostorecordinfo.uploadxingshizhenginfo.ActivityShowScanXingShiZhengResult;
import com.aofan.cardismantling.util.ToastUtil;
import com.baoyz.actionsheet.NewActionSheet;
import com.intsig.vlcardscansdk.ISCardScanActivity;
import com.intsig.vlcardscansdk.ResultData;
import com.jakewharton.rxbinding.view.RxView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import rx.functions.Action1;

/**拍照管理界面
 * Created by Administrator on 2016/11/22.
 */

public class ActivityTakePhotoManage extends BaseActivity implements View.OnClickListener{


    private ImageView ivTakeXingshizhengPhoto;
    //private ImageView ivTakeCarPhoto;
    private ImageView ivTakeChezhuIdcardFrontPhoto;
    private ImageView ivTakeChezhuIdcardBackPhoto;
    private ImageView ivTakeDailirenIdcardFrontPhoto;
    private ImageView ivTakeDailirenIdcardBackPhoto;
    private ImageView ivTakeCarSalerIdcardFrontPhoto;
    private ImageView ivTakeCarSalerIdcardBackPhoto;
    private ImageView ivTakeBusinessLicencePhoto;



    private List<PhotoInfo> mPhotoList = new ArrayList<>();
    private List<File> fileList = new ArrayList<>();

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

   /* //拍照类型  行驶证，车辆，车主身份证前后照，代理人身份证前后照，售车人身份证前后照
    private final static int TAKE_PHOTO_TYPE_XINGSHIZHENG = 1;
    private final static int TAKE_PHOTO_TYPE_CHELIANG = 2;
    private final static int TAKE_PHOTO_TYPE_CHEZHU_IDCARD_FRONT = 3;
    private final static int TAKE_PHOTO_TYPE_CHEZHU_IDCARD_BACK = 4;
    private final static int TAKE_PHOTO_TYPE_DAILI_IDCARD_FRONT = 5;
    private final static int TAKE_PHOTO_TYPE_DAILI_IDCARD_BACK = 6;
    private final static int TAKE_PHOTO_TYPE_CARSALER_IDCARD_FRONT = 7;
    private final static int TAKE_PHOTO_TYPE_CARSALER_IDCARD_BACK = 8;*/

    private static final int REQ_CODE_CAPTURE = 100;//

    int mNowChooseTakePhotoType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo_manage);
        //requestPermission();
        initTitleWithRightTvOrIv(null,null,"照片拍摄",true,null,null);
        initData();
        assignView();
        initView();
    }

    private void requestPermission() {
       /* MultiplePermissionsListener dialogMultiplePermissionsListener =
                DialogOnAnyDeniedMultiplePermissionsListener.Builder
                        .withContext(this)
                        .withTitle("相机权限")
                        .withMessage("我们需要以上权限保证app的正常运行,请到系统设置中进行设置")
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.drawable.ico_info)
                        .build();
        Dexter.checkPermissions(dialogMultiplePermissionsListener, Manifest.permission.CAMERA);*/
    }

    @Override
    public void initData() {

    }

    @Override
    public void assignView() {
        ivTakeXingshizhengPhoto = (ImageView) findViewById(R.id.iv_take_xingshizheng_photo);
//        ivTakeCarPhoto = (ImageView) findViewById(R.id.iv_take_car_photo);
        ivTakeChezhuIdcardFrontPhoto = (ImageView) findViewById(R.id.iv_take_chezhu_idcard_front_photo);
        ivTakeChezhuIdcardBackPhoto = (ImageView) findViewById(R.id.iv_take_chezhu_idcard_back_photo);
        ivTakeDailirenIdcardFrontPhoto = (ImageView) findViewById(R.id.iv_take_dailiren_idcard_front_photo);
        ivTakeDailirenIdcardBackPhoto = (ImageView) findViewById(R.id.iv_take_dailiren_idcard_back_photo);
        ivTakeCarSalerIdcardFrontPhoto = (ImageView) findViewById(R.id.iv_take_car_saler_idcard_front_photo);
        ivTakeCarSalerIdcardBackPhoto = (ImageView) findViewById(R.id.iv_take_car_saler_idcard_back_photo);
        ivTakeBusinessLicencePhoto = (ImageView) findViewById(R.id.iv_take_business_licence_photo);

    }

    @Override
    public void initView() {

        ivTakeXingshizhengPhoto.setOnClickListener(this);

        toChooseOrTakePhoto(ivTakeChezhuIdcardFrontPhoto,Config.TAKE_PHOTO_TYPE_CHEZHU_IDCARD_FRONT);
        toChooseOrTakePhoto(ivTakeChezhuIdcardBackPhoto,Config.TAKE_PHOTO_TYPE_CHEZHU_IDCARD_BACK);
        toChooseOrTakePhoto(ivTakeDailirenIdcardFrontPhoto,Config.TAKE_PHOTO_TYPE_DAILI_IDCARD_FRONT);
        toChooseOrTakePhoto(ivTakeDailirenIdcardBackPhoto,Config.TAKE_PHOTO_TYPE_DAILI_IDCARD_BACK);
        toChooseOrTakePhoto(ivTakeCarSalerIdcardFrontPhoto,Config.TAKE_PHOTO_TYPE_CARSALER_IDCARD_FRONT);
        toChooseOrTakePhoto(ivTakeCarSalerIdcardBackPhoto,Config.TAKE_PHOTO_TYPE_CARSALER_IDCARD_BACK);
        toChooseOrTakePhoto(ivTakeBusinessLicencePhoto,Config.TAKE_PHOTO_TYPE_BUSINESS_LICENCE);

    }


    private void toChooseOrTakePhoto(final View view, final int takePhotoType)
    {
        RxView.clicks(view)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        Dexter.checkPermissions(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted())
                                {
                                    showChoosePicSheet(takePhotoType);

                                }else {
                                    ToastUtil.showShort(ActivityTakePhotoManage.this,"我们需要相机和本地存储权限用来保证app的正常运行,请到系统设置中进行设置");
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }, Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE);


                    }
                });


    }


    //显示选择照片的actionSheet
    private void showChoosePicSheet(int takePhotoType) {

        mNowChooseTakePhotoType = takePhotoType;

        NewActionSheet.createBuilder(ActivityTakePhotoManage.this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("从相册选一张","拍一张照片")
                .setCancelableOnTouchOutside(true)
                .setListener(new NewActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(NewActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(NewActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, initGalleyFunctionConfig(),mOnHanlderResultCallback);

                                break;
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA,initGalleyFunctionConfig(), mOnHanlderResultCallback);

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

            if (requestCode == REQUEST_CODE_GALLERY)
            {
                if (resultList != null) {
                    mPhotoList.clear();
                    mPhotoList.addAll(resultList);

                    showChooseOrTakePhoto(mNowChooseTakePhotoType,mPhotoList.get(0).getPhotoPath());

                }
            }
            //如果是拍照的话，则直接添加
            if (requestCode == REQUEST_CODE_CAMERA)
            {
                if (resultList != null) {
                    mPhotoList.clear();
                    mPhotoList.addAll(resultList);
                    showChooseOrTakePhoto(mNowChooseTakePhotoType,mPhotoList.get(0).getPhotoPath());

                }
            }


        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(ActivityTakePhotoManage.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };


    private void showChooseOrTakePhoto(int takePhotoType,String photoLocalPath)
    {
        Intent toShowPhoto = new Intent(ActivityTakePhotoManage.this,ActivityShowTakePhoto.class);
        toShowPhoto.putExtra(IntentKey.INTENT_KEY_TAKE_PHOTO_KIND,takePhotoType);
        toShowPhoto.putExtra(IntentKey.INTENT_KEY_TAKE_PHOTO_LOCAL_PATH,photoLocalPath);
        startActivity(toShowPhoto);
        //toShowPhoto.putExtra(IntentKey.INTENT_KEY_SHOW_PHOTO_TITLE,photoLocalPath);
       /* ImageView view = null;
        switch (takePhotoType)
        {
            case Config.TAKE_PHOTO_TYPE_XINGSHIZHENG:
                view = ivTakeXingshizhengPhoto;
                break;
            case Config.TAKE_PHOTO_TYPE_CHELIANG:
                view = ivTakeCarPhoto;
                break;
            case Config.TAKE_PHOTO_TYPE_CHEZHU_IDCARD_FRONT:
                view = ivTakeChezhuIdcardFrontPhoto;
                break;
            case Config.TAKE_PHOTO_TYPE_CHEZHU_IDCARD_BACK:
                view = ivTakeChezhuIdcardBackPhoto;
                break;
            case Config.TAKE_PHOTO_TYPE_DAILI_IDCARD_FRONT:
                view = ivTakeDailirenIdcardFrontPhoto;
                break;
            case Config.TAKE_PHOTO_TYPE_DAILI_IDCARD_BACK:
                view = ivTakeDailirenIdcardBackPhoto;
                break;
            case Config.TAKE_PHOTO_TYPE_CARSALER_IDCARD_FRONT:
                view = ivTakeCarSalerIdcardFrontPhoto;
                break;
            case Config.TAKE_PHOTO_TYPE_CARSALER_IDCARD_BACK:
                view = ivTakeCarSalerIdcardBackPhoto;
                break;

        }

       *//* Picasso.with(ActivityTakePhotoManage.this)
                .load(photoFile)
                .transform(new BitmapTransformation2(ActivityTakePhotoManage.this,R.drawable.bg_take_photo))
                .error(R.drawable.bg_take_photo)
                .placeholder(R.drawable.bg_take_photo)
                .into(view);*/

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (resultCode == RESULT_OK && requestCode == REQ_CODE_CAPTURE) {
            //获取行驶证图片绝对路径
            String imagePath = data.getStringExtra(ISCardScanActivity.EXTRA_KEY_RESULT_IMAGE);
            //获取识别ResultData识别结果
            ResultData result = (ResultData) data.getSerializableExtra(ISCardScanActivity.EXTRA_KEY_RESULT_DATA);
            //在RecogResultActivity中显示识别结果
            Intent intent = new Intent(this, ActivityShowScanXingShiZhengResult.class);
            intent.putExtra(ISCardScanActivity.EXTRA_KEY_RESULT_IMAGE, imagePath);
            intent.putExtra(ISCardScanActivity.EXTRA_KEY_RESULT_DATA, result);
            startActivity(intent);
        }*/

        if(resultCode == RESULT_OK && requestCode == REQ_CODE_CAPTURE){
            //获取图片绝对路径
            String imagePath = data.getStringExtra(ISCardScanActivity.EXTRA_KEY_RESULT_IMAGE);
            //获取头像图片路径
            String avatarPath = data.getStringExtra(ISCardScanActivity.EXTRA_KEY_RESULT_AVATAR);
            //获取识别ResultData识别结果
            ResultData result = (ResultData) data.getSerializableExtra(ISCardScanActivity.EXTRA_KEY_RESULT_DATA);
            //在RecogResultActivity中显示识别结果
            Intent intent = new Intent(this, ActivityShowScanXingShiZhengResult.class);
            intent.putExtra(ISCardScanActivity.EXTRA_KEY_RESULT_IMAGE, imagePath);
            intent.putExtra(ISCardScanActivity.EXTRA_KEY_RESULT_DATA, result);
            intent.putExtra(ISCardScanActivity.EXTRA_KEY_RESULT_AVATAR, avatarPath);
            startActivity(intent);
        } else if (resultCode == RESULT_CANCELED && requestCode == REQ_CODE_CAPTURE) {
            //识别失败或取消
            //Log.d(TAG, "识别失败或取消");
            if (data != null) {
                /**
                 * 101 包名错误
                 * 102 appKey错误
                 * 103 超过时间限制
                 * 104 达到设备上限
                 * 201 签名错误
                 * 202 其他错误
                 * 203 服务器错误
                 * 204 网络错误
                 * 205 包名/签名错误
                 */
                int error_code = data.getIntExtra(ISCardScanActivity.EXTRA_KEY_RESULT_ERROR_CODE, 0);
                Toast.makeText(this, "扫描错误码："+ error_code, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_take_xingshizheng_photo:
                Dexter.checkPermissions(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted())
                        {
                           /* //通过Intent调用SDK中的相机拍摄模块ISCardScanActivity进行识别
                            Intent intent = new Intent(ActivityTakePhotoManage.this, ISCardScanActivity.class);
                            //指定要临时保存的图片路径
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_IMAGE_FOLDER, "/sdcard/vlcardscan/");
                            //指定SDK相机模块ISCardScanActivity四边框角线条,检测到图片后的颜色,可以不传递
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_COLOR_MATCH, 0xffff0000);
                            //指定SDK相机模块ISCardScanActivity四边框角线条，正常显示颜色,可以不传递
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_COLOR_NORMAL, 0xff00ff00);
                            //合合信息授权提供的APP_KEY
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_APP_KEY, "d8HbKXT7YP9PC3Nf0R3FTW7E");
                            //指定SDK相机模块ISCardScanActivity提示字符串
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_TIPS, "请将行驶证放在框内识别");
                            startActivityForResult(intent, REQ_CODE_CAPTURE);*/
                            //通过Intent调用SDK中的相机拍摄模块ISCardScanActivity进行识别
                            Intent intent = new Intent(ActivityTakePhotoManage.this, ISCardScanActivity.class);
                            //指定要临时保存的图片路径
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_IMAGE_FOLDER, "/sdcard/vlcardscan/");
                            //指定SDK相机模块ISCardScanActivity四边框角线条,检测到图片后的颜色
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_COLOR_MATCH, 0xffff0000);
                            //指定SDK相机模块ISCardScanActivity四边框角线条颜色，正常显示颜色
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_COLOR_NORMAL, 0xff00ff00);
                            //合合信息授权提供的APP_KEY
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_APP_KEY, "d8HbKXT7YP9PC3Nf0R3FTW7E");
                            //指定SDK相机模块ISCardScanActivity提示字符串
                            intent.putExtra(ISCardScanActivity.EXTRA_KEY_TIPS, "请将行驶证放在框内识别");
                            startActivityForResult(intent, REQ_CODE_CAPTURE);


                        }else {
                            ToastUtil.showShort(ActivityTakePhotoManage.this,"我们需要相机和本地存储权限用来保证app的正常运行，请到系统设置中进行设置");
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }, Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
        }
    }
}

