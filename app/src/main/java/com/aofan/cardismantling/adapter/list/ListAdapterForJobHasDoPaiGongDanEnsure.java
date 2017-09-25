package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.HasDoPaiGongDanListItem;

import java.util.List;

/**已办任务派工单（已经确认列表）
 * Created by Administrator on 2016/11/23.
 */

public class ListAdapterForJobHasDoPaiGongDanEnsure extends BaseAdapter {

    List<HasDoPaiGongDanListItem> mJobList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForJobHasDoPaiGongDanEnsure(List<HasDoPaiGongDanListItem> mJobList, Context mContext) {
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
            convertView = mLayoutInflater.inflate(R.layout.list_item_job_todo_paidan, null);
            viewHolder = new ViewHolder();

            viewHolder.tvChaijieRenwuState = (TextView) convertView.findViewById(R.id.tv_chaijie_renwu_state);
            viewHolder.tvCarNum = (TextView)convertView. findViewById(R.id.tv_car_num);

            viewHolder.tvChaijieWorker = (TextView) convertView.findViewById(R.id.tv_chaijie_worker);
            viewHolder.tvChaijieLingjian = (TextView)convertView. findViewById(R.id.tv_chaijie_lingjian);


            viewHolder.tvChaijieType = (TextView) convertView.findViewById(R.id.tv_chaijie_type);


            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        HasDoPaiGongDanListItem hasDoPaiGongDanListItem = mJobList.get(i);

        viewHolder.tvChaijieRenwuState.setText(hasDoPaiGongDanListItem.getType());
        viewHolder.tvCarNum.setText(hasDoPaiGongDanListItem.getVehiclenumber());

        viewHolder.tvChaijieWorker.setText("拆解人:"+hasDoPaiGongDanListItem.getDispatchperson());

        if (TextUtils.isEmpty(hasDoPaiGongDanListItem.getPartnamelist()) == false)
        {
            //viewHolder.tvChaijieType.setText("拆解方式:"+ Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI);
            viewHolder.tvChaijieLingjian.setText("拆解零件:"+hasDoPaiGongDanListItem.getPartnamelist());
            viewHolder.tvChaijieLingjian.setVisibility(View.VISIBLE);
        }else {
            //viewHolder.tvChaijieType.setText("拆解方式:"+ Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI);
            viewHolder.tvChaijieLingjian.setVisibility(View.GONE);
        }



        return convertView;

    }


    private static class ViewHolder{
        private TextView tvChaijieRenwuState;
        private TextView tvCarNum;
        private TextView tvChaijieWorker;
        private TextView tvChaijieType;
        private TextView tvChaijieLingjian;






    }


}

