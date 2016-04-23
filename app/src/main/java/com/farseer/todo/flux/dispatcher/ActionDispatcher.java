/*
 * ActionDispatcher      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
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
public class ActionDispatcher extends Bus {

    public ActionDispatcher(){
        super(ThreadEnforcer.MAIN);
    }
}