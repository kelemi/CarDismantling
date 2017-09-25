package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/** 首界面menuitem
 * Created by Administrator on 2016/11/22.
 */

public class MenuItem implements Serializable {
    /**
     * id : 1
     * name : 车辆分析
     * parentid : 7fd13766-a18c-11e6-9dd6-003067436a73
     * url : null
     * sort : null
     * icon :
     * state : true
     */

    private String id;
    private String name;
    private String parentid;
    private Object url;
    private Object sort;
    private String icon;
    private boolean state;
    //本地图片资源id
    private int localIconResId;

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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }


    public int getLocalIconResId() {
        return localIconResId;
    }

    public void setLocalIconResId(int localIconResId) {
        this.localIconResId = localIconResId;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentid='" + parentid + '\'' +
                ", url=" + url +
                ", sort=" + sort +
                ", state=" + state +
                '}';
    }
}
