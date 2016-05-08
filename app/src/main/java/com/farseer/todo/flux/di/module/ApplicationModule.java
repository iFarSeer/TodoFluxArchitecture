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

package com.farseer.todo.flux.di.module;

import android.app.Application;
import android.content.res.Resources;
import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.action.creator.TodoActionCreator;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.dispatcher.Dispatcher;
import com.farseer.todo.flux.store.Store;
import com.farseer.todo.flux.store.TodoStore;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Application Module.
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */

@Module
public class ApplicationModule {

    private final Application application;

    /**
     * 构造ApplicationModule.
     *
     * @param application application
     */
    public ApplicationModule(Application application) {
        this.application = application;
    }

    /**
     * 获得Application.
     *
     * @return application
     */
    @Provides
    @Singleton
    Application getApplication() {
        return application;
    }

    /**
     * 获得Resources.
     *
     * @return resources
     */
    @Provides
    @Singleton
    Resources resources() {
        return application.getResources();
    }

    /**
     * 获得事件分发器.
     *
     * @param actionDispatcher 事件分发器
     * @return actionDispatcher
     */
    @Provides
    @Singleton
    @Named("actionDispatcher")
    Dispatcher actionDispatcher(ActionDispatcher actionDispatcher) {
        return actionDispatcher;
    }

    /**
     * 获得数据分发器.
     *
     * @param dataDispatcher 数据分发器
     * @return dataDispatcher
     */
    @Provides
    @Singleton
    @Named("dataDispatcher")
    Dispatcher dataDispatcher(DataDispatcher dataDispatcher) {
        return dataDispatcher;
    }

    /**
     * 获得TodoStore.
     *
     * @param todoStore todoStore
     * @return todoStore
     */
    @Provides
    @Singleton
    Store todoStore(TodoStore todoStore) {
        return todoStore;
    }


    /**
     * 获得ActionCreator.
     *
     * @param todoActionCreator todoActionCreator
     * @return ActionCreator
     */
    @Provides
    @Singleton
    ActionCreator actionCreator(TodoActionCreator todoActionCreator) {
        return todoActionCreator;
    }
}