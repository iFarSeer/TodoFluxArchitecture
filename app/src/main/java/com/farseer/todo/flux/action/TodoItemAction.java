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
 * Todo数据相关Action
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoItemAction extends Action {

    public TodoItemAction(Type type) {
        this.type = type;
        this.bundle = new DataBundle<>();
    }

    public TodoItemAction(Type type, DataBundle<Key> data) {
        this.type = type;
        this.bundle = data;
    }

    public enum Type implements ActionType {
        NEW,
        EDIT,
        DELETE,
    }

    public enum Key implements DataKey {
        ID,
        ITEM,
        LIST
    }
}