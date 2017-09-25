package com.aofan.cardismantling.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.MenuItem;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.Res;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class RecycleAdapterForIndexMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<MenuItem> mMenuItemList;
    Context context;

    MenuItemClickListener menuItemClickListener;

    public MenuItemClickListener getMenuItemClickListener() {
        return menuItemClickListener;
    }

    public void setMenuItemClickListener(MenuItemClickListener menuItemClickListener) {
        this.menuItemClickListener = menuItemClickListener;
    }

    public RecycleAdapterForIndexMenu(List<MenuItem> mMenuItemList, Context context) {
        this.mMenuItemList = mMenuItemList;
        this.context = context;
    }

    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_menu_item, parent, false);
        MenuItemViewHolder viewHolder = new MenuItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MenuItemViewHolder viewHolder = (MenuItemViewHolder) holder;

        MenuItem menuItem = mMenuItemList.get(position);

        viewHolder.tv.setText(menuItem.getName());

        if (TextUtils.isEmpty(menuItem.getIcon())==false)
        {
            Picasso.with(context).load(menuItem.getIcon())
                    .placeholder(R.drawable.ico_default_circle)
                    .error(R.drawable.ico_default_circle)

                    .into(viewHolder.iv);
        }else {
            Picasso.with(context).load(menuItem.getLocalIconResId())
                    .placeholder(R.drawable.ico_default_circle)
                    .error(R.drawable.ico_default_circle)
                    .into(viewHolder.iv);
        }



        viewHolder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null!=menuItemClickListener)
                {
                    menuItemClickListener.onMenuItemClick(position);
                }
            }
        });

         /* SalerKindItem salerKindItem = mSalerKindItemList.get(position);
        holder.tv.setText(salerKindItem.getMyTexts());*/
        /*Picasso.with(context).load(Config.PIC_BASE_URL+salerKindItem.getIcon())
                .placeholder(R.drawable.ico_default)
                .error(R.drawable.ico_default)
                .resize(80,80)
                .into();*/

       /* Picasso.with(context).load(HttpService.BASE_URL_IN_USE )
                .placeholder(R.drawable.ico_default_circle)
                .error(R.drawable.ico_default_circle)
                .resize(Res.getDimen(R.dimen.menu_item_pic_size),Res.getDimen(R.dimen.menu_item_pic_size))
                .into(holder.iv);*/
/*
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=menuItemClickListener)
                {
                    menuItemClickListener.onMenuItemClick(position);
                }
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return mMenuItemList.size();
    }


    class MenuItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parentlayout;
        TextView tv;
        ImageView iv;

        public MenuItemViewHolder(View view) {
            super(view);
            parentlayout = (RelativeLayout) view.findViewById(R.id.parentlayout);
            iv = (ImageView) view.findViewById(R.id.iv);
            tv = (TextView) view.findViewById(R.id.nametv);
        }
    }


    public interface MenuItemClickListener{
        public void onMenuItemClick(int position);
    }


}

