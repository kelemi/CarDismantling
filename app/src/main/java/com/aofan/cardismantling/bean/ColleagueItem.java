package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public class ColleagueItem implements Serializable {


    /**
     * message : 获取成功
     * staues : sucess
     * TotalCount : 4
     * data : [{"id":"71","email":"","encrypted_password":"14e1b600b1fd579f47433b88e8d85291","reset_password_token":null,"reset_password_sent_at":null,"remember_created_at":null,"sign_in_count":"0","current_sign_in_at":null,"last_sign_in_at":null,"current_sign_in_ip":null,"last_sign_in_ip":null,"created_at":"2016-11-23 02:57:17","updated_at":"2016-11-23 02:57:17","username":"10000_leo","tel":"15161752615","zfmm":null,"sfz":null,"shen":null,"city":null,"qu":null,"addr":null,"sfzzm":null,"sfzfm":null,"mdhz":null,"usertype":null,"stop":null,"city_id":null,"tp":null,"manager_id":null,"mentor_id":null,"bzlx":null,"yyzz":null,"jf":null,"tjf":null,"tjp":null,"bmm":null,"longitude":null,"latitude":null,"istg":null,"tjr":null,"departmentid":"08829fce-ed67-4106-9159-934f2747cd39","manufactureid":"3","myname":"厉开亮","sex":"男","sort":null,"deptname":"龙腾科技有限公司","orgname":"龙腾科技","orgbhnumber":"10002"},{"id":"72","email":"","encrypted_password":"14e1b600b1fd579f47433b88e8d85291","reset_password_token":null,"reset_password_sent_at":null,"remember_created_at":null,"sign_in_count":"0","current_sign_in_at":null,"last_sign_in_at":null,"current_sign_in_ip":null,"last_sign_in_ip":null,"created_at":"2016-11-23 03:04:34","updated_at":"2016-11-23 03:04:34","username":"1000_dyw","tel":null,"zfmm":null,"sfz":null,"shen":null,"city":null,"qu":null,"addr":null,"sfzzm":null,"sfzfm":null,"mdhz":null,"usertype":null,"stop":null,"city_id":null,"tp":null,"manager_id":null,"mentor_id":null,"bzlx":null,"yyzz":null,"jf":null,"tjf":null,"tjp":null,"bmm":null,"longitude":null,"latitude":null,"istg":null,"tjr":null,"departmentid":"08829fce-ed67-4106-9159-934f2747cd39","manufactureid":"3","myname":"段元文","sex":"男","sort":null,"deptname":"龙腾科技有限公司","orgname":"龙腾科技","orgbhnumber":"10002"},{"id":"74","email":"","encrypted_password":"14e1b600b1fd579f47433b88e8d85291","reset_password_token":null,"reset_password_sent_at":null,"remember_created_at":null,"sign_in_count":"0","current_sign_in_at":null,"last_sign_in_at":null,"current_sign_in_ip":null,"last_sign_in_ip":null,"created_at":"2016-11-23 03:08:34","updated_at":"2016-11-23 03:08:34","username":"10000_ZJX","tel":null,"zfmm":null,"sfz":null,"shen":null,"city":null,"qu":null,"addr":null,"sfzzm":null,"sfzfm":null,"mdhz":null,"usertype":null,"stop":null,"city_id":null,"tp":null,"manager_id":null,"mentor_id":null,"bzlx":null,"yyzz":null,"jf":null,"tjf":null,"tjp":null,"bmm":null,"longitude":null,"latitude":null,"istg":null,"tjr":null,"departmentid":"08829fce-ed67-4106-9159-934f2747cd39","manufactureid":"3","myname":"周家新","sex":"男","sort":null,"deptname":"龙腾科技有限公司","orgname":"龙腾科技","orgbhnumber":"10002"},{"id":"75","email":"","encrypted_password":"14e1b600b1fd579f47433b88e8d85291","reset_password_token":null,"reset_password_sent_at":null,"remember_created_at":null,"sign_in_count":"0","current_sign_in_at":null,"last_sign_in_at":null,"current_sign_in_ip":null,"last_sign_in_ip":null,"created_at":"2016-11-23 03:09:12","updated_at":"2016-11-23 03:09:12","username":"1000_ZJM","tel":null,"zfmm":null,"sfz":null,"shen":null,"city":null,"qu":null,"addr":null,"sfzzm":null,"sfzfm":null,"mdhz":null,"usertype":null,"stop":null,"city_id":null,"tp":null,"manager_id":null,"mentor_id":null,"bzlx":null,"yyzz":null,"jf":null,"tjf":null,"tjp":null,"bmm":null,"longitude":null,"latitude":null,"istg":null,"tjr":null,"departmentid":"08829fce-ed67-4106-9159-934f2747cd39","manufactureid":"3","myname":"朱加冕","sex":"男","sort":null,"deptname":"龙腾科技有限公司","orgname":"龙腾科技","orgbhnumber":"10002"}]
     */

    private String message;
    private String staues;
    private int TotalCount;
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
     * deptname : 龙腾科技有限公司
     * orgname : 龙腾科技
     * orgbhnumber : 10002
     */

    private String id;
    private String email;
    private String username;
    private String tel;
    private String departmentid;
    private String manufactureid;
    private String myname;
    private String sex;
    private String deptname;
    private String orgname;
    private String orgbhnumber;

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

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgbhnumber() {
        return orgbhnumber;
    }

    public void setOrgbhnumber(String orgbhnumber) {
        this.orgbhnumber = orgbhnumber;
    }


    @Override
    public String toString() {
        return "ColleagueItem{" +
                "departmentid='" + departmentid + '\'' +
                ", message='" + message + '\'' +
                ", staues='" + staues + '\'' +
                ", TotalCount=" + TotalCount +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", tel='" + tel + '\'' +
                ", manufactureid='" + manufactureid + '\'' +
                ", myname='" + myname + '\'' +
                ", sex='" + sex + '\'' +
                ", deptname='" + deptname + '\'' +
                ", orgname='" + orgname + '\'' +
                ", orgbhnumber='" + orgbhnumber + '\'' +
                '}';
    }
}
