package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.di.ForApplication;
import com.farseer.todo.flux.di.module.ApplicationModule;
import com.farseer.todo.flux.dispatcher.Dispatcher;
import com.farseer.todo.flux.store.Store;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Named;
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
    @Named("actionDispatcher")
    Dispatcher actionDispatcher();

    /**
     * 获得Data处理器
     */
    @Named("dataDispatcher")
    Dispatcher dataDispatcher();

    /**
     * 获得TodoStore
     */
    Store todoStore();

    /**
     * 获得actionCreator
     */
    ActionCreator actionCreator();
}