package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ChaiJieDetailInfoForCuChai implements Serializable {


    /**
     * staues : sucess
     * message : 获取成功
     * data : {"oid":"91","partnamelist":"","requirement":"呜呜呜呜","dispatchperson":"zs,11","caption":"","cjtype":"粗拆","dispatchtime":null,"createtime":"2017-02-07 16:50:07","files":"呜呜呜呜","createperson":"zs","comletetime":null,"comleteperson":null,"surecompetetime":null,"surecompeteperson":"0","completepersonname":"","surecompletepersonname":"","imgattachlist":[{"id":"266","oid":"91","partid":"-1","name":"立春 (4)_&_58586a56-3fc5-4c91-8e8e-674b9499e971.jpg","path":"/upload/91/立春 (4)_&_58586a56-3fc5-4c91-8e8e-674b9499e971.jpg","fullpath":"./upload/91/?? (4)_&_58586a56-3fc5-4c91-8e8e-674b9499e971.jpg","suffix":"image/jpeg","size":"285465","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"267","oid":"91","partid":"-1","name":"IMG20170207163608.jpg","path":"/upload/91/IMG20170207163608.jpg","fullpath":"./upload/91/IMG20170207163608.jpg","suffix":"image/jpeg","size":"757489","remark":null,"state":null,"createtime":null,"createperson":null}]}
     */

    /**
     * oid : 91
     * partnamelist :
     * requirement : 呜呜呜呜
     * dispatchperson : zs,11
     * caption :
     * cjtype : 粗拆
     * dispatchtime : null
     * createtime : 2017-02-07 16:50:07
     * files : 呜呜呜呜
     * createperson : zs
     * comletetime : null
     * comleteperson : null
     * surecompetetime : null
     * surecompeteperson : 0
     * completepersonname :
     * surecompletepersonname :
     * imgattachlist : [{"id":"266","oid":"91","partid":"-1","name":"立春 (4)_&_58586a56-3fc5-4c91-8e8e-674b9499e971.jpg","path":"/upload/91/立春 (4)_&_58586a56-3fc5-4c91-8e8e-674b9499e971.jpg","fullpath":"./upload/91/?? (4)_&_58586a56-3fc5-4c91-8e8e-674b9499e971.jpg","suffix":"image/jpeg","size":"285465","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"267","oid":"91","partid":"-1","name":"IMG20170207163608.jpg","path":"/upload/91/IMG20170207163608.jpg","fullpath":"./upload/91/IMG20170207163608.jpg","suffix":"image/jpeg","size":"757489","remark":null,"state":null,"createtime":null,"createperson":null}]
     */

    private String oid;
    private String partnamelist;
    private String requirement;
    private String dispatchperson;
    private String caption;
    private String cjtype;
    private Object dispatchtime;
    private String createtime;
    private String files;
    private String createperson;
    private Object comletetime;
    private Object comleteperson;
    private Object surecompetetime;
    private String surecompeteperson;
    private String completepersonname;
    private String surecompletepersonname;
    private List<ImgattachlistBean> imgattachlist;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPartnamelist() {
        return partnamelist;
    }

    public void setPartnamelist(String partnamelist) {
        this.partnamelist = partnamelist;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getDispatchperson() {
        return dispatchperson;
    }

    public void setDispatchperson(String dispatchperson) {
        this.dispatchperson = dispatchperson;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCjtype() {
        return cjtype;
    }

    public void setCjtype(String cjtype) {
        this.cjtype = cjtype;
    }

    public Object getDispatchtime() {
        return dispatchtime;
    }

    public void setDispatchtime(Object dispatchtime) {
        this.dispatchtime = dispatchtime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getCreateperson() {
        return createperson;
    }

    public void setCreateperson(String createperson) {
        this.createperson = createperson;
    }

    public Object getComletetime() {
        return comletetime;
    }

    public void setComletetime(Object comletetime) {
        this.comletetime = comletetime;
    }

    public Object getComleteperson() {
        return comleteperson;
    }

    public void setComleteperson(Object comleteperson) {
        this.comleteperson = comleteperson;
    }

    public Object getSurecompetetime() {
        return surecompetetime;
    }

    public void setSurecompetetime(Object surecompetetime) {
        this.surecompetetime = surecompetetime;
    }

    public String getSurecompeteperson() {
        return surecompeteperson;
    }

    public void setSurecompeteperson(String surecompeteperson) {
        this.surecompeteperson = surecompeteperson;
    }

    public String getCompletepersonname() {
        return completepersonname;
    }

    public void setCompletepersonname(String completepersonname) {
        this.completepersonname = completepersonname;
    }

    public String getSurecompletepersonname() {
        return surecompletepersonname;
    }

    public void setSurecompletepersonname(String surecompletepersonname) {
        this.surecompletepersonname = surecompletepersonname;
    }

    public List<ImgattachlistBean> getImgattachlist() {
        return imgattachlist;
    }

    public void setImgattachlist(List<ImgattachlistBean> imgattachlist) {
        this.imgattachlist = imgattachlist;
    }


    //拆解的图片信息
    public static class ImgattachlistBean {
        /**
         * id : 266
         * oid : 91
         * partid : -1
         * name : 立春 (4)_&_58586a56-3fc5-4c91-8e8e-674b9499e971.jpg
         * path : /upload/91/立春 (4)_&_58586a56-3fc5-4c91-8e8e-674b9499e971.jpg
         * fullpath : ./upload/91/?? (4)_&_58586a56-3fc5-4c91-8e8e-674b9499e971.jpg
         * suffix : image/jpeg
         * size : 285465
         * remark : null
         * state : null
         * createtime : null
         * createperson : null
         */

        private String id;
        private String oid;
        private String partid;
        private String name;
        private String path;//图片的相对路径
        private String fullpath;
        private String suffix;
        private String size;

        //下面几个暂时没有用
        private String remark;
        private String state;
        private String createtime;
        private String createperson;

        //图片的本地地址
        private String localPicPath;

        //是否是本地图片，默认的不是
        private boolean isLoaclPic;


        public boolean isLoaclPic() {
            return isLoaclPic;
        }

        public void setLoaclPic(boolean loaclPic) {
            isLoaclPic = loaclPic;
        }

        public String getLocalPicPath() {
            return localPicPath;
        }

        public void setLocalPicPath(String localPicPath) {
            this.localPicPath = localPicPath;
        }

        public String getCreateperson() {
            return createperson;
        }

        public void setCreateperson(String createperson) {
            this.createperson = createperson;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getFullpath() {
            return fullpath;
        }

        public void setFullpath(String fullpath) {
            this.fullpath = fullpath;
        }

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

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getPartid() {
            return partid;
        }

        public void setPartid(String partid) {
            this.partid = partid;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public ImgattachlistBean() {
        }
    }
}
