package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.JobWaitToDoChaiJieTaskItem;

import java.util.List;

/**待办任务（拆解任务的adapter）
 * Created by Administrator on 2016/11/23.
 */

public class ListAdapterForJobWaitToDoChaiJie extends BaseAdapter {

    List<JobWaitToDoChaiJieTaskItem> mJobList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForJobWaitToDoChaiJie(List<JobWaitToDoChaiJieTaskItem> mJobList, Context mContext) {
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
            convertView = mLayoutInflater.inflate(R.layout.list_item_job_todo_chaijie, null);
            viewHolder = new ViewHolder();

            viewHolder.tvCarNum = (TextView) convertView.findViewById(R.id.tv_car_num);
            viewHolder.tvPaigongTime = (TextView) convertView.findViewById(R.id.tv_paigong_time);
            viewHolder.tvChaijieWorkers = (TextView) convertView.findViewById(R.id.tv_chaijie_workers);

            viewHolder.tvChaijieWay = (TextView) convertView.findViewById(R.id.tv_chaijie_way);
            viewHolder.tvTagLingjianToChaijie = (TextView) convertView.findViewById(R.id.tv_tag_lingjian_to_chaijie);
            viewHolder.tvLingjianToChaijie = (TextView)convertView. findViewById(R.id.tv_lingjian_to_chaijie);

            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        JobWaitToDoChaiJieTaskItem jobWaitToDoChaiJieTaskItem = mJobList.get(i);

        viewHolder.tvCarNum .setText(jobWaitToDoChaiJieTaskItem.getVehiclenumber());
        viewHolder.tvPaigongTime.setText("派工时间:"+jobWaitToDoChaiJieTaskItem.getCreatetime());
        viewHolder.tvChaijieWorkers.setText("拆解人员:"+jobWaitToDoChaiJieTaskItem.getDispatchperson());

        if (TextUtils.isEmpty(jobWaitToDoChaiJieTaskItem.getPartnamelist()) == false)
        {
           // viewHolder.tvChaijieWay.setText("拆解方式:"+"精拆");
            viewHolder.tvLingjianToChaijie.setText(jobWaitToDoChaiJieTaskItem.getPartnamelist());

            viewHolder.tvTagLingjianToChaijie.setVisibility(View.VISIBLE);
            viewHolder.tvLingjianToChaijie.setVisibility(View.VISIBLE);

        }else {
           // viewHolder.tvChaijieWay.setText("拆解方式:"+"粗拆");
            viewHolder.tvTagLingjianToChaijie.setVisibility(View.GONE);
            viewHolder.tvLingjianToChaijie.setVisibility(View.GONE);
        }

        return convertView;

    }


    private static class ViewHolder{

        /*private TextView tvCarNum;
        private TextView tvPaigongTime;
        private TextView tvChaijieWorkers;
        private TextView tvLingjianToChaijie;*/

        private TextView tvCarNum;
        private TextView tvPaigongTime;
        private TextView tvChaijieWorkers;
        private TextView tvChaijieWay;
        private TextView tvTagLingjianToChaijie;
        private TextView tvLingjianToChaijie;







    }


}

