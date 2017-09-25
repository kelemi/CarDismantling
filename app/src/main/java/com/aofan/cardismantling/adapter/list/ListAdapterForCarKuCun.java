package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.CarKuCunListItem;
import com.aofan.cardismantling.bean.CarOfPaiGongDan;
import com.aofan.cardismantling.util.Res;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public class ListAdapterForCarKuCun extends BaseAdapter {

    List<CarKuCunListItem> mCarKuCunList;

    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForCarKuCun(List<CarKuCunListItem> mCarKuCunList, Context mContext) {
        this.mCarKuCunList = mCarKuCunList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mCarKuCunList.size();
    }

    @Override
    public Object getItem(int i) {
        return mCarKuCunList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_car_kucun, null);
            viewHolder = new ViewHolder();

            viewHolder.tvCarnum = (TextView) convertView.findViewById(R.id.tv_carnum);
            viewHolder.tvCarKind = (TextView) convertView.findViewById(R.id.tv_car_kind);
            viewHolder.tvCarBrand = (TextView) convertView.findViewById(R.id.tv_car_brand);



            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tvCarnum.setText(mCarKuCunList.get(i).getVehiclenumber());
        viewHolder.tvCarKind.setText(mCarKuCunList.get(i).getVehicletype());
        viewHolder.tvCarBrand.setText(mCarKuCunList.get(i).getBrandmodel());

        return convertView;

    }

    private static class ViewHolder{
        private TextView tvCarnum;
        private TextView tvCarKind;
        private TextView tvCarBrand;
    }
}