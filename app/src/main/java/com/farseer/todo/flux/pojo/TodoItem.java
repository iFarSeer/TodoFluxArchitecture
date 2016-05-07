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

package com.farseer.todo.flux.pojo;

/**
 * Todo事项.
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public class TodoItem {
    private Long id;
    private String description;
    private boolean completed;
    private boolean stared;

    /**
     * 构造Todo事项.
     * @param id                    id
     * @param description           描述
     */
    public TodoItem(Long id, String description) {
        this(id, description, false, false);
    }

    /**
     * 构造Todo事项.
     * @param id                    构造Todo事项
     * @param description           描述
     * @param completed             是否已完成
     */
    public TodoItem(Long id, String description, boolean completed) {
        this(id, description, completed, false);
    }

    /**
     * 构造Todo事项.
     * @param id                    id
     * @param description           描述
     * @param completed             是否已完成
     * @param stared                是否重要
     */
    public TodoItem(Long id, String description, boolean completed, boolean stared) {
        this.stared = stared;
        this.completed = completed;
        this.description = description;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isStared() {
        return stared;
    }

    public void setStared(boolean stared) {
        this.stared = stared;
    }

    @Override
    public String toString() {
        return "TodoItem{"
                + "id="
                + id
                + ", description='"
                + description
                +  ", isCompleted="
                + completed
                + ", isStared="
                + stared
                + "}";
    }
}