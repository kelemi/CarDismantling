package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**版本信息
 * Created by Administrator on 2016/11/28.
 */

public class VersionInfo implements Serializable {


    /**
     * id : 1
     * versionname : 1.0.0
     * versioncode : 1
     * downurl :
     * versioninf : v1.0.0
     * createtime : 2016-11-04 00:00:00
     */

    private String id;
    private String versionname;
    private String versioncode;
    private String downurl;
    private String versioninf;
    private String createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public String getVersioninf() {
        return versioninf;
    }

    public void setVersioninf(String versioninf) {
        this.versioninf = versioninf;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    @Override
    public String toString() {
        return "VersionInfo{" +
                "createtime='" + createtime + '\'' +
                ", id='" + id + '\'' +
                ", versionname='" + versionname + '\'' +
                ", versioncode='" + versioncode + '\'' +
                ", downurl='" + downurl + '\'' +
                ", versioninf='" + versioninf + '\'' +
                '}';
    }
}
