/*
 * ApplicationModule      2016-04-19
 *
 */
package com.farseer.todo.flux.di.module;

import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.action.creator.TodoActionCreator;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.store.Store;
import com.farseer.todo.flux.store.TodoStore;
import com.squareup.sqlbrite.BriteDatabase;

import android.app.Application;
import android.content.res.Resources;

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
    Store todoStore(DataDispatcher dataDispatcher, ActionDispatcher actionDispatcher) {
        return new TodoStore(dataDispatcher, actionDispatcher);
    }

    @Provides
    @Singleton
    ActionCreator actionCreator(ActionDispatcher actionDispatcher, BriteDatabase briteDatabase) {
        return new TodoActionCreator(actionDispatcher, briteDatabase);
    }
}