/*
 * ApplicationModule      2016-04-19
 *
 */
package com.farseer.todo.flux.di.module;

import com.farseer.todo.flux.BuildConfig;
import com.farseer.todo.flux.database.DatabaseHelper;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import android.app.Application;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;

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
}