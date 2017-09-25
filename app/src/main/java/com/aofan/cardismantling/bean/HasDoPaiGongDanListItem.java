package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**已经确认的派工单的listitem
 * Created by Administrator on 2016/11/29.
 */

public class HasDoPaiGongDanListItem implements Serializable {


    /**
     * oid : 1
     * partnamelist : 发动机
     * vehiclenumber : 苏H77777777
     * requirement : 测试
     * dispatchperson : 10000_leo
     * createtime : 2016-11-29 03:36:46
     * type : 已确认派工单
     */

    private String oid;
    private String partnamelist;
    private String vehiclenumber;
    private String requirement;
    private String dispatchperson;
    private String createtime;
    private String type;

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

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getDispatchperson() {
        return dispatchperson;
    }

    public void setDispatchperson(String dispatchperson) {
        this.dispatchperson = dispatchperson;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "HasDoPaiGongDanListItem{" +
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
