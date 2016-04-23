package com.farseer.todo.flux.database.contants;

import com.farseer.todo.flux.tool.CursorTool;
import com.farseer.todo.flux.pojo.TodoItem;

import android.content.ContentValues;
import android.database.Cursor;

import rx.functions.Func1;

/**
 * Created by zhaosc on 16/4/23.
 * Todo table
 */
public class TodoItemTable {

    public static final String TABLE = "todo_item";

    public static final String ID = "_id";
    public static final String DESCRIPTION = "description";
    public static final String IS_COMPLETE = "is_complete";
    public static final String IS_STAR = "is_star";


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
