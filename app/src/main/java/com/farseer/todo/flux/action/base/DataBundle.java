package com.farseer.todo.flux.action.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据集合
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class DataBundle<T extends DataKey> {
    Map<T, Object> map;

    public DataBundle() {
        map = new HashMap<>();
    }

    public void put(T key, Object data) {
        map.put(key, data);
    }

    public Object get(T key, Object defaultValue) {
        Object obj = map.get(key);
        if (obj == null) {
            obj = defaultValue;
        }

        return obj;
    }

    @Override
    public String toString() {
        return "DataBundle{" +
                "map=" + map +
                '}';
    }
}

