package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.grid.GridAdapterForJingChaiLingJianPic;
import com.aofan.cardismantling.bean.ChaiJieDetailInfo;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.widget.GridViewForScrollView;

import java.util.List;

/**拆解零件提交信息界面待拆解的零件信息的adapter
 * Created by Administrator on 2016/11/23.
 */

public class ListAdapterForChaiJieLingJianSubmitInfo extends BaseAdapter {

    List<ChaiJieDetailInfo.PartlistBean> mPartInfoList;
    Context mContext;
    LayoutInflater mLayoutInflater;


    //显示的拆解的信息 未提交拆解信息的类型，已经提交拆解信息
    public final static int TYPE_OF_NOT_SUBMIT_CAIJIE_INFO = 0;
    public final static int TYPE_OF_HAS_SUBMIT_CAIJIE_INFO = 1;

    public final static int TYPE_COUNT = 2;

    ToTakeLingJianPhotoTvClickListener toTakeLingJianPhotoTvClickListener;
    ToSubmitLingJianChaiJieInfoTvClickListener toSubmitLingJianChaiJieInfoTvClickListener;

    public ToTakeLingJianPhotoTvClickListener getToTakeLingJianPhotoTvClickListener() {
        return toTakeLingJianPhotoTvClickListener;
    }

    public void setToTakeLingJianPhotoTvClickListener(ToTakeLingJianPhotoTvClickListener toTakeLingJianPhotoTvClickListener) {
        this.toTakeLingJianPhotoTvClickListener = toTakeLingJianPhotoTvClickListener;
    }

    public ToSubmitLingJianChaiJieInfoTvClickListener getToSubmitLingJianChaiJieInfoTvClickListener() {
        return toSubmitLingJianChaiJieInfoTvClickListener;
    }

    public void setToSubmitLingJianChaiJieInfoTvClickListener(ToSubmitLingJianChaiJieInfoTvClickListener toSubmitLingJianChaiJieInfoTvClickListener) {
        this.toSubmitLingJianChaiJieInfoTvClickListener = toSubmitLingJianChaiJieInfoTvClickListener;
    }

    GridAdapterForJingChaiLingJianPic.GridPicClickListener lingJianPicClickListener;
    GridAdapterForJingChaiLingJianPic.GridPicDeleteClickListener lingJianPicDeleteClickListener;

    public GridAdapterForJingChaiLingJianPic.GridPicClickListener getLingJianPicClickListener() {
        return lingJianPicClickListener;
    }

    public void setLingJianPicClickListener(GridAdapterForJingChaiLingJianPic.GridPicClickListener lingJianPicClickListener) {
        this.lingJianPicClickListener = lingJianPicClickListener;
    }

    public GridAdapterForJingChaiLingJianPic.GridPicDeleteClickListener getLingJianPicDeleteClickListener() {
        return lingJianPicDeleteClickListener;
    }

    public void setLingJianPicDeleteClickListener(GridAdapterForJingChaiLingJianPic.GridPicDeleteClickListener lingJianPicDeleteClickListener) {
        this.lingJianPicDeleteClickListener = lingJianPicDeleteClickListener;
    }

    AddLingJianNumCliclListener addLingJianNumCliclListener;
    SubLingJianNumClickListener subLingJianNumClickListener;
    //LingJianNumInputListener lingJianNumInputListener;

    public SubLingJianNumClickListener getSubLingJianNumClickListener() {
        return subLingJianNumClickListener;
    }

    public void setSubLingJianNumClickListener(SubLingJianNumClickListener subLingJianNumClickListener) {
        this.subLingJianNumClickListener = subLingJianNumClickListener;
    }

    public AddLingJianNumCliclListener getAddLingJianNumCliclListener() {
        return addLingJianNumCliclListener;
    }

    public void setAddLingJianNumCliclListener(AddLingJianNumCliclListener addLingJianNumCliclListener) {
        this.addLingJianNumCliclListener = addLingJianNumCliclListener;
    }

   /* public LingJianNumInputListener getLingJianNumInputListener() {
        return lingJianNumInputListener;
    }

    public void setLingJianNumInputListener(LingJianNumInputListener lingJianNumInputListener) {
        this.lingJianNumInputListener = lingJianNumInputListener;
    }*/

    public ListAdapterForChaiJieLingJianSubmitInfo(List<ChaiJieDetailInfo.PartlistBean> mPartInfoList, Context mContext) {
        this.mPartInfoList = mPartInfoList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }


