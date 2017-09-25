package com.aofan.cardismantling.mvp.takephotostorecordinfo.uploadphoto;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.io.File;

/**
 * Created by Administrator on 2016/11/24.
 */

public class UploadPhotoContract {

    public interface View extends BaseView<Presenter>
    {
        void uploadPhoto();

        void showUploadPhotoSuccess();

        void showError(String tip);


        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter
    {
        //void uploadPhoto(String fileType,String userId,File file);

        /**
         *
         * @param haoPaiNum    汽车号牌号码
         * @param owner         汽车所有人
         * @param address       地址
         * @param shiYongXingZhi 试用性质
         * @param pinPaiXingHao  品牌型号
         * @param carIdCode      车辆识别代号
         * @param faDongJiNum     发动机号码
         * @param registerDate     注册日期
         * @param faZhengDate      发证日期
         * @param userId           用户id
         * @param picType          图片类型
         * @param file             文件
         */
        void uploadOtherPic( String haoPaiNum,
                                     String owner,
                                     String address,
                                     String shiYongXingZhi,
                                     String pinPaiXingHao,
                                     String carIdCode,
                                     String faDongJiNum,
                                     String registerDate,
                                     String faZhengDate,
                                     String userId,
                                     String picType,
                                     File file);
    }
}
