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

package com.farseer.todo.flux.action;

import com.farseer.todo.flux.action.base.Action;
import com.farseer.todo.flux.action.base.ActionType;
import com.farseer.todo.flux.action.base.DataBundle;
import com.farseer.todo.flux.action.base.DataKey;

/**
 * Todo数据相关Action.
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoItemAction extends Action {

    /**
     * 构造单个todo事项事件.
     *
     * @param type 事件类型
     */
    public TodoItemAction(final Type type) {
        this.type = type;
        this.dataBundle = new DataBundle<>();
    }

    /**
     * 构造单个todo事项事件.
     *
     * @param type 事件类型
     * @param data 事件数据
     */
    public TodoItemAction(final Type type, final DataBundle<Key> data) {
        this.type = type;
        this.dataBundle = data;
    }

    /**
     * 事件类型.
     */
    public enum Type implements ActionType {
        /**
         * 创建todo事项.
         */
        NEW,
        /**
         * 编辑todo事项.
         */
        EDIT,
        /**
         * 删除todo事项.
         */
        DELETE,
    }

    /**
     * 数据key.
     */
    public enum Key implements DataKey {
        /**
         * todo事项id的key.
         */
        ID,
        /**
         * 单个todo事项的key.
         */
        ITEM
    }
}