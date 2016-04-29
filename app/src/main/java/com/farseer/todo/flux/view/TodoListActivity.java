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

import com.afollestad.materialdialogs.MaterialDialog;
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

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TodoListActivity extends BaseActivity {

    Store todoStore;

    Dispatcher dataDispatcher;

    ActionCreator actionCreator;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.inputEditText)
    AppCompatEditText inputEditText;

    private TodoListModel todoListModel;

    private RecyclerAdapter recyclerAdapter;

    private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        ButterKnife.bind(this);

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

        setSupportActionBar(toolbar);
        setTitleTextView(toolbar, R.string.app_name);
        setActionImageView(toolbar, R.drawable.action_more, (view) -> {
            hideDialog();
            showAboutDialog();
        });
        setOtherActionImageView(toolbar, R.drawable.action_filter, (view) -> {
            hideDialog();
            showFilterDialog();
        });

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

        recyclerAdapter.setOnClickListener(view -> {
            TodoItem item = (TodoItem) view.getTag();
            showEditDialog(item);
        });

        recyclerAdapter.setOnLongClickListener(view -> {
            TodoItem item = (TodoItem) view.getTag();
            hideDialog();
            showDeleteDialog(item);
            return false;
        });

    }

    //显示编辑对话框
    private void showEditDialog(TodoItem item) {

        View customView = LayoutInflater.from(this).inflate(R.layout.dialog_todo_edit, null);
        final AppCompatEditText editText = ButterKnife.findById(customView, R.id.inputEditText);
        editText.setText(item.getDescription());
        editText.setSelection(item.getDescription().length());

        materialDialog = new MaterialDialog.Builder(this)
                .customView(customView, false)
                .positiveColorRes(R.color.positive_color)
                .negativeColorRes(R.color.positive_color)
                .positiveText(R.string.action_sure)
                .negativeText(R.string.action_cancel)
                .onPositive((dialog, which) -> {
                            String text = editText.getText().toString();
                            if (!TextUtils.isEmpty(text)) {
                                actionCreator.createItemEditAction(item.getId(), text, item.isCompleted(), item.isStar());
                                dialog.dismiss();
                            }
                        }
                )
                .build();


        materialDialog.show();
    }

    //显示删除对话框
    private void showDeleteDialog(TodoItem item) {
        materialDialog = new MaterialDialog.Builder(this)
                .content(R.string.todo_item_delete_content)
                .positiveColorRes(R.color.positive_color)
                .negativeColorRes(R.color.positive_color)
                .positiveText(R.string.action_sure)
                .negativeText(R.string.action_cancel)
                .onPositive((dialog, which) -> {
                            actionCreator.createItemDeleteAction(item.getId());
                            dialog.dismiss();
                        }
                )
                .onNegative((dialog, which) -> dialog.dismiss())
                .build();
        materialDialog.show();
    }

    //显示过滤条件
    private void showFilterDialog() {
        materialDialog = new MaterialDialog.Builder(this)
                .title(R.string.action_filter)
                .items(R.array.todo_filter_array)
                .itemsCallback((dialog, view, which, text) -> {
                    switch (which) {
                        case 0:
                            actionCreator.createListAllAction();
                            break;
                        case 1:
                            actionCreator.createListCompletedAction();
                            break;
                        case 2:
                            actionCreator.createListStaredAction();
                            break;
                    }
                })
                .build();

        materialDialog.show();
    }

    //显示About对话框
    private void showAboutDialog() {
        materialDialog = new MaterialDialog.Builder(this)
                .title(R.string.action_about)
                .content(Html.fromHtml(getString(R.string.about_content)))
                .contentLineSpacing(1.6f)
                .build();
        materialDialog.show();
    }

    //隐藏对话框
    private void hideDialog() {
        if (materialDialog != null) {
            materialDialog.dismiss();
            materialDialog = null;
        }
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private View.OnClickListener onClickListener;
        private View.OnLongClickListener onLongClickListener;

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
            this.onLongClickListener = onLongClickListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(TodoListActivity.this).inflate(R.layout.item_todo, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final TodoItem item = todoListModel.list.get(position);

            holder.isCompletedCheckBox.setChecked(item.isCompleted());
            holder.isStarImageView.setSelected(item.isStar());

            holder.descriptionTextView.setText(item.getDescription());
            if (item.isCompleted()) {
                holder.descriptionTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            } else {
                holder.descriptionTextView.getPaint().setFlags(0);
            }

            holder.isCompletedCheckBox.setOnClickListener(view ->
                    actionCreator.createItemEditAction(item.getId(), item.getDescription(), !item.isCompleted(), item.isStar())
            );

            holder.isStarImageView.setOnClickListener(view ->
                    actionCreator.createItemEditAction(item.getId(), item.getDescription(), item.isCompleted(), !item.isStar())
            );

            holder.itemView.setTag(item);
            holder.itemView.setOnClickListener(onClickListener);
            holder.itemView.setOnLongClickListener(onLongClickListener);
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

            @Bind(R.id.isCompletedCheckBox)
            CheckBox isCompletedCheckBox;
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
