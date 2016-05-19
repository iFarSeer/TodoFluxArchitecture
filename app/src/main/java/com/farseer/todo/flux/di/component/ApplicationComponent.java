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

import android.app.Application;
import android.content.res.Resources;
import com.farseer.todo.flux.FluxApplication;
import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.di.ForApplication;
import com.farseer.todo.flux.di.module.ApplicationModule;
import com.farseer.todo.flux.dispatcher.Dispatcher;
import com.farseer.todo.flux.store.Store;
import dagger.Component;

import javax.inject.Named;
import javax.inject.Singleton;

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

    void inject(FluxApplication application);

    //暴露给对象图

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
     * @return 事件分发器
     */
    @Named("actionDispatcher")
    Dispatcher actionDispatcher();

    /**
     * 获得数据分发器.
     *
     * @return 数据分发器
     */
    @Named("dataDispatcher")
    Dispatcher dataDispatcher();

    /**
     * 获得TodoStore.
     *
     * @return Store
     */
    Store store();

    /**
     * 获得actionCreator.
     *
     * @return ActionCreator
     */
    ActionCreator actionCreator();

    /**
     * ApplicationComponent的Initializer.
     */
    public static final class Initializer {
        /**
         * 初始化ApplicationComponent.
         *
         * @param application application
         * @return ApplicationComponent
         */
        public static ApplicationComponent init(Application application) {
            return DaggerApplicationComponent.builder()
                    .storageComponent(StorageComponent.Initializer.init(application, "aa"))
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }
    }
}