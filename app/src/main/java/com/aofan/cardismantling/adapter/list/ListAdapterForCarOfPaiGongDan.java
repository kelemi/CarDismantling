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
import com.aofan.cardismantling.bean.CarOfPaiGongDan;
import com.squareup.picasso.Picasso;

import java.util.List;

/**派工单中车辆列表
 * Created by Administrator on 2016/11/21.
 */

public class ListAdapterForCarOfPaiGongDan extends BaseAdapter {

    List<CarOfPaiGongDan> mCarInfoList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForCarOfPaiGongDan(List<CarOfPaiGongDan> mCarInfoList, Context mContext) {
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
            convertView = mLayoutInflater.inflate(R.layout.list_item_car_of_paigongdan, null);
            viewHolder = new ViewHolder();

            viewHolder.tvCarNum = (TextView) convertView.findViewById(R.id.tv_car_num);
            viewHolder.tvCarChaijieWay = (TextView)convertView. findViewById(R.id.tv_car_chaijie_way);
            viewHolder.tvCarBrand = (TextView) convertView.findViewById(R.id.tv_car_brand);
            viewHolder.ivCarBrandPic = (ImageView) convertView.findViewById(R.id.iv_car_brand_pic);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        CarOfPaiGongDan carOfPaiGongDan = mCarInfoList.get(i);

        viewHolder.tvCarNum.setText(carOfPaiGongDan.getVehiclenumber());
        viewHolder.tvCarChaijieWay.setText(carOfPaiGongDan.getType());
        viewHolder.tvCarBrand.setText(carOfPaiGongDan.getBrandmodel());
        if (TextUtils.isEmpty(carOfPaiGongDan.getCaricon()) == false)
        {
            Picasso.with(mContext)
                    .load(carOfPaiGongDan.getCaricon())
                    .resize(100,100)
                    .tag(mContext)
                    .placeholder(R.drawable.ico_car_default_mid)
                    .error(R.drawable.ico_car_default_mid)
                    .into(viewHolder.ivCarBrandPic);
        }



        return convertView;

    }


    private static class ViewHolder{
        private ImageView ivCarBrandPic;
        private TextView tvCarNum;

        private TextView tvCarBrand;
        private TextView tvCarChaijieWay;






    }
}
