package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**派工单中的车的信息
 * Created by Administrator on 2016/11/27.
 */

public class CarOfPaiGongDan implements Serializable {
    /**
     * staues : sucess
     * message : 获取成功
     * data : [{"did":"99","baseid":"15903","vehiclenumber":"ZS55555","type":"粗拆","cjtype":"粗拆","vehicletype":"轿车","brandmodel":"555","allocat":"已分析","registerdate":"2017-03-02 00:00:00","caricon":null,"createtime":"2017-03-02 17:58:59","hasparts":false},{"did":"90","baseid":"15247","vehiclenumber":"2323","type":"精拆","cjtype":"精拆","vehicletype":"轿车","brandmodel":"3232","allocat":"已分析","registerdate":"2017-02-28 00:00:00","caricon":null,"createtime":"2017-03-02 16:17:20","hasparts":true},{"did":"89","baseid":"15248","vehiclenumber":"232","type":"精拆","cjtype":"精拆","vehicletype":"轿车","brandmodel":"323232","allocat":"已分析","registerdate":"2017-02-17 00:00:00","caricon":null,"createtime":"2017-03-02 16:14:35","hasparts":true}]
     */




    /**
     * staues : sucess
     * message : 获取成功
     * data : [{"did":"56","baseid":"8312","vehiclenumber":"3434","type":"粗拆","cjtype":"粗拆","vehicletype":"轿车","brandmodel":"3434","allocat":"已分析","registerdate":"2017-01-13 00:00:00","caricon":null,"createtime":"2017-02-07 10:36:12"},{"did":"55","baseid":"10801","vehiclenumber":"454654","type":"粗拆","cjtype":"粗拆","vehicletype":"轿车","brandmodel":"2323","allocat":"已分析","registerdate":"2017-02-07 00:00:00","caricon":null,"createtime":"2017-02-07 10:27:32"},{"did":"54","baseid":"10802","vehiclenumber":"测试粗拆","type":"粗拆","cjtype":"粗拆","vehicletype":"轿车","brandmodel":"343434","allocat":"已分析","registerdate":"2017-02-15 00:00:00","caricon":null,"createtime":"2017-02-07 10:14:57"},{"did":"53","baseid":"1014","vehiclenumber":"12","type":"粗拆","cjtype":"粗拆","vehicletype":"轿车","brandmodel":"12","allocat":"已分析","registerdate":"2016-12-15 00:00:00","caricon":null,"createtime":"2017-02-07 09:56:52"},{"did":"37","baseid":"2806","vehiclenumber":"1234124","type":"精拆","cjtype":"","vehicletype":"轿车","brandmodel":"宝马1234124","allocat":"已分析","registerdate":"2016-12-21 00:00:00","caricon":null,"createtime":"2017-01-03 16:31:24"}]
     */

    /*private String did;
    private String baseid;
    private String vehiclenumber;
    private String type;
    private String cjtype;
    private String vehicletype;
    private String brandmodel;
    private String allocat;
    private String registerdate;
    private String caricon;
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

    public String getCaricon() {
        return caricon;
    }

    public void setCaricon(String caricon) {
        this.caricon = caricon;
    }

    public String getCjtype() {
        return cjtype;
    }

    public void setCjtype(String cjtype) {
        this.cjtype = cjtype;
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
        return "CarOfPaiGongDan{" +
                "allocat='" + allocat + '\'' +
                ", did='" + did + '\'' +
                ", baseid='" + baseid + '\'' +
                ", vehiclenumber='" + vehiclenumber + '\'' +
                ", type='" + type + '\'' +
                ", cjtype='" + cjtype + '\'' +
                ", vehicletype='" + vehicletype + '\'' +
                ", brandmodel='" + brandmodel + '\'' +
                ", registerdate='" + registerdate + '\'' +
                ", caricon='" + caricon + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }*/


    private String did;
    private String baseid;
    private String vehiclenumber;
    private String type;
    private String cjtype;
    private String vehicletype;
    private String brandmodel;
    private String allocat;
    private String registerdate;
    private String caricon;
    private String createtime;
    private boolean hasparts;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCjtype() {
        return cjtype;
    }

    public void setCjtype(String cjtype) {
        this.cjtype = cjtype;
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

    public String getAllocat() {
        return allocat;
    }

    public void setAllocat(String allocat) {
        this.allocat = allocat;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public boolean isHasparts() {
        return hasparts;
    }

    public void setHasparts(boolean hasparts) {
        this.hasparts = hasparts;
    }


    @Override
    public String toString() {
        return "CarOfPaiGongDan{" +
                "allocat='" + allocat + '\'' +
                ", did='" + did + '\'' +
                ", baseid='" + baseid + '\'' +
                ", vehiclenumber='" + vehiclenumber + '\'' +
                ", type='" + type + '\'' +
                ", cjtype='" + cjtype + '\'' +
                ", vehicletype='" + vehicletype + '\'' +
                ", brandmodel='" + brandmodel + '\'' +
                ", registerdate='" + registerdate + '\'' +
                ", caricon=" + caricon +
                ", createtime='" + createtime + '\'' +
                ", hasparts=" + hasparts +
                '}';
    }
}
