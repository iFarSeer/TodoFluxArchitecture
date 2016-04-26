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

package com.farseer.todo.flux.store;

import com.farseer.todo.flux.action.TodoItemAction;
import com.farseer.todo.flux.action.TodoListAction;
import com.farseer.todo.flux.action.base.DataBundle;
import com.farseer.todo.flux.dispatcher.Dispatcher;
import com.farseer.todo.flux.model.TodoListModel;
import com.farseer.todo.flux.pojo.TodoItem;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Todo Store
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoStore implements Store {

    private Map<Long, TodoItem> todoItemMap = new HashMap<>();
    private TodoListModel.Filter filter = TodoListModel.Filter.ALL;

    private Dispatcher dataDispatcher;
    private Dispatcher actionDispatcher;

    @Inject
    public TodoStore(@Named("dataDispatcher") Dispatcher dataDispatcher,
                     @Named("actionDispatcher") Dispatcher actionDispatcher) {
        LogTool.debug("构造 TodoStore");
        this.dataDispatcher = dataDispatcher;
        this.actionDispatcher = actionDispatcher;
    }

    @Override
    public void register() {
        actionDispatcher.register(this);
    }

    @Override
    public void unregister() {
        actionDispatcher.unregister(this);
    }

    @Subscribe
    public final void subscribeItemAction(TodoItemAction action) {
        LogTool.debug(action.toString());
        DataBundle<TodoItemAction.Key> data = action.getBundle();
        Long id = (Long) data.get(TodoItemAction.Key.ID, -1L);
        TodoItem item = (TodoItem) data.get(TodoItemAction.Key.ITEM, null);
        switch ((TodoItemAction.Type) action.getType()) {
            case NEW:
                if (item != null) {
                    todoItemMap.put(item.getId(), item);
                    notifyTodoListModelChanged();
                }
                break;
            case EDIT:
                if (item != null) {
                    todoItemMap.put(item.getId(), item);
                    notifyTodoListModelChanged();
                }
                break;
            case DELETE:
                todoItemMap.remove(id);
                notifyTodoListModelChanged();
                break;
        }
    }

    @Subscribe
    public final void subscribeListAction(TodoListAction action) {
        LogTool.debug(action.toString());
        DataBundle<TodoListAction.Key> data = action.getBundle();
        switch ((TodoListAction.Type) action.getType()) {
            case LOAD: {
                List<TodoItem> list = (List<TodoItem>) data.get(TodoListAction.Key.LIST, null);
                if (list != null) {
                    todoItemMap.clear();
                    for (TodoItem item : list) {
                        todoItemMap.put(item.getId(), item);
                    }
                }
                filter = TodoListModel.Filter.ALL;
                break;
            }
            case SHOW_ALL:
                filter = TodoListModel.Filter.ALL;
                break;
            case SHOW_COMPLETED:
                filter = TodoListModel.Filter.COMPLETED;
                break;
        }

        notifyTodoListModelChanged();
    }

    private void notifyTodoListModelChanged() {
        List<TodoItem> todoItemList = new ArrayList<>();
        todoItemList.addAll(todoItemMap.values());
        TodoListModel todoListModel = new TodoListModel(todoItemList, filter);
        dataDispatcher.post(todoListModel);
    }

}