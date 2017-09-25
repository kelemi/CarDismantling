package com.aofan.cardismantling.mvp.base;

import android.support.v4.app.DialogFragment;
import android.view.View;

/**
 * Created by Administrator on 2016/11/21.
 */

public abstract class BaseDialogFragment extends DialogFragment {


    public abstract void initData();

    public abstract void assignView(View view);

    public abstract void initView();

    public abstract void getData();
}
