/*
 * TodoListModel      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux.model;

import com.farseer.todo.flux.pojo.TodoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        list = filter(list, filter);
    }

    private List<TodoItem> filter(List<TodoItem> list, Filter filter) {
        List<TodoItem> tmpList = new ArrayList<>();

        switch (filter) {
            case ALL:
                tmpList.addAll(list);
                break;
            case ACTIVE:
                for (TodoItem item : list) {
                    if (!item.isCompleted()) {
                        tmpList.add(item);
                    }
                }
                break;
            case COMPLETED:
                for (TodoItem item : list) {
                    if (item.isCompleted()) {
                        tmpList.add(item);
                    }
                }
                break;
            default:
                break;
        }

        Collections.sort(tmpList, new Comparator<TodoItem>() {
            @Override
            public int compare(TodoItem lhs, TodoItem rhs) {
                return (int) (lhs.getId() - rhs.getId());
            }
        });

        return tmpList;
    }

    public enum Filter {
        ALL, ACTIVE, COMPLETED
    }

    @Override
    public String toString() {
        return "TodoListModel{" +
                "list=" + list +
                ", filter=" + filter +
                '}';
    }
}