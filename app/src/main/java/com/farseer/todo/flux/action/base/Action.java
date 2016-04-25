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

package com.farseer.todo.flux.action.base;

/**
 * 抽象的Action
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public abstract class Action<A extends ActionType, D extends DataKey> {

    protected A type;
    protected DataBundle<D> bundle;

    public A getType() {
        return type;
    }

    public DataBundle<D> getBundle() {
        return bundle;
    }

    @Override
    public String toString() {
        return "Action{" +
                "type=" + type +
                ", bundle=" + bundle.toString() +
                '}';
    }
}