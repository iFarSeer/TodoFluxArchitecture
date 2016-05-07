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

import com.farseer.todo.flux.tool.LogTool;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Inject;

/**
 * 数据分发器
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class DataDispatcher implements Dispatcher {

    private Bus bus = new Bus(ThreadEnforcer.MAIN);

    /**
     * 构造数据分发器.
     */
    @Inject
    public DataDispatcher() {
        LogTool.debug("构造 DataDispatcher");
    }

    @Override
    public void register(Object object) {
        bus.register(object);
    }

    @Override
    public void unregister(Object object) {
        bus.unregister(object);
    }

    @Override
    public void post(Object event) {
        bus.post(event);
    }
}