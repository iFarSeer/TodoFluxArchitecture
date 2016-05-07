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

    Store mTodoStore;

    Dispatcher mDataDispatcher;

    ActionCreator mActionCreator;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.inputEditText)
    AppCompatEditText mInputEditText;

    private TodoListModel mTodoListModel;

    private RecyclerAdapter mRecyclerAdapter;

    private MaterialDialog mMaterialDialog;

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
        mTodoStore.register();
        mDataDispatcher.register(this);

        mActionCreator.createListLoadAction();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTodoStore.unregister();
        mDataDispatcher.unregister(this);
    }

    /**
     * 订阅TodoListModel数据事件
     *
     * @param todoListModel todoListModel
     */
    @Subscribe
    public void onTodoModelChanged(final TodoListModel todoListModel) {
        this.mTodoListModel = todoListModel;
        LogTool.debug(todoListModel.toString());
        mRecyclerAdapter.notifyDataSetChanged();
    }

    private void initializeInjector() {
        ActivityComponent component = ActivityComponent.Initializer.init(this);
        component.inject(this);
        mActionCreator = component.actionCreator();
        mDataDispatcher = component.dataDispatcher();
        mTodoStore = component.todoStore();
    }

    private void initView() {

        setSupportActionBar(mToolbar);
        setTitleTextView(mToolbar, R.string.app_name);
        setActionImageView(mToolbar, R.drawable.action_more, (view) -> {
            hideDialog();
            showAboutDialog();
        });
        setOtherActionImageView(mToolbar, R.drawable.action_filter, (view) -> {
            hideDialog();
            showFilterDialog();
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(TodoListActivity.this));
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .colorResId(R.color.transport_color)
                        .sizeResId(R.dimen.todo_list_divider_height)
                        .marginResId(R.dimen.item_margin, R.dimen.item_margin)
                        .build());
        mRecyclerAdapter = new RecyclerAdapter();

        mRecyclerView.setAdapter(mRecyclerAdapter);

        mInputEditText.setOnEditorActionListener((view, actionId, event) -> {
            boolean flag = false;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String text = mInputEditText.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    mActionCreator.createItemNewAction(text);
                    mInputEditText.setText("");
                }
                flag = true;
            }
            return flag;
        });

        mRecyclerAdapter.setOnClickListener(view -> {
            TodoItem item = (TodoItem) view.getTag();
            showEditDialog(item);
        });

        mRecyclerAdapter.setOnLongClickListener(view -> {
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

        mMaterialDialog = new MaterialDialog.Builder(this)
                .customView(customView, false)
                .positiveColorRes(R.color.positive_color)
                .negativeColorRes(R.color.positive_color)
                .positiveText(R.string.action_sure)
                .negativeText(R.string.action_cancel)
                .onPositive((dialog, which) -> {
                            String text = editText.getText().toString();
                            if (!TextUtils.isEmpty(text)) {
                                mActionCreator.createItemEditAction(item.getId(), text, item.isCompleted(), item.isStared());
                                dialog.dismiss();
                            }
                        }
                )
                .build();


        mMaterialDialog.show();
    }

    //显示删除对话框
    private void showDeleteDialog(TodoItem item) {
        mMaterialDialog = new MaterialDialog.Builder(this)
                .content(R.string.todo_item_delete_content)
                .positiveColorRes(R.color.positive_color)
                .negativeColorRes(R.color.positive_color)
                .positiveText(R.string.action_sure)
                .negativeText(R.string.action_cancel)
                .onPositive((dialog, which) -> {
                            mActionCreator.createItemDeleteAction(item.getId());
                            dialog.dismiss();
                        }
                )
                .onNegative((dialog, which) -> dialog.dismiss())
                .build();
        mMaterialDialog.show();
    }

    //显示过滤条件
    private void showFilterDialog() {
        mMaterialDialog = new MaterialDialog.Builder(this)
                .title(R.string.action_filter)
                .items(R.array.todo_filter_array)
                .itemsCallback((dialog, view, which, text) -> {
                    switch (which) {
                        case 0:
                            mActionCreator.createListAllAction();
                            break;
                        case 1:
                            mActionCreator.createListCompletedAction();
                            break;
                        case 2:
                            mActionCreator.createListStaredAction();
                            break;
                        default:
                            break;
                    }
                })
                .build();

        mMaterialDialog.show();
    }

    //显示About对话框
    private void showAboutDialog() {
        mMaterialDialog = new MaterialDialog.Builder(this)
                .title(R.string.action_about)
                .content(Html.fromHtml(getString(R.string.about_content)))
                .contentLineSpacing(1.6f)
                .build();
        mMaterialDialog.show();
    }

    //隐藏对话框
    private void hideDialog() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }

    /**
     * RecyclerAdapter
     */
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private View.OnClickListener mOnClickListener;
        private View.OnLongClickListener mLongClickListener;

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
        }

        public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
            this.mLongClickListener = onLongClickListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(TodoListActivity.this).inflate(R.layout.item_todo, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final TodoItem item = mTodoListModel.mTodoItemList.get(position);

            holder.mIsCompletedCheckBox.setChecked(item.isCompleted());
            holder.mIsStarImageView.setSelected(item.isStared());

            holder.mDescriptionTextView.setText(item.getDescription());
            if (item.isCompleted()) {
                holder.mDescriptionTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            } else {
                holder.mDescriptionTextView.getPaint().setFlags(0);
            }

            holder.mIsCompletedCheckBox.setOnClickListener(view ->
                    mActionCreator.createItemEditAction(item.getId(), item.getDescription(), !item.isCompleted(), item.isStared())
            );

            holder.mIsStarImageView.setOnClickListener(view ->
                    mActionCreator.createItemEditAction(item.getId(), item.getDescription(), item.isCompleted(), !item.isStared())
            );

            holder.itemView.setTag(item);
            holder.itemView.setOnClickListener(mOnClickListener);
            holder.itemView.setOnLongClickListener(mLongClickListener);
        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (mTodoListModel != null && mTodoListModel.mTodoItemList != null) {
                count = mTodoListModel.mTodoItemList.size();
            }
            return count;
        }

        /**
         * RecyclerAdapter's ViewHolder
         */
        class ViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.isCompletedCheckBox)
            CheckBox mIsCompletedCheckBox;
            @Bind(R.id.isStarImageView)
            ImageView mIsStarImageView;
            @Bind(R.id.descriptionTextView)
            TextView mDescriptionTextView;

            /**
             * ViewHolder
             *
             * @param view view
             */
            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
