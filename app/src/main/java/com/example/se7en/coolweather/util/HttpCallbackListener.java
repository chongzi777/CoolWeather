package com.example.se7en.coolweather.util;

/**
 * http请求回调接口
 * Created by se7en on 2016/2/19.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
