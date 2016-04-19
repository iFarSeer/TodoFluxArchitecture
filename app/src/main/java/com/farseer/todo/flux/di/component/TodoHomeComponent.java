/*
 * ActivityComponent      2016-04-19
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */
package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.FluxApplication;
import com.farseer.todo.flux.di.PerActivity;
import com.farseer.todo.flux.di.module.ActivityModule;
import com.farseer.todo.flux.di.module.TodoHomeModule;
import com.farseer.todo.flux.view.TodoActivity;

import dagger.Component;

/**
 * class description here
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
@PerActivity
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {ActivityModule.class, TodoHomeModule.class}
)
public interface TodoHomeComponent {

    public static final class Initializer {
        public static TodoHomeComponent init(TodoActivity activity) {
            return DaggerTodoHomeComponent.builder()
                    .applicationComponent(((FluxApplication)activity.getApplicationContext()).component())
                    .todoHomeModule(new TodoHomeModule(activity))
                    .activityModule(new ActivityModule(activity))
                    .build();
//            return null;
        }
    }

    public void inject(TodoActivity activity);

    TodoActivity activity();

}