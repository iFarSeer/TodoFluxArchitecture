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
import com.farseer.todo.flux.model.TodoListModel;
import com.farseer.todo.flux.pojo.TodoItem;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Todo Store
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoStore {

    private List<TodoItem> todoItemList;

    private DataDispatcher dataDispatcher;
    private ActionDispatcher actionDispatcher;

    @Inject
    public TodoStore(DataDispatcher dataDispatcher, ActionDispatcher actionDispatcher) {
        this.dataDispatcher = dataDispatcher;
        this.actionDispatcher = actionDispatcher;

        todoItemList = new ArrayList<>();
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
                dataDispatcher.post(new TodoListModel(todoItemList, TodoListModel.Filter.ALL));
                break;
            case VIEW_ACTIVE:
                dataDispatcher.post(new TodoListModel(todoItemList, TodoListModel.Filter.ACTIVE));
                break;
            case VIEW_COMPLETE:
                dataDispatcher.post(new TodoListModel(todoItemList, TodoListModel.Filter.COMPLETED));
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