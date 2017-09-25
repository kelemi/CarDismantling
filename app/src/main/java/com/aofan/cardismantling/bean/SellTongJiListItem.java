package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/5.
 */

public class SellTongJiListItem implements Serializable {


    /**
     * staues : sucess
     * message : 获取成功
     * data : [{"partid":"473","partname":"发动机","price":"0.00","sellprice":"0.00","selltime":"2017-01-05 05:31:00","seller":"zs"},{"partid":"473","partname":"发动机","price":"0.00","sellprice":"0.00","selltime":"2017-01-06 10:30:00","seller":"zs"}]
     * sumprice : 0.00
     * totalount : 2
     */

    /**
     * partid : 473
     * partname : 发动机
     * price : 0.00
     * sellprice : 0.00
     * selltime : 2017-01-05 05:31:00
     * seller : zs
     */

    private String partid;
    private String partname;
    private String price;
    private String sellprice;
    private String selltime;
    private String seller;

    public String getPartid() {
        return partid;
    }

    public void setPartid(String partid) {
        this.partid = partid;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public String getSelltime() {
        return selltime;
    }

    public void setSelltime(String selltime) {
        this.selltime = selltime;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }


    @Override
    public String toString() {
        return "SellTongJiListItem{" +
                "partid='" + partid + '\'' +
                ", partname='" + partname + '\'' +
                ", price='" + price + '\'' +
                ", sellprice='" + sellprice + '\'' +
                ", selltime='" + selltime + '\'' +
                ", seller='" + seller + '\'' +
                '}';
    }
}
