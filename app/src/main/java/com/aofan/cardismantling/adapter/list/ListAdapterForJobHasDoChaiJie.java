package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.HasDoChaiJieTaskItem;

import java.util.List;

/**已办事项（拆解任务）的adapter
 * Created by Administrator on 2016/11/23.
 */

public class ListAdapterForJobHasDoChaiJie extends BaseAdapter {

    List<HasDoChaiJieTaskItem> mJobList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForJobHasDoChaiJie(List<HasDoChaiJieTaskItem> mJobList, Context mContext) {
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
            convertView = mLayoutInflater.inflate(R.layout.list_item_job_hasdo_chaijie, null);
            viewHolder = new ViewHolder();

            viewHolder.tvCarNum = (TextView) convertView.findViewById(R.id.tv_car_num);
            viewHolder.tvLingjianToChaijie = (TextView)convertView. findViewById(R.id.tv_lingjian_to_chaijie);

            viewHolder.tvJobState = (TextView) convertView.findViewById(R.id.tv_job_state);

            viewHolder.tvChaijieType = (TextView) convertView.findViewById(R.id.tv_chaijie_type);



            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        HasDoChaiJieTaskItem hasDoChaiJieTaskItem = mJobList.get(i);

        viewHolder.tvCarNum.setText("汽车编号:"+hasDoChaiJieTaskItem.getVehiclenumber());

        if (TextUtils.isEmpty(hasDoChaiJieTaskItem.getPartnamelist()) == false)
        {
            //viewHolder.tvChaijieType.setText("拆解方式:"+ Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI);
            viewHolder.tvLingjianToChaijie.setText("拆解零件："+hasDoChaiJieTaskItem.getPartnamelist());
            viewHolder.tvLingjianToChaijie.setVisibility(View.VISIBLE);
        }else {
            //viewHolder.tvChaijieType.setText("拆解方式:"+ Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI);
            viewHolder.tvLingjianToChaijie.setVisibility(View.GONE);
        }

        viewHolder.tvJobState.setVisibility(View.GONE);

        return convertView;

    }


    private static class ViewHolder{

        private TextView tvJobState;
        private TextView tvCarNum;
        private TextView tvLingjianToChaijie;
        private TextView tvChaijieType;


        /*private TextView tvJobState;
        private TextView tvCarNum;

        private TextView tvLingjianToChaijie;*/

        /*tvJobState = (TextView) findViewById(R.id.tv_job_state);
        tvCarNum = (TextView) findViewById(R.id.tv_car_num);
        tvLingjianToChaijie = (TextView) findViewById(R.id.tv_lingjian_to_chaijie);*/

    }

}
