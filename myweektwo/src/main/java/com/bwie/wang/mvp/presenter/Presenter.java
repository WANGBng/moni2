package com.bwie.wang.mvp.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bwie.wang.mvp.model.Model;
import com.bwie.wang.mvp.view.activity.IView;
import com.bwie.wang.mvp.view.bean.LoginBean;
import com.bwie.wang.mvp.view.utils.ICallback;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wangbingjun on 2018/10/13.
 */

public class Presenter {

    private IView iv;
    private Model model;

    public void attch(IView iv){
        this.iv = iv;
        model = new Model();
    }
    public void detach(){//结束,防止泄露
        if (iv != null){
            iv =null;
        }
    }

    //进行效验的
    public void check(){
        if (TextUtils.isEmpty(iv.getMobile()) || TextUtils.isEmpty(iv.getPassword())){
            iv.check(false);
        }else {
            iv.check(true);
        }
    }

    public  void isFirst(){
        SharedPreferences sharedPreferences = iv.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String mobile = sharedPreferences.getString("mobile", "");

        String password = sharedPreferences.getString("password", "");
        if (!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(password)){
//            iv.gotoMain();
            iv.setMobile(mobile);
            iv.setPassword(password);
        }
    }


    public void login(String url){
        iv.show();
        final String mobile = iv.getMobile();
        final String password = iv.getPassword();
        url.concat("?mobile=").concat(mobile).concat("&password").concat(password);
        Type type = new TypeToken<LoginBean>(){
        }.getType();
        model.login(url, new ICallback() {

            @Override
            public void onSuccess(Object obj) {
                iv.dissmiss();
                iv.success(obj);
                SharedPreferences sharedPreferences = iv.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("mobile",mobile)
                .putString("password",password).commit();
                iv.gotoMain();
            }

            @Override
            public void onFailed(Exception e) {
                iv.dissmiss();
                iv.failed(e);
            }
        },type);

    }




}
