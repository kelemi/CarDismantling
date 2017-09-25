package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.ColleagueItem;
import com.aofan.cardismantling.bean.PaiGongDanitem;

import java.util.List;

/**我的同事列表接口
 * Created by Administrator on 2016/11/30.
 */

public class ListAdapterForMyColleagues extends BaseAdapter {

    List<ColleagueItem> mColleagueList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForMyColleagues(List<ColleagueItem> mColleagueList, Context mContext) {
        this.mColleagueList = mColleagueList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mColleagueList.size();
    }

    @Override
    public Object getItem(int i) {
        return mColleagueList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_my_colleagues, null);
            viewHolder = new ViewHolder();

            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvTelno = (TextView) convertView.findViewById(R.id.tv_telno);


            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

       final ColleagueItem colleagueItem = mColleagueList.get(i);

        viewHolder.tvName.setText(colleagueItem.getMyname());

        if (TextUtils.isEmpty(colleagueItem.getTel()) == false)
        {
            viewHolder.tvTelno.setText(colleagueItem.getTel());

            viewHolder.tvTelno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + colleagueItem.getTel()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }else {
            viewHolder.tvTelno.setVisibility(View.GONE);
        }


        return convertView;

    }


    private static class ViewHolder{
        private TextView tvName;
        private TextView tvTelno;



    }
}

