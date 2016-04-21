/*
 * TodoStore      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux.store;

import com.farseer.todo.flux.action.TodoDataAction;
import com.farseer.todo.flux.action.TodoViewAction;
import com.farseer.todo.flux.action.base.DataBundle;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.model.TodoListModel;
import com.farseer.todo.flux.pojo.TodoItem;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Todo Store
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoStore {

    private Map<Long, TodoItem> todoItemMap = new HashMap<>();
    private TodoListModel.Filter filter = TodoListModel.Filter.ALL;

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
        DataBundle<TodoDataAction.Key> data = action.getBundle();
        Long id = (Long)data.get(TodoDataAction.Key.ID, -1L);
        String description = (String) data.get(TodoDataAction.Key.DESCRIPTION, "");
        boolean isCompleted = (Boolean) data.get(TodoDataAction.Key.IS_COMPLETED, false);
        boolean isStar = (Boolean) data.get(TodoDataAction.Key.IS_STAR, false);
        switch ((TodoDataAction.Type) action.getType()) {
            case NEW:
                long newId = System.currentTimeMillis();
                TodoItem newItem = new TodoItem(newId, description, false, false);
                todoItemMap.put(newId, newItem);
                break;
            case EDIT:
                TodoItem oldItem = todoItemMap.get(id);
                todoItemMap.put(oldItem.getId(), new TodoItem(oldItem.getId(), description, isCompleted, isStar));
                break;
            case DELETE:
                todoItemMap.remove(id);
                break;
            case DELETE_ALL:
                todoItemMap.clear();
                break;
            case UNDO_DELETE_ALL:
                break;
        }

        notifyTodoModelChanged();
    }

    @Subscribe
    public final void processTodoViewAction(TodoViewAction action) {
        LogTool.debug(action.toString());
        switch ((TodoViewAction.Type) action.getType()) {
            case VIEW_ALL:
                filter = TodoListModel.Filter.ALL;
                break;
            case VIEW_ACTIVE:
                filter = TodoListModel.Filter.ACTIVE;
                break;
            case VIEW_COMPLETE:
                filter = TodoListModel.Filter.COMPLETED;
                break;
        }

        notifyTodoModelChanged();
    }

    public void onResume() {
        dataDispatcher.register(this);
        actionDispatcher.register(this);
    }

    public void onPause() {
        dataDispatcher.unregister(this);
        actionDispatcher.unregister(this);
    }

    private void notifyTodoModelChanged(){
        List<TodoItem> todoItemList = new ArrayList<>();
        todoItemList.addAll(todoItemMap.values());
        TodoListModel todoListModel = new TodoListModel(todoItemList, filter);
        dataDispatcher.post(todoListModel);
    }

}