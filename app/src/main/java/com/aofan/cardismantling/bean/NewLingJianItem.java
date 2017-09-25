package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**零件item
 * Created by Administrator on 2016/11/21.
 */

public class NewLingJianItem implements Serializable{

    private String pid;
    private String type;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "NewLingJianItem{" +
                "name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
