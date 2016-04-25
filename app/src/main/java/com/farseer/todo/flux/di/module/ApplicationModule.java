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
 * class description here
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
    Store todoStore(@Named("dataDispatcher") Dispatcher dataDispatcher, @Named("actionDispatcher") Dispatcher actionDispatcher) {
        return new TodoStore(dataDispatcher, actionDispatcher);
    }

    @Provides
    @Singleton
    ActionCreator actionCreator(@Named("actionDispatcher") Dispatcher actionDispatcher, BriteDatabase briteDatabase) {
        return new TodoActionCreator(actionDispatcher, briteDatabase);
    }
}