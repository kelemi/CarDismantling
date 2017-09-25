package com.aofan.cardismantling.mvp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.AppManager;
import com.aofan.cardismantling.util.Res;
import com.aofan.cardismantling.widget.LoadingProgressDialog;

/**
 * Created by Administrator on 2016/10/25.
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //CustomApplication.getInstance().addActivity(this);
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);

    }

    public abstract void initData();

    public abstract void assignView();

    public abstract void initView();


    public RelativeLayout titlebar;
    public ImageView ivLeftBack;
    public TextView tvTitle;
    public TextView tvRight;
    public ImageView ivRight;


    public void initTitleWithRightTvOrIv(Integer backgroundResId, Integer titleColorResId, String title, boolean isLeftBack, String rightTvStr, Integer rightIvResId) {
        titlebar = (RelativeLayout) findViewById(R.id.titlebar);
        ivLeftBack = (ImageView) findViewById(R.id.iv_left_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivRight = (ImageView) findViewById(R.id.iv_right);

        if (null != backgroundResId) {
            titlebar.setBackgroundResource(backgroundResId);
        }

        if (null != titleColorResId) {
            tvTitle.setTextColor(Res.getColor(titleColorResId));
        }

        tvTitle.setText(title);

        if (isLeftBack == true) {
            ivLeftBack.setVisibility(View.VISIBLE);
            ivLeftBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        if (null != rightTvStr) {
            tvRight.setText(rightTvStr);
            tvRight.setVisibility(View.VISIBLE);
        }


        if (null != rightIvResId) {
            ivRight.setImageResource(rightIvResId);
            ivRight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

    LoadingProgressDialog progressDialog;

    public void showProgressDialog(boolean canBack, String loadingMsg) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }

        progressDialog = new LoadingProgressDialog(this, loadingMsg);
        if (canBack) {
            progressDialog.setCancelable(true);
        } else {
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.cancel();

        }
    }
}
