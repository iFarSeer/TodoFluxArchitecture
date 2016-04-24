package com.farseer.todo.flux.di.module;

import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.store.TodoStore;
import com.squareup.sqlbrite.BriteDatabase;

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
    TodoStore todoStore(DataDispatcher dataDispatcher, ActionDispatcher actionDispatcher) {
        return new TodoStore(dataDispatcher, actionDispatcher);
    }

    @Provides
    @Singleton
    ActionCreator actionCreator(ActionDispatcher actionDispatcher, BriteDatabase briteDatabase) {
        return new ActionCreator(actionDispatcher, briteDatabase);
    }
}
