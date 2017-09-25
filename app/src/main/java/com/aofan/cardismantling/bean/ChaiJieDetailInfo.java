package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**拆解详情数据
 * Created by Administrator on 2016/11/28.
 */

public class ChaiJieDetailInfo implements Serializable {





    /**
     * oid : 34
     * partnamelist : 前后轮胎,钢圈,前桥,车门,引擎盖,电脑板
     * requirement : 测试
     * dispatchperson : 10000_leo
     * caption :
     * dispatchtime : 0000-00-00 00:00:00
     * createtime : 2016-11-28 09:44:28
     * files : 测试
     * partlist : [{"id":"79","orderid":"34","partid":"247","partname":"前后轮胎","outputcount":"3","imgattachlist":[{"id":"23","oid":"34","partid":"247","name":"IMG20161125210316.jpg","path":"./upload/34/247/IMG20161125210316.jpg","fullpath":null,"suffix":"image/jpeg","size":"1538211","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"24","oid":"34","partid":"247","name":"IMG20161125210458.jpg","path":"./upload/34/247/IMG20161125210458.jpg","fullpath":null,"suffix":"image/jpeg","size":"1675768","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"25","oid":"34","partid":"247","name":"IMG20161125210643.jpg","path":"./upload/34/247/IMG20161125210643.jpg","fullpath":null,"suffix":"image/jpeg","size":"1636326","remark":null,"state":null,"createtime":null,"createperson":null}]},{"id":"80","orderid":"34","partid":"248","partname":"钢圈","outputcount":"0","imgattachlist":[]},{"id":"81","orderid":"34","partid":"249","partname":"前桥","outputcount":"0","imgattachlist":[]},{"id":"82","orderid":"34","partid":"250","partname":"车门","outputcount":"3","imgattachlist":[{"id":"44","oid":"34","partid":"250","name":"IMG20161125210643.jpg","path":"./upload/34/250/IMG20161125210643.jpg","fullpath":null,"suffix":"image/jpeg","size":"1636326","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"45","oid":"34","partid":"250","name":"IMG20161125210316.jpg","path":"./upload/34/250/IMG20161125210316.jpg","fullpath":null,"suffix":"image/jpeg","size":"1538211","remark":null,"state":null,"createtime":null,"createperson":null}]},{"id":"83","orderid":"34","partid":"251","partname":"引擎盖","outputcount":"2","imgattachlist":[{"id":"38","oid":"34","partid":"251","name":"IMG20161125210458.jpg","path":"./upload/34/251/IMG20161125210458.jpg","fullpath":null,"suffix":"image/jpeg","size":"1675768","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"39","oid":"34","partid":"251","name":"IMG20161125210643.jpg","path":"./upload/34/251/IMG20161125210643.jpg","fullpath":null,"suffix":"image/jpeg","size":"1636326","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"40","oid":"34","partid":"251","name":"IMG20161125210316.jpg","path":"./upload/34/251/IMG20161125210316.jpg","fullpath":null,"suffix":"image/jpeg","size":"1538211","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"41","oid":"34","partid":"251","name":"IMG20161125210458.jpg","path":"./upload/34/251/IMG20161125210458.jpg","fullpath":null,"suffix":"image/jpeg","size":"1675768","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"42","oid":"34","partid":"251","name":"IMG20161125210643.jpg","path":"./upload/34/251/IMG20161125210643.jpg","fullpath":null,"suffix":"image/jpeg","size":"1636326","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"43","oid":"34","partid":"251","name":"IMG20161125210316.jpg","path":"./upload/34/251/IMG20161125210316.jpg","fullpath":null,"suffix":"image/jpeg","size":"1538211","remark":null,"state":null,"createtime":null,"createperson":null}]},{"id":"84","orderid":"34","partid":"252","partname":"电脑板","outputcount":"2","imgattachlist":[{"id":"36","oid":"34","partid":"252","name":"IMG20161128201106.jpg","path":"./upload/34/252/IMG20161128201106.jpg","fullpath":null,"suffix":"image/jpeg","size":"3634891","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"37","oid":"34","partid":"252","name":"IMG20161128200806.jpg","path":"./upload/34/252/IMG20161128200806.jpg","fullpath":null,"suffix":"image/jpeg","size":"3219548","remark":null,"state":null,"createtime":null,"createperson":null}]}]
     */


    private String oid;
    private String partnamelist;
    private String requirement;
    private String dispatchperson;
    private String caption;
    private String dispatchtime;
    private String createtime;
    private String files;
    private String createperson;
    private String comletetime;
    private String comleteperson;
    private String surecompetetime;
    private String surecompeteperson;
    private String completepersonname;
    private String surecompletepersonname;

    public String getComleteperson() {
        return comleteperson;
    }

    public void setComleteperson(String comleteperson) {
        this.comleteperson = comleteperson;
    }

