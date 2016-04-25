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

package com.farseer.todo.flux.database.table;

import com.farseer.todo.flux.pojo.TodoItem;
import com.farseer.todo.flux.tool.CursorTool;

import android.content.ContentValues;
import android.database.Cursor;

import rx.functions.Func1;

/**
 * TBTodoItem
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-18
 */
public class TBTodoItem {

    public static final String TABLE_NAME = "todo_item";

    public static final String ID = "_id";
    public static final String DESCRIPTION = "description";
    public static final String IS_COMPLETE = "is_complete";
    public static final String IS_STAR = "is_star";

     public static final String CREATE_SQL_V1 = ""
            + "CREATE TABLE " + TBTodoItem.TABLE_NAME + "("
            + TBTodoItem.ID + " INTEGER NOT NULL PRIMARY KEY,"
            + TBTodoItem.DESCRIPTION + " TEXT NOT NULL,"
            + TBTodoItem.IS_COMPLETE + " INTEGER NOT NULL DEFAULT 0,"
            + TBTodoItem.IS_STAR + " INTEGER NOT NULL DEFAULT 0"
            + ")";

    public static final String INDEX_SQL_V1 =
            "create index if not exists INDEX_" + TABLE_NAME + " on "
                    + TABLE_NAME + "("
                    + ID + ","
                    + IS_COMPLETE + ","
                    + IS_STAR
                    + ");";

    public static ContentValues contentValue(TodoItem item) {
        ContentValues values = new ContentValues();
        values.put(ID, item.getId());
        values.put(DESCRIPTION, item.getDescription());
        values.put(IS_COMPLETE, item.isCompleted() ? 1 : 0);
        values.put(IS_STAR, item.isStar() ? 1 : 0);
        return values;
    }

    public static Func1<Cursor, TodoItem> MAPPER = cursor -> {
            long id = CursorTool.getLong(cursor, ID);
            String description = CursorTool.getString(cursor, DESCRIPTION);
            boolean isComplete = CursorTool.getBoolean(cursor, IS_COMPLETE);
            boolean isStar = CursorTool.getBoolean(cursor, IS_STAR);
            return new TodoItem(id, description, isComplete, isStar);
    };
}
