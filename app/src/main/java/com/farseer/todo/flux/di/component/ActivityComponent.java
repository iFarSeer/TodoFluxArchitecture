/*
 * ActivityComponent      2016-04-19
 *
 */
package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.FluxApplication;
import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.di.PerActivity;
import com.farseer.todo.flux.di.module.ActivityModule;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.dispatcher.Dispatcher;
import com.farseer.todo.flux.store.Store;
import com.farseer.todo.flux.view.base.BaseActivity;
import com.farseer.todo.flux.view.base.BaseFragment;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Named;

import dagger.Component;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
@PerActivity
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {ActivityModule.class}
)
public interface ActivityComponent {

    public static final class Initializer {
        public static ActivityComponent init(BaseActivity activity) {
            return DaggerActivityComponent.builder()
                    .applicationComponent(((FluxApplication)activity.getApplicationContext()).component())
                    .activityModule(new ActivityModule(activity))
                    .build();
        }
    }

    public void inject(BaseActivity activity);

    public void inject(BaseFragment activity);

    BaseActivity activity();

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