package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public class UserInfo implements Serializable {


    /**
     * id : 71
     * email :
     * encrypted_password : 14e1b600b1fd579f47433b88e8d85291
     * reset_password_token : null
     * reset_password_sent_at : null
     * remember_created_at : null
     * sign_in_count : 0
     * current_sign_in_at : null
     * last_sign_in_at : null
     * current_sign_in_ip : null
     * last_sign_in_ip : null
     * created_at : 2016-11-23 02:57:17
     * updated_at : 2016-11-23 02:57:17
     * username : 10000_leo
     * tel : 15161752615
     * zfmm : null
     * sfz : null
     * shen : null
     * city : null
     * qu : null
     * addr : null
     * sfzzm : null
     * sfzfm : null
     * mdhz : null
     * usertype : null
     * stop : null
     * city_id : null
     * tp : null
     * manager_id : null
     * mentor_id : null
     * bzlx : null
     * yyzz : null
     * jf : null
     * tjf : null
     * tjp : null
     * bmm : null
     * longitude : null
     * latitude : null
     * istg : null
     * tjr : null
     * departmentid : 08829fce-ed67-4106-9159-934f2747cd39
     * manufactureid : 3
     * myname : 厉开亮
     * sex : 男
     * sort : null
     */

    private String id;
    private String email;
    private String created_at;
    private String updated_at;
    private String username;
    private String tel;
    private String departmentid;
    private String manufactureid;
    private String myname;
    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getManufactureid() {
        return manufactureid;
    }

    public void setManufactureid(String manufactureid) {
        this.manufactureid = manufactureid;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "created_at='" + created_at + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", username='" + username + '\'' +
                ", tel='" + tel + '\'' +
                ", departmentid='" + departmentid + '\'' +
                ", manufactureid='" + manufactureid + '\'' +
                ", myname='" + myname + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
