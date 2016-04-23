/*
 * DataDispatcher      2016-04-19
 *
 */
package com.farseer.todo.flux.dispatcher;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class DataDispatcher extends Bus {

    public DataDispatcher() {
        super(ThreadEnforcer.MAIN);
    }

//    public DataDispatcher(ThreadEnforcer thread) {
//        super(thread);
//    }
}