package com.aofan.cardismantling.http;

import com.aofan.cardismantling.common.Config;
import com.aofan.cardismantling.util.CommonUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class FileUploadAboutService {


    public final static  String BASE_URL_TEST = "http://192.168.2.142:6666/";
    public final static  String BASE_URL_REAL = "http://211.149.250.152:6001/";

    public final static  String BASE_URL_REAL_2 = "http://hall.hdtcom.com/";

    public final static String BASE_URL_IN_USE = BASE_URL_REAL_2;

    /**
     * 提交零件拆解信息
     * @param userId
     * @param oId
     * @param partId
     * @param partName
     * @param lingJianCount
     * @param fileList
     * @param responseHandler
     */
    public static void submitLingJianChaiJieInfo(String userId, String oId, String partId, String partName, String lingJianCount, List<File> fileList, AsyncHttpResponseHandler responseHandler)
    {

        RequestParams params = new RequestParams();
        params.put("userid",userId);
        params.put("oid",oId);
        params.put("partid",partId);
        params.put("PartName",partName);
        params.put("itemcount",lingJianCount);

        for (int i=0;i<fileList.size();i++)
        {
            try {
                File file =  fileList.get(i);
                params.put("file"+(i+1),file, CommonUtil.guessMimeType(file.getName()),file.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        TwitterRestClient.postByAbsoluteUrl(Config.URL_BASE_IN_USE +"Home/System/saveTempCompleteOrder", params, responseHandler);
    }

}
