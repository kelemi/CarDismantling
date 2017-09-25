package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.TongJiDataOfWorker;

import java.util.List;

/**人员统计adapter
 * Created by Administrator on 2016/11/22.
 */

public class ListAdapterForWorkerTongji extends BaseAdapter {

    List<TongJiDataOfWorker> mWorkerTongJiData;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForWorkerTongji(List<TongJiDataOfWorker> mWorkerTongJiData, Context mContext) {
        this.mWorkerTongJiData = mWorkerTongJiData;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mWorkerTongJiData.size();
    }

    @Override
    public Object getItem(int i) {
        return mWorkerTongJiData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_worker_tongji, null);
            viewHolder = new ViewHolder();

            viewHolder.tvWorkerName = (TextView) convertView.findViewById(R.id.tv_worker_name);
            viewHolder.tvTelno = (TextView)convertView. findViewById(R.id.tv_telno);
            viewHolder.tvChaijieNum = (TextView) convertView.findViewById(R.id.tv_chaijie_num);



            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        TongJiDataOfWorker tongJiDataOfWorker = mWorkerTongJiData.get(i);
        viewHolder.tvWorkerName .setText(tongJiDataOfWorker.getUsername());
        if (TextUtils.isEmpty(tongJiDataOfWorker.getTel()) == false)
        {
            viewHolder.tvTelno .setText(tongJiDataOfWorker.getTel());
        }else {
            viewHolder.tvTelno .setText("");
        }

        viewHolder.tvChaijieNum.setText("拆解数量："+tongJiDataOfWorker.getNum());

        return convertView;

    }


    private static class ViewHolder{
        private TextView tvWorkerName;
        private TextView tvTelno;
        private TextView tvChaijieNum;







    }
}

