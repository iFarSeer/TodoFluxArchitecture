/*
 * TodoStore      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux.store;

import com.farseer.todo.flux.action.TodoDataAction;
import com.farseer.todo.flux.action.TodoViewAction;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * Todo Store
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoStore {

    private DataDispatcher dataDispatcher;
    private ActionDispatcher actionDispatcher;

    @Inject
    public TodoStore(DataDispatcher dataDispatcher, ActionDispatcher actionDispatcher) {
        this.dataDispatcher = dataDispatcher;
        this.actionDispatcher = actionDispatcher;
    }

    @Subscribe
    public final void processTodoDataAction(TodoDataAction action) {
        LogTool.debug(action.toString());
        switch ((TodoDataAction.Type) action.getType()) {
            case NEW:
                break;
            case EDIT:
                break;
            case DELETE:
                break;
            case DELETE_ALL:
                break;
            case UNDO_DELETE_ALL:
                break;
        }
    }

    @Subscribe
    public final void processTodoViewAction(TodoViewAction action) {
        LogTool.debug(action.toString());
        switch ((TodoViewAction.Type) action.getType()) {
            case VIEW_ALL:
                break;
            case VIEW_ACTIVE:
                break;
            case VIEW_COMPLETE:
                break;
            case MARK_EDITABLE:
                break;
        }
    }

    public void onResume() {
        dataDispatcher.register(this);
        actionDispatcher.register(this);
    }

    public void onPause() {
        dataDispatcher.unregister(this);
        actionDispatcher.unregister(this);
    }


}