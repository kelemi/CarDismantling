package com.aofan.cardismantling.adapter.grid;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.ChaiJieDetailInfoForCuChai;
import com.aofan.cardismantling.common.Config;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class GridAdapterForCuChaiLingJianPic extends BaseAdapter {


    //List<ChaiJieDetailInfo.PartlistBean.ImgattachlistBean> picAttachList;

    List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> picList;


    Context context;
    LayoutInflater layoutInflater;
    boolean isNeedDelete;

    GridPicClickListener gridPicClickListener;

    GridPicDeleteClickListener gridPicDeleteClickListener;

    public GridPicDeleteClickListener getGridPicDeleteClickListener() {
        return gridPicDeleteClickListener;
    }

    public void setGridPicDeleteClickListener(GridPicDeleteClickListener gridPicDeleteClickListener) {
        this.gridPicDeleteClickListener = gridPicDeleteClickListener;
    }

    public GridPicClickListener getGridPicClickListener() {
        return gridPicClickListener;
    }

    public void setGridPicClickListener(GridPicClickListener gridPicClickListener) {
        this.gridPicClickListener = gridPicClickListener;
    }


    public GridAdapterForCuChaiLingJianPic(List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> allPicList, Context context, boolean isNeedDelete) {

        this.picList = allPicList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

        this.isNeedDelete = isNeedDelete;
    }

    @Override
    public int getCount() {
        return picList.size();
    }

    @Override
    public Object getItem(int position) {
        return picList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.gv_item_pic,null);
            viewHolder = new ViewHolder();

            viewHolder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
            viewHolder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int targetWidth  = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth()/4 - 10;

        String picUrl = null;

        //picUrl = picList.get(position).getLocalPath();

        if (TextUtils.isEmpty(picList.get(position).getPath()) == false)
        {

            String picRemotePath = null;
            if (picList.get(position).getPath().startsWith("."))
            {
                picRemotePath = picList.get(position).getPath().substring(1,picList.get(position).getPath().length());
            }else {
                picRemotePath = picList.get(position).getPath();
            }

            picUrl = Config.URL_TO_LOAD_PIC_IN_USE+ picRemotePath;
        }else {
            picUrl = picList.get(position).getLocalPicPath();
        }


        File picFile = null;
        if (!picUrl.startsWith(Config.HTTP_PREX))
        {
            /*picUrl = HttpService.BASE_URL_IN_USE+picUrl;*/
            picFile = new File(picUrl);
            Picasso.with(context)
                    .load(picFile)
                    .resize(targetWidth,targetWidth)
                    .centerCrop()
                    .placeholder(R.drawable.ico_default)
                    .error(R.drawable.ico_default)
                    .tag(context)
                    .into(viewHolder.ivPic, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {

                        }
                    });

        }else {
            Picasso.with(context)
                    .load(picUrl)
                    .resize(targetWidth,targetWidth)
                    .centerCrop()
                    .placeholder(R.drawable.ico_default)
                    .error(R.drawable.ico_default)
                    .tag(context)
                    .into(viewHolder.ivPic, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }




        viewHolder.ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=gridPicClickListener)
                {
                    gridPicClickListener.onGridPicItemClick(position,picList);
                }
            }
        });

        /*if (isNeedDelete==true)
        {
            viewHolder.ivDelete.setVisibility(View.VISIBLE);
        }else {
            viewHolder.ivDelete.setVisibility(View.GONE);
        }*/


        if (picList.get(position).isLoaclPic() == true)
        {
            viewHolder.ivDelete.setVisibility(View.VISIBLE);
        }else {
            viewHolder.ivDelete.setVisibility(View.GONE);
        }

        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=gridPicDeleteClickListener)
                {
                    gridPicDeleteClickListener.onGridPicDelete(position);
                }
            }
        });

        return convertView;
    }




    static class ViewHolder{
        private ImageView ivPic;
        private ImageView ivDelete;

    }

    /**
     * grid的pic item点击监听
     */
    public interface GridPicClickListener{
        public void onGridPicItemClick(int position,  List<ChaiJieDetailInfoForCuChai.ImgattachlistBean> cuChaiPicList);
    }

    /**
     * 图片删除按钮点击事件监听
     */
    public interface GridPicDeleteClickListener{
        public void onGridPicDelete(int position);
    }
}

