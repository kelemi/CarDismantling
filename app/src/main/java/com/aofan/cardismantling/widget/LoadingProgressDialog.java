package com.aofan.cardismantling.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.aofan.cardismantling.R;


/**
 * Created by Administrator on 2016/1/22.
 */
public class LoadingProgressDialog extends Dialog {
    public LoadingProgressDialog(Context context, String strMessage) {
        this(context, R.style.CustomProgressDialog, strMessage);
    }

    public LoadingProgressDialog(Context context, int theme, String strMessage) {
        super(context, theme);
        applyCompat();
        this.setContentView(R.layout.view_loading_dialog);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView tvMsg = (TextView) this.findViewById(R.id.loadingtv);
        if (tvMsg != null) {
            if (TextUtils.isEmpty(strMessage)==false)
            {
                tvMsg.setText(strMessage);
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

       /* if (!hasFocus) {
            dismiss();
        }*/
    }

    private void applyCompat() {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
