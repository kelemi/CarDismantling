package com.aofan.cardismantling.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public class LingJianInfo implements Serializable {

    /**
     * partinfo : {"id":"188","baseid":"1334","biannumber":"M6_170207143406","did":"60","oid":"81","vehiclenumber":"粤A4DF98","partid":"476","partname":"缸头","weight":"0.00","color":"343","scnf":"2017-02-10 00:00:00","pailiang":"343","zdyms":"343","presellprice":"343.00","state":"待进库","dsstate":"","createtime":"2017-02-07 14:34:06","createperson":"zs","mid":"6","price":"0.00","sellprice":"0.00","selltime":null,"seller":null,"goodplace":"","shelfnumber":"","instockperson":"","instocktime":null,"tuihuanhuostate":"","tuihuanhuodesc":"","outstockperson":"","outstocktime":null}
     * imgattachlist : [{"id":"259","oid":"81","partid":"476","name":"Fiel0.jpg","path":"/upload/81/476/Fiel0.jpg","fullpath":"./upload/81/476/Fiel0.jpg","suffix":"image/jpeg","size":"956890","remark":null,"state":null,"createtime":null,"createperson":null}]
     * baseinfo : {"baseid":"1334","guidid":"9f6632a62a23467cbb4b65d0644c9871","entryno":"2016121615382232","vehiclenumber":"粤A4DF98","vehicletype":"轿车","vehicleowner":"黄沛文","owneraddress":"广东省从化市城郊街东风村十三社新和里2","useattribute":"非营运","brandmodel":"别克CBU1CKXSGM7180LS","vehiclemodel":"CBU1CKXSGM7180LS","vehicleidentifyno":"LSGJV52P84S244832","outfacdate":"2016-12-16 15:38:22","fdjhm":"T18SED254689NC","registerdate":"2016-12-01 00:00:00","certificate":"2016-12-16 00:00:00","approvednumber":"4","totalmass":"0","fueltype":"汽油","power_1":"","power_2":"","looksize_1":"","looksize_2":"","looksize_3":"","vehicleoperator":"zs","vehiclecolor":"","vehiclelocation":"","clasificacion":"","outfactory2":"","drivinglic":"","registercertify":"","flapper":"","vehicleclass":"","flapperclass":"","vehicleweight":"0","carchargeamount":"0","custominfactorynumber":"0","documentnumber":"","ispay":"0","paytime":null,"paybyid":null,"isupplate":"0","createtime":"2016-12-16 15:38:22","entrytime":"2016-12-16 00:00:00","canceltime":null,"caijietime":null,"createperson":"zs","state":"未交牌","manufactureid":"6","remarks":"","allocat":"拆解中","vehicleimg":"/upload/81/2016121217092403.jpg","vehiclenumberimg":null,"vehiclelicensezipcode":"510000"}
     * partorderinfo : {"id":"222","orderid":"81","partid":"476","partname":"缸头","outputcount":"1","dealtime":"2017-02-07 14:34:06","dealperson":"81","dealpersonname":"zs","mid":"6"}
     * partdetailinfo : null
     */

    private PartinfoBean partinfo;
    private BaseinfoBean baseinfo;
    private PartorderinfoBean partorderinfo;
    private Object partdetailinfo;
    private List<ImgattachlistBean> imgattachlist;

    public PartinfoBean getPartinfo() {
        return partinfo;
    }

    public void setPartinfo(PartinfoBean partinfo) {
        this.partinfo = partinfo;
    }

    public BaseinfoBean getBaseinfo() {
        return baseinfo;
    }

    public void setBaseinfo(BaseinfoBean baseinfo) {
        this.baseinfo = baseinfo;
    }

    public PartorderinfoBean getPartorderinfo() {
        return partorderinfo;
    }

    public void setPartorderinfo(PartorderinfoBean partorderinfo) {
        this.partorderinfo = partorderinfo;
    }

    public Object getPartdetailinfo() {
        return partdetailinfo;
    }

    public void setPartdetailinfo(Object partdetailinfo) {
        this.partdetailinfo = partdetailinfo;
    }

    public List<ImgattachlistBean> getImgattachlist() {
        return imgattachlist;
    }

    public void setImgattachlist(List<ImgattachlistBean> imgattachlist) {
        this.imgattachlist = imgattachlist;
    }

    public static class PartinfoBean {
        /**
         * id : 188
         * baseid : 1334
         * biannumber : M6_170207143406
         * did : 60
         * oid : 81
         * vehiclenumber : 粤A4DF98
         * partid : 476
         * partname : 缸头
         * weight : 0.00
         * color : 343
         * scnf : 2017-02-10 00:00:00
         * pailiang : 343
         * zdyms : 343
         * presellprice : 343.00
         * state : 待进库
         * dsstate :
         * createtime : 2017-02-07 14:34:06
         * createperson : zs
         * mid : 6
         * price : 0.00
         * sellprice : 0.00
         * selltime : null
         * seller : null
         * goodplace :
         * shelfnumber :
         * instockperson :
         * instocktime : null
         * tuihuanhuostate :
         * tuihuanhuodesc :
         * outstockperson :
         * outstocktime : null
         */

        private String id;
        private String baseid;
        private String biannumber;
        private String did;
        private String oid;
        private String vehiclenumber;
        private String partid;
        private String partname;
        private String weight;
        private String color;
        private String scnf;
        private String pailiang;
        private String zdyms;
        private String presellprice;
        private String state;
        private String dsstate;
        private String createtime;
        private String createperson;
        private String mid;
        private String price;
        private String sellprice;
        private Object selltime;
        private Object seller;
        private String goodplace;
        private String shelfnumber;
        private String instockperson;
        private Object instocktime;
        private String tuihuanhuostate;
        private String tuihuanhuodesc;
        private String outstockperson;
        private Object outstocktime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBaseid() {
            return baseid;
        }

        public void setBaseid(String baseid) {
            this.baseid = baseid;
        }

        public String getBiannumber() {
            return biannumber;
        }

        public void setBiannumber(String biannumber) {
            this.biannumber = biannumber;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getVehiclenumber() {
            return vehiclenumber;
        }

        public void setVehiclenumber(String vehiclenumber) {
            this.vehiclenumber = vehiclenumber;
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

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getScnf() {
            return scnf;
        }

        public void setScnf(String scnf) {
            this.scnf = scnf;
        }

        public String getPailiang() {
            return pailiang;
        }

        public void setPailiang(String pailiang) {
            this.pailiang = pailiang;
        }

        public String getZdyms() {
            return zdyms;
        }

        public void setZdyms(String zdyms) {
            this.zdyms = zdyms;
        }

        public String getPresellprice() {
            return presellprice;
        }

        public void setPresellprice(String presellprice) {
            this.presellprice = presellprice;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDsstate() {
            return dsstate;
        }

        public void setDsstate(String dsstate) {
            this.dsstate = dsstate;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getCreateperson() {
            return createperson;
        }

        public void setCreateperson(String createperson) {
            this.createperson = createperson;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSellprice() {
            return sellprice;
        }

        public void setSellprice(String sellprice) {
            this.sellprice = sellprice;
        }

        public Object getSelltime() {
            return selltime;
        }

        public void setSelltime(Object selltime) {
            this.selltime = selltime;
        }

        public Object getSeller() {
            return seller;
        }

        public void setSeller(Object seller) {
            this.seller = seller;
        }

        public String getGoodplace() {
            return goodplace;
        }

        public void setGoodplace(String goodplace) {
            this.goodplace = goodplace;
        }

        public String getShelfnumber() {
            return shelfnumber;
        }

        public void setShelfnumber(String shelfnumber) {
            this.shelfnumber = shelfnumber;
        }

        public String getInstockperson() {
            return instockperson;
        }

        public void setInstockperson(String instockperson) {
            this.instockperson = instockperson;
        }

        public Object getInstocktime() {
            return instocktime;
        }

        public void setInstocktime(Object instocktime) {
            this.instocktime = instocktime;
        }

        public String getTuihuanhuostate() {
            return tuihuanhuostate;
        }

        public void setTuihuanhuostate(String tuihuanhuostate) {
            this.tuihuanhuostate = tuihuanhuostate;
        }

        public String getTuihuanhuodesc() {
            return tuihuanhuodesc;
        }

        public void setTuihuanhuodesc(String tuihuanhuodesc) {
            this.tuihuanhuodesc = tuihuanhuodesc;
        }

        public String getOutstockperson() {
            return outstockperson;
        }

        public void setOutstockperson(String outstockperson) {
            this.outstockperson = outstockperson;
        }

        public Object getOutstocktime() {
            return outstocktime;
        }

        public void setOutstocktime(Object outstocktime) {
            this.outstocktime = outstocktime;
        }
    }

    public static class BaseinfoBean {
        /**
         * baseid : 1334
         * guidid : 9f6632a62a23467cbb4b65d0644c9871
         * entryno : 2016121615382232
         * vehiclenumber : 粤A4DF98
         * vehicletype : 轿车
         * vehicleowner : 黄沛文
         * owneraddress : 广东省从化市城郊街东风村十三社新和里2
         * useattribute : 非营运
         * brandmodel : 别克CBU1CKXSGM7180LS
         * vehiclemodel : CBU1CKXSGM7180LS
         * vehicleidentifyno : LSGJV52P84S244832
         * outfacdate : 2016-12-16 15:38:22
         * fdjhm : T18SED254689NC
         * registerdate : 2016-12-01 00:00:00
         * certificate : 2016-12-16 00:00:00
         * approvednumber : 4
         * totalmass : 0
         * fueltype : 汽油
         * power_1 :
         * power_2 :
         * looksize_1 :
         * looksize_2 :
         * looksize_3 :
         * vehicleoperator : zs
         * vehiclecolor :
         * vehiclelocation :
         * clasificacion :
         * outfactory2 :
         * drivinglic :
         * registercertify :
         * flapper :
         * vehicleclass :
         * flapperclass :
         * vehicleweight : 0
         * carchargeamount : 0
         * custominfactorynumber : 0
         * documentnumber :
         * ispay : 0
         * paytime : null
         * paybyid : null
         * isupplate : 0
         * createtime : 2016-12-16 15:38:22
         * entrytime : 2016-12-16 00:00:00
         * canceltime : null
         * caijietime : null
         * createperson : zs
         * state : 未交牌
         * manufactureid : 6
         * remarks :
         * allocat : 拆解中
         * vehicleimg : /upload/81/2016121217092403.jpg
         * vehiclenumberimg : null
         * vehiclelicensezipcode : 510000
         */

        private String baseid;
        private String guidid;
        private String entryno;
        private String vehiclenumber;
        private String vehicletype;
        private String vehicleowner;
        private String owneraddress;
        private String useattribute;
        private String brandmodel;
        private String vehiclemodel;
        private String vehicleidentifyno;
        private String outfacdate;
        private String fdjhm;
        private String registerdate;
        private String certificate;
        private String approvednumber;
        private String totalmass;
        private String fueltype;
        private String power_1;
        private String power_2;
        private String looksize_1;
        private String looksize_2;
        private String looksize_3;
        private String vehicleoperator;
        private String vehiclecolor;
        private String vehiclelocation;
        private String clasificacion;
        private String outfactory2;
        private String drivinglic;
        private String registercertify;
        private String flapper;
        private String vehicleclass;
        private String flapperclass;
        private String vehicleweight;
        private String carchargeamount;
        private String custominfactorynumber;
        private String documentnumber;
        private String ispay;
        private Object paytime;
        private Object paybyid;
        private String isupplate;
        private String createtime;
        private String entrytime;
        private Object canceltime;
        private Object caijietime;
        private String createperson;
        private String state;
        private String manufactureid;
        private String remarks;
        private String allocat;
        private String vehicleimg;
        private Object vehiclenumberimg;
        private String vehiclelicensezipcode;

        public String getBaseid() {
            return baseid;
        }

        public void setBaseid(String baseid) {
            this.baseid = baseid;
        }

        public String getGuidid() {
            return guidid;
        }

        public void setGuidid(String guidid) {
            this.guidid = guidid;
        }

        public String getEntryno() {
            return entryno;
        }

        public void setEntryno(String entryno) {
            this.entryno = entryno;
        }

        public String getVehiclenumber() {
            return vehiclenumber;
        }

        public void setVehiclenumber(String vehiclenumber) {
            this.vehiclenumber = vehiclenumber;
        }

        public String getVehicletype() {
            return vehicletype;
        }

        public void setVehicletype(String vehicletype) {
            this.vehicletype = vehicletype;
        }

        public String getVehicleowner() {
            return vehicleowner;
        }

        public void setVehicleowner(String vehicleowner) {
            this.vehicleowner = vehicleowner;
        }

        public String getOwneraddress() {
            return owneraddress;
        }

        public void setOwneraddress(String owneraddress) {
            this.owneraddress = owneraddress;
        }

        public String getUseattribute() {
            return useattribute;
        }

        public void setUseattribute(String useattribute) {
            this.useattribute = useattribute;
        }

        public String getBrandmodel() {
            return brandmodel;
        }

        public void setBrandmodel(String brandmodel) {
            this.brandmodel = brandmodel;
        }

        public String getVehiclemodel() {
            return vehiclemodel;
        }

        public void setVehiclemodel(String vehiclemodel) {
            this.vehiclemodel = vehiclemodel;
        }

        public String getVehicleidentifyno() {
            return vehicleidentifyno;
        }

        public void setVehicleidentifyno(String vehicleidentifyno) {
            this.vehicleidentifyno = vehicleidentifyno;
        }

        public String getOutfacdate() {
            return outfacdate;
        }

        public void setOutfacdate(String outfacdate) {
            this.outfacdate = outfacdate;
        }

        public String getFdjhm() {
            return fdjhm;
        }

        public void setFdjhm(String fdjhm) {
            this.fdjhm = fdjhm;
        }

        public String getRegisterdate() {
            return registerdate;
        }

        public void setRegisterdate(String registerdate) {
            this.registerdate = registerdate;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getApprovednumber() {
            return approvednumber;
        }

        public void setApprovednumber(String approvednumber) {
            this.approvednumber = approvednumber;
        }

        public String getTotalmass() {
            return totalmass;
        }

        public void setTotalmass(String totalmass) {
            this.totalmass = totalmass;
        }

        public String getFueltype() {
            return fueltype;
        }

        public void setFueltype(String fueltype) {
            this.fueltype = fueltype;
        }

        public String getPower_1() {
            return power_1;
        }

        public void setPower_1(String power_1) {
            this.power_1 = power_1;
        }

        public String getPower_2() {
            return power_2;
        }

        public void setPower_2(String power_2) {
            this.power_2 = power_2;
        }

        public String getLooksize_1() {
            return looksize_1;
        }

        public void setLooksize_1(String looksize_1) {
            this.looksize_1 = looksize_1;
        }

        public String getLooksize_2() {
            return looksize_2;
        }

        public void setLooksize_2(String looksize_2) {
            this.looksize_2 = looksize_2;
        }

        public String getLooksize_3() {
            return looksize_3;
        }

        public void setLooksize_3(String looksize_3) {
            this.looksize_3 = looksize_3;
        }

        public String getVehicleoperator() {
            return vehicleoperator;
        }

        public void setVehicleoperator(String vehicleoperator) {
            this.vehicleoperator = vehicleoperator;
        }

        public String getVehiclecolor() {
            return vehiclecolor;
        }

        public void setVehiclecolor(String vehiclecolor) {
            this.vehiclecolor = vehiclecolor;
        }

        public String getVehiclelocation() {
            return vehiclelocation;
        }

        public void setVehiclelocation(String vehiclelocation) {
            this.vehiclelocation = vehiclelocation;
        }

        public String getClasificacion() {
            return clasificacion;
        }

        public void setClasificacion(String clasificacion) {
            this.clasificacion = clasificacion;
        }

        public String getOutfactory2() {
            return outfactory2;
        }

        public void setOutfactory2(String outfactory2) {
            this.outfactory2 = outfactory2;
        }

        public String getDrivinglic() {
            return drivinglic;
        }

        public void setDrivinglic(String drivinglic) {
            this.drivinglic = drivinglic;
        }

        public String getRegistercertify() {
            return registercertify;
        }

        public void setRegistercertify(String registercertify) {
            this.registercertify = registercertify;
        }

        public String getFlapper() {
            return flapper;
        }

        public void setFlapper(String flapper) {
            this.flapper = flapper;
        }

        public String getVehicleclass() {
            return vehicleclass;
        }

        public void setVehicleclass(String vehicleclass) {
            this.vehicleclass = vehicleclass;
        }

        public String getFlapperclass() {
            return flapperclass;
        }

        public void setFlapperclass(String flapperclass) {
            this.flapperclass = flapperclass;
        }

        public String getVehicleweight() {
            return vehicleweight;
        }

        public void setVehicleweight(String vehicleweight) {
            this.vehicleweight = vehicleweight;
        }

        public String getCarchargeamount() {
            return carchargeamount;
        }

        public void setCarchargeamount(String carchargeamount) {
            this.carchargeamount = carchargeamount;
        }

        public String getCustominfactorynumber() {
            return custominfactorynumber;
        }

        public void setCustominfactorynumber(String custominfactorynumber) {
            this.custominfactorynumber = custominfactorynumber;
        }

        public String getDocumentnumber() {
            return documentnumber;
        }

        public void setDocumentnumber(String documentnumber) {
            this.documentnumber = documentnumber;
        }

        public String getIspay() {
            return ispay;
        }

        public void setIspay(String ispay) {
            this.ispay = ispay;
        }

        public Object getPaytime() {
            return paytime;
        }

        public void setPaytime(Object paytime) {
            this.paytime = paytime;
        }

        public Object getPaybyid() {
            return paybyid;
        }

        public void setPaybyid(Object paybyid) {
            this.paybyid = paybyid;
        }

        public String getIsupplate() {
            return isupplate;
        }

        public void setIsupplate(String isupplate) {
            this.isupplate = isupplate;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getEntrytime() {
            return entrytime;
        }

        public void setEntrytime(String entrytime) {
            this.entrytime = entrytime;
        }

        public Object getCanceltime() {
            return canceltime;
        }

        public void setCanceltime(Object canceltime) {
            this.canceltime = canceltime;
        }

        public Object getCaijietime() {
            return caijietime;
        }

        public void setCaijietime(Object caijietime) {
            this.caijietime = caijietime;
        }

        public String getCreateperson() {
            return createperson;
        }

        public void setCreateperson(String createperson) {
            this.createperson = createperson;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getManufactureid() {
            return manufactureid;
        }

        public void setManufactureid(String manufactureid) {
            this.manufactureid = manufactureid;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getAllocat() {
            return allocat;
        }

        public void setAllocat(String allocat) {
            this.allocat = allocat;
        }

        public String getVehicleimg() {
            return vehicleimg;
        }

        public void setVehicleimg(String vehicleimg) {
            this.vehicleimg = vehicleimg;
        }

        public Object getVehiclenumberimg() {
            return vehiclenumberimg;
        }

        public void setVehiclenumberimg(Object vehiclenumberimg) {
            this.vehiclenumberimg = vehiclenumberimg;
        }

        public String getVehiclelicensezipcode() {
            return vehiclelicensezipcode;
        }

        public void setVehiclelicensezipcode(String vehiclelicensezipcode) {
            this.vehiclelicensezipcode = vehiclelicensezipcode;
        }
    }

    public static class PartorderinfoBean {
        /**
         * id : 222
         * orderid : 81
         * partid : 476
         * partname : 缸头
         * outputcount : 1
         * dealtime : 2017-02-07 14:34:06
         * dealperson : 81
         * dealpersonname : zs
         * mid : 6
         */

        private String id;
        private String orderid;
        private String partid;
        private String partname;
        private String outputcount;
        private String dealtime;
        private String dealperson;
        private String dealpersonname;
        private String mid;

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

        public String getDealtime() {
            return dealtime;
        }

        public void setDealtime(String dealtime) {
            this.dealtime = dealtime;
        }

        public String getDealperson() {
            return dealperson;
        }

        public void setDealperson(String dealperson) {
            this.dealperson = dealperson;
        }

        public String getDealpersonname() {
            return dealpersonname;
        }

        public void setDealpersonname(String dealpersonname) {
            this.dealpersonname = dealpersonname;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }
    }

    public static class ImgattachlistBean {
        /**
         * id : 259
         * oid : 81
         * partid : 476
         * name : Fiel0.jpg
         * path : /upload/81/476/Fiel0.jpg
         * fullpath : ./upload/81/476/Fiel0.jpg
         * suffix : image/jpeg
         * size : 956890
         * remark : null
         * state : null
         * createtime : null
         * createperson : null
         */

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
        private Object createtime;
        private Object createperson;

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

        public void setFullpath(String fullpath) {
            this.fullpath = fullpath;
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

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public Object getCreateperson() {
            return createperson;
        }

        public void setCreateperson(Object createperson) {
            this.createperson = createperson;
        }
    }


}
