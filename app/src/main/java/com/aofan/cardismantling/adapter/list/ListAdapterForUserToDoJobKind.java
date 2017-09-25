package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.UserToDoJobKind;

import java.util.List;

/**用户去办理的事项类型adapter
 * Created by Administrator on 2016/11/28.
 */

public class ListAdapterForUserToDoJobKind extends BaseAdapter {

    List<UserToDoJobKind> mUserToDoJobKindList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ListAdapterForUserToDoJobKind(List<UserToDoJobKind> mUserToDoJobKindList, Context mContext) {
        this.mUserToDoJobKindList = mUserToDoJobKindList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mUserToDoJobKindList.size();
    }

    @Override
    public Object getItem(int i) {
        return mUserToDoJobKindList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_tv_link, null);
            viewHolder = new ViewHolder();

            viewHolder.tvTodoJobKind = (TextView) convertView.findViewById(R.id.tv_todo_job_kind);




            convertView.setTag(viewHolder);
        } else {
            viewHolder =  (ViewHolder)convertView.getTag();
        }

        viewHolder.tvTodoJobKind.setText(mUserToDoJobKindList.get(i).getName());

        return convertView;

    }


    private static class ViewHolder{
        private TextView tvTodoJobKind;






    }
}
