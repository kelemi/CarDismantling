package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**确认派工单列表item
 * Created by Administrator on 2016/11/29.
 */

public class PaiGongDanOfEnsure implements Serializable {

    /**
     * oid : 4
     * partnamelist : 发动机
     * vehiclenumber : 苏A223565
     * requirement : 测试测试
     * dispatchperson : 10000_leo
     * createtime : 2016-11-29 05:01:27
     * type : 待确认派工单
     */

    private String oid;
    private String partnamelist;
    private String vehiclenumber;
    private String requirement;
    private String dispatchperson;
    private String createtime;
    private String type;


    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDispatchperson() {
        return dispatchperson;
    }

    public void setDispatchperson(String dispatchperson) {
        this.dispatchperson = dispatchperson;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPartnamelist() {
        return partnamelist;
    }

    public void setPartnamelist(String partnamelist) {
        this.partnamelist = partnamelist;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
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


    @Override
    public String toString() {
        return "PaiGongDanOfEnsure{" +
                "createtime='" + createtime + '\'' +
                ", oid='" + oid + '\'' +
                ", partnamelist='" + partnamelist + '\'' +
                ", vehiclenumber='" + vehiclenumber + '\'' +
                ", requirement='" + requirement + '\'' +
                ", dispatchperson='" + dispatchperson + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
