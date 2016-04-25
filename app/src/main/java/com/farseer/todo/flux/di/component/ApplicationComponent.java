/*
 * ApplicationComponent      2016-04-19
 *
 */
package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.action.creator.TodoActionCreator;
import com.farseer.todo.flux.di.ForApplication;
import com.farseer.todo.flux.di.module.ApplicationModule;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.store.Store;
import com.farseer.todo.flux.store.TodoStore;
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
        dependencies = {StorageComponent.class},
        modules = {ApplicationModule.class}
)
public interface ApplicationComponent {

    public static final class Initializer {
        public static ApplicationComponent init(Application application) {
            return DaggerApplicationComponent.builder()
                    .storageComponent(StorageComponent.Initializer.init(application, "aa"))
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }
    }

    void inject(Application application);


    //暴露给对象图

    /**
     * 获得Application
     */
    Application application();

    /**
     * 获得Resources
     */
    Resources resources();

    /**
     * 获得Action处理器
     */
    ActionDispatcher actionDispatcher();

    /**
     * 获得Data处理器
     */
    DataDispatcher dataDispatcher();

    /**
     * 获得TodoStore
     */
    Store todoStore();

    /**
     * 获得actionCreator
     */
    ActionCreator actionCreator();
}