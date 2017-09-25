package com.aofan.cardismantling.mvp.base.common.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;


/**
 * Created by Administrator on 2016/3/17.
 */
public class DialogFragmentTip extends DialogFragment {



    public static final String DEAL_TYPE = "detlType";


    private String mDealType;


    TipDialogPositionBtnClickListener positionBtnClickListener;


    public TipDialogPositionBtnClickListener getPositionBtnClickListener() {
        return positionBtnClickListener;
    }

    public void setPositionBtnClickListener(TipDialogPositionBtnClickListener positionBtnClickListener) {
        this.positionBtnClickListener = positionBtnClickListener;
    }

    public static DialogFragmentTip getInstance(String argument)
    {
        DialogFragmentTip dialogFragmentTip = new DialogFragmentTip();
        Bundle bundle = new Bundle();
        bundle.putString(DEAL_TYPE,argument);

        dialogFragmentTip.setArguments(bundle);
        return dialogFragmentTip;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (null!=bundle)
        {
            mDealType = bundle.getString(DEAL_TYPE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Set a theme on the dialog builder constructor!
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity(),R.style.TipDialogTheme );
        String message = null;
        if (mDealType.equals(Config.ACTION_FINISH_PAIGONG_WITH_LEFT_LINGJIAN))
        {
            message = TipStr.TIP_ENSURE_NOT_ALL_LINGJIAN_HAS_PAIGONG;
        }else if (mDealType.equals(Config.ACTION_EXIST_APP))
    {
        message = TipStr.TIP_ENSURE_EXIST_APP;
    }else if (mDealType.equals(Config.ACTION_FINISH_CHAIJIE_WITH_LEFT_PAIGONGDAN))
        {
            message = TipStr.TIP_ENSURE_NOT_ALL_PAIGONGDAN_HAS_CHAIJIE;
        }


        builder
                .setTitle(Constant.TIP_STR)
                .setMessage(message)
                .setPositiveButton( Constant.ENSURE_STR , new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (null != positionBtnClickListener)
                        {
                            positionBtnClickListener.onPositionBtnClick(mDealType);
                        }
                    }
                }).setNegativeButton(Constant.CANCEL_STR, null);
        return builder.create();
    }

    public interface TipDialogPositionBtnClickListener{
        public void onPositionBtnClick(String dealType);
    }
}
