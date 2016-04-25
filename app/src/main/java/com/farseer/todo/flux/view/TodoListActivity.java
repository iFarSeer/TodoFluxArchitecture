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

package com.farseer.todo.flux.view;

import com.farseer.todo.flux.R;
import com.farseer.todo.flux.action.creator.ActionCreator;
import com.farseer.todo.flux.di.component.ActivityComponent;
import com.farseer.todo.flux.dispatcher.Dispatcher;
import com.farseer.todo.flux.model.TodoListModel;
import com.farseer.todo.flux.pojo.TodoItem;
import com.farseer.todo.flux.store.Store;
import com.farseer.todo.flux.tool.LogTool;
import com.farseer.todo.flux.view.base.BaseActivity;
import com.squareup.otto.Subscribe;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoListActivity extends BaseActivity {

    Store todoStore;

    Dispatcher dataDispatcher;

    ActionCreator actionCreator;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.inputEditText)
    AppCompatEditText inputEditText;

    private TodoListModel todoListModel;

    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initializeInjector();

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoStore.register();
        dataDispatcher.register(this);

        actionCreator.createListLoadAction();
    }

    @Override
    protected void onPause() {
        super.onPause();
        todoStore.unregister();
        dataDispatcher.unregister(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo, menu);
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

    @OnClick(R.id.fab)
    public void clickFloatingActionButton() {
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void onTodoModelChanged(final TodoListModel todoListModel) {
        this.todoListModel = todoListModel;
        LogTool.debug(todoListModel.toString());
        recyclerAdapter.notifyDataSetChanged();
    }


    public void initializeInjector() {
        ActivityComponent component = ActivityComponent.Initializer.init(this);
        component.inject(this);
        actionCreator = component.actionCreator();
        dataDispatcher = component.dataDispatcher();
        todoStore = component.todoStore();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(TodoListActivity.this));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .colorResId(R.color.transport_color)
                        .sizeResId(R.dimen.todo_list_divider_height)
                        .marginResId(R.dimen.item_margin, R.dimen.item_margin)
                        .build());
        recyclerAdapter = new RecyclerAdapter();

        recyclerView.setAdapter(recyclerAdapter);

        inputEditText.setOnEditorActionListener((view, actionId, event) -> {
            boolean flag = false;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String text = inputEditText.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    actionCreator.createItemNewAction(text);
                    inputEditText.setText("");
                }
                flag = true;
            }
            return flag;
        });
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(TodoListActivity.this).inflate(R.layout.item_todo, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final TodoItem item = todoListModel.list.get(position);

            holder.isCompletedImageView.setSelected(item.isCompleted());
            holder.isStarImageView.setSelected(item.isStar());
            holder.descriptionTextView.setText(item.getDescription());


            holder.isCompletedImageView.setOnClickListener(view ->
                    actionCreator.createItemEditAction(item.getId(), item.getDescription(), !item.isCompleted(), item.isStar())
            );

            holder.isStarImageView.setOnClickListener(view ->
                    actionCreator.createItemEditAction(item.getId(), item.getDescription(), item.isCompleted(), !item.isStar())
            );

            holder.itemView.setOnLongClickListener(view -> {
                        actionCreator.createItemDeleteAction(item.getId());
                        return false;
                    }
            );
        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (todoListModel != null && todoListModel.list != null) {
                count = todoListModel.list.size();
            }
            return count;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.isCompletedImageView)
            ImageView isCompletedImageView;
            @Bind(R.id.isStarImageView)
            ImageView isStarImageView;
            @Bind(R.id.descriptionTextView)
            TextView descriptionTextView;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
