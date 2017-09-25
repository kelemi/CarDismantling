package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.ChaiJieWorkerInfo;

import java.util.List;

/**
 * 拆解工作人员adapter
 * Created by Administrator on 2016/11/21.
 */

public class ListAdapterForChaiJieWorker extends BaseAdapter {

    List<ChaiJieWorkerInfo> mWorkerList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    WorkerClickListener workerClickListener;

    public WorkerClickListener getWorkerClickListener() {
        return workerClickListener;
    }

    public void setWorkerClickListener(WorkerClickListener workerClickListener) {
        this.workerClickListener = workerClickListener;
    }

    public ListAdapterForChaiJieWorker(List<ChaiJieWorkerInfo> mWorkerList, Context mContext) {
        this.mWorkerList = mWorkerList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mWorkerList.size();
    }

    @Override
    public Object getItem(int i) {
        return mWorkerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_choose_chaijie_worker, null);
            viewHolder = new ViewHolder();

            viewHolder.layoutParent = (LinearLayout) convertView.findViewById(R.id.layout_parent);


            viewHolder.cbChoose = (CheckBox) convertView.findViewById(R.id.cb_choose);
            viewHolder.tvName = (TextView)convertView. findViewById(R.id.tv_name);


            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        ChaiJieWorkerInfo chaiJieWorkerInfo = mWorkerList.get(i);

        viewHolder.tvName.setText(chaiJieWorkerInfo.getUsername());

        if (chaiJieWorkerInfo.isCheck()){
            viewHolder.cbChoose.setChecked(true);
        }else {
            viewHolder.cbChoose.setChecked(false);
        }


        viewHolder.layoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null!=workerClickListener)
                {
                    workerClickListener.onWorkerClick(i);
                }
            }
        });

        return convertView;

    }


    private static class ViewHolder{
        private LinearLayout layoutParent;
        private CheckBox cbChoose;
        private TextView tvName;



    }

    public interface WorkerClickListener {
        public void onWorkerClick(int position);
    }
}
