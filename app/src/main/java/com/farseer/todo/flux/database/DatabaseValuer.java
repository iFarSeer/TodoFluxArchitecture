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

import com.farseer.todo.flux.database.table.TBTodoItem;
import com.farseer.todo.flux.pojo.TodoItem;

import android.content.ContentValues;

/**
 * Created by zhaosc.
 *
 * class description
 *
 * @version 1.0.0
 * @since 16/5/5
 */
public class DatabaseValuer {

    /**
     * 把TodoItem转换成ContentValues
     * @param item
     * @return
     */
    public static ContentValues todoItemValues(TodoItem item) {
        ContentValues values = new ContentValues();
        values.put(TBTodoItem.ID, item.getId());
        values.put(TBTodoItem.DESCRIPTION, item.getDescription());
        values.put(TBTodoItem.IS_COMPLETE, item.isCompleted() ? 1 : 0);
        values.put(TBTodoItem.IS_STAR, item.isStared() ? 1 : 0);
        return values;
    }
}
