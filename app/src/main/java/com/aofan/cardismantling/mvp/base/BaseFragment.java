package com.aofan.cardismantling.mvp.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.util.Res;
import com.aofan.cardismantling.widget.LoadingProgressDialog;

/**
 * Created by Administrator on 2016/11/21.
 */

public abstract class BaseFragment extends Fragment {


    public abstract void initData();
    public abstract void assignView(View view);
    public abstract void initView();
    public abstract void getData();


    private RelativeLayout titlebar;
    private ImageView ivLeftBack;
    private TextView tvTitle;
    private TextView tvRight;
    private ImageView ivRight;


    public void initTitleWithRightTvOrIv(View view,Integer backgroundResId, Integer titleColorResId, String title, boolean isLeftBack, String rightTvStr,Integer rightIvResId)
    {
        titlebar = (RelativeLayout) view.findViewById(R.id.titlebar);
        ivLeftBack = (ImageView)  view.findViewById(R.id.iv_left_back);
        tvTitle = (TextView)  view.findViewById(R.id.tv_title);
        tvRight = (TextView)  view.findViewById(R.id.tv_right);
        ivRight = (ImageView)  view.findViewById(R.id.iv_right);


        if (null != backgroundResId) {
            titlebar.setBackgroundResource(backgroundResId);
        }

        if (null != titleColorResId) {
            tvTitle.setTextColor(Res.getColor(titleColorResId));
        }

        tvTitle.setText(title);

        if (isLeftBack == true) {
            ivLeftBack.setVisibility(View.VISIBLE);
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


    LoadingProgressDialog progressDialog;

    public void showProgressDialog(boolean canBack, String loadingMsg) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }

        progressDialog = new LoadingProgressDialog(getActivity(), loadingMsg);
        if (canBack)
        {
            progressDialog.setCancelable(true);
        }else{
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void dismissProgressDialog()
    {
        if (null!=progressDialog && progressDialog.isShowing())
        {
            progressDialog.cancel();

        }
    }

}
