/**
 * Project Name:IDCardScanCaller
 * File Name:RecogActivity.java
 * Package Name:com.intsig.idcardscancaller
 * Date:2016年3月15日下午3:58:29
 * Copyright (c) 2016, 上海合合信息 All Rights Reserved.
 *
*/

package com.intsig.vlcardscansdkcaller;

import com.intsig.vlcardscansdk.ISCardScanActivity;
import com.intsig.vlcardscansdk.ResultData;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class RecogResultActivity extends Activity {
	private static final String TAG = "RecogResultActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_recog);
        Intent intent = getIntent();

        String image_path = intent.getStringExtra(ISCardScanActivity.EXTRA_KEY_RESULT_IMAGE);
        if (!TextUtils.isEmpty(image_path)) {
            Bitmap bmp = loadBitmap(image_path);
            if (bmp != null) {
                ImageView image_trim = (ImageView) findViewById(R.id.img_trim);
                image_trim.setImageBitmap(bmp);
            }
        }

        ResultData result = (ResultData) intent.getSerializableExtra(ISCardScanActivity.EXTRA_KEY_RESULT_DATA);
        //获取行驶证图片路径
        String imagePath = result.getOriImagePath();
        //获取行驶证图片角度，比如0度或180度
        int angle = result.getAngel();
        //获取行驶证图片号牌号码
        ((TextView) findViewById(R.id.tv_label_plate_no)).setText(result.getPlateNo());
        printPos("号牌号码", result.getPlateNoPos());
        //获取行驶证图片车辆类型
        ((TextView) findViewById(R.id.tv_label_type)).setText(result.getType());
        printPos("车辆类型", result.getTypePos());
        //获取行驶证图片所有人
        ((TextView) findViewById(R.id.tv_label_owner)).setText(result.getOwner());
        printPos("所有人", result.getOwnerPos());
        //获取行驶证图片地址
        ((TextView) findViewById(R.id.tv_label_address)).setText(result.getAddress());
        printPos("地址", result.getAddressPos());
        //获取行驶证图片使用性质
        ((TextView) findViewById(R.id.tv_label_use_character)).setText(result.getUseCharacter());
        printPos("使用性质", result.getUseCharacterPos());
        //获取行驶证图片品牌型号
        ((TextView) findViewById(R.id.tv_label_model)).setText(result.getModel());
        printPos("品牌型号", result.getModelPos());
        //获取行驶证图片车辆识别代号
        ((TextView) findViewById(R.id.tv_label_vin)).setText(result.getVin());
        printPos("车辆识别代号", result.getVinPos());
        //获取行驶证图片发动机号码
        ((TextView) findViewById(R.id.tv_label_engine_no)).setText(result.getEngineNo());
        printPos("发动机号码", result.getEngineNoPos());
        //获取行驶证图片注册日期
        ((TextView) findViewById(R.id.tv_label_register_date)).setText(result.getRegisterDate());
        printPos("注册日期", result.getRegisterDatePos());
        //获取行驶证图片发证日期
        ((TextView) findViewById(R.id.tv_label_issue_date)).setText(result.getIssueDate());
        printPos("发证日期", result.getIssueDatePos());
    }

    public void onClick(View view){
        finish();
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
    
    private void printPos(String type, int[] pos){
    	if (pos != null && pos.length == 8) {
    		Log.d(TAG, " type="+type+" pos x1="+pos[0]+" y1="+pos[1]+" x2="+pos[2]+" y2="+pos[3]+" x3="+pos[4]+" y3="+pos[5]+" x4="+pos[6]+" y4="+pos[7]);
		} else {
	    	Log.e(TAG, " type="+type+" pos error");
		}
    }
}

