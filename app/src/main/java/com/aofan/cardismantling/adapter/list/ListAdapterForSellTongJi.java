package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.SellTongJiListItem;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public class ListAdapterForSellTongJi extends BaseAdapter {

    List<SellTongJiListItem> mSellTongJiList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForSellTongJi(Context context, List<SellTongJiListItem> sellTongJiList) {
        mContext = context;
        mSellTongJiList = sellTongJiList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mSellTongJiList.size();
    }

    @Override
    public Object getItem(int i) {
        return mSellTongJiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (view == null)
        {

            view = mLayoutInflater.inflate(R.layout.list_item_sell_tongji,null);
            viewHolder = new ViewHolder();

            viewHolder.tvLingjianName = (TextView) view.findViewById(R.id.tv_lingjian_name);
            viewHolder.tvLingjianSellPrice = (TextView) view.findViewById(R.id.tv_lingjian_sell_price);
            viewHolder.tvLingjianSeller = (TextView) view.findViewById(R.id.tv_lingjian_seller);
            viewHolder.tvLingjianSellTime = (TextView) view.findViewById(R.id.tv_lingjian_sell_time);
            view.setTag(viewHolder);


        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        SellTongJiListItem sellTongJiListItem = mSellTongJiList.get(i);


        viewHolder.tvLingjianName.setText(sellTongJiListItem.getPartname());
        viewHolder.tvLingjianSeller.setText("销售员:"+sellTongJiListItem.getSeller());
        viewHolder.tvLingjianSellPrice.setText("售价:"+sellTongJiListItem.getSellprice());
        viewHolder.tvLingjianSellTime.setText("销售时间:"+sellTongJiListItem.getSelltime());



        return view;
    }


    static class ViewHolder{

        private TextView tvLingjianName;
        private TextView tvLingjianSellPrice;
        private TextView tvLingjianSeller;
        private TextView tvLingjianSellTime;

        /*tvLingjianName = (TextView) findViewById(R.id.tv_lingjian_name);
        tvLingjianSellPrice = (TextView) findViewById(R.id.tv_lingjian_sell_price);
        tvLingjianSeller = (TextView) findViewById(R.id.tv_lingjian_seller);
        tvLingjianSellTime = (TextView) findViewById(R.id.tv_lingjian_sell_time);*/


    }
}
