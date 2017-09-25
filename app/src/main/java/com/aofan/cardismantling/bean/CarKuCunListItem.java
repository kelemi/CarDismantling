package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**汽车库存数据item
 * Created by Administrator on 2016/11/30.
 */

public class CarKuCunListItem implements Serializable {


    /**
     * baseid : 141
     * vehiclenumber : 苏HEZ792
     * vehicletype : 重型普通货车
     * brandmodel : 雪佛兰牌SGK7202EAAB
     * registerdate : 2015-01-26 00:00:00
     * caricon : null
     * type : 精拆
     * did : 9
     */
    private String baseid;
    private String vehiclenumber;
    private String vehicletype;
    private String brandmodel;
    private String registerdate;
    private String caricon;
    private String type;
    private String did;

    public String getBaseid() {
        return baseid;
    }

    public void setBaseid(String baseid) {
        this.baseid = baseid;
    }

    public String getBrandmodel() {
        return brandmodel;
    }

    public void setBrandmodel(String brandmodel) {
        this.brandmodel = brandmodel;
    }

    public String getCaricon() {
        return caricon;
    }

    public void setCaricon(String caricon) {
        this.caricon = caricon;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }


    @Override
    public String toString() {
        return "CarKuCunListItem{" +
                "baseid='" + baseid + '\'' +
                ", vehiclenumber='" + vehiclenumber + '\'' +
                ", vehicletype='" + vehicletype + '\'' +
                ", brandmodel='" + brandmodel + '\'' +
                ", registerdate='" + registerdate + '\'' +
                ", caricon='" + caricon + '\'' +
                ", type='" + type + '\'' +
                ", did='" + did + '\'' +
                '}';
    }
}
