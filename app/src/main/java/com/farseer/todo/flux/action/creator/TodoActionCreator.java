/*
 * ActionCreator      2016-04-19
 *
 */
package com.farseer.todo.flux.action.creator;

import com.farseer.todo.flux.action.TodoItemAction;
import com.farseer.todo.flux.action.TodoListAction;
import com.farseer.todo.flux.action.base.DataBundle;
import com.farseer.todo.flux.database.table.TBTodoItem;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.pojo.TodoItem;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;

import javax.inject.Inject;

/**
 * TodoActionCreator
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoActionCreator implements ActionCreator {

    private ActionDispatcher actionDispatcher;

    private BriteDatabase briteDatabase;

    @Inject
    public TodoActionCreator(ActionDispatcher actionDispatcher, BriteDatabase briteDatabase) {
        LogTool.debug("构造 TodoActionCreator");
        this.actionDispatcher = actionDispatcher;
        this.briteDatabase = briteDatabase;
    }

    @Override
    public void createItemNewAction(final String description) {

        long id = System.currentTimeMillis();
        TodoItem item = new TodoItem(id, description, false, false);
        briteDatabase.insert(TBTodoItem.TABLE_NAME, TBTodoItem.contentValue(item));

        DataBundle<TodoItemAction.Key> bundle = new DataBundle<>();
        bundle.put(TodoItemAction.Key.ITEM, item);
        actionDispatcher.post(new TodoItemAction(TodoItemAction.Type.NEW, bundle));
    }

    @Override
    public void createItemEditAction(final Long id, final String description, boolean isCompleted, boolean isStar) {
        TodoItem item = new TodoItem(id, description, isCompleted, isStar);
        briteDatabase.update(TBTodoItem.TABLE_NAME, TBTodoItem.contentValue(item), String.format("%s = %s", TBTodoItem.ID, item.getId()));

        DataBundle<TodoItemAction.Key> bundle = new DataBundle<>();
        bundle.put(TodoItemAction.Key.ID, id);
        bundle.put(TodoItemAction.Key.ITEM, item);
        actionDispatcher.post(new TodoItemAction(TodoItemAction.Type.EDIT, bundle));
    }

    @Override
    public void createItemDeleteAction(final Long id) {
        DataBundle<TodoItemAction.Key> bundle = new DataBundle<>();
        bundle.put(TodoItemAction.Key.ID, id);
        actionDispatcher.post(new TodoItemAction(TodoItemAction.Type.DELETE, bundle));
    }


    @Override
    public void createListLoadAction() {
        QueryObservable queryObservable = briteDatabase.createQuery(TBTodoItem.TABLE_NAME, "select * from " + TBTodoItem.TABLE_NAME);
        queryObservable.
                mapToList(TBTodoItem.MAPPER)
                .subscribe(list -> {
                    DataBundle<TodoListAction.Key> bundle = new DataBundle<>();
                    bundle.put(TodoListAction.Key.LIST, list);
                    actionDispatcher.post(new TodoListAction(TodoListAction.Type.LOAD, bundle));
                });
    }

    @Override
    public void createListAllAction() {
        QueryObservable queryObservable = briteDatabase.createQuery(TBTodoItem.TABLE_NAME, "select * from " + TBTodoItem.TABLE_NAME);
        queryObservable.
                mapToList(TBTodoItem.MAPPER)
                .subscribe(list -> {
                    DataBundle<TodoListAction.Key> bundle = new DataBundle<>();
                    bundle.put(TodoListAction.Key.LIST, list);
                    actionDispatcher.post(new TodoListAction(TodoListAction.Type.LOAD, bundle));
                });
    }

    @Override
    public void createListCompletedAction() {
        QueryObservable queryObservable = briteDatabase.createQuery(TBTodoItem.TABLE_NAME, "select * from " + TBTodoItem.TABLE_NAME);
        queryObservable.
                mapToList(TBTodoItem.MAPPER)
                .subscribe(list -> {
                    DataBundle<TodoListAction.Key> bundle = new DataBundle<>();
                    bundle.put(TodoListAction.Key.LIST, list);
                    actionDispatcher.post(new TodoListAction(TodoListAction.Type.SHOW_ALL, bundle));
                });
    }
}