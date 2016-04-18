/*
 * FluxApplication      2016-04-18
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux;

import android.app.Application;

import com.farseer.todo.flux.di.application.ApplicationComponent;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-18
 */
public class FluxApplication extends Application {

    private ApplicationComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }

    public ApplicationComponent component() {
        return component;
    }

    //初始化Application
    private void initApplication() {
        component = ApplicationComponent.Initializer.init(this);
    }
}