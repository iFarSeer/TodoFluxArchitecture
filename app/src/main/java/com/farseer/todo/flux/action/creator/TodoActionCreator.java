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

package com.farseer.todo.flux.action.creator;

import com.farseer.todo.flux.action.TodoItemAction;
import com.farseer.todo.flux.action.TodoListAction;
import com.farseer.todo.flux.action.base.DataBundle;
import com.farseer.todo.flux.database.DatabaseMapper;
import com.farseer.todo.flux.database.DatabaseValuer;
import com.farseer.todo.flux.database.table.TBTodoItem;
import com.farseer.todo.flux.dispatcher.Dispatcher;
import com.farseer.todo.flux.pojo.TodoItem;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * TodoActionCreator
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoActionCreator implements ActionCreator {

    private Dispatcher actionDispatcher;

    private BriteDatabase briteDatabase;

    @Inject
    public TodoActionCreator(@Named("actionDispatcher") Dispatcher actionDispatcher, BriteDatabase briteDatabase) {
        LogTool.debug("构造 TodoActionCreator");
        this.actionDispatcher = actionDispatcher;
        this.briteDatabase = briteDatabase;
    }

    @Override
    public void createItemNewAction(final String description) {

        long id = System.currentTimeMillis();
        TodoItem item = new TodoItem(id, description, false, false);
        briteDatabase.insert(TBTodoItem.TABLE_NAME, DatabaseValuer.todoItemValues(item));

        DataBundle<TodoItemAction.Key> bundle = new DataBundle<>();
        bundle.put(TodoItemAction.Key.ITEM, item);
        actionDispatcher.post(new TodoItemAction(TodoItemAction.Type.NEW, bundle));
    }

    @Override
    public void createItemEditAction(final Long id, final String description, boolean isCompleted, boolean isStar) {
        TodoItem item = new TodoItem(id, description, isCompleted, isStar);
        briteDatabase.update(TBTodoItem.TABLE_NAME, DatabaseValuer.todoItemValues(item), String.format("%s = %s", TBTodoItem.ID, item.getId()));

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
                mapToList(DatabaseMapper.MAPPER_TODO_ITEM)
                .subscribe(list -> {
                    DataBundle<TodoListAction.Key> bundle = new DataBundle<>();
                    bundle.put(TodoListAction.Key.LIST, list);
                    actionDispatcher.post(new TodoListAction(TodoListAction.Type.LOAD, bundle));
                });
    }

    @Override
    public void createListAllAction() {
        actionDispatcher.post(new TodoListAction(TodoListAction.Type.SHOW_ALL));
    }

    @Override
    public void createListCompletedAction() {
        actionDispatcher.post(new TodoListAction(TodoListAction.Type.SHOW_COMPLETED));
    }

    @Override
    public void createListStaredAction() {
        actionDispatcher.post(new TodoListAction(TodoListAction.Type.SHOW_STARED));

    }
}