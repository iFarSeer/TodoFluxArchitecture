package com.farseer.todo.flux.dispatcher;

/**
 * Created by zhaosc on 16/4/25.
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
