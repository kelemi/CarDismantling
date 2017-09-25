package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.CarInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ListAdapterForCarWaitToAnalysis extends BaseAdapter {

    List<CarInfo> mCarInfoList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForCarWaitToAnalysis(List<CarInfo> mCarInfoList, Context mContext) {
        this.mCarInfoList = mCarInfoList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mCarInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return mCarInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_car_wait_to_analysis, null);
            viewHolder = new ViewHolder();

            viewHolder.tvCarNum = (TextView) convertView.findViewById(R.id.tv_car_num);
            viewHolder.tvCarRegisterTime = (TextView)convertView. findViewById(R.id.tv_car_register_time);
            viewHolder.tvCarKind = (TextView) convertView.findViewById(R.id.tv_car_kind);
            viewHolder.tvCarBrand = (TextView) convertView.findViewById(R.id.tv_car_brand);
            viewHolder.ivCarLogo = (ImageView) convertView.findViewById(R.id.iv_car_logo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CarInfo carInfo = mCarInfoList.get(i);


        viewHolder.tvCarNum.setText(carInfo.getVehiclenumber());
        viewHolder.tvCarRegisterTime.setText("注册时间:"+carInfo.getRegisterdate());
        viewHolder.tvCarKind.setText(carInfo.getVehicletype());
        viewHolder.tvCarBrand.setText(carInfo.getBrandmodel());


        Picasso.with(mContext)
                .load(carInfo.getCaricon())
                .resize(100,100)
                .tag(mContext)
                .placeholder(R.drawable.ico_car_default_mid)
                .error(R.drawable.ico_car_default_mid)
                .into(viewHolder.ivCarLogo);

        return convertView;

    }

    /*@Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i== AbsListView.OnScrollListener.SCROLL_STATE_FLING)
        {
            Picasso.with(mContext).pauseTag(mContext);
        }else if (i== AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
        {
            Picasso.with(mContext).resumeTag(mContext);
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }*/


    private static class ViewHolder{
        private TextView tvCarNum;
        private TextView tvCarRegisterTime;
        private TextView tvCarKind;
        private TextView tvCarBrand;
        private ImageView ivCarLogo;


    }
}
