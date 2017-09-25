package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/22.
 */

public class LoginUser implements Serializable {


    /**
     * uid : 64
     * username : 1000demo
     * roleid : null
     * departname : null
     * manufacture : null
     */

    private String uid;
    private String username;
    private String roleid;
    private String departname;
    private String manufacture;


    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "departname='" + departname + '\'' +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", roleid='" + roleid + '\'' +
                ", manufacture='" + manufacture + '\'' +
                '}';
    }
}
