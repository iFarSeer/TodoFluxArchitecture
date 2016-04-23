/*
 * TodoAction      2016-04-19
 *
 */
package com.farseer.todo.flux.action;

import com.farseer.todo.flux.action.base.Action;
import com.farseer.todo.flux.action.base.ActionType;
import com.farseer.todo.flux.action.base.DataBundle;
import com.farseer.todo.flux.action.base.DataKey;

/**
 * Todo数据相关Action
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoItemAction extends Action {

    public TodoItemAction(Type type) {
        this.type = type;
        this.bundle = new DataBundle<>();
    }

    public TodoItemAction(Type type, DataBundle<Key> data) {
        this.type = type;
        this.bundle = data;
    }

    public enum Type implements ActionType {
        NEW,
        EDIT,
        DELETE,
    }

    public enum Key implements DataKey {
        ID,
        ITEM,
        LIST
    }
}