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

