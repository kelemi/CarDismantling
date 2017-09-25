package com.aofan.cardismantling.common;

/**
 * Created by Administrator on 2016/10/25.
 */
public class Config {


    //默认的欢迎界面的时间
    public static final int DEFAULT_WELCOME_PAGE_TIME = 2000;

    public static final String APP_BASE_FOLDER = "/AoFan";
    //本地图片地址
    public static final String IMAGE_FOLDER = "/IMG";

    public static final Integer DEFAULT_PAGE_SIZE = 10;

    public static final String HTTP_PREX = "http://";

    public static final String URL_BASE_TEST = "http://192.168.2.142/zsj/index.php/";
    // http://localhost:8088/zsj/index.php/Home/System/getPartByCar

    public static final String URL_BASE_TEST2 = "http://192.168.2.191/zsj/index.php/";


    public static final String URL_BASE_TEST3 = "http://159p5l0509.imwork.net:10000/zsj/index.php/";

    public static final String URL_BASE_TEST4 = "http://192.168.2.68:10000/zsj/index.php/";

    public static final String URL_BASE_REAL = "http://222.184.127.50:10104/zsj/index.php/";

    public static final String URL_BASE_REAL2 = "http://211.149.250.152/zsj/index.php/";

    //离开两测试
    public static final String URL_TEST_NEW = "http://192.168.2.192:8080/www/zsj/index.php";

    //离开两测试图片
    public static final String URL_TEST_NEW_PIC = "http://192.168.2.192:8080/www/zsj";

    //当前真实地址
    public static final String URL_BASE_REAL3 = "http://59.110.112.125:8800/zsj/index.php";


    public static final String URL_TO_LOAD_PIC = "http://192.168.2.142/zsj";

    public static final String URL_TO_LOAD_PIC2 = "http://192.168.2.191/zsj";

    public static final String URL_TO_LOAD_PIC3 = "http://159p5l0509.imwork.net:10000/zsj";

    public static final String URL_TO_LOAD_PIC4 = "http://192.168.2.68:10000/zsj";

    public static final String URL_TO_LOAD_PIC5 = "http://222.184.127.50:10104/zsj";

    public static final String URL_TO_LOAD_PIC_REAL2 = "http://211.149.250.152/zsj/";

    public static final String URL_TO_LOAD_PIC_REAL3 = "http://59.110.112.125:8800/zsj/";


    //当前的真实地址的图片base路径
    public static final String URL_TO_LOAD_PIC_IN_USE = URL_TO_LOAD_PIC_REAL3;

    public static final String URL_BASE_IN_USE = URL_BASE_REAL3;

    //离开两本季测试
    //public static final String URL_BASE_IN_USE = URL_TEST_NEW;

    //拍照类型  行驶证，车辆，车主身份证前后照，代理人身份证前后照，售车人身份证前后照 企业营业执照
    public final static int TAKE_PHOTO_TYPE_XINGSHIZHENG = 1;
    public final static int TAKE_PHOTO_TYPE_CHELIANG = 2;
    public final static int TAKE_PHOTO_TYPE_CHEZHU_IDCARD_FRONT = 3;
    public final static int TAKE_PHOTO_TYPE_CHEZHU_IDCARD_BACK = 4;
    public final static int TAKE_PHOTO_TYPE_DAILI_IDCARD_FRONT = 5;
    public final static int TAKE_PHOTO_TYPE_DAILI_IDCARD_BACK = 6;
    public final static int TAKE_PHOTO_TYPE_CARSALER_IDCARD_FRONT = 7;
    public final static int TAKE_PHOTO_TYPE_CARSALER_IDCARD_BACK = 8;
    public final static int TAKE_PHOTO_TYPE_BUSINESS_LICENCE = 9;


