package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**派工单item
 * Created by Administrator on 2016/11/27.
 */

public class PaiGongDanitem implements Serializable {


    /**
     * oid : 12
     * did : 46
     * partnamelist : 发动机,启动机,发电机,电脑板,钢圈,前后轮胎,前桥,车门,引擎盖,前箱板
     * dispatchperson : 1000demo
     * dispatchtime : 0000-00-00 00:00:00
     * state : 待拆解
     * createtime : 2016-11-27 10:37:53
     */

    private String oid;
    private String did;
    private String partnamelist;
    private String dispatchperson;
    private String dispatchtime;
    private String state;
    private String createtime;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getPartnamelist() {
        return partnamelist;
    }

    public void setPartnamelist(String partnamelist) {
        this.partnamelist = partnamelist;
    }

    public String getDispatchperson() {
        return dispatchperson;
    }

    public void setDispatchperson(String dispatchperson) {
        this.dispatchperson = dispatchperson;
    }

    public String getDispatchtime() {
        return dispatchtime;
    }

    public void setDispatchtime(String dispatchtime) {
        this.dispatchtime = dispatchtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "PaiGongDanitem{" +
                "createtime='" + createtime + '\'' +
                ", oid='" + oid + '\'' +
                ", did='" + did + '\'' +
                ", partnamelist='" + partnamelist + '\'' +
                ", dispatchperson='" + dispatchperson + '\'' +
                ", dispatchtime='" + dispatchtime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
