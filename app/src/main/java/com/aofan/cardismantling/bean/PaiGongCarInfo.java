package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**派工的车的信息
 * Created by Administrator on 2016/11/25.
 */

public class PaiGongCarInfo implements Serializable {


    /**
     * did : 17
     * baseid : 2
     * vehiclenumber : 苏H6665
     * type : 精拆
     * vehicletype : 轿车
     * brandmodel : 沃尔沃
     * registerdate : 2015-07-10 00:00:00
     * caricon :
     * createtime : 2016-11-02 00:00:00
     */

    private String did;
    private String baseid;
    private String vehiclenumber;
    private String type;
    private String vehicletype;
    private String brandmodel;
    private String registerdate;
    private String caricon;
    private String createtime;


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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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
        return "PaiGongCarInfo{" +
                "baseid='" + baseid + '\'' +
                ", did='" + did + '\'' +
                ", vehiclenumber='" + vehiclenumber + '\'' +
                ", type='" + type + '\'' +
                ", vehicletype='" + vehicletype + '\'' +
                ", brandmodel='" + brandmodel + '\'' +
                ", registerdate='" + registerdate + '\'' +
                ", caricon='" + caricon + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
