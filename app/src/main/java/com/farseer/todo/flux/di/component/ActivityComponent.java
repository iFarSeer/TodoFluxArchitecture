/*
 *
 *    Copyright 2016 zhaosc
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.FluxApplication;
import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.di.PerActivity;
import com.farseer.todo.flux.di.module.ActivityModule;
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

    /**
     * 注入BaseActivity.
     *
     * @param activity activity
     */
    public void inject(BaseActivity activity);

    /**
     * 注入BaseFragment.
     *
     * @param fragment fragment
     */
    public void inject(BaseFragment fragment);


    //暴露给对象图

    /**
     * 获得activity.
     *
     * @return activity
     */
    BaseActivity activity();

    /**
     * 获得Application.
     *
     * @return Application
     */
    Application application();

    /**
     * 获得Resources.
     *
     * @return Resources
     */
    Resources resources();

    /**
     * 获得事件分发器.
     *
     * @return Dispatcher.
     */
    @Named("actionDispatcher")
    Dispatcher actionDispatcher();

    /**
     * 获得数据分发器.
     *
     * @return Dispatcher
     */
    @Named("dataDispatcher")
    Dispatcher dataDispatcher();

    /**
     * 获得TodoStore.
     *
     * @return Store
     */
    Store todoStore();

    /**
     * 获得actionCreator.
     *
     * @return ActionCreator
     */
    ActionCreator actionCreator();

    /**
     * ActivityComponent的Initializer.
     */
    public static final class Initializer {

        /**
         * 初始化ActivityComponent.
         *
         * @param activity activity
         * @return ActivityComponent
         */
        public static ActivityComponent init(BaseActivity activity) {
            return DaggerActivityComponent.builder()
                    .applicationComponent(((FluxApplication) activity.getApplicationContext()).getComponent())
                    .activityModule(new ActivityModule(activity))
                    .build();
        }
    }
}