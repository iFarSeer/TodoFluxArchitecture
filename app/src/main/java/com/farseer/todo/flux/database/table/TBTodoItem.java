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

/**
 * TBTodoItem
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-18
 */
public final class TBTodoItem {

    /**
     * TodoItem表名称
     */
    public static final String TABLE_NAME = "todo_item";

    /**
     * 字段id
     */
    public static final String ID = "_id";
    /**
     * 字段description
     */
    public static final String DESCRIPTION = "description";

    /**
     * 字段is_complete
     */
    public static final String IS_COMPLETE = "is_complete";
    /**
     * 字段is_star
     */
    public static final String IS_STAR = "is_star";

    /**
     * 创建表SQL语句
     */
    public static final String CREATE_SQL_V1 = ""
            + "CREATE TABLE " + TBTodoItem.TABLE_NAME + "("
            + TBTodoItem.ID + " INTEGER NOT NULL PRIMARY KEY,"
            + TBTodoItem.DESCRIPTION + " TEXT NOT NULL,"
            + TBTodoItem.IS_COMPLETE + " INTEGER NOT NULL DEFAULT 0,"
            + TBTodoItem.IS_STAR + " INTEGER NOT NULL DEFAULT 0"
            + ")";

    /**
     * 建立索引SQL语句
     */
    public static final String INDEX_SQL_V1 = ""
            + "CREATE INDEX if not exists INDEX_" + TABLE_NAME + " on "
            + TABLE_NAME + "("
            + ID + ","
            + IS_COMPLETE + ","
            + IS_STAR
            + ");";

    private TBTodoItem() {
        //not called
    }
}
