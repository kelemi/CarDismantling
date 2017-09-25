package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 汽车信息
 * Created by Administrator on 2016/11/24.
 */

public class CarInfo implements Serializable {


    /**
     * baseid : 126
     * vehiclenumber : 苏A223565
     * vehicletype : 小型普通客车
     * brandmodel : 555666
     * registerdate : 2016-11-18 00:00:00
     * caricon : null
     * state : 已注销
     */

    private String baseid;
    private String vehiclenumber;
    private String vehicletype;
    private String brandmodel;
    private String registerdate;
    private String caricon;
    private String state;

    public String getBaseid() {
        return baseid;
    }

    public void setBaseid(String baseid) {
        this.baseid = baseid;
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

    public String getBrandmodel() {
        return brandmodel;
    }

    public void setBrandmodel(String brandmodel) {
        this.brandmodel = brandmodel;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getCaricon() {
        return caricon;
    }

    public void setCaricon(String caricon) {
        this.caricon = caricon;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "baseid='" + baseid + '\'' +
                ", vehiclenumber='" + vehiclenumber + '\'' +
                ", vehicletype='" + vehicletype + '\'' +
                ", brandmodel='" + brandmodel + '\'' +
                ", registerdate='" + registerdate + '\'' +
                ", caricon='" + caricon + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
