/*
 * ApplicationComponent      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux.di.application;

import android.app.Application;
import android.content.res.Resources;


import javax.inject.Singleton;

import dagger.Component;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
@Singleton
@Component(
        modules = {ApplicationModule.class}
)
public interface ApplicationComponent {

    public static final class Initializer {
        public static ApplicationComponent init(Application application) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }
    }

    void inject(Application application);

    Application application();

    Resources resources();
}