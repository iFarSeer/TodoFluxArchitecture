package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.di.ForApplication;
import com.farseer.todo.flux.di.module.FluxModule;
import com.farseer.todo.flux.dispatcher.ActionDispatcher;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.store.TodoStore;
import com.farseer.todo.flux.view.TodoListActivity;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhaosc on 16/4/24.
 */

@Singleton
@Component(
        dependencies = {StorageComponent.class},
        modules = {FluxModule.class}
)
public interface FluxComponent {

    public static final class Initializer {
        public static FluxComponent init(Application application, String userId) {
            return DaggerFluxComponent.builder()
                    .storageComponent(StorageComponent.Initializer.init(application, userId))
                    .fluxModule(new FluxModule())
                    .build();
        }
    }

    void inject(TodoListActivity activity);


    /**
     * 获得Action处理器
     */
    ActionDispatcher actionDispatcher();

    /**
     * 获得Data处理器
     */
    DataDispatcher dataDispatcher();

    /**
     * 获得TodoStore
     */
    TodoStore todoStore();

    /**
     * 获得actionCreator
     */
    ActionCreator actionCreator();

}
