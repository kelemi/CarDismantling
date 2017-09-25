package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**拆解工人信息bean
 * Created by Administrator on 2016/11/27.
 */

public class ChaiJieWorkerInfo implements Serializable {


    /**
     * id : 64
     * username : 1000demo
     */

    private String id;
    private String username;

    boolean isCheck;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "ChaiJieWorkerInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
