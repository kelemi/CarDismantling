package com.aofan.cardismantling.util;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.aofan.cardismantling.app.CustomApplication;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/11/22.
 */

public class CommonUtil {


    //获取文件的类型
    public static String guessMimeType(String path)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);

        if (contentTypeFor == null)
        {
            contentTypeFor = "application/octet-stream";
        }
        LogUtil.e("fileType:" + contentTypeFor);
        return contentTypeFor;
    }

    //校验并且弹出toast
    public static boolean validateAndToast(boolean validateResult,String tip)
    {
        if (validateResult==false)
        {
            ToastUtil.showShort(CustomApplication.getInstance(),tip);
        }

        return validateResult;
    }

    public static void putShareValue(String key, Object value) {
        SPUtils.put(CustomApplication.globalContext, key, value);
    }

    public static Object getShareValue(String key, Object defaultObject) {
        return SPUtils.get(CustomApplication.globalContext, key, defaultObject);
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    //判断是否是手机号码
    public static boolean isMobile(String mobile) {

        boolean flag = false;

        if (mobile.length() == 0) {

            return false;

        }

        String[] mobiles = mobile.split(",");

        int len = mobiles.length;

        if (len == 1) {

            return Pattern.matches("^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$", mobile);

        } else {

            for (int i = 0; i < len; i++) {

                if (isMobile(mobiles[i])) {

                    flag = true;

                } else {

                    flag = false;

                }

            }

        }

        return flag;

    }

    //格式化json数据变成类型 Object
    public static Object formatJsonToObject(JsonObject dataObject, String jsonKeyWord, TypeToken aimTypeToken) {
        return new Gson().fromJson(dataObject.get(jsonKeyWord), aimTypeToken.getType());
    }

    //格式化json数据变成类型 list
    public static List formatJsonToList(JsonObject dataObject, String jsonKeyWord, TypeToken aimTypeToken) {
        return new Gson().fromJson(dataObject.get(jsonKeyWord), aimTypeToken.getType());
    }

    //判断list是否为空
    public static boolean isListNullOrEmpty(List dataList) {

        return dataList == null || dataList.size() == 0 ? true : false;

    }

    //判断两个字符串是否一致
    public static boolean isTwoStrSame(String firstStr, String secondStr, String tip) {
        if (!firstStr.equals(secondStr)) {
            ToastUtil.showShort(CustomApplication.globalContext, tip);
            return false;
        } else {
            return true;
        }
    }


    public static boolean isListNullOrEmpty(List dataList, String tip) {

        if (dataList == null || dataList.size() == 0) {
            ToastUtil.showShort(CustomApplication.globalContext, tip);
            return true;

        } else {
            return false;
        }


    }


    //判断某一个对象是否为空
    public static boolean isStringNullOrEmpty(String str, String tip) {
        if (null == str || TextUtils.isEmpty(str)) {
            ToastUtil.showShort(CustomApplication.globalContext, tip);
            return true;
        } else {
            return false;
        }
    }

    //判断某一个对象是否为空
    public static boolean isObjectNull(Object object, String tip) {
        if (null == object) {
            ToastUtil.showShort(CustomApplication.globalContext, tip);
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEditTextEmpty(EditText editTexes, String tip) {
        if (TextUtils.isEmpty(editTexes.getText().toString())) {
            editTexes.requestFocus();
            ToastUtil.showShort(CustomApplication.globalContext, tip);
            return true;
        } else {
            return false;
        }
    }

    public static boolean isTextViewEmpty(TextView textView, String tip) {
        if (TextUtils.isEmpty(textView.getText().toString())) {
            ToastUtil.showShort(CustomApplication.globalContext, tip);
            return true;
        } else {
            return false;
        }
    }


}
