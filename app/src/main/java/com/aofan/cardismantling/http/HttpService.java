package com.aofan.cardismantling.http;


import com.google.gson.JsonObject;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;


/**
 * 网络服务
 * Created by Administrator on 2016/10/25.
 */
public interface HttpService {

    @POST("getNewsList")
    Observable<JsonObject> getNewsList(@Query("pageIndex") String pageIndex,
                                       @Query("pageSize") String pageSize,
                                       @Query("type") String newsType);


    //获取未出手续车辆列表
/*    @POST("GetUnSignCarList")
    Observable<JsonObject> getUnSignCarList(
            @Query("userid") String userid,
            @Query("rows") String rows,
            @Query("page") String page);*/

    @FormUrlEncoded
    @POST("Home/System/GetUnSignCarList")
    Observable<JsonObject> getUnSignCarList(
            @Field("userid") String userid,
            @Field("rows") String rows,
            @Field("page") String page);

    //保存部分完成拆解
//精拆：拆解类型cjtype, userid 用户id,partid 部件id，PartName 部件名称，itemcount 数目，DId 分析id,附件文件最多是4张3)
//粗拆：拆解类型cjtype, userid 用户id，DId 分析,(粗拆时候cjtype传入粗拆，照片传入汽车照片而不是零件照片)
    @Multipart
    @POST("Home/System/saveUnSignCaijie")
    Observable<JsonObject> saveUnSignCaijie(
            @Part("cjtype") RequestBody cjtype,
            @Part("userid") RequestBody userId,
            @Part("partid") RequestBody partid,
            @Part("PartName") RequestBody PartName,
            @Part("itemcount") RequestBody itemcount,
            @Part("DId") RequestBody DId,
            @PartMap Map<String, RequestBody> picMaps
    );

    /**
     * @param userid
     * @param VehicleNumber         车牌号码
     * @param CustomInFactoryNumber 自定义入场编号
     * @param cjtype                拆解方式
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/AddUnSignCar")
    Observable<JsonObject> addUnSignCar(
            @Field("userid") String userid,
            @Field("VehicleNumber") String VehicleNumber,
            @Field("CustomInFactoryNumber") String CustomInFactoryNumber,
            @Field("cjtype") String cjtype);

    /*
      获取未出手续车 拆解详情
      分析id：did
     */
    @FormUrlEncoded
    @POST("Home/System/getChaiJieDetail")
    Observable<JsonObject> getChaiJieDetail(
            @Field("did") String did);

    //完成拆解接口
    @FormUrlEncoded
    @POST("Home/System/no_saveCompleteOrder")
    Observable<JsonObject> no_saveCompleteOrder(
            @Field("did") String did);

    //获取汽车零件列表，用于选择后上传照片提交
    //传入 baseid汽车主键 ；dismantlestate:已分析,未分析
    @FormUrlEncoded
    @POST("Home/System/getUnSignPartByCar")
    Observable<JsonObject> getUnSignPartByCar(
            @Field("baseid") String baseid);

    @FormUrlEncoded
    @POST("Home/System/login")
    Observable<JsonObject> login(
            @Field("username") String userName,
            @Field("pwd") String passWord);


    /**
     * 上传行驶证和其他照片信息（上传行驶证的时候要带文字信息，其他的只要图片类型加上文字信息就可以）
     *
     * @param haoPaiNum
     * @param owner
     * @param address
     * @param shiYongXingZhi
     * @param pinPaiXingHao
     * @param carIdCode
     * @param faDongJiNum
     * @param registerDate
     * @param faZhengDate
     * @param userId
     * @param picType
     * @param file
     * @return
     */
    @Multipart
    @POST("Home/System/UploadBasePic")
    Observable<JsonObject> uploadXingShiZhengInfo(
            @Part("hphm") RequestBody haoPaiNum,
            @Part("cllx") RequestBody carLeiXing,
            @Part("syr") RequestBody owner,
            @Part("address") RequestBody address,
            @Part("syxz") RequestBody shiYongXingZhi,
            @Part("ppxh") RequestBody pinPaiXingHao,
            @Part("clsbdh") RequestBody carIdCode,
            @Part("fdjhm") RequestBody faDongJiNum,
            @Part("zcrq") RequestBody registerDate,
            @Part("fzrq") RequestBody faZhengDate,
            @Part("userid") RequestBody userId,
            @Part("pictype") RequestBody picType,
            @Part MultipartBody.Part file);


