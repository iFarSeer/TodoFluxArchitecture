package com.farseer.todo.flux.di.module;

import com.farseer.todo.flux.BuildConfig;
import com.farseer.todo.flux.database.DatabaseHelper;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhaosc on 16/4/24.
 */

@Module
public class StorageModule {

    private Application application;
    private String userId;

    public StorageModule(Application application, String userId) {
        this.application = application;
        this.userId = userId;
    }

    @Provides
    BriteDatabase briteDatabase() {
        SqlBrite sqlBrite = SqlBrite.create(message -> LogTool.debug("Database", message));

        SQLiteOpenHelper sqLiteOpenHelper = new DatabaseHelper(application, userId);
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(sqLiteOpenHelper);
        db.setLoggingEnabled(BuildConfig.DEBUG);
        return db;
    }
}
