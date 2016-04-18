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

import com.farseer.todo.flux.di.activity.ActivityComponent;

import javax.inject.Inject;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Resources resources;

    private ActivityComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = ActivityComponent.Initializer.init(this);
        component.inject(this);
    }

    public ActivityComponent getComponent() {
        return component;
    }
}