    public String getComletetime() {
        return comletetime;
    }

    public void setComletetime(String comletetime) {
        this.comletetime = comletetime;
    }

    public String getCompletepersonname() {
        return completepersonname;
    }

    public void setCompletepersonname(String completepersonname) {
        this.completepersonname = completepersonname;
    }

    public String getCreateperson() {
        return createperson;
    }

    public void setCreateperson(String createperson) {
        this.createperson = createperson;
    }

    public String getSurecompeteperson() {
        return surecompeteperson;
    }

    public void setSurecompeteperson(String surecompeteperson) {
        this.surecompeteperson = surecompeteperson;
    }

    public String getSurecompetetime() {
        return surecompetetime;
    }

    public void setSurecompetetime(String surecompetetime) {
        this.surecompetetime = surecompetetime;
    }

    public String getSurecompletepersonname() {
        return surecompletepersonname;
    }

    public void setSurecompletepersonname(String surecompletepersonname) {
        this.surecompletepersonname = surecompletepersonname;
    }

    /**
     * id : 79
     * orderid : 34
     * partid : 247
     * partname : 前后轮胎
     * outputcount : 3
     * imgattachlist : [{"id":"23","oid":"34","partid":"247","name":"IMG20161125210316.jpg","path":"./upload/34/247/IMG20161125210316.jpg","fullpath":null,"suffix":"image/jpeg","size":"1538211","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"24","oid":"34","partid":"247","name":"IMG20161125210458.jpg","path":"./upload/34/247/IMG20161125210458.jpg","fullpath":null,"suffix":"image/jpeg","size":"1675768","remark":null,"state":null,"createtime":null,"createperson":null},{"id":"25","oid":"34","partid":"247","name":"IMG20161125210643.jpg","path":"./upload/34/247/IMG20161125210643.jpg","fullpath":null,"suffix":"image/jpeg","size":"1636326","remark":null,"state":null,"createtime":null,"createperson":null}]
     */

    private List<PartlistBean> partlist;

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

    public String getDispatchtime() {
        return dispatchtime;
    }

    public void setDispatchtime(String dispatchtime) {
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

    public List<PartlistBean> getPartlist() {
        return partlist;
    }

    public void setPartlist(List<PartlistBean> partlist) {
        this.partlist = partlist;
    }

    public static class PartlistBean implements Serializable{
        private String id;
        private String orderid;
        private String partid;
        private String partname;
        private String outputcount;


        /**
         * id : 23
         * oid : 34
         * partid : 247
         * name : IMG20161125210316.jpg
         * path : ./upload/34/247/IMG20161125210316.jpg
         * fullpath : null
         * suffix : image/jpeg
         * size : 1538211
         * remark : null
         * state : null
         * createtime : null
         * createperson : null
         */

        private List<ImgattachlistBean> imgattachlist;

        //是否是本地图片
        private boolean isLocalPic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getPartid() {
            return partid;
        }

        public void setPartid(String partid) {
            this.partid = partid;
        }

        public String getPartname() {
            return partname;
        }

        public void setPartname(String partname) {
            this.partname = partname;
        }

        public String getOutputcount() {
            return outputcount;
        }

        public void setOutputcount(String outputcount) {
            this.outputcount = outputcount;
        }

        public List<ImgattachlistBean> getImgattachlist() {
            return imgattachlist;
        }

        public void setImgattachlist(List<ImgattachlistBean> imgattachlist) {
            this.imgattachlist = imgattachlist;
        }


        public boolean isLocalPic() {
            return isLocalPic;
        }

        public void setLocalPic(boolean localPic) {
            isLocalPic = localPic;
        }

        public static class ImgattachlistBean implements Serializable{
            private String id;
            private String oid;
            private String partid;
            private String name;
            private String path;
            private String fullpath;
            private String suffix;
            private String size;
            private Object remark;
            private Object state;
            private String createtime;
            private String createperson;

            //本地图片路径
            private String loaclPicPath;

            public String getLoaclPicPath() {
                return loaclPicPath;
            }

            public void setLoaclPicPath(String loaclPicPath) {
                this.loaclPicPath = loaclPicPath;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getFullpath() {
                return fullpath;
            }

            public String getSuffix() {
                return suffix;
            }

            public void setSuffix(String suffix) {
                this.suffix = suffix;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getState() {
                return state;
            }

            public void setState(Object state) {
                this.state = state;
            }

            public String getCreateperson() {
                return createperson;
            }

            public void setCreateperson(String createperson) {
                this.createperson = createperson;
            }

            public void setFullpath(String fullpath) {
                this.fullpath = fullpath;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }
        }

        //本地图片地址
        private List<String> localImgPathList;

        public List<String> getLocalImgPathList() {
            return localImgPathList;
        }

        public void setLocalImgPathList(List<String> localImgPathList) {
            this.localImgPathList = localImgPathList;
        }
    }






}
