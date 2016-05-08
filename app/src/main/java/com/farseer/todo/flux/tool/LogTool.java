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

package com.farseer.todo.flux.tool;


import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * 日志工具类
 *
 * @author zhaosc
 * @version 1.0.0
 * @since 2016-04-19
 */
public final class LogTool {

    private static final String TAG = LogTool.class.getSimpleName();
    private static final boolean FLAG = true;
    private static String mClassName;
    private static ArrayList<String> mMethods;

    static {
        mClassName = LogTool.class.getName();
        mMethods = new ArrayList<>();

        Method[] ms = LogTool.class.getDeclaredMethods();
        for (Method m : ms) {
            mMethods.add(m.getName());
        }
    }

    private LogTool() {
        //not called
    }

    /**
     * debug.
     *
     * @param tag tag
     * @param msg msg
     */
    public static void debug(String tag, String msg) {
        if (FLAG) {
            Log.d(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * debug.
     *
     * @param msg msg
     */
    public static void debug(String msg) {
        if (FLAG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.d(content[0], content[1]);
        }
    }

    /**
     * error.
     *
     * @param tag tag
     * @param msg msg
     */
    public static void error(String tag, String msg) {
        if (FLAG) {
            Log.e(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * error.
     *
     * @param msg msg
     */
    public static void error(String msg) {
        if (FLAG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.e(content[0], content[1]);
        }
    }

    /**
     * info.
     *
     * @param tag tag
     * @param msg msg
     */
    public static void info(String tag, String msg) {
        if (FLAG) {
            Log.i(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * info.
     *
     * @param msg msg
     */
    public static void info(String msg) {
        if (FLAG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.i(content[0], content[1]);
        }
    }

    /**
     * warn.
     *
     * @param tag tag
     * @param msg msg
     */
    public static void warn(String tag, String msg) {
        if (FLAG) {
            Log.w(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * warn.
     *
     * @param msg msg
     */
    public static void warn(String msg) {
        if (FLAG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.w(content[0], content[1]);
        }
    }

    /**
     * verbose.
     *
     * @param tag tag
     * @param msg msg
     */
    public static void verbose(String tag, String msg) {
        if (FLAG) {
            Log.v(tag, getMsgWithLineNumber(msg));
        }
    }

    /**
     * verbose.
     *
     * @param msg msg
     */
    public static void verbose(String msg) {
        if (FLAG) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.v(content[0], content[1]);
        }
    }

    private static String getMsgWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (mClassName.equals(st.getClassName()) || mMethods.contains(st.getMethodName())) {
                    continue;
                } else {
                    int index = st.getClassName().lastIndexOf(".") + 1;
                    String tag = st.getClassName().substring(index);
                    String message = tag + "->" + st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                    return message;
                }

            }
        } catch (Exception exception) {
            Log.e(TAG, exception.getLocalizedMessage());
        }
        return msg;
    }

    private static String[] getMsgAndTagWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (mClassName.equals(st.getClassName()) || mMethods.contains(st.getMethodName())) {
                    continue;
                } else {
                    int index = st.getClassName().lastIndexOf(".") + 1;
                    String tag = st.getClassName().substring(index);
                    String message = st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                    String[] content = new String[]{tag, message};
                    return content;
                }

            }
        } catch (Exception exception) {
            Log.e(TAG, exception.getLocalizedMessage());
        }
        return new String[]{"universal tag", msg};
    }

}

