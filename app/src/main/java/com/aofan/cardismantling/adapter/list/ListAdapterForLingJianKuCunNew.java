package com.aofan.cardismantling.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.bean.LingJianKuCunListItem;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public class ListAdapterForLingJianKuCunNew extends BaseAdapter {



    List<LingJianKuCunListItem> mLingJianList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    String mLingJianState;

    //商品类型  待销售，已销售，大宗商品
    private final static int TYPE_WAIT_SELL = 0;
    private final static int TYPE_HAS_SELLED = 1;
    private final static int TYPE_DAZONG_SHANGPIN = 2;

    public ListAdapterForLingJianKuCunNew(Context context, List<LingJianKuCunListItem> lingJianList,String lingJianState) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mLingJianList = lingJianList;
        mLingJianState = lingJianState;
    }

    @Override
    public int getItemViewType(int position) {

        int type = TYPE_WAIT_SELL;
        switch (mLingJianState)
        {
            case "待销售":
                type = TYPE_WAIT_SELL;
                break;

            case "已销售":
                type = TYPE_HAS_SELLED;
                break;

            case "大宗商品":
                type = TYPE_DAZONG_SHANGPIN;

                break;
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolderForLingJianWaitSell viewHolderForLingJianWaitSell = null;
        ViewHolderForLingJianHasSelled viewHolderForLingJianHasSelled = null;
        ViewHolderForLingJianDaZongShangPin viewHolderForLingJianDaZongShangPin = null;

        int type = getItemViewType(i);


        if (view == null)
        {
            switch (type)
            {
                case TYPE_WAIT_SELL:

                    view = mLayoutInflater.inflate(R.layout.list_item_lingjian_kucun_wait_selled,null);
                    viewHolderForLingJianWaitSell = new ViewHolderForLingJianWaitSell();

                    viewHolderForLingJianWaitSell.tvLingjianName = (TextView) view.findViewById(R.id.tv_lingjian_name);
                    viewHolderForLingJianWaitSell.tvLingjianSourseCar = (TextView) view.findViewById(R.id.tv_lingjian_sourse_car);
                    viewHolderForLingJianWaitSell.tvLingjianChaijiePerson = (TextView) view.findViewById(R.id.tv_lingjian_chaijie_person);
                    viewHolderForLingJianWaitSell.tvLingjianChaijieTime = (TextView) view.findViewById(R.id.tv_lingjian_chaijie_time);

                    view.setTag(viewHolderForLingJianWaitSell);

                    break;
                case TYPE_HAS_SELLED:

                    view = mLayoutInflater.inflate(R.layout.list_item_lingjian_kucun_has_selled,null);
                    viewHolderForLingJianHasSelled = new ViewHolderForLingJianHasSelled();

                    viewHolderForLingJianHasSelled.tvLingjianName = (TextView) view.findViewById(R.id.tv_lingjian_name);
                    viewHolderForLingJianHasSelled.tvLingjianSellPrice = (TextView) view.findViewById(R.id.tv_lingjian_sell_price);
                    viewHolderForLingJianHasSelled.tvLingjianSeller = (TextView) view.findViewById(R.id.tv_lingjian_seller);
                    viewHolderForLingJianHasSelled.tvLingjianSellTime = (TextView) view.findViewById(R.id.tv_lingjian_sell_time);

                    view.setTag(viewHolderForLingJianHasSelled);

                    break;
                case TYPE_DAZONG_SHANGPIN:

                    view = mLayoutInflater.inflate(R.layout.list_item_lingjian_kucun_dazongshangpin,null);
                    viewHolderForLingJianDaZongShangPin = new ViewHolderForLingJianDaZongShangPin();

                    viewHolderForLingJianDaZongShangPin.tvLingjianName = (TextView) view.findViewById(R.id.tv_lingjian_name);
                    viewHolderForLingJianDaZongShangPin.tvLingjianSourseCar = (TextView) view.findViewById(R.id.tv_lingjian_sourse_car);
                    viewHolderForLingJianDaZongShangPin.tvLingjianChaijiePerson = (TextView) view.findViewById(R.id.tv_lingjian_chaijie_person);
                    viewHolderForLingJianDaZongShangPin.tvLingjianChaijieTime = (TextView) view.findViewById(R.id.tv_lingjian_chaijie_time);

                    view.setTag(viewHolderForLingJianHasSelled);

                    break;

            }
        }else {
            switch (type)
            {
                case TYPE_WAIT_SELL:

                    viewHolderForLingJianWaitSell = (ViewHolderForLingJianWaitSell) view.getTag();

                    break;
                case TYPE_HAS_SELLED:

                    viewHolderForLingJianHasSelled = (ViewHolderForLingJianHasSelled) view.getTag();

                    break;
                case TYPE_DAZONG_SHANGPIN:

                    viewHolderForLingJianDaZongShangPin = (ViewHolderForLingJianDaZongShangPin) view.getTag();

                    break;

            }
        }

        LingJianKuCunListItem lingJianKuCunItem = mLingJianList.get(i);
        switch (type)
        {
            case TYPE_WAIT_SELL:

                viewHolderForLingJianWaitSell.tvLingjianName.setText(lingJianKuCunItem.getPartname());
                viewHolderForLingJianWaitSell.tvLingjianSourseCar.setText("来源汽车车牌:"+lingJianKuCunItem.getVehiclenumber());
                viewHolderForLingJianWaitSell.tvLingjianChaijiePerson.setText("拆解人:"+lingJianKuCunItem.getCreateperson());
                viewHolderForLingJianWaitSell.tvLingjianChaijieTime.setText("拆解时间:"+lingJianKuCunItem.getCreatetime());

                break;
            case TYPE_HAS_SELLED:

                viewHolderForLingJianHasSelled.tvLingjianName.setText(lingJianKuCunItem.getPartname());
                viewHolderForLingJianHasSelled.tvLingjianSeller.setText("销售员:"+lingJianKuCunItem.getSeller());
                viewHolderForLingJianHasSelled.tvLingjianSellPrice.setText("售价:"+lingJianKuCunItem.getSellprice());
                viewHolderForLingJianHasSelled.tvLingjianSellTime.setText("销售时间:"+lingJianKuCunItem.getSelltime());


                break;
            case TYPE_DAZONG_SHANGPIN:

                viewHolderForLingJianDaZongShangPin.tvLingjianName.setText(lingJianKuCunItem.getPartname());
                viewHolderForLingJianDaZongShangPin.tvLingjianSourseCar.setText("来源汽车车牌:"+lingJianKuCunItem.getVehiclenumber());
                viewHolderForLingJianDaZongShangPin.tvLingjianChaijiePerson.setText("拆解人:"+lingJianKuCunItem.getCreateperson());
                viewHolderForLingJianDaZongShangPin.tvLingjianChaijieTime.setText("拆解时间:"+lingJianKuCunItem.getCreatetime());

                break;

        }


        return view;
    }


    static class ViewHolderForLingJianWaitSell{
        private TextView tvLingjianName;
        private TextView tvLingjianSourseCar;
        private TextView tvLingjianChaijiePerson;
        private TextView tvLingjianChaijieTime;

        /*tvLingjianName = (TextView) findViewById(R.id.tv_lingjian_name);
        tvLingjianSourseCar = (TextView) findViewById(R.id.tv_lingjian_sourse_car);
        tvLingjianChaijiePerson = (TextView) findViewById(R.id.tv_lingjian_chaijie_person);
        tvLingjianChaijieTime = (TextView) findViewById(R.id.tv_lingjian_chaijie_time);*/

    }

    static class ViewHolderForLingJianHasSelled{

        private TextView tvLingjianName;
        private TextView tvLingjianSellPrice;
        private TextView tvLingjianSeller;
        private TextView tvLingjianSellTime;

        /*tvLingjianName = (TextView) findViewById(R.id.tv_lingjian_name);
        tvLingjianSellPrice = (TextView) findViewById(R.id.tv_lingjian_sell_price);
        tvLingjianSeller = (TextView) findViewById(R.id.tv_lingjian_seller);
        tvLingjianSellTime = (TextView) findViewById(R.id.tv_lingjian_sell_time);*/


    }

    static class ViewHolderForLingJianDaZongShangPin{
        private TextView tvLingjianName;
        private TextView tvLingjianSourseCar;
        private TextView tvLingjianChaijiePerson;
        private TextView tvLingjianChaijieTime;

        /*tvLingjianName = (TextView) findViewById(R.id.tv_lingjian_name);
        tvLingjianSourseCar = (TextView) findViewById(R.id.tv_lingjian_sourse_car);
        tvLingjianChaijiePerson = (TextView) findViewById(R.id.tv_lingjian_chaijie_person);
        tvLingjianChaijieTime = (TextView) findViewById(R.id.tv_lingjian_chaijie_time);*/
    }
}
