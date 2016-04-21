/*
 * ActionCreator      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux.action.creator;

import com.farseer.todo.flux.action.TodoDataAction;
import com.farseer.todo.flux.action.TodoViewAction;
import com.farseer.todo.flux.action.base.DataBundle;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;

import javax.inject.Inject;

/**
 * ActionCreator
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class ActionCreator {

    private ActionDispatcher actionDispatcher;

    @Inject
    public ActionCreator(ActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;
    }

    public final void createNewAction(final String description) {
        DataBundle<TodoDataAction.Key> bundle = new DataBundle<>();
        bundle.put(TodoDataAction.Key.DESCRIPTION, description);
        actionDispatcher.post(new TodoDataAction(TodoDataAction.Type.NEW, bundle));
    }

    public final void createEditAction(final Long id, final String description, boolean isCompleted, boolean isStar) {
        DataBundle<TodoDataAction.Key> bundle = new DataBundle<>();
        bundle.put(TodoDataAction.Key.ID, id);
        bundle.put(TodoDataAction.Key.DESCRIPTION, description);
        bundle.put(TodoDataAction.Key.IS_COMPLETED, isCompleted);
        bundle.put(TodoDataAction.Key.IS_STAR, isStar);
        actionDispatcher.post(new TodoDataAction(TodoDataAction.Type.EDIT, bundle));
    }

    public final void createDeleteAction(final Long id) {
        DataBundle<TodoDataAction.Key> bundle = new DataBundle<>();
        bundle.put(TodoDataAction.Key.ID, id);
        actionDispatcher.post(new TodoDataAction(TodoDataAction.Type.DELETE, bundle));
    }

    public final void createDeleteAllAction() {
        actionDispatcher.post(new TodoDataAction(TodoDataAction.Type.DELETE_ALL));
    }

    public final void createUndoDeleteAllAction() {
        actionDispatcher.post(new TodoDataAction(TodoDataAction.Type.UNDO_DELETE_ALL));
    }


    public final void createAllViewAction() {
        actionDispatcher.post(new TodoViewAction(TodoViewAction.Type.VIEW_ALL));
    }

    public final void createActiveViewAction() {
        actionDispatcher.post(new TodoViewAction(TodoViewAction.Type.VIEW_ACTIVE));
    }

    public final void createCompleteViewAction() {
        actionDispatcher.post(new TodoViewAction(TodoViewAction.Type.VIEW_COMPLETE));
    }
}