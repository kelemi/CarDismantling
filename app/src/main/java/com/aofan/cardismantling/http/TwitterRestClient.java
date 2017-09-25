package com.aofan.cardismantling.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2015/12/31.
 */
public class TwitterRestClient {

    private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象

    static {
        client.setConnectTimeout(30000);
        client.setMaxRetriesAndTimeout(5,30000);
        client.setResponseTimeout(100000);
    }

    public static void getByAbsoluteUrl(String url, RequestParams params,
                                        AsyncHttpResponseHandler responseHandler) {

        client.get(url, params, responseHandler);
    }

    public static void postByAbsoluteUrl(String url, RequestParams params,
                                        AsyncHttpResponseHandler responseHandler) {

        client.post(url, params, responseHandler);
    }

    /**
     * 通过绝对地址下载文件(每次下载一个)
     * @param url
     * @param responseHandler
     */
    public static synchronized void  getFileByAbsoluteUrl(String url,
                                         BinaryHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);
    }
}
