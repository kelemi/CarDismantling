package com.aofan.cardismantling.adapter.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.bean.WeiChuChaijieDetail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class GridAdapterForWeiChuChaijie extends BaseAdapter {

    private Context context;
    private List<WeiChuChaijieDetail.PartlistBean.ImgattachlistBean> picpathList;

    private PicClickListener picClickListener;

    public GridAdapterForWeiChuChaijie(Context context, List<WeiChuChaijieDetail.PartlistBean.ImgattachlistBean> picpathList, PicClickListener picClickListener) {
        this.context = context;
        this.picpathList = picpathList;
        this.picClickListener = picClickListener;
    }

    @Override
    public int getCount() {
        return picpathList.size();
    }

    @Override
    public Object getItem(int position) {
        return picpathList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item_img, null);
            myViewHolder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        int targetWidth = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 4 - 10;

        Picasso.with(context)
                .load(Config.URL_TO_LOAD_PIC_IN_USE + picpathList.get(position).getPath())
                .resize(targetWidth, targetWidth)
                .centerCrop()
                .placeholder(R.drawable.ico_default)
                .error(R.drawable.ico_default)
                .tag(context)
                .into(myViewHolder.iv_pic, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {

                    }
                });
        //图片点击
        myViewHolder.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> picUrls = new ArrayList<String>();
                for (WeiChuChaijieDetail.PartlistBean.ImgattachlistBean imgattachlistBean : picpathList) {
                    picUrls.add(Config.URL_TO_LOAD_PIC_IN_USE + imgattachlistBean.getPath());
                }
                picClickListener.tolookPic(position, picUrls);
            }
        });

        return convertView;
    }

    private class MyViewHolder {

        private ImageView iv_pic;
    }

    public interface PicClickListener {

        void tolookPic(int position, List<String> picpathList);
    }

}
