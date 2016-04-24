/*
 * ActivityComponent      2016-04-19
 *
 */
package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.FluxApplication;
import com.farseer.todo.flux.view.base.BaseActivity;
import com.farseer.todo.flux.view.base.BaseFragment;
import com.farseer.todo.flux.di.PerActivity;
import com.farseer.todo.flux.di.module.ActivityModule;

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
        modules = {ActivityModule.class}
)
public interface ActivityComponent {

    public static final class Initializer {
        public static ActivityComponent init(BaseActivity activity) {
            return DaggerActivityComponent.builder()
                    .applicationComponent(((FluxApplication)activity.getApplicationContext()).component())
                    .activityModule(new ActivityModule(activity))
                    .build();
        }
    }

    public void inject(BaseActivity activity);

    public void inject(BaseFragment activity);

    BaseActivity activity();
}