    @Override
    public int getItemViewType(int position) {
         super.getItemViewType(position);

        int type =TYPE_OF_NOT_SUBMIT_CAIJIE_INFO;

        //如果有图片附件，且图片附件的信息不是来自本地的
        if (mPartInfoList.get(position).getImgattachlist().size()>0 &&
                mPartInfoList.get(position).isLocalPic() == false)
        {
            type = TYPE_OF_HAS_SUBMIT_CAIJIE_INFO;
        }

        return type;
    }

    @Override
    public int getCount() {
        return mPartInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return mPartInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        ViewHolderOfNotSubmitChaiJieInfo viewHolderOfNotSubmitChaiJieInfo = null;

        ViewHolderOfHasSubmitChaiJieInfo viewHolderOfHasSubmitChaiJieInfo = null;


        int itemType =    getItemViewType(i);

        if (convertView == null) {

            switch (itemType)
            {
                case TYPE_OF_NOT_SUBMIT_CAIJIE_INFO:

                    convertView = mLayoutInflater.inflate(R.layout.list_item_chaijie_detail, null);

                    viewHolderOfNotSubmitChaiJieInfo = new ViewHolderOfNotSubmitChaiJieInfo();

                    viewHolderOfNotSubmitChaiJieInfo.tvLingjianName = (TextView) convertView.findViewById(R.id.tv_lingjian_name);
                    viewHolderOfNotSubmitChaiJieInfo.tvLingjianNum = (TextView)convertView. findViewById(R.id.tv_lingjian_num);
                    viewHolderOfNotSubmitChaiJieInfo.tvSubmitLingjianChaijieinfo = (TextView) convertView.findViewById(R.id.tv_submit_lingjian_chaijieinfo);
                    viewHolderOfNotSubmitChaiJieInfo.tvToTakePhoto = (TextView)convertView. findViewById(R.id.tv_to_take_photo);
                    viewHolderOfNotSubmitChaiJieInfo.gvLingjianPic = (GridViewForScrollView)convertView. findViewById(R.id.gv_lingjian_pic);

                    viewHolderOfNotSubmitChaiJieInfo.tvAddLingjianNum = (TextView) convertView.findViewById(R.id.tv_add_lingjian_num);
                    viewHolderOfNotSubmitChaiJieInfo.tvSubLingjianNum = (TextView) convertView.findViewById(R.id.tv_sub_lingjian_num);

                    convertView.setTag(viewHolderOfNotSubmitChaiJieInfo);

                    break;
                case TYPE_OF_HAS_SUBMIT_CAIJIE_INFO:

                    convertView = mLayoutInflater.inflate(R.layout.list_item_has_finished_chaijie_lingjian, null);
                    viewHolderOfHasSubmitChaiJieInfo = new ViewHolderOfHasSubmitChaiJieInfo();

                    viewHolderOfHasSubmitChaiJieInfo.tvLingjianName = (TextView) convertView.findViewById(R.id.tv_lingjian_name);
                    viewHolderOfHasSubmitChaiJieInfo.tvLingjianNum = (TextView)convertView. findViewById(R.id.tv_lingjian_num);
                    viewHolderOfHasSubmitChaiJieInfo.gvLingjianPic = (GridViewForScrollView)convertView. findViewById(R.id.gv_lingjian_pic);

                    convertView.setTag(viewHolderOfHasSubmitChaiJieInfo);

                    break;
            }

        } else {
            switch (itemType)
            {
                case TYPE_OF_NOT_SUBMIT_CAIJIE_INFO:

                    viewHolderOfNotSubmitChaiJieInfo = (ViewHolderOfNotSubmitChaiJieInfo) convertView.getTag();
                    break;
                case TYPE_OF_HAS_SUBMIT_CAIJIE_INFO:
                    viewHolderOfHasSubmitChaiJieInfo = (ViewHolderOfHasSubmitChaiJieInfo) convertView.getTag();
                    break;
            }
        }

        ChaiJieDetailInfo.PartlistBean partItem = mPartInfoList.get(i);


        switch (itemType)
        {
            case TYPE_OF_NOT_SUBMIT_CAIJIE_INFO:

                viewHolderOfNotSubmitChaiJieInfo.tvLingjianName.setText(partItem.getPartname());
                viewHolderOfNotSubmitChaiJieInfo.tvLingjianNum.setText(partItem.getOutputcount());

                if (CommonUtil.isListNullOrEmpty(partItem.getImgattachlist())==false)
                {
                    viewHolderOfNotSubmitChaiJieInfo.gvLingjianPic.setVisibility(View.VISIBLE);

                    GridAdapterForJingChaiLingJianPic lingJianPicAdapter = new GridAdapterForJingChaiLingJianPic(partItem.getImgattachlist(),mContext,i,true);
                    viewHolderOfNotSubmitChaiJieInfo.gvLingjianPic.setAdapter(lingJianPicAdapter);
                    viewHolderOfNotSubmitChaiJieInfo.gvLingjianPic.setVisibility(View.VISIBLE);
                    if (null!= lingJianPicClickListener)
                    {
                        lingJianPicAdapter.setGridPicClickListener(lingJianPicClickListener);
                    }

                    if (null!= lingJianPicDeleteClickListener)
                    {
                        lingJianPicAdapter.setGridPicDeleteClickListener(lingJianPicDeleteClickListener);
                    }
                }else {
                    viewHolderOfNotSubmitChaiJieInfo.gvLingjianPic.setVisibility(View.GONE);
                }

                viewHolderOfNotSubmitChaiJieInfo.tvSubmitLingjianChaijieinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null != toSubmitLingJianChaiJieInfoTvClickListener)
                        {
                            toSubmitLingJianChaiJieInfoTvClickListener.onToSubmitLingJianChaiJieInfoTvClick(i);
                        }
                    }
                });


                viewHolderOfNotSubmitChaiJieInfo.tvToTakePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null != toTakeLingJianPhotoTvClickListener)
                        {
                            toTakeLingJianPhotoTvClickListener.onToTakeLingJianPhotoClick(i);
                        }
                    }
                });

                viewHolderOfNotSubmitChaiJieInfo.tvAddLingjianNum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null!=addLingJianNumCliclListener)
                        {
                            addLingJianNumCliclListener.onAddLingJianNumClick(i);
                        }
                    }
                });
                viewHolderOfNotSubmitChaiJieInfo.tvSubLingjianNum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null!=subLingJianNumClickListener)
                        {
                            subLingJianNumClickListener.onSubLingJianNumClick(i);
                        }
                    }
                });

                break;
            case TYPE_OF_HAS_SUBMIT_CAIJIE_INFO:


                viewHolderOfHasSubmitChaiJieInfo.tvLingjianName.setText(partItem.getPartname());
                viewHolderOfHasSubmitChaiJieInfo.tvLingjianNum.setText(String.valueOf(partItem.getOutputcount()));


                if (CommonUtil.isListNullOrEmpty(partItem.getImgattachlist())==false)
                {

                    GridAdapterForJingChaiLingJianPic lingJianPicAdapter = new GridAdapterForJingChaiLingJianPic(partItem.getImgattachlist(),mContext,i,false);
                    viewHolderOfHasSubmitChaiJieInfo.gvLingjianPic.setAdapter(lingJianPicAdapter);
                    viewHolderOfHasSubmitChaiJieInfo.gvLingjianPic.setVisibility(View.VISIBLE);
                    if (null!= lingJianPicClickListener)
                    {
                        lingJianPicAdapter.setGridPicClickListener(lingJianPicClickListener);
                    }

                }else {
                    viewHolderOfHasSubmitChaiJieInfo.gvLingjianPic.setVisibility(View.GONE);
                }
                break;
        }





        return convertView;

    }


    private static class ViewHolderOfNotSubmitChaiJieInfo{
        private TextView tvLingjianName;
        private TextView tvSubmitLingjianChaijieinfo;
        private TextView tvToTakePhoto;
        private GridViewForScrollView gvLingjianPic;
        private TextView tvAddLingjianNum;
        private TextView tvLingjianNum;
        private TextView tvSubLingjianNum;

    }


    private static class ViewHolderOfHasSubmitChaiJieInfo{
        private TextView tvLingjianName;
        private TextView tvLingjianNum;
        private GridViewForScrollView gvLingjianPic;

    }


    public interface ToTakeLingJianPhotoTvClickListener{
        public void onToTakeLingJianPhotoClick(int position);
    }

    public interface ToSubmitLingJianChaiJieInfoTvClickListener{

        public void onToSubmitLingJianChaiJieInfoTvClick(int position);
    }



    public interface AddLingJianNumCliclListener{
        public void onAddLingJianNumClick(int position);
    }

    public interface SubLingJianNumClickListener{
        public void onSubLingJianNumClick(int position);
    }


   /* public interface LingJianNumInputListener{
        public void onAfterLingJianNumInput(int lingJianPosition ,int inputNum);
    }*/

}
