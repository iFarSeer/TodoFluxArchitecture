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

package com.farseer.todo.flux.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.farseer.todo.flux.tool.LogTool;

/**
 * DatabaseOpenHelper
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-23
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    /**
     * 构造DatabaseOpenHelper.
     *
     * @param context      上下文context
     * @param databaseName 数据库名称
     */
    public DatabaseOpenHelper(Context context, String databaseName) {
        super(context, databaseName, null, DatabaseSQL.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            for (int i = 0; i < DatabaseSQL.SQL_ALL.length; i++) {
                String[] tmp = DatabaseSQL.SQL_ALL[i];
                for (int j = 0; j < tmp.length; j++) {
                    db.execSQL(tmp[j]);
                }
            }
            db.setTransactionSuccessful();
        } catch (SQLException exception) {
            LogTool.error(exception.getMessage());
        } finally {
            db.endTransaction();
        }

        LogTool.debug("DatabaseOpenHelper.onCreate finish");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try {
            for (int i = oldVersion; i < DatabaseSQL.SQL_ALL.length; i++) {
                String[] tmp = DatabaseSQL.SQL_ALL[i];
                for (int j = 0; j < tmp.length; j++) {
                    db.execSQL(tmp[j]);
                }
            }
            db.setTransactionSuccessful();
        } catch (SQLException exception) {
            LogTool.error(exception.getMessage());
        } finally {
            db.endTransaction();
        }

        LogTool.debug("DatabaseOpenHelper.onUpgrade finish");

    }
}
