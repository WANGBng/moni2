package com.bwie.wang.mvp.model;

import com.bwie.wang.mvp.view.utils.HttpUtils;
import com.bwie.wang.mvp.view.utils.ICallback;

import java.lang.reflect.Type;

/**
 * Created by wangbingjun on 2018/10/13.
 */

public class Model {
    public void login(String url, ICallback callback, Type type){
        HttpUtils .getInstance().get(url,callback,type);
    }
}
