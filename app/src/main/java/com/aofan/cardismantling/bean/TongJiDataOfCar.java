package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**统计中车辆数据
 * Created by Administrator on 2016/11/30.
 */

public class TongJiDataOfCar implements Serializable {


    /**
     * baseid : 142
     * vehiclenumber : 苏H77777777
     * vehicletype : 普通货车
     * brandmodel : 335567
     * registerdate : 2016-11-08 00:00:00
     * type : 精拆
     * did : 1
     * allocat : 拆解中
     * caijietime : null
     * createtime : 2016-11-29 03:21:10
     */

    private String baseid;
    private String vehiclenumber;
    private String vehicletype;
    private String brandmodel;
    private String registerdate;
    private String type;
    private String did;
    private String allocat;
    private String caijietime;
    private String createtime;

    public String getAllocat() {
        return allocat;
    }

    public void setAllocat(String allocat) {
        this.allocat = allocat;
    }

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

    public String getCaijietime() {
        return caijietime;
    }

    public void setCaijietime(String caijietime) {
        this.caijietime = caijietime;
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
        return "TongJiDataOfCar{" +
                "allocat='" + allocat + '\'' +
                ", baseid='" + baseid + '\'' +
                ", vehiclenumber='" + vehiclenumber + '\'' +
                ", vehicletype='" + vehicletype + '\'' +
                ", brandmodel='" + brandmodel + '\'' +
                ", registerdate='" + registerdate + '\'' +
                ", type='" + type + '\'' +
                ", did='" + did + '\'' +
                ", caijietime='" + caijietime + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
