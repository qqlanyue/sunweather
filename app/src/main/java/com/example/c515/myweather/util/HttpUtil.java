package com.example.c515.myweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by C515 on 2017/5/11.
 */
public class HttpUtil {
    public static void sendOKHttpRequest(String address,okhttp3.Callback callback) {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
