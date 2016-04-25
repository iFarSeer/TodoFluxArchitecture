package com.farseer.todo.flux.action.creator;

/**
 * Created by zhaosc on 16/4/25.
 */
public interface ActionCreator {

    /**
     * 创建新建Item事件
     */
    void createItemNewAction(final String description);

    /**
     * 创建编辑Item事件
     */
    void createItemEditAction(final Long id, final String description, boolean isCompleted, boolean isStar);

    /**
     * 创建删除Item事件
     */
    void createItemDeleteAction(final Long id);

    /**
     * 创建加载List事件
     */
    void createListLoadAction();

    /**
     * 创建显示List All Item 事件
     */
    void createListAllAction();

    /**
     * 创建显示List Compeleted Item 事件
     */
    void createListCompletedAction();
}
