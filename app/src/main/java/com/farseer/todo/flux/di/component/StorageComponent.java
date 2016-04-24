package com.farseer.todo.flux.di.component;

import com.farseer.todo.flux.FluxApplication;
import com.farseer.todo.flux.di.module.StorageModule;
import com.farseer.todo.flux.view.base.BaseActivity;
import com.squareup.sqlbrite.BriteDatabase;

import android.app.Application;

import dagger.Component;

/**
 * Created by zhaosc on 16/4/24.
 */

@Component(
        modules = {StorageModule.class}
)
public interface StorageComponent {

    public static final class Initializer {
        public static StorageComponent init(Application application, String userId) {
            return DaggerStorageComponent.builder()
                    .storageModule(new StorageModule(application, userId))
                    .build();
        }
    }

    /**
     * 获得数据库
     */
    BriteDatabase briteDatabase();
}
