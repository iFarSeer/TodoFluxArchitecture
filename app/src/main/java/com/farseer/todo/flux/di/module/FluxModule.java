package com.farseer.todo.flux.di.module;

import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.action.creator.TodoActionCreator;
import com.farseer.todo.flux.di.ForApplication;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.store.Store;
import com.farseer.todo.flux.store.TodoStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhaosc on 16/4/24.
 */

@Module
public class FluxModule {

    public FluxModule() {
    }

    @Provides
    @Singleton
    ActionDispatcher actionDispatcher() {
        return new ActionDispatcher();
    }

    @Provides
    @Singleton
    DataDispatcher dataDispatcher() {
        return new DataDispatcher();
    }

    @Provides
    @Singleton
    Store todoStore(TodoStore todoStore) {
        return todoStore;
    }

    @Provides
    @Singleton
    ActionCreator actionCreator(TodoActionCreator actionCreator) {
        return actionCreator;
    }
}
