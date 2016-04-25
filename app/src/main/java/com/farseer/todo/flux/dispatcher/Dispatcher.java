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

package com.farseer.todo.flux.dispatcher;


/**
 * Dispatcher
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-18
 */
public interface Dispatcher {

    /**
     * 注册
     * @param object
     */
    void register(Object object);

    /**
     * 注销
     * @param object
     */
    void unregister(Object object);

    /**
     * 发送通知
     * @param event
     */
    void post(Object event);

}
