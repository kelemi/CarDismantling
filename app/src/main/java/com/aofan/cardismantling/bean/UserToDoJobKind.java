package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**用户将要去办理的事项的类型（待办事项和已办事项）
 * Created by Administrator on 2016/11/28.
 */

public class UserToDoJobKind implements Serializable {


    /**
     * id : 8af7644d-4c81-414f-8137-3d5ab9063780
     * name : 待确认派工单
     * isbutton : 48
     * parentid : 5
     * url :
     * dllfile : 无
     * mfile : 无
     * mtype : null
     * sort : 2
     * remark :
     * state :
     * createperson :
     * createtime : 2016-11-28 16:06:18
     * menutype : phone
     * icon : ?
     * buttonid : ?
     */

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserToDoJobKind{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
