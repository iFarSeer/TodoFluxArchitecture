package com.farseer.todo.flux.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.farseer.todo.flux.R;
import com.farseer.todo.flux.base.BaseActivity;
import com.farseer.todo.flux.di.component.TodoHomeComponent;
import com.farseer.todo.flux.dispatcher.DataDispatcher;
import com.farseer.todo.flux.model.TodoListModel;
import com.farseer.todo.flux.pojo.TodoItem;
import com.farseer.todo.flux.store.TodoStore;
import com.farseer.todo.flux.tool.LogTool;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class TodoActivity extends BaseActivity {



    private TodoHomeComponent component;

    @Inject
    TodoStore todoStore;

    @Inject
    DataDispatcher dataDispatcher;



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
    public void clickFloatingActionButton() {
        actionCreator.createNewAction("天亮啦");
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


    @Subscribe
    public void onTodoModelChanged(final TodoListModel todoListModel) {
        this.todoListModel = todoListModel;
        LogTool.debug(todoListModel.toString());
        recyclerAdapter.notifyDataSetChanged();
    }

    public void initializeInjector() {
        component = TodoHomeComponent.Initializer.init(this);
        component.inject(this);
    }


    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(TodoActivity.this));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        inputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean flag = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    actionCreator.createNewAction(inputEditText.getText().toString());
                    inputEditText.setText("");
                    flag = true;
                }
                return flag;
            }
        });

        inputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
    }


    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(TodoActivity.this).
                    inflate(R.layout.item_todo, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            TodoItem item = todoListModel.list.get(position);

            holder.isActiveImageView.setSelected(item.isCompleted());
            holder.isStarImageView.setSelected(item.isStar());
            holder.descriptionTextView.setText(item.getDescription());

            LogTool.debug("getLayoutPosition = " + holder.getLayoutPosition());
            LogTool.debug("getAdapterPosition = " + holder.getAdapterPosition());
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

            @Bind(R.id.isActiveImageView)
            ImageView isActiveImageView;
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
