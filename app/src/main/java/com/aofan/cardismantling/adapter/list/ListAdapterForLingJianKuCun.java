package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.LingJianKuCunListItem;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class ListAdapterForLingJianKuCun extends BaseAdapter {
    List<LingJianKuCunListItem> mLingJianKuCunInfoList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForLingJianKuCun(List<LingJianKuCunListItem> mLingJianKuCunInfoList, Context mContext) {
        this. mLingJianKuCunInfoList = mLingJianKuCunInfoList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mLingJianKuCunInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return mLingJianKuCunInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_lingjian_kucun, null);
            viewHolder = new ViewHolder();

            viewHolder.tvLingjianNum = (TextView) convertView.findViewById(R.id.tv_lingjian_num);
            viewHolder.tvLingjianName = (TextView) convertView.findViewById(R.id.tv_lingjian_name);


            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        /*viewHolder.tvLingjianNum.setText("数量:"+mLingJianKuCunInfoList.get(i).getCount());*/
        viewHolder.tvLingjianName.setText(mLingJianKuCunInfoList.get(i).getPartname());

        return convertView;

    }


    private static class ViewHolder{
        private TextView tvLingjianNum;
        private TextView tvLingjianName;







    }
}