    /**
     * 上传行驶证以外的其他图片：
     *
     * @param file
     * @return
     */
    @Multipart
    @POST("Home/System/UploadBasePic")
    Observable<JsonObject> uploadOtherBasePic(

            @Part("userid") RequestBody userId,
            @Part("pictype") RequestBody picType,
            @Part MultipartBody.Part file);

    /**
     * 保存零件拆解提交信息
     */
    @Multipart
    @POST("Home/System/saveTempCompleteOrder_V1")
    Observable<JsonObject> submitLingJianChaiJieInfo(
            @Part("cjtype") RequestBody chaijieType,
            @Part("userid") RequestBody userId,
            @Part("oid") RequestBody oId,
            @Part("partid") RequestBody partId,
            @Part("PartName") RequestBody partName,
            @Part("itemcount") RequestBody lingJianChaiJieCount,
            @PartMap Map<String, RequestBody> picMaps
    );


    /**
     * 保存零件粗拆拆解信息
     */
    @Multipart
    @POST("Home/System/saveTempCompleteOrder_V1")
    Observable<JsonObject> submitCuChaiChaiJieInfo(
            @Part("cjtype") RequestBody chaijieType,
            @Part("userid") RequestBody userId,
            @Part("oid") RequestBody oId,
            @PartMap Map<String, RequestBody> picMaps
    );

  /*  @Field("hphm") String haoPaiNum,
    @Field("syr") String owner,
    @Field("address") String address,
    @Field("syxz") String shiYongXingZhi,
    @Field("ppxh") String pinPaiXingHao,
    @Field("clsbdh") String carIdCode,
    @Field("fdjhm") String faDongJiNum,
    @Field("zcrq") String registerDate,
    @Field("fzrq") String faZhengDate,
    @Field("userid") String userId,
    @Field("pictype") String picType,*/



    /*// 创建 RequestBody，用于封装 请求RequestBody
    RequestBody requestFile =
            RequestBody.create(MediaType.parse("multipart/form-data"), file);

    // MultipartBody.Part is used to send also the actual file name
    MultipartBody.Part body =
            MultipartBody.Part.createFormData("image", file.getName(), requestFile);

    文／Tamic（简书作者）
    原文链接：http://www.jianshu.com/p/acfefb0a204f
    著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。*/

    @FormUrlEncoded
    @POST("Home/System/getMenu")
    Observable<JsonObject> getMenuList(
            @Field("userid") String userId);

    /**
     * 获取带分析车辆列表
     *
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getCarList")
    Observable<JsonObject> getWaitAnalysisCarList(
            @Field("userid") String userId,
            @Field("state") String carAnalysisState,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize);


    /**
     * 获取汽车的所有零件信息(各种总成及零件)
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getParts")
    Observable<JsonObject> getCarAllToChaiJieLingJianInfo(
            @Field("userid") String userId
    );

    /**
     * 获取废弃物信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getdangeriousParts")
    Observable<JsonObject> getAllFeiQiWuInfo(
            @Field("userid") String userId
    );


    /**
     * 提交车辆分析信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/saveDismantle_V1")
    Observable<JsonObject> submitCarAnalysisInfo(
            @Field("baseid") String carId,
            @Field("type") String chaiJieWay,
            @Field("userid") String userId,
            @Field("partsval") String analysisLingJianInfo,
            @Field("unpartsval") String unAnalysisLingJianInfo
    );

    /**
     * 粗拆分析的时候提交选择的危险废弃物的信息
     *
     * @param carId
     * @param chaiJieWay
     * @param userId
     * @param toChaiJieFeiQiWuInfo
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/saveDismantle_V1")
    Observable<JsonObject> submitCarAnalysisInfoCuChai(
            @Field("baseid") String carId,
            @Field("type") String chaiJieWay,
            @Field("userid") String userId,
            @Field("partsval") String toChaiJieFeiQiWuInfo);


    /**
     * 获取派工相关的车辆信息
     *
     * @param userId          用户id
     * @param carPaiGongState 汽车派工状态
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getPgList")
    Observable<JsonObject> getPaiGongAboutCarList(
            @Field("userid") String userId,
            @Field("state") String carPaiGongState,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize);


    /**
     * 获取一辆车的派工单
     *
     * @param did       主键
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getOrderList")
    Observable<JsonObject> getOneCarPaiGongDanList(
            @Field("did") String did,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize);


    /**
     * 获取已经分析的车辆可以选择的零件
     *
     * @param analysisCarId         已经分析的车辆的主键
     * @param lingJianAnalysisState 零件分析状态 ：已分析，未分析
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getPartByCar_v1")
    Observable<JsonObject> getCanChooseChaiJieLingJian(
            @Field("did") String analysisCarId,
            @Field("dismantlestate") String lingJianAnalysisState);


    /**
     * 获取可以选择传拆解人员
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getPersonList")
    Observable<JsonObject> getCanChooseChaiJieWorkers(
            @Field("userid") String userId);

    //添加派工单
    @FormUrlEncoded
    @POST("Home/System/saveOrder")
    Observable<JsonObject> addNewPaiGongDan(

            @Field("cjtype") String chaijieType,         //拆解类型
            @Field("userid") String userId,              //用户id
            @Field("did") String analysisCarId,          //分析车辆的id
            @Field("requirement") String chaiJieRequire, //拆解要求
            @Field("dpersons") String chaiJieWorkerNames,// 名称,名称
            @Field("persons") String chaiJieWorkIdAndNames,//64^1000demo,65^颜荐轩
            @Field("dispatchpartments") String chaiJieLingJianNames,//反光镜，轮子
            @Field("partments") String chaiJieLingJianIdAndNames //PartId^PartName,artId^PartName
    );

    /**
     * 完成派工
     *
     * @param did
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/Completepg")
    Observable<JsonObject> completePaiGong(
            @Field("did") String did);


    /**
     * 获取用户可以处理的待办事项类型
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getdbmainList")
    Observable<JsonObject> getUserWaitToDoJobKind(
            @Field("userid") String userId);


    /**
     * 获取用户待办事项列表
     *
     * @param userId
     * @param jobKind   待办事项类型：待拆解任务，待确认派工单
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getDBList")
    Observable<JsonObject> getUserWaitToDoJobList(
            @Field("userid") String userId,
            @Field("type") String jobKind,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize);


    /**
     * 获取拆解派工单的详情
     *
     * @param paiGongDanOid
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getOrderDetail")
    Observable<JsonObject> getChaiJiePaiGongDanDetail(
            @Field("oid") String paiGongDanOid);


    @FormUrlEncoded
    @POST("Home/System/getOrderDetailnew")
    Observable<JsonObject> getChaiJiePaiGongDanDetailNew(
            @Field("oid") String paiGongDanOid,
            @Field("cjtype ") String chaiJieType);


    /**
     * 完成拆解接口（拆解工人完成拆解接口）
     *
     * @param userId
     * @param oId
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/saveCompleteOrder")
    Observable<JsonObject> completeChaiJie(
            @Field("userid") String userId,
            @Field("oid") String oId);

    /**
     * 确认拆解派工单(派工的人)
     *
     * @param userId
     * @param oId
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/realcompleteorder")
    Observable<JsonObject> ensureChaiJiePaiGongDan(
            @Field("userid") String userId,
            @Field("oid") String oId);


    /**
     * 判断汽车是否有未完成的派工单
     *
     * @param carId 汽车id
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/checkbeforeCarCompleteChaiJie")
    Observable<JsonObject> checkCarHasNotFinishedPaiGongDan(
            @Field("baseid") String carId
    );

    /**
     * 汽车完成拆解
     *
     * @param userId 确认人id
     * @param carId  汽车id
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/CarCompleteChaiJie")
    Observable<JsonObject> carCompleteChaiJie(
            @Field("userid") String userId,
            @Field("baseid") String carId,
            @Field("cjtype ") String chaijieType);


    /**
     * 获取个人信息
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/GetUsersById")
    Observable<JsonObject> getUserInfoById(
            @Field("userid") String userId);


    /**
     * 获取同事列表
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/GetUsersByDeptManaFacture")
    Observable<JsonObject> getColleagues(
            @Field("userid") String userId);

    /**
     * 修改密码
     *
     * @param userId
     * @param oldPass
     * @param newPass
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/changePwd")
    Observable<JsonObject> modifyPass(
            @Field("userid") String userId,
            @Field("oldpwd") String oldPass,
            @Field("newpwd") String newPass);


    /**
     * 获取版本信息
     *
     * @return
     */
    @POST("Home/System/getVersion")
    Observable<JsonObject> getVersion();

