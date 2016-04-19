/*
 * ActivityModule      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux.di.module;

import com.farseer.todo.flux.di.PerActivity;
import com.farseer.todo.flux.view.TodoActivity;

import dagger.Module;
import dagger.Provides;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
@Module( includes = ActivityModule.class)
public class TodoHomeModule {
    private TodoActivity activity;

    public TodoHomeModule(TodoActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    TodoActivity activity() {
        return activity;
    }
}