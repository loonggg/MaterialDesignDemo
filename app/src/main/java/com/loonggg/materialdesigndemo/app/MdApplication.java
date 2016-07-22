package com.loonggg.materialdesigndemo.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by loongggdroid on 2016/7/22.
 */
public class MdApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sp = this.getSharedPreferences("loonggg", this.MODE_PRIVATE);
        boolean isNight = sp.getBoolean("night", false);
        if (isNight) {
            //使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
