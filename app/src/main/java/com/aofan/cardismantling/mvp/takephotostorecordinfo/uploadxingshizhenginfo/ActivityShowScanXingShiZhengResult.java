/**
 * 显示扫描行驶证结果界面
 */

package com.aofan.cardismantling.mvp.takephotostorecordinfo.uploadxingshizhenginfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.intsig.vlcardscansdk.ISCardScanActivity;
import com.intsig.vlcardscansdk.ResultData;

import java.io.File;

/**
 * 显示扫描行驶证结果view
 */
public class ActivityShowScanXingShiZhengResult extends BaseActivity implements UploadXingShiZhengInfoContract.View, View.OnClickListener {
    private static final String TAG = "RecogResultActivity";

    ResultData mScanResult;
    String mImagePath;


    private Button btnUploaInfo;


    UploadXingShiZhengInfoContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_scan_xingshizheng_result);
        initTitleWithRightTvOrIv(null, null, "行驶证信息", true, null, null);
        initData();
        assignView();
        initView();
        mPresenter = new UploadXingShiZhengInfoPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Override
    public void initData() {
        Intent intent = getIntent();

        //mImagePath = intent.getStringExtra(ISCardScanActivity.EXTRA_KEY_RESULT_AVATAR);
        mScanResult = (ResultData) intent.getSerializableExtra(ISCardScanActivity.EXTRA_KEY_RESULT_DATA);
    }

    @Override
    public void assignView() {

        btnUploaInfo = (Button) findViewById(R.id.btn_uploa_info);


        //获取行驶证图片路径
        String imagePath = mScanResult.getTrimImagePath();
        //获取行驶证图片角度，比如0度或180度
        int angle = mScanResult.getAngel();
        //获取行驶证图片号牌号码
        ((TextView) findViewById(R.id.tv_label_plate_no)).setText(mScanResult.getPlateNo());
        printPos("号牌号码", mScanResult.getPlateNoPos());
        //获取行驶证图片车辆类型
        ((TextView) findViewById(R.id.tv_label_type)).setText(mScanResult.getType());
        printPos("车辆类型", mScanResult.getTypePos());
        //获取行驶证图片所有人
        ((TextView) findViewById(R.id.tv_label_owner)).setText(mScanResult.getOwner());
        printPos("所有人", mScanResult.getOwnerPos());
        //获取行驶证图片地址
        ((TextView) findViewById(R.id.tv_label_address)).setText(mScanResult.getAddress());
        printPos("地址", mScanResult.getAddressPos());
        //获取行驶证图片使用性质
        ((TextView) findViewById(R.id.tv_label_use_character)).setText(mScanResult.getUseCharacter());
        printPos("使用性质", mScanResult.getUseCharacterPos());
        //获取行驶证图片品牌型号
        ((TextView) findViewById(R.id.tv_label_model)).setText(mScanResult.getModel());
        printPos("品牌型号", mScanResult.getModelPos());
        //获取行驶证图片车辆识别代号
        ((TextView) findViewById(R.id.tv_label_vin)).setText(mScanResult.getVin());
        printPos("车辆识别代号", mScanResult.getVinPos());
        //获取行驶证图片发动机号码
        ((TextView) findViewById(R.id.tv_label_engine_no)).setText(mScanResult.getEngineNo());
        printPos("发动机号码", mScanResult.getEngineNoPos());
        //获取行驶证图片注册日期
        ((TextView) findViewById(R.id.tv_label_register_date)).setText(mScanResult.getRegisterDate());
        printPos("注册日期", mScanResult.getRegisterDatePos());
        //获取行驶证图片发证日期
        ((TextView) findViewById(R.id.tv_label_issue_date)).setText(mScanResult.getIssueDate());
        printPos("发证日期", mScanResult.getIssueDatePos());

        if (!TextUtils.isEmpty(imagePath)) {
            Bitmap bmp = loadBitmap(imagePath);
            if (bmp != null) {
                ImageView image_trim = (ImageView) findViewById(R.id.img_trim);
                image_trim.setImageBitmap(bmp);
            }
        }
    }

    @Override
    public void initView() {
        btnUploaInfo.setOnClickListener(this);
    }

    public void onClick(View view) {
        /*finish();*/
        switch (view.getId()) {
            case R.id.btn_uploa_info:

               /* ((TextView) findViewById(R.id.tv_label_plate_no)).setText(mScanResult.getPlateNo());
                printPos("号牌号码", mScanResult.getPlateNoPos());
                //获取行驶证图片车辆类型
                ((TextView) findViewById(R.id.tv_label_type)).setText(mScanResult.getType());
                printPos("车辆类型", mScanResult.getTypePos());
                //获取行驶证图片所有人
                ((TextView) findViewById(R.id.tv_label_owner)).setText(mScanResult.getOwner());
                printPos("所有人", mScanResult.getOwnerPos());
                //获取行驶证图片地址
                ((TextView) findViewById(R.id.tv_label_address)).setText(mScanResult.getAddress());
                printPos("地址", mScanResult.getAddressPos());
                //获取行驶证图片使用性质
                ((TextView) findViewById(R.id.tv_label_use_character)).setText(mScanResult.getUseCharacter());
                printPos("使用性质", mScanResult.getUseCharacterPos());
                //获取行驶证图片品牌型号
                ((TextView) findViewById(R.id.tv_label_model)).setText(mScanResult.getModel());
                printPos("品牌型号", mScanResult.getModelPos());
                //获取行驶证图片车辆识别代号
                ((TextView) findViewById(R.id.tv_label_vin)).setText(mScanResult.getVin());
                printPos("车辆识别代号", mScanResult.getVinPos());
                //获取行驶证图片发动机号码
                ((TextView) findViewById(R.id.tv_label_engine_no)).setText(mScanResult.getEngineNo());
                printPos("发动机号码", mScanResult.getEngineNoPos());
                //获取行驶证图片注册日期
                ((TextView) findViewById(R.id.tv_label_register_date)).setText(mScanResult.getRegisterDate());
                printPos("注册日期", mScanResult.getRegisterDatePos());
                //获取行驶证图片发证日期
                ((TextView) findViewById(R.id.tv_label_issue_date)).setText(mScanResult.getIssueDate());
                printPos("发证日期", mScanResult.getIssueDatePos());*/


                uploadXingShiZhengInfo();
                break;
        }
    }

    public static Bitmap loadBitmap(String pathName) {
        Bitmap b = null;
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inPreferredConfig = Bitmap.Config.RGB_565;
            b = BitmapFactory.decodeFile(pathName, opts);
        } catch (Exception e) {
            e.printStackTrace();
            b = null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            b = null;
        }
        return b;
    }

    private void printPos(String type, int[] pos) {
        if (pos != null && pos.length == 8) {
            Log.d(TAG, " type=" + type + " pos x1=" + pos[0] + " y1=" + pos[1] + " x2=" + pos[2] + " y2=" + pos[3] + " x3=" + pos[4] + " y3=" + pos[5] + " x4=" + pos[6] + " y4=" + pos[7]);
        } else {
            Log.e(TAG, " type=" + type + " pos error");
        }
    }

    @Override
    public void uploadXingShiZhengInfo() {

        /*//获取行驶证图片号牌号码
        ((TextView) findViewById(R.id.tv_label_plate_no)).setText(mScanResult.getPlateNo());
        printPos("号牌号码", mScanResult.getPlateNoPos());
        //获取行驶证图片车辆类型
        ((TextView) findViewById(R.id.tv_label_type)).setText(mScanResult.getType());
        printPos("车辆类型", mScanResult.getTypePos());
        //获取行驶证图片所有人
        ((TextView) findViewById(R.id.tv_label_owner)).setText(mScanResult.getOwner());
        printPos("所有人", mScanResult.getOwnerPos());
        //获取行驶证图片地址
        ((TextView) findViewById(R.id.tv_label_address)).setText(mScanResult.getAddress());
        printPos("地址", mScanResult.getAddressPos());
        //获取行驶证图片使用性质
        ((TextView) findViewById(R.id.tv_label_use_character)).setText(mScanResult.getUseCharacter());
        printPos("使用性质", mScanResult.getUseCharacterPos());
        //获取行驶证图片品牌型号
        ((TextView) findViewById(R.id.tv_label_model)).setText(mScanResult.getModel());
        printPos("品牌型号", mScanResult.getModelPos());
        //获取行驶证图片车辆识别代号
        ((TextView) findViewById(R.id.tv_label_vin)).setText(mScanResult.getVin());
        printPos("车辆识别代号", mScanResult.getVinPos());
        //获取行驶证图片发动机号码
        ((TextView) findViewById(R.id.tv_label_engine_no)).setText(mScanResult.getEngineNo());
        printPos("发动机号码", mScanResult.getEngineNoPos());
        //获取行驶证图片注册日期
        ((TextView) findViewById(R.id.tv_label_register_date)).setText(mScanResult.getRegisterDate());
        printPos("注册日期", mScanResult.getRegisterDatePos());
        //获取行驶证图片发证日期
        ((TextView) findViewById(R.id.tv_label_issue_date)).setText(mScanResult.getIssueDate());
        printPos("发证日期", mScanResult.getIssueDatePos());*/
        LogUtil.e("车辆号码:", mScanResult.getPlateNo());
        mPresenter.uploadXingShiZhengInfo(TextUtils.isEmpty(mScanResult.getPlateNo()) == false? mScanResult.getPlateNo():"",
                TextUtils.isEmpty(mScanResult.getType()) == false? mScanResult.getType():"" ,
                TextUtils.isEmpty(mScanResult.getOwner()) == false? mScanResult.getOwner():"",
                TextUtils.isEmpty(mScanResult.getAddress()) == false? mScanResult.getAddress():"" ,
                TextUtils.isEmpty(mScanResult.getUseCharacter()) == false? mScanResult.getUseCharacter():"",
                TextUtils.isEmpty(mScanResult.getModel()) == false? mScanResult.getModel():"",
                TextUtils.isEmpty(mScanResult.getVin()) == false? mScanResult.getVin():"",
                TextUtils.isEmpty(mScanResult.getEngineNo()) == false?mScanResult.getEngineNo():"",
                TextUtils.isEmpty(mScanResult.getRegisterDate()) == false?mScanResult.getRegisterDate():"",
                TextUtils.isEmpty(mScanResult.getIssueDate()) == false?mScanResult.getIssueDate():"",
                (String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, ""),
                "行驶证",
                new File(mScanResult.getOriImagePath()));

    }

    @Override
    public void showUploadXingShiZhengSuccess() {
        ToastUtil.showShort(this, "行驶证信息上传成功");
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
    public void setPresenter(UploadXingShiZhengInfoContract.Presenter presenter) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

