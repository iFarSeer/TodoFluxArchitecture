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

package com.farseer.todo.flux.view.base;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.farseer.todo.flux.R;

/**
 * Activity基类
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 设置Toolbar的title.
     *
     * @param toolbar toolbar
     * @param resId   title的resId
     */
    protected void setTitleTextView(Toolbar toolbar, @StringRes int resId) {
        setTitleTextView(toolbar, getString(resId));
    }

    /**
     * 设置ToolBar的title.
     *
     * @param toolbar toolbar
     * @param text    title的内容
     */
    protected void setTitleTextView(Toolbar toolbar, String text) {
        TextView titleTextView = ButterKnife.findById(toolbar, R.id.titleTextView);
        if (titleTextView != null) {
            titleTextView.setText(text);
            titleTextView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置toolbar最右侧按钮的图片,以及点击事件.
     *
     * @param toolbar         toolbar
     * @param resId           图片的resId
     * @param onClickListener 点击事件监听
     */
    protected void setActionImageView(Toolbar toolbar, @DrawableRes int resId, View.OnClickListener onClickListener) {
        View actionView = ButterKnife.findById(toolbar, R.id.actionView);
        ImageView actionImageView = ButterKnife.findById(toolbar, R.id.actionImageView);
        if (actionImageView != null) {
            actionView.setVisibility(View.VISIBLE);
            actionImageView.setImageResource(resId);
            actionView.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置toolbar次右侧按钮的图片,以及点击事件.
     *
     * @param toolbar         toolbar
     * @param resId           图片的resId
     * @param onClickListener 点击事件监听
     */
    protected void setOtherActionImageView(Toolbar toolbar, @DrawableRes int resId, View.OnClickListener onClickListener) {
        View otherActionView = ButterKnife.findById(toolbar, R.id.otherActionView);
        ImageView otherActionImageView = ButterKnife.findById(toolbar, R.id.otherActionImageView);
        if (otherActionImageView != null) {
            otherActionView.setVisibility(View.VISIBLE);
            otherActionImageView.setImageResource(resId);
            otherActionView.setOnClickListener(onClickListener);
        }
    }

}