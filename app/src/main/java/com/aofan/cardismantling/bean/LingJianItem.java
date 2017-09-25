package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**零件item
 * Created by Administrator on 2016/11/21.
 */

public class LingJianItem implements Serializable{

    String name;
    boolean isCheck;

    public LingJianItem() {
    }


    public LingJianItem(boolean isCheck, String name) {
        this.isCheck = isCheck;
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LingJianItem{" +
                "isCheck=" + isCheck +
                ", name='" + name + '\'' +
                '}';
    }
}
