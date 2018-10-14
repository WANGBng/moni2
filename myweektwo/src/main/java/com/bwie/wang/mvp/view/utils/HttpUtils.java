package com.bwie.wang.mvp.view.utils;


import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangbingjun on 2018/10/13.
 */

public class HttpUtils {
    private static volatile HttpUtils instance;
    private OkHttpClient client;
    private Handler handler = new Handler();


    private HttpUtils() {
        client = new OkHttpClient();
    }

    public static HttpUtils getInstance() {
        if (instance == null){
            synchronized (HttpUtils.class){
                if (null == instance){
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }
    public void get(String url, final ICallback callback, final Type type){
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {//失败传的
            @Override
            public void onFailure(final Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailed(e);
                    }
                });
            }

            @Override//成功传的
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                Gson gson= new Gson();
                final Object o = gson.fromJson(result, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(o);
                    }
                });
            }
        });
    }
}