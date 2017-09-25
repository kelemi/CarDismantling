package com.aofan.cardismantling.bean;

/**
 * Created by Administrator on 2017/7/13.
 */

public class JobWaitWeiChuItem {


    /**
     * staues : sucess
     * message : 获取成功
     * data : [{"createtime":"2017-07-13 21:16:44","baseid":"22068","custominfactorynumber":"1","vehiclenumber":"11111111800","type":"精拆","did":"147"},{"createtime":"2017-07-13 21:11:21","baseid":"22067","custominfactorynumber":"1","vehiclenumber":"333666","type":"精拆","did":"146"},{"createtime":"2017-07-13 20:52:02","baseid":"22066","custominfactorynumber":"1","vehiclenumber":"87878","type":"精拆","did":"145"},{"createtime":"2017-07-13 20:34:31","baseid":"22065","custominfactorynumber":"2","vehiclenumber":"22222","type":"精拆","did":"144"},{"createtime":"2017-07-13 20:30:09","baseid":"22064","custominfactorynumber":"1","vehiclenumber":"88885","type":"粗拆","did":"143"},{"createtime":"2017-07-13 20:22:20","baseid":"22063","custominfactorynumber":"9999","vehiclenumber":"3366","type":"精拆","did":"142"},{"createtime":"2017-07-13 20:16:16","baseid":"22062","custominfactorynumber":"333","vehiclenumber":"1223366","type":"精拆","did":"141"},{"createtime":"2017-07-13 20:11:56","baseid":"22061","custominfactorynumber":"3","vehiclenumber":"3333","type":null,"did":null},{"createtime":"2017-07-13 20:10:40","baseid":"22060","custominfactorynumber":"6666","vehiclenumber":"1111111","type":null,"did":null},{"createtime":"2017-07-13 20:09:27","baseid":"22059","custominfactorynumber":"6666","vehiclenumber":"566633366","type":null,"did":null}]
     */

    /**
     * createtime : 2017-07-13 21:16:44
     * baseid : 22068
     * custominfactorynumber : 1
     * vehiclenumber : 11111111800
     * type : 精拆
     * did : 147
     */

    private String createtime;
    private String baseid;
    private String custominfactorynumber;
    private String vehiclenumber;
    private String type;
    private String did;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getBaseid() {
        return baseid;
    }

    public void setBaseid(String baseid) {
        this.baseid = baseid;
    }

    public String getCustominfactorynumber() {
        return custominfactorynumber;
    }

    public void setCustominfactorynumber(String custominfactorynumber) {
        this.custominfactorynumber = custominfactorynumber;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

}
