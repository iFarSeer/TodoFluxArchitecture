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

import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.action.creator.TodoActionCreator;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.dispatcher.Dispatcher;
import com.farseer.todo.flux.store.Store;
import com.farseer.todo.flux.store.TodoStore;
import com.squareup.sqlbrite.BriteDatabase;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *  Application Module
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    Resources resources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    @Named("actionDispatcher")
    Dispatcher actionDispatcher(ActionDispatcher actionDispatcher) {
        return actionDispatcher;
    }

    @Provides
    @Singleton
    @Named("dataDispatcher")
    Dispatcher dataDispatcher(DataDispatcher dataDispatcher) {
        return dataDispatcher;
    }

    @Provides
    @Singleton
    Store todoStore(TodoStore todoStore) {
        return todoStore;
    }

    @Provides
    @Singleton
    ActionCreator actionCreator(TodoActionCreator todoActionCreator) {
        return todoActionCreator;
    }
}