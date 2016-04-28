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

/**
 * ActionCreator
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-18
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

    /**
     * 创建显示List Stared Item 事件
     */
    void createListStaredAction();
}
