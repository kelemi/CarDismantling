package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.PaiGongDanOfEnsure;

import java.util.List;

/**待办事项派工单确认list adapter
 * Created by Administrator on 2016/11/23.
 */

public class ListAdapterForJobWaitToDoPaiGongDanEnsure extends BaseAdapter {

    List<PaiGongDanOfEnsure> mPaiGongDanList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForJobWaitToDoPaiGongDanEnsure(List<PaiGongDanOfEnsure> mPaiGongDanList, Context mContext) {
        this.mPaiGongDanList = mPaiGongDanList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mPaiGongDanList.size();
    }

    @Override
    public Object getItem(int i) {
        return mPaiGongDanList.get(i);
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

        PaiGongDanOfEnsure paiGongDanOfEnsure = mPaiGongDanList.get(i);

        viewHolder.tvChaijieRenwuState.setText(paiGongDanOfEnsure.getType());
        viewHolder.tvCarNum.setText("车牌号:"+paiGongDanOfEnsure.getVehiclenumber());

        viewHolder.tvChaijieWorker.setText("拆解人:"+paiGongDanOfEnsure.getDispatchperson());
        /*if (TextUtils.isEmpty(paiGongDanOfEnsure.getPartnamelist())==false)
        {
            viewHolder.tvChaijieLingjian.setVisibility(View.VISIBLE);
            viewHolder.tvChaijieLingjian.setText(paiGongDanOfEnsure.getPartnamelist());
        }else {
            viewHolder.tvChaijieLingjian.setVisibility(View.GONE);
        }*/


        if (TextUtils.isEmpty(paiGongDanOfEnsure.getPartnamelist()) == false)
        {
            viewHolder.tvChaijieLingjian.setText("拆解的零件:"+paiGongDanOfEnsure.getPartnamelist());
            viewHolder.tvChaijieLingjian.setVisibility(View.VISIBLE);
           /* viewHolder.tvChaijieType.setText("拆解方式:"+ Config.TYPE_STR_OF_CHAIJIEWAY_JINGCHAI);*/
        }else {
            viewHolder.tvChaijieLingjian.setVisibility(View.GONE);
            /*viewHolder.tvChaijieType.setText("拆解方式:"+Config.TYPE_STR_OF_CHAIJIEWAY_CUCHAI);*/
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
