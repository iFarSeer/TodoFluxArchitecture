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
public class TodoViewAction extends Action {

    public TodoViewAction(Type type) {
        this.type = type;
        this.bundle = new DataBundle<>();
    }

    public TodoViewAction(Type type, DataBundle<Key> bundle) {
        this.type = type;
        this.bundle = bundle;
    }


    public enum Type implements ActionType {
        VIEW_ALL,
        VIEW_ACTIVE,
        VIEW_COMPLETE,
        MARK_EDITABLE
    }

    public enum Key implements DataKey {
        ID
    }
}