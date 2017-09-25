package com.aofan.cardismantling.adapter.vp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;


import com.aofan.cardismantling.R;
import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.util.viewutil.BitmapTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class PicViewPagerAdapter extends PagerAdapter {

    List<View> mViewList;
    List<String> mPicUrls;
    Context mContext;
    LayoutInflater mLayoutInflater;


    public PicViewPagerAdapter(Context mContext, List<String> picUrls, List<View> mViewList) {
        this.mContext = mContext;
        this.mPicUrls = picUrls;
        this.mViewList = mViewList;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

       /* View viewItem = mViewList.get(position);*/
        View viewItem = mLayoutInflater.inflate(R.layout.vp_item_pic_show, null);

        final ImageView loadingiv = (ImageView) viewItem.findViewById(R.id.loading_iv);
        final ImageView picIv = (ImageView) viewItem.findViewById(R.id.pic_iv);
        String picUrl = mPicUrls.get(position);


        int targetWidth = ((WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() - 10;
        int targetHeight = ((WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight() - 10;

        if (picUrl.startsWith(Config.HTTP_PREX)) {


            Picasso.with(mContext).load(picUrl).
                    placeholder(R.drawable.default_pic_big)
                    .error(R.drawable.default_pic_big)
                    .transform(new BitmapTransformation(mContext, targetWidth, targetHeight))
                    .into(picIv, new Callback() {


                        @Override
                        public void onSuccess() {
                            loadingiv.setVisibility(View.INVISIBLE);
                            picIv.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError() {
                            loadingiv.setVisibility(View.VISIBLE);
                            picIv.setVisibility(View.INVISIBLE);
                        }
                    });

        } else {
            Picasso.with(mContext).load(new File(picUrl)).
                    placeholder(R.drawable.default_pic_big)
                    .error(R.drawable.default_pic_big)
                    .transform(new BitmapTransformation(mContext, targetWidth, targetHeight))
                    .into(picIv, new Callback() {


                        @Override
                        public void onSuccess() {
                            loadingiv.setVisibility(View.INVISIBLE);
                            picIv.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError() {
                            loadingiv.setVisibility(View.VISIBLE);
                            picIv.setVisibility(View.INVISIBLE);
                        }
                    });
        }

       /* if (mFromType.equals(Config.FROM_WEB))
        {

        }else  if (mFromType.equals(Config.FROM_LOCAL))
        {

        }*/

        container.addView(viewItem, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return viewItem;
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}

