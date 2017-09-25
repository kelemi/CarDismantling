package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**拆解任务list item
 * Created by Administrator on 2016/11/28.
 */

public class JobWaitToDoChaiJieTaskItem implements Serializable {

    /**
     * oid : 22
     * partnamelist : 发动机,发电机,启动机,前后轮胎,钢圈,前桥
     * vehiclenumber : 苏H223989
     * requirement : 测试拆解要求111
     * dispatchperson : 10000_leo,1000_dyw
     * createtime : 2016-11-28 09:56:10
     * type : 待拆解任务
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
        return "JobWaitToDoChaiJieTaskItem{" +
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
