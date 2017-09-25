package com.intsig.vlcardscansdkcaller;

import com.intsig.vlcardscansdk.ISCardScanActivity;
import com.intsig.vlcardscansdk.ResultData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = "MainActivity";//
    private static final String APP_KEY ="a3872652010570f0913a274e91-Vagfvt";//替换您申请的合合信息授权提供的APP_KEY;
    private static final int REQ_CODE_CAPTURE = 100;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViewById(R.id.btn_use_camare_module).setOnClickListener(this);
        findViewById(R.id.btn_not_use_camare_module).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int id = view.getId();

        if (id == R.id.btn_use_camare_module) {
        	//通过Intent调用SDK中的相机拍摄模块ISCardScanActivity进行识别
            Intent intent = new Intent(this, ISCardScanActivity.class);
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
            startActivityForResult(intent, REQ_CODE_CAPTURE);
        } else {
        	//在PreviewActivity中自定义相机拍摄模块，然后直接调用SDK中的预览方法进行识别
        	//在PreviewActivity中可以直接获得SDK返回的ResultData识别结果
        	Intent intent = new Intent(this, PreviewActivity.class);
        	//合合信息授权提供的APP_KEY
            intent.putExtra(PreviewActivity.EXTRA_KEY_APP_KEY, APP_KEY);
            startActivityForResult(intent, REQ_CODE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQ_CODE_CAPTURE){
            //获取行驶证图片绝对路径
            String imagePath = data.getStringExtra(ISCardScanActivity.EXTRA_KEY_RESULT_IMAGE);
            //获取识别ResultData识别结果
            ResultData result = (ResultData) data.getSerializableExtra(ISCardScanActivity.EXTRA_KEY_RESULT_DATA);
            //在RecogResultActivity中显示识别结果
            Intent intent = new Intent(this, RecogResultActivity.class);
            intent.putExtra(ISCardScanActivity.EXTRA_KEY_RESULT_IMAGE, imagePath);
            intent.putExtra(ISCardScanActivity.EXTRA_KEY_RESULT_DATA, result);
            startActivity(intent);
        } else if (resultCode == RESULT_CANCELED && requestCode == REQ_CODE_CAPTURE) {
          //识别失败或取消
            Log.d(TAG, "识别失败或取消，请参考返回错误码说明");
            if (data != null) {
                /**
                 * 101 包名错误, 授权APP_KEY与绑定的APP包名不匹配；
                 * 102 appKey错误，传递的APP_KEY填写错误；
                 * 103 超过时间限制，授权的APP_KEY超出使用时间限制；
                 * 104 达到设备上限，授权的APP_KEY使用设备数量达到限制；
                 * 201 签名错误，授权的APP_KEY与绑定的APP签名不匹配；
                 * 202 其他错误，其他未知错误，比如初始化有问题；
                 * 203 服务器错误，第一次联网验证时，因服务器问题，没有验证通过；
                 * 204 网络错误，第一次联网验证时，没有网络连接，导致没有验证通过；
                 * 205 包名/签名错误，授权的APP_KEY与绑定的APP包名和签名都不匹配；
                 */
                int error_code = data.getIntExtra(ISCardScanActivity.EXTRA_KEY_RESULT_ERROR_CODE, 0);
                Toast.makeText(this, "Error >>> " + error_code, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
