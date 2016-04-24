package com.farseer.todo.flux.di;

/**
 * Created by zhaosc on 16/4/24.
 * Interface representing a contract for clients that contains a component for dependency injection.
 */
public interface HasComponent<T> {
    T getComponent();
}
