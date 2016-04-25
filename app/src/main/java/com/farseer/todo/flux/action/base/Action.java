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