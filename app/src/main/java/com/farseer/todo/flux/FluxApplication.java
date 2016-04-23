/*
 * FluxApplication      2016-04-18
 *
 */
package com.farseer.todo.flux;

import android.app.Application;

import com.farseer.todo.flux.di.component.ApplicationComponent;

/**
 * Flux Application
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
        initializeApplication();
    }

    public ApplicationComponent component() {
        return component;
    }

    //初始化Application
    private void initializeApplication() {
        initializeInjector();
    }

    private void initializeInjector() {
        component = ApplicationComponent.Initializer.init(this);
    }
}