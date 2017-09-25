package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.grid.GridAdapterForWeiChuChaijie;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.bean.WeiChuChaijieDetail;
import com.aofan.cardismantling.widget.GridViewForScrollView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

//未出手续车辆
public class ListAdapterForWeiChuCar extends BaseAdapter implements GridAdapterForWeiChuChaijie.PicClickListener {

    private Context context;

    private List<WeiChuChaijieDetail.PartlistBean> partlistBeenList;

    private PicItemClickListener picItemClickListener;

    public ListAdapterForWeiChuCar(Context context, List<WeiChuChaijieDetail.PartlistBean> partlistBeenList, PicItemClickListener picItemClickListener) {
        this.context = context;
        this.partlistBeenList = partlistBeenList;
        this.picItemClickListener = picItemClickListener;
    }


    @Override
    public int getCount() {
        return partlistBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return partlistBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;

        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_weichu_chaijie, null);
            myViewHolder.tvComponentName = (TextView) convertView.findViewById(R.id.tv_component_name);
            myViewHolder.tvLingjianNum = (TextView) convertView.findViewById(R.id.tv_lingjian_num);
            myViewHolder.gvComponents = (GridViewForScrollView) convertView.findViewById(R.id.gv_components);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        myViewHolder.tvComponentName.setText(partlistBeenList.get(position).getPartname() + "");
        myViewHolder.tvLingjianNum.setText("数量:" + partlistBeenList.get(position).getOutputcount() + "");

        GridAdapterForWeiChuChaijie gridAdapterForWeiChuChaijie = new GridAdapterForWeiChuChaijie(context, partlistBeenList.get(position).getImgattachlist(), this);

        myViewHolder.gvComponents.setAdapter(gridAdapterForWeiChuChaijie);

        return convertView;
    }

    @Override
    public void tolookPic(int position, List<String> picpathList) {
        picItemClickListener.tolookPic(position, picpathList);
    }


    class MyViewHolder {

        private TextView tvComponentName, tvLingjianNum;

        private GridViewForScrollView gvComponents;
    }

    public interface PicItemClickListener {

        void tolookPic(int position, List<String> picpathList);
    }
}
