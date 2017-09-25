package com.aofan.cardismantling.adapter.vp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.LingJianInfo;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.util.viewutil.BitmapTransformation3;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/10.
 */

public class ViewPagerAdapterForLingJianPic extends PagerAdapter {

    private List<View> viewList;
    private List<LingJianInfo.ImgattachlistBean> mLingJianPicList;
    private Context mContext;

    public ViewPagerAdapterForLingJianPic(List<View> viewList,List<LingJianInfo.ImgattachlistBean> mLingJianPicList,Context context) {
        this.viewList = viewList;
        this.mLingJianPicList = mLingJianPicList;
        this.mContext = context;

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View view = viewList.get(position);

        ImageView bannerIv = (ImageView) view
                .findViewById(R.id.bannerpiciv);

        int targetWidth = ((WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        //int targetHeight = (int)((targetWidth*1.0/750*1.0)*270);

        LingJianInfo.ImgattachlistBean picItem = mLingJianPicList.get(position);


        if (TextUtils.isEmpty(picItem.getPath())==false)
        {
           /* if (picItem.getPath().endsWith("/")==false)
            {*/
                String headivUrl = Config.URL_TO_LOAD_PIC_IN_USE+picItem.getPath();

                Picasso.with(mContext)
                        .load(headivUrl)
                        .transform(new BitmapTransformation3(mContext,targetWidth))
                        .placeholder(R.drawable.default_pic_trans)
                        .error(R.drawable.default_pic_trans)
                        .tag(mContext)
                        .into(bannerIv);



        }else {
            Picasso.with(mContext)
                    .load(R.drawable.default_pic_trans)
                    .placeholder(R.drawable.default_pic_trans)
                    .error(R.drawable.default_pic_trans)
                    .tag(mContext)
                    .into(bannerIv);
        }

       /* bannerIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != bannerPicClickListener)
                {
                    bannerPicClickListener.OnBannerPicClick(position);
                }
            }
        });


        container.addView(bannerIv);

        return bannerIvItem;*/

        collection.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /*public void setData(@Nullable List<View> viewList,List<BannerItem> bannerList) {
        if (viewList == null) {
            this.viewList.clear();
        } else {
            this.viewList.addAll(viewList);
        }
        notifyDataSetChanged();
    }*/

    @NonNull
    public List<View> getData() {
        if (viewList == null) {
            viewList = new ArrayList<>();
        }

        return viewList;
    }
}
