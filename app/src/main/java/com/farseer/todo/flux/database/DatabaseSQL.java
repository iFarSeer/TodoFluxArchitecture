package com.farseer.todo.flux.database;

import com.farseer.todo.flux.database.table.TBTodoItem;

/**
 * Created by zhaosc on 16/4/23.
 */
public class DatabaseSQL {

    public static final String[] SQL_ALL_V1 = {
            TBTodoItem.CREATE_SQL_V1, TBTodoItem.INDEX_SQL_V1
    };
    
    public static final String[][] SQL_ALL = {SQL_ALL_V1};

    public static final int VERSION = SQL_ALL.length;
}
