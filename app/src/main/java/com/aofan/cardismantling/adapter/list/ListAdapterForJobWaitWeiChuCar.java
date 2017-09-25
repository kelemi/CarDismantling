package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.JobWaitWeiChuItem;

import java.util.List;

/**
 * 待办任务（拆解任务的adapter）
 * Created by Administrator on 2016/11/23.
 */

public class ListAdapterForJobWaitWeiChuCar extends BaseAdapter {

    List<JobWaitWeiChuItem> mJobList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForJobWaitWeiChuCar(List<JobWaitWeiChuItem> mJobList, Context mContext) {
        this.mJobList = mJobList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mJobList.size();
    }

    @Override
    public Object getItem(int i) {
        return mJobList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_job_todo_weichu, null);
            viewHolder = new ViewHolder();

            viewHolder.tvCarBrand = (TextView) convertView.findViewById(R.id.tv_car_brand);
            viewHolder.tvType = (TextView) convertView.findViewById(R.id.tv_type);
            viewHolder.tvCarNumber = (TextView) convertView.findViewById(R.id.tv_car_number);

            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        JobWaitWeiChuItem jobWaitWeiChuItem = mJobList.get(i);

        //车牌号码
        viewHolder.tvCarBrand.setText(jobWaitWeiChuItem.getVehiclenumber());
        viewHolder.tvType.setText( jobWaitWeiChuItem.getType());
        //自定义入场编号
        viewHolder.tvCarNumber.setText(jobWaitWeiChuItem.getCustominfactorynumber());
        viewHolder.tvDate.setText(jobWaitWeiChuItem.getCreatetime());
        return convertView;

    }


    private static class ViewHolder {
        private TextView tvCarBrand;
        private TextView tvType;
        private TextView tvCarNumber;
        private TextView tvDate;
    }

}

