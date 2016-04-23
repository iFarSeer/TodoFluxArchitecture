package com.farseer.todo.flux.database;

import com.farseer.todo.flux.database.contants.TodoItemTable;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhaosc on 16/4/23.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;


    private static final String CREATE_TABLE_ITEM = ""
            + "CREATE TABLE " + TodoItemTable.TABLE + "("
            + TodoItemTable.ID + " INTEGER NOT NULL PRIMARY KEY,"
            + TodoItemTable.DESCRIPTION + " TEXT NOT NULL,"
            + TodoItemTable.IS_COMPLETE + " INTEGER NOT NULL DEFAULT 0,"
            + TodoItemTable.IS_STAR + " INTEGER NOT NULL DEFAULT 0"
            + ")";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
