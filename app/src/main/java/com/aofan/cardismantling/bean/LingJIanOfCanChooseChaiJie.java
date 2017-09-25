package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**汽车客运选择去拆解的零件信息
 * Created by Administrator on 2016/11/27.
 */

public class LingJIanOfCanChooseChaiJie implements Serializable{

    /**
     * pid : 222
     * partid : 224
     * partname : 发动机
     * type : 发动机总成
     */

    private String pid;
    private String partid;
    private String partname;
    private String type;

    private boolean isCheck;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPartid() {
        return partid;
    }

    public void setPartid(String partid) {
        this.partid = partid;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "LingJIanOfCanChooseChaiJie{" +
                "isCheck=" + isCheck +
                ", pid='" + pid + '\'' +
                ", partid='" + partid + '\'' +
                ", partname='" + partname + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
