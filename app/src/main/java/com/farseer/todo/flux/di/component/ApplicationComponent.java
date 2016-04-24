/*
 * ApplicationComponent      2016-04-19
 *
 */
package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.di.ForApplication;
import com.farseer.todo.flux.di.module.ApplicationModule;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.squareup.sqlbrite.BriteDatabase;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ApplicationComponent
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
@ForApplication
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

    /**
     * 获得Application
     */
    Application application();

    /**
     * 获得Resources
     */
    Resources resources();
}