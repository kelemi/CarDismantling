package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**人员统计数据
 * Created by Administrator on 2016/11/30.
 */

public class TongJiDataOfWorker implements Serializable {


    /**
     * personid : 71
     * username : 10000_leo
     * tel : null
     * num : 6
     */

    private String personid;
    private String username;
    private String tel;
    private String num;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TongJiDataOfWorker{" +
                "num='" + num + '\'' +
                ", personid='" + personid + '\'' +
                ", username='" + username + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
