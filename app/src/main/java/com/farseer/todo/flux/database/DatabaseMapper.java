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

import android.database.Cursor;
import com.farseer.todo.flux.database.table.TBTodoItem;
import com.farseer.todo.flux.pojo.TodoItem;
import com.farseer.todo.flux.tool.CursorTool;
import rx.functions.Func1;

/**
 * Rx map Cursor to Object
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-18
 */
public final class DatabaseMapper {


    /**
     * Rx map Cursor to TodoItem.
     *
     * @return func1
     */
    public static final Func1<Cursor, TodoItem> MAPPER_TODO_ITEM = cursor -> {
        long id = CursorTool.getLong(cursor, TBTodoItem.ID);
        String description = CursorTool.getString(cursor, TBTodoItem.DESCRIPTION);
        boolean isComplete = CursorTool.getBoolean(cursor, TBTodoItem.IS_COMPLETE);
        boolean isStar = CursorTool.getBoolean(cursor, TBTodoItem.IS_STAR);
        return new TodoItem(id, description, isComplete, isStar);
    };

    private DatabaseMapper() {
        // not called
    }
}
