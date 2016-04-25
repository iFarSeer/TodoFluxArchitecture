/*
 * ActionDispatcher      2016-04-19
 *
 */
package com.farseer.todo.flux.dispatcher;

import com.farseer.todo.flux.tool.LogTool;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Inject;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class ActionDispatcher implements Dispatcher {

    private Bus bus = new Bus(ThreadEnforcer.MAIN);

    @Inject
    public ActionDispatcher() {
        LogTool.debug("构造 ActionDispatcher");
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