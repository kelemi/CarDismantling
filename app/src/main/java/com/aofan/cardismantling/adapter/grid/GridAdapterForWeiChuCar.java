package com.aofan.cardismantling.adapter.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aofan.cardismantling.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class GridAdapterForWeiChuCar extends BaseAdapter {

    private Context context;
    private List<String> picpathList;
    private GridPicListener gridPicListener;

    public GridAdapterForWeiChuCar(Context context, List<String> picpathList,
                                   GridPicListener gridPicListener) {
        this.context = context;
        this.picpathList = picpathList;
        this.gridPicListener = gridPicListener;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item_pic, null);
            myViewHolder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            myViewHolder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        int targetWidth = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 4 - 10;

        Picasso.with(context)
                .load("file:///"+picpathList.get(position))
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

        //查看
        myViewHolder.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridPicListener.onGridPicItemClick(position, picpathList);
            }
        });

        //删除
        myViewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridPicListener.onGridPicDelete(position);
            }
        });
        return convertView;
    }

    private class MyViewHolder {

        private ImageView iv_pic;
        private ImageView iv_delete;
    }

    /**
     * grid的pic item点击监听
     */
    public interface GridPicListener {

        void onGridPicItemClick(int position, List<String> picpathList);

        // 图片删除按钮点击事件监听
        void onGridPicDelete(int position);
    }

}
