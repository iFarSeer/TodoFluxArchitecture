/*
 * ActivityModule      2016-04-19
 *
 */
package com.farseer.todo.flux.di.module;

import com.farseer.todo.flux.di.PerActivity;
import com.farseer.todo.flux.view.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
@Module
public class ActivityModule {
    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    BaseActivity activity() {
        return activity;
    }


}