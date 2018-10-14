package com.bwie.wang;

import android.app.Application;

/**
 * Created by wangbingjun on 2018/10/12.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler.getsInstance().init(this);
    }
}
