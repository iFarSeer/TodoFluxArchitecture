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

    public TodoStore(Dispatcher dataDispatcher, Dispatcher actionDispatcher) {
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