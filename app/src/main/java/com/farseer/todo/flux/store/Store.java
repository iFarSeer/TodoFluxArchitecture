package com.farseer.todo.flux.store;

/**
 * Created by zhaosc on 16/4/25.
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
