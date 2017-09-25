package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**待拆解的零件的信息item
 * Created by Administrator on 2016/11/23.
 */

public class ChaiJieLingJianInfoItem implements Serializable {

    String lingJianName;
    int lingJianNum;

    List<String> lingJianPicList;


    public ChaiJieLingJianInfoItem() {
    }

    public String getLingJianName() {
        return lingJianName;
    }

    public void setLingJianName(String lingJianName) {
        this.lingJianName = lingJianName;
    }

    public int getLingJianNum() {
        return lingJianNum;
    }

    public void setLingJianNum(int lingJianNum) {
        this.lingJianNum = lingJianNum;
    }

    public List<String> getLingJianPicList() {
        return lingJianPicList;
    }

    public void setLingJianPicList(List<String> lingJianPicList) {
        this.lingJianPicList = lingJianPicList;
    }


    @Override
    public String toString() {
        return "WaitChaiJieLingJianInfoItem{" +
                "lingJianName='" + lingJianName + '\'' +
                ", lingJianNum='" + lingJianNum + '\'' +
                ", lingJianPicList=" + lingJianPicList +
                '}';
    }
}
