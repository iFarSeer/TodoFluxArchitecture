package com.farseer.todo.flux.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.farseer.todo.flux.R;
import com.farseer.todo.flux.base.BaseActivity;
import com.farseer.todo.flux.di.component.TodoHomeComponent;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.model.TodoListModel;
import com.farseer.todo.flux.store.TodoStore;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Inject
    TodoStore todoStore;

    @Inject
    DataDispatcher dataDispatcher;

    private TodoListModel todoListModel;

    private TodoHomeComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initializeInjector();
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoStore.onResume();
        dataDispatcher.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        todoStore.onPause();
        dataDispatcher.unregister(this);
    }

    @OnClick(R.id.fab)
    public void clickFloatingActionButton(){
        actionCreator.createNewAction("天亮啦");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Subscribe
    public void onTodoModelChanged(final TodoListModel todoListModel) {
       this.todoListModel = todoListModel;
        LogTool.debug(todoListModel.toString());
    }

    public void initializeInjector() {
        component = TodoHomeComponent.Initializer.init(this);
        component.inject(this);
    }
}
