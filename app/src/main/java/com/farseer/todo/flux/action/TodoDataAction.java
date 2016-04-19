/*
 * TodoAction      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
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
public class TodoDataAction extends Action {

    public TodoDataAction(Type type) {
        this.type = type;
        this.bundle = new DataBundle<>();
    }

    public TodoDataAction(Type type, DataBundle<Key> data) {
        this.type = type;
        this.bundle = data;
    }

    public enum Type implements ActionType {
        NEW,
        EDIT,
        DELETE,
        DELETE_ALL,
        UNDO_DELETE_ALL
    }

    public enum Key implements DataKey {
        ID,
        DESCRIPTION,
    }
}