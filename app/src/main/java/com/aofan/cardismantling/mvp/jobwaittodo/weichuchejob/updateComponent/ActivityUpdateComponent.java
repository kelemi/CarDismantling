package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.updateComponent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.grid.GridAdapterForWeiChuCar;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.bean.WeiChuComponent;
import com.aofan.cardismantling.mvp.picshow.ActivityPicShow;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.aofan.cardismantling.widget.GridViewForScrollView;
import com.baoyz.actionsheet.NewActionSheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class ActivityUpdateComponent extends BaseActivity implements GridAdapterForWeiChuCar.GridPicListener, UpdateComponentContract.View {

    private TextView tvComponentName;
    private GridViewForScrollView gvComponents;
    private TextView tvTakePhoto;
    private TextView tvUpphoto;

    private String userId;
    private String tstype;
    private String baseId;
    private String did;
    private int itemCount;//零件数量

    private WeiChuComponent weiChuComponent;
    private final int REQUEST_CODE_GALLERY = 1000;
    private final int REQUEST_CODE_CAMERA = 1001;

    private GridAdapterForWeiChuCar gridAdapterForWeiChuCar;

    private UpdateComponentContract.Presenter presenter;
    //当前的照片
    List<PhotoInfo> mNowCarPhotoInfo = new ArrayList<>();
    List<String> picPhotoList = new ArrayList<>();
    private TextView tvAddLingjianNum;
    private TextView tvLingjianNum;
    private TextView tvSubLingjianNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_component);
        initTitleWithRightTvOrIv(null, null, "上传零件图片", true, null, null);
        initData();
        initView();
        assignView();
    }

    @Override
    public void initData() {
        weiChuComponent = (WeiChuComponent) getIntent().getSerializableExtra("weiChuComponent");
        tstype = getIntent().getStringExtra("tstype");
        baseId = getIntent().getStringExtra("baseId");
        did = getIntent().getStringExtra("did");

        itemCount = 1;
        userId = (String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, "");

        presenter = new UpdateComponentPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Override
    public void assignView() {
        tvComponentName.setText(weiChuComponent.getPartname() + "");

        gridAdapterForWeiChuCar = new GridAdapterForWeiChuCar(ActivityUpdateComponent.this, picPhotoList, ActivityUpdateComponent.this);
        gvComponents.setAdapter(gridAdapterForWeiChuCar);
        //拍照,照片
        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicSheet();
            }
        });
        //上传照片
        tvUpphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picPhotoList.size() == 0) {
                    Toast.makeText(ActivityUpdateComponent.this, "请选择照片后上传", Toast.LENGTH_SHORT).show();
                    return;
                }
                String itemCountStr = tvLingjianNum.getText().toString();
                presenter.saveUnSignCaijie(tstype, userId, weiChuComponent.getPartid(), weiChuComponent.getPartname(), itemCountStr, did, picPhotoList);
            }
        });

    }

    @Override
    public void initView() {
        tvComponentName = (TextView) findViewById(R.id.tv_component_name);
        gvComponents = (GridViewForScrollView) findViewById(R.id.gv_components);
        tvTakePhoto = (TextView) findViewById(R.id.tv_take_photo);
        tvUpphoto = (TextView) findViewById(R.id.tv_upphoto);
        tvAddLingjianNum = (TextView) findViewById(R.id.tv_add_lingjian_num);
        tvLingjianNum = (TextView) findViewById(R.id.tv_lingjian_num);
        tvSubLingjianNum = (TextView) findViewById(R.id.tv_sub_lingjian_num);

        //添加数量
        tvAddLingjianNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCount++;
                tvLingjianNum.setText(itemCount+"");
            }
        });
        //减少数量
        tvSubLingjianNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCount == 1) {
                    itemCount = 1;
                } else {
                    itemCount--;
                }
                tvLingjianNum.setText(itemCount+"");
            }
        });
    }


    //显示选择照片的actionSheet
    private void showChoosePicSheet() {


        //mNowCarPhotoInfo = mHcbPhotoList.get(nowChooseCarPosition);

        NewActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("打开相册", "拍照")
                .setCancelableOnTouchOutside(true)
                .setListener(new NewActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(NewActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(NewActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, initGalleyFunctionConfig(4, mNowCarPhotoInfo)
                                        , mOnHanlderResultCallback);
                                break;
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, initGalleyFunctionConfig(1, mNowCarPhotoInfo),
                                        mOnHanlderResultCallback);
                                break;
                        }
                    }
                }).show();
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int requestCode, List<PhotoInfo> resultList) {

            if (requestCode == REQUEST_CODE_GALLERY) {
                if (resultList != null) {
                    mNowCarPhotoInfo.clear();
                    mNowCarPhotoInfo.addAll(resultList);
                    picPhotoList.clear();
                    for (int i = 0; i < mNowCarPhotoInfo.size(); i++) {
                        picPhotoList.add(mNowCarPhotoInfo.get(i).getPhotoPath());
                    }
                   /* gridAdapterForWeiChuCar = new GridAdapterForWeiChuCar(ActivityUpdateComponent.this, picPhotoList, ActivityUpdateComponent.this);
                    gvComponents.setAdapter(gridAdapterForWeiChuCar);*/
                    gridAdapterForWeiChuCar.notifyDataSetChanged();
                }
            }

            //如果是拍照的话，则直接添加
            if (requestCode == REQUEST_CODE_CAMERA) {
                if (resultList != null) {
                    //拍照后就直接添加，不需要清空，因为能拍照说明，还能选择图片
                    //mNowCarPhotoInfo.clear();
                    mNowCarPhotoInfo.addAll(resultList);
                    // List<String> localpaths = new ArrayList<>();
                    picPhotoList.clear();
                    for (int i = 0; i < mNowCarPhotoInfo.size(); i++) {
                        picPhotoList.add(mNowCarPhotoInfo.get(i).getPhotoPath());
                    }
                    /*gridAdapterForWeiChuCar = new GridAdapterForWeiChuCar(ActivityUpdateComponent.this, picPhotoList, ActivityUpdateComponent.this);
                    gvComponents.setAdapter(gridAdapterForWeiChuCar);*/
                    gridAdapterForWeiChuCar.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(ActivityUpdateComponent.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    //初始化图片选择functionconfig

    private FunctionConfig initGalleyFunctionConfig(int leftCanChoosePicNum, List<PhotoInfo> hasChoosedPhotoList) {
        //配置功能
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        functionConfigBuilder.setMutiSelectMaxSize(leftCanChoosePicNum);
        functionConfigBuilder.setEnableCamera(true);
        functionConfigBuilder.setEnablePreview(true);
        functionConfigBuilder.setSelected(hasChoosedPhotoList);//添加过滤集合
        return functionConfigBuilder.build();

    }



    //查看放大详情
    @Override
    public void onGridPicItemClick(int position, List<String> picpathList) {
        List<String> picUrls = new ArrayList<>();

        picUrls.addAll(picpathList);

        Intent toShowPics = new Intent(ActivityUpdateComponent.this, ActivityPicShow.class);

        toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION, position);

        toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_URLS, (Serializable) picUrls);

        startActivity(toShowPics);
    }

    //删除图片
    @Override
    public void onGridPicDelete(int position) {

        //删除本地已经选择图片
        picPhotoList.remove(position);

        mNowCarPhotoInfo.remove(position);

        gridAdapterForWeiChuCar.notifyDataSetChanged();
    }

    @Override
    public void saveUnSignCaijieFail(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveUnSignCaijieSuccess(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void netError(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        showProgressDialog(true, null);
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }

    @Override
    public void setPresenter(UpdateComponentContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
