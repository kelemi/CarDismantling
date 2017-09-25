package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.grid.GridAdapterForJingChaiLingJianPic;
import com.aofan.cardismantling.bean.ChaiJieLingJianInfoItem;
import com.aofan.cardismantling.widget.GridViewForScrollView;

import java.util.List;

/**已经拆解成功的任务的详情界面的零件列表的adapter
 * Created by Administrator on 2016/11/24.
 */

public class ListAdapterForChaiJieLingJianHasFinished extends BaseAdapter {

    List<ChaiJieLingJianInfoItem> mInfoList;
    Context mContext;
    LayoutInflater mLayoutInflater;


    GridAdapterForJingChaiLingJianPic.GridPicClickListener lingJianPicClickListener;

    public GridAdapterForJingChaiLingJianPic.GridPicClickListener getLingJianPicClickListener() {
        return lingJianPicClickListener;
    }

    public void setLingJianPicClickListener(GridAdapterForJingChaiLingJianPic.GridPicClickListener lingJianPicClickListener) {
        this.lingJianPicClickListener = lingJianPicClickListener;
    }

    public ListAdapterForChaiJieLingJianHasFinished(List<ChaiJieLingJianInfoItem> mInfoList, Context mContext) {
        this.mInfoList = mInfoList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return mInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_has_finished_chaijie_lingjian, null);
            viewHolder = new ViewHolder();

            viewHolder.tvLingjianName = (TextView) convertView.findViewById(R.id.tv_lingjian_name);
            viewHolder.tvLingjianNum = (TextView)convertView. findViewById(R.id.tv_lingjian_num);
            viewHolder.gvLingjianPic = (GridViewForScrollView)convertView. findViewById(R.id.gv_lingjian_pic);


            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        ChaiJieLingJianInfoItem waitChaiJieLingJianInfo = mInfoList.get(i);

        viewHolder.tvLingjianName.setText(waitChaiJieLingJianInfo.getLingJianName());
        viewHolder.tvLingjianNum.setText(String.valueOf(waitChaiJieLingJianInfo.getLingJianNum()));

        /*if (CommonUtil.isListNullOrEmpty(waitChaiJieLingJianInfo.getLingJianPicList())==false)
        {
            GridAdapterForChaiJieLingJianPic lingJianPicAdapter = new GridAdapterForChaiJieLingJianPic(waitChaiJieLingJianInfo.get(),mContext,i,false);
            viewHolder.gvLingjianPic.setAdapter(lingJianPicAdapter);
            viewHolder.gvLingjianPic.setVisibility(View.VISIBLE);
            if (null!= lingJianPicClickListener)
            {
                lingJianPicAdapter.setGridPicClickListener(lingJianPicClickListener);
            }

        }else {
            viewHolder.gvLingjianPic.setVisibility(View.GONE);
        }*/



        return convertView;

    }


    private static class ViewHolder{
        private TextView tvLingjianName;
        private TextView tvLingjianNum;
        private GridViewForScrollView gvLingjianPic;
    }



}
