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

package com.farseer.todo.flux.store;

/**
 * Store
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-18
 */
public interface Store {

    /**
     * 注册Store需要的Bus
     */
    void register();

    /**
     * 注销Store需要的Bus
     */
    void unregister();
}
