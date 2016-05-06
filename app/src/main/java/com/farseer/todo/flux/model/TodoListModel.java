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

package com.farseer.todo.flux.model;

import com.farseer.todo.flux.pojo.TodoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Todo事项列表模型
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoListModel {
    public List<TodoItem> list;
    public Filter filter;

    public TodoListModel(List<TodoItem> list, Filter filter) {
        this.filter = filter;
        this.list = filterTodoItemList(list, filter);
    }

    private List<TodoItem> filterTodoItemList(List<TodoItem> list, Filter filter) {
        List<TodoItem> tmpList = new ArrayList<>();

        switch (filter) {
            case ALL:
                tmpList.addAll(list);
                break;
            case COMPLETED:
                for (TodoItem item : list) {
                    if (item.isCompleted()) {
                        tmpList.add(item);
                    }
                }
                break;
            case STARED:
                for (TodoItem item : list) {
                    if (item.isStared()) {
                        tmpList.add(item);
                    }
                }
                break;
            default:
                break;
        }

        Collections.sort(tmpList, (lhs, rhs) -> (int) (rhs.getId() - lhs.getId()));

        return tmpList;
    }

    public enum Filter {
        ALL, COMPLETED, STARED
    }

    @Override
    public String toString() {
        return "TodoListModel{" +
                "list=" + list +
                ", filterTodoItemList=" + filter +
                '}';
    }
}