    //零件的类型：发动机，底盘，驾驶室，车箱
    public final static int TYPE_OF_LINGJIAN_FADONGJI_COMPONENT = 1;
    public final static int TYPE_OF_LINGJIAN_DIPAN_COMPONENT = 2;
    public final static int TYPE_OF_LINGJIAN_JIASHISHI_COMPONENT = 3;
    public final static int TYPE_OF_LINGJIAN_CHEXIANG_COMPONENT = 4;


    //废弃物类型：需要拆解的电池废弃物，需要拆解的其他废弃物，缺失的电池废弃物，缺失的其他废弃物
    public final static int TYPE_OF_LINGJIAN_NEED_CHAIJIE_DIANCHI_FEIQIWU = 5;
    public final static int TYPE_OF_LINGJIAN_NEED_CHAIJIE_OTHER_FEIQIWU = 6;
    public final static int TYPE_OF_LINGJIAN_QUESHI_DIANCHI_FEIQIWU = 7;
    public final static int TYPE_OF_LINGJIAN_QUESHI_OTHER_FEIQIWU = 8;

    //四大总成:发动机总成 底盘总成 驾驶室总成 车箱总成
    public final static String TYPE_STR_OF_LINGJIAN_COMPONENT_FADONGJI = "发动机总成";
    public final static String TYPE_STR_OF_LINGJIAN_COMPONENT_DIPAN = "底盘总成";
    public final static String TYPE_STR_OF_LINGJIAN_COMPONENT_JIASHISHI = "驾驶室总成";
    public final static String TYPE_STR_OF_LINGJIAN_COMPONENT_CHEXIANG = "车箱总成";
    public final static String TYPE_STR_OF_LINGJIAN_DANGEROUS_FEIQIWU = "危险废弃物";

    //四大废弃物类型
    public final static String TYPE_STR_OF_LINGJIAN_NEED_CHAIJIE_DIANCHI_FEIQIWU = "需要拆解电池废弃物";
    public final static String TYPE_STR_OF_LINGJIAN_NEED_CHAIJIE_OTHER_FEIQIWU = "需要拆解其他废弃物";
    public final static String TYPE_STR_OF_LINGJIAN_QUESHI_DIANCHI_FEIQIWU = "缺失电池废弃物";
    public final static String TYPE_STR_OF_LINGJIAN_QUESHI_OTHER_FEIQIWU = "缺失其他废弃物";
    ;


    //拆解方式类型：精拆，粗拆
    public final static String TYPE_STR_OF_CHAIJIEWAY_JINGCHAI = "精拆";
    public final static String TYPE_STR_OF_CHAIJIEWAY_CUCHAI = "粗拆";


    //menu的类型str
    public final static String MENU_TYPE_CAR_ANALYSIS = "车辆分析";
    public final static String MENU_TYPE_PAIGONGDAN = "派工单";
    public final static String MENU_TYPE_CHECK_KUCUN = "查看库存";
    public final static String MENU_TYPE_DATA_TONGJI = "数据报表";
    public final static String MENU_TYPE_JOB_WAIT_TODO = "待办事项";
    public final static String MENU_TYPE_JOB_HAS_DO = "已办事项";


    //汽车派工状态：未派工，派工中，已派工
    public final static String TYPE_OF_PAIGONG_STATE_NOT_PAIGONG = "未派工";
    public final static String TYPE_OF_PAIGONG_STATE_PAIGONGING = "派工中";
    public final static String TYPE_OF_PAIGONG_STATE_HAS_PAIGONG = "已派工";


    //action
    //还有剩余零件就完成派工
    public final static String ACTION_FINISH_PAIGONG_WITH_LEFT_LINGJIAN = "action_finish_paigong_with_left_lingjian";


    //action
    //还有未完成拆解的派工单就完成拆解
    public final static String ACTION_FINISH_CHAIJIE_WITH_LEFT_PAIGONGDAN = "action_finish_chaijie_with_left_paigongdan";

    //action 退出app
    public final static String ACTION_EXIST_APP = "action_exist_app";

    //提示dialogfrag的tag
    public final static String DIALOG_FRAGMENT_TIP = "dialog_fragment_tip";

}
