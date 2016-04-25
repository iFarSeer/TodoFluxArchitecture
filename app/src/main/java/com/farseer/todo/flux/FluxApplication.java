/*
 * FluxApplication      2016-04-18
 *
 */
package com.farseer.todo.flux;

import com.farseer.todo.flux.di.HasComponent;
import com.farseer.todo.flux.di.component.ApplicationComponent;

import android.app.Application;

/**
 * Flux Application
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-18
 */
public class FluxApplication extends Application implements HasComponent<ApplicationComponent>{

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
        initInjector();
    }

    private void initInjector() {
        component = ApplicationComponent.Initializer.init(this);
    }

    @Override
    public ApplicationComponent getComponent() {
        return component;
    }
}