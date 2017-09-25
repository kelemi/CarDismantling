package com.aofan.cardismantling.adapter.expandlist;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.LingJIanOfCanChooseChaiJie;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ExpandAdapterForLingJianChoose extends BaseExpandableListAdapter {

    List<String> mGroupDataList;
    List<List<LingJIanOfCanChooseChaiJie>> mLingJianList;

    Context mContext;
    LayoutInflater  mLayoutInflater;

    static Handler handler;


    LingJianItemClickListener lingJianItemClickListener;

    public LingJianItemClickListener getLingJianItemClickListener() {
        return lingJianItemClickListener;
    }

    public void setLingJianItemClickListener(LingJianItemClickListener lingJianItemClickListener) {
        this.lingJianItemClickListener = lingJianItemClickListener;
    }

    public ExpandAdapterForLingJianChoose( Context mContext,List<String> mGroupDataList, List<List<LingJIanOfCanChooseChaiJie>> mLingJianList) {
        this.mGroupDataList = mGroupDataList;
        this.mContext = mContext;
        this.mLingJianList = mLingJianList;

        mLayoutInflater = LayoutInflater.from(mContext);

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                notifyDataSetChanged();
                super.handleMessage(msg);
            }
        };
    }

    public void refresh() {
        handler.sendMessage(new Message());
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return mGroupDataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mLingJianList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupDataList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mLingJianList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_group_chaijie_lingjian_kind,
                    null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvChaijieLingjianKind = (TextView) convertView
                    .findViewById(R.id.tv_chaijie_lingjian_kind);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.tvChaijieLingjianKind.setText(mGroupDataList.get(groupPosition));

        groupViewHolder.tvChaijieLingjianKind.setText(mGroupDataList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_child_chaijie_lingjian,
                    null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.layoutLingjian = (LinearLayout) convertView.findViewById(R.id.layout_lingjian);
            childViewHolder.cbChoose = (CheckBox) convertView
                    .findViewById(R.id.cb_choose);
            childViewHolder.tvName = (TextView) convertView
                    .findViewById(R.id.tv_name);

            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        childViewHolder.layoutLingjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null !=lingJianItemClickListener)
                {
                    lingJianItemClickListener.onLingJianItemClick(groupPosition,childPosition);
                }
            }
        });

        if (mLingJianList.get(groupPosition).get(childPosition).isCheck())
        {
            childViewHolder.cbChoose .setChecked(true);
        }else {
            childViewHolder.cbChoose .setChecked(false);
        }

        childViewHolder.tvName.setText(mLingJianList.get(groupPosition).get(childPosition).getPartname());


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }


    public static class GroupViewHolder{
        private TextView tvChaijieLingjianKind;


    }


    public static class ChildViewHolder{
        private LinearLayout layoutLingjian;
        private CheckBox cbChoose;
        private TextView tvName;

    }


    public interface LingJianItemClickListener{
        public void onLingJianItemClick(int groupPos,int childPos);
    }
}
