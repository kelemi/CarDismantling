package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.PaiGongDanitem;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ListAdapterForPaiGongDanCuChai extends BaseAdapter {

    List<PaiGongDanitem> mPaiGongDanList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForPaiGongDanCuChai(List<PaiGongDanitem> mPaiGongDanList, Context mContext) {
        this.mPaiGongDanList = mPaiGongDanList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mPaiGongDanList.size();
    }

    @Override
    public Object getItem(int i) {
        return mPaiGongDanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_car_chaijie_paigongdan_cuchai, null);
            viewHolder = new ViewHolder();

            viewHolder.tvChaijieWorker = (TextView) convertView.findViewById(R.id.tv_chaijie_worker);
            viewHolder.tvChajieState = (TextView) convertView.findViewById(R.id.tv_chajie_state);
            viewHolder.tvTipChaijieType = (TextView)convertView. findViewById(R.id.tv_tip_chaijie_type);




            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        PaiGongDanitem paiGongDanitem = mPaiGongDanList.get(i);

        viewHolder.tvChajieState.setText(paiGongDanitem.getState());
        viewHolder.tvChaijieWorker.setText(paiGongDanitem.getDispatchperson());
        viewHolder.tvTipChaijieType.setText("拆解方式:粗拆");


        return convertView;

    }


    private static class ViewHolder{
        private TextView tvChajieState;
        private TextView tvChaijieWorker;
        private TextView tvTipChaijieType;



    }

}
