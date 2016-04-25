package com.farseer.todo.flux.action.creator;

/**
 * Created by zhaosc on 16/4/25.
 */
public interface ActionCreator {

    void createItemNewAction(final String description);

    void createItemEditAction(final Long id, final String description, boolean isCompleted, boolean isStar);


    void createItemDeleteAction(final Long id);

    void createListLoadAction();

    void createListAllAction();

    void createListCompletedAction();
}
