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

package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.FluxApplication;
import com.farseer.todo.flux.di.PerActivity;
import com.farseer.todo.flux.di.module.ActivityModule;
import com.farseer.todo.flux.view.TodoListActivity;
import com.farseer.todo.flux.view.base.BaseActivity;
import dagger.Component;

/**
 * ActivityComponent
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class
)
public interface ActivityComponent {

    /**
     * 注入TodoListActivity.
     *
     * @param baseActivity TodoListActivity
     */
    void inject(TodoListActivity baseActivity);

    /**
     * 获得Activity
     *
     * @return BaseActivity.
     */
    BaseActivity baseActivity();

    /**
     * ActivityComponent的Initializer.
     */
    public static final class Initializer {
        /**
         * 初始化ActivityComponent.
         *
         * @param baseActivity baseActivity
         * @return ActivityComponent
         */
        public static ActivityComponent init(BaseActivity baseActivity) {
            return DaggerActivityComponent.builder()
                    .applicationComponent(((FluxApplication) baseActivity.getApplication()).getComponent())
                    .activityModule(new ActivityModule(baseActivity))
                    .build();
        }
    }
}
