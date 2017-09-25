package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**汽车零件信息
 * Created by Administrator on 2016/11/25.
 */

public class CarLingJianInfo implements Serializable {

    private String partname;
    private String id;
    private String sort;

    boolean isCheck;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "CarLingJianInfo{" +
                "id='" + id + '\'' +
                ", partname='" + partname + '\'' +
                ", sort='" + sort + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
