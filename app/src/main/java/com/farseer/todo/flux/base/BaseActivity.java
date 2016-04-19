/*
 * BaseActivity      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.di.component.ActivityComponent;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;

import javax.inject.Inject;

/**
 * Activity基类
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected ActionCreator actionCreator;

    @Inject
    protected Resources resources;

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Inject
    protected DataDispatcher dataDispatcher;

    private ActivityComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    protected void inject() {
        component = ActivityComponent.Initializer.init(this);
        component.inject(this);
    }

    public ActivityComponent getComponent() {
        return component;
    }
}