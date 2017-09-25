package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**已完成拆解任务item
 * Created by Administrator on 2016/11/29.
 */

public class HasDoChaiJieTaskItem implements Serializable {


    /**
     * oid : 7
     * partnamelist : 发动机
     * vehiclenumber : 苏HEZ792
     * requirement : 测试
     * dispatchperson : 10000_leo
     * createtime : 2016-11-29 09:13:25
     * type : 已完成拆解任务
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
        return "HasDoChaiJieTaskItem{" +
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
