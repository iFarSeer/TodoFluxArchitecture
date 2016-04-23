/*
 * ViewAction      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux.action;

import com.farseer.todo.flux.action.base.Action;
import com.farseer.todo.flux.action.base.ActionType;
import com.farseer.todo.flux.action.base.DataBundle;
import com.farseer.todo.flux.action.base.DataKey;

/**
 * Todo视图相关Action
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoListAction extends Action {

    public TodoListAction(Type type) {
        this.type = type;
        this.bundle = new DataBundle<>();
    }

    public TodoListAction(Type type, DataBundle<Key> bundle) {
        this.type = type;
        this.bundle = bundle;
    }


    public enum Type implements ActionType {
        LOAD,
        SHOW_ALL,
        SHOW_COMPLETED
    }

    public enum Key implements DataKey {
        LIST
    }
}