package com.example.testdaggerhilt;

import android.app.Application;

import com.example.testdaggerhilt.db.AppComponent;

import dagger.hilt.android.HiltAndroidApp;
import io.reactivex.plugins.RxJavaPlugins;

@HiltAndroidApp
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> {}); // nothing or some logging

    }

}
