package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**零件库存list item
 * Created by Administrator on 2016/11/30.
 */

public class LingJianKuCunListItem implements Serializable {

    /**
     * partid : 474
     * partname : 发电机
     * price : 0.00
     * sellprice : 0.00
     * selltime : null
     * seller : null
     * createperson : zs
     * vehiclenumber : 3434
     */

    private String partid;
    private String partname;
    private String price;
    private String sellprice;
    private String selltime;
    private String seller;
    private String createperson;
    private String vehiclenumber;
    private String createtime;

    public String getCreateperson() {
        return createperson;
    }

    public void setCreateperson(String createperson) {
        this.createperson = createperson;
    }

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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    @Override
    public String toString() {
        return "LingJianKuCunListItem{" +
                "createperson='" + createperson + '\'' +
                ", partid='" + partid + '\'' +
                ", partname='" + partname + '\'' +
                ", price='" + price + '\'' +
                ", sellprice='" + sellprice + '\'' +
                ", selltime='" + selltime + '\'' +
                ", seller='" + seller + '\'' +
                ", vehiclenumber='" + vehiclenumber + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
