package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.TongJiDataOfCar;

import java.util.List;

/**汽车统计adapter
 * Created by Administrator on 2016/11/22.
 */

public class ListAdapterForVheicleTongJi extends BaseAdapter {

    List<TongJiDataOfCar> mVheicleTongJiData;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForVheicleTongJi(List<TongJiDataOfCar> mVheicleTongJiData, Context mContext) {
        this.mVheicleTongJiData = mVheicleTongJiData;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mVheicleTongJiData.size();
    }

    @Override
    public Object getItem(int i) {
        return mVheicleTongJiData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_vheicle_tongji, null);
            viewHolder = new ViewHolder();

            viewHolder.tvCarNum = (TextView) convertView.findViewById(R.id.tv_car_num);
            viewHolder.tvCarBrand = (TextView)convertView. findViewById(R.id.tv_car_brand);
            viewHolder.tvCarChaijieWay = (TextView) convertView.findViewById(R.id.tv_car_chaijie_way);
            viewHolder.tvCarChaijieTime = (TextView)convertView. findViewById(R.id.tv_car_chaijie_time);
            viewHolder.tvCarChaijieState = (TextView)convertView. findViewById(R.id.tv_car_chaijie_state);


            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        TongJiDataOfCar tongJiDataOfCar = mVheicleTongJiData.get(i);

        viewHolder.tvCarNum.setText(tongJiDataOfCar.getVehiclenumber());
        viewHolder.tvCarBrand .setText(tongJiDataOfCar.getBrandmodel());
        viewHolder.tvCarChaijieWay .setText("拆解方式:"+tongJiDataOfCar.getType());


        viewHolder.tvCarChaijieTime .setText("完成拆解时间"+ (TextUtils.isEmpty(tongJiDataOfCar.getCaijietime()) == false?tongJiDataOfCar.getCaijietime():"暂无"));
       /* if (TextUtils.isEmpty(tongJiDataOfCar.getCaijietime()) == false)
        {

            viewHolder.tvCarChaijieTime .setVisibility(View.VISIBLE);
        }else {
            viewHolder.tvCarChaijieTime .setVisibility(View.GONE);
        }*/

        viewHolder.tvCarChaijieState .setText("状态:"+tongJiDataOfCar.getAllocat());

        return convertView;

    }


    private static class ViewHolder{
        private TextView tvCarNum;
        private TextView tvCarBrand;
        private TextView tvCarChaijieWay;
        private TextView tvCarChaijieTime;
        private TextView tvCarChaijieState;




    }
}