    /**
     * 获取已办任务类型
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getybmainList")
    Observable<JsonObject> getHasDoJobKind(
            @Field("userid") String userId
    );


    // type:已完成拆解任务，已确认派工单,
//userid,用户id
//page,页数
//rows，每页行数

    @FormUrlEncoded
    @POST("Home/System/getYBList")
    Observable<JsonObject> getHasDoJobList(
            @Field("userid") String userId,
            @Field("type") String jobKind,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize
    );

    /**
     * 获取汽车库存信息
     *
     * @param userId
     * @param jobKind
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getCarKCList")
    Observable<JsonObject> getCarKuCunList(
            @Field("userid") String userId,
            @Field("KCstate") String jobKind,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize
    );

    /**
     * 获取零件库存情况  （第一版：getStockList）
     *
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getStockList_V2")
    Observable<JsonObject> getLingJianKuCunList(
            @Field("userid") String userId,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize,
            @Field("State") String lingJianState,
            @Field("seller") String seller,
            @Field("minprice") String minPrice,
            @Field("maxprice") String maxPrice,
            @Field("minselltime") String minSellTime,
            @Field("maxselltime") String maxSellTime);


    /**
     * 获取人员统计数据
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getPersonReport")
    Observable<JsonObject> getWorkerTongJiData(
            @Field("userid") String userId,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize
    );

    /**
     * 获取汽车统计数据
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/getCarReport")
    Observable<JsonObject> getCarTongJiData(
            @Field("userid") String userId,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize
    );


    /**
     * 获取零件信息
     *
     * @param lingJianInfo
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/GetEwminfo")
    Observable<JsonObject> getLingJianInfo(
            @Field("id") String lingJianInfo
    );


    /**
     * 获取销售统计数据
     *
     * @param userid      用户id
     * @param minSellTime
     * @param maxSellTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("Home/System/StockAnalyse")
    Observable<JsonObject> getSellTongJiData(
            @Field("userid") String userid,
            @Field("minselltime") String minSellTime,
            @Field("maxselltime") String maxSellTime,
            @Field("page") String pageIndex,
            @Field("rows") String pageSize
    );


    class Factory {

        private static OkHttpClient generateOkHttpClient() {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .readTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .build();


            /*InputStream cerInputStream = null;
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[]{cerInputStream},null,null);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .readTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager)
                    .build();*/


            return httpClient;
        }

        public static HttpService create(String baseUrl) {

            /*Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();*/

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl + "/")
                    .client(generateOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(HttpService.class);
        }
    }
}
