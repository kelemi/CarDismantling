package com.aofan.cardismantling.adapter.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.CarLingJianInfo;

import java.util.List;

/**总成详细的内容的adapter
 * Created by Administrator on 2016/11/21.
 */

public class GridAdapterForCarComponentDetail extends BaseAdapter {

    List<CarLingJianInfo> mLingJianList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    LingJianLayoutClickListener lingJianLayoutClickListener;
    /*LingJianCheckBoxCheckListener lingJianCheckBoxCheckListener;*/

   /* public LingJianCheckBoxCheckListener getLingJianCheckBoxCheckListener() {
        return lingJianCheckBoxCheckListener;
    }

    public void setLingJianCheckBoxCheckListener(LingJianCheckBoxCheckListener lingJianCheckBoxCheckListener) {
        this.lingJianCheckBoxCheckListener = lingJianCheckBoxCheckListener;
    }*/

    public LingJianLayoutClickListener getLingJianLayoutClickListener() {
        return lingJianLayoutClickListener;
    }

    public void setLingJianLayoutClickListener(LingJianLayoutClickListener lingJianLayoutClickListener) {
        this.lingJianLayoutClickListener = lingJianLayoutClickListener;
    }


    int mLingJianTyppe;



    public GridAdapterForCarComponentDetail(List<CarLingJianInfo> mLingJianList, Context mContext,int lingJianType) {
        this.mLingJianList = mLingJianList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        mLingJianTyppe = lingJianType;
    }


    public void setLingJianData(List<CarLingJianInfo> lingJianList){
        mLingJianList = lingJianList;
    }

    public void setLingJianType(int lingJianType){
        mLingJianTyppe = lingJianType;
    }

    @Override
    public int getCount() {
        return mLingJianList.size();
    }

    @Override
    public Object getItem(int i) {
        return mLingJianList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.grid_item_car_component_detail, null);
            viewHolder = new ViewHolder();

            viewHolder.cbChoose = (CheckBox) convertView.findViewById(R.id.cb_choose);
            viewHolder.tvName = (TextView)convertView. findViewById(R.id.tv_name);
            viewHolder.layoutLingjian = (LinearLayout) convertView.findViewById(R.id.layout_lingjian);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        CarLingJianInfo carLingJianInfo = mLingJianList.get(i);

        viewHolder.layoutLingjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null!= lingJianLayoutClickListener)
                {
                    lingJianLayoutClickListener.onLingJianLayoutClick(i,mLingJianTyppe);
                }
            }
        });

       /* viewHolder.cbChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (null!=lingJianCheckBoxCheckListener)
                {
                    lingJianCheckBoxCheckListener.onLingJianCheckBoxCheck(i,mLingJianTyppe);
                }
            }
        });*/

        if (carLingJianInfo.isCheck()==true)
        {
            viewHolder.cbChoose.setChecked(true);
        }else {
            viewHolder.cbChoose.setChecked(false);
        }

        viewHolder.tvName.setText(carLingJianInfo.getPartname());

        return convertView;

    }


    private static class ViewHolder{
        private LinearLayout layoutLingjian;
        private CheckBox cbChoose;
        private TextView tvName;

    }

    //零件整个item点击事件
    public interface LingJianLayoutClickListener{
        public void onLingJianLayoutClick(int position,int lingJianType);
    }



    /*//零件前面的cb check事件
    public interface LingJianCheckBoxCheckListener{
        public void onLingJianCheckBoxCheck(int position,int lingJianType);
    }
*/
}
