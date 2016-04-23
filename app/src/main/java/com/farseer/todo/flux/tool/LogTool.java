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
public class LogTool {

    public static final boolean flag = true;

    private static String className;
    private static ArrayList<String> methods;

    static {
        className = LogTool.class.getName();
        methods = new ArrayList<String>();

        Method[] ms = LogTool.class.getDeclaredMethods();
        for (Method m : ms) {
            methods.add(m.getName());
        }
    }

    public static void debug(String tag, String msg) {
        if (flag) {
            Log.d(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void error(String tag, String msg) {
        if (flag) {
            Log.e(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void info(String tag, String msg) {
        if (flag) {
            Log.i(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void warn(String tag, String msg) {
        if (flag) {
            Log.w(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void verbose(String tag, String msg) {
        if (flag) {
            Log.v(tag, getMsgWithLineNumber(msg));
        }
    }


    public static void debug(String msg) {
        if (flag) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.d(content[0], content[1]);
        }
    }

    public static void error(String msg) {
        if (flag) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.e(content[0], content[1]);
        }
    }

    public static void info(String msg) {
        if (flag) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.i(content[0], content[1]);
        }
    }

    public static void warn(String msg) {
        if (flag) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.w(content[0], content[1]);
        }
    }

    public static void verbose(String msg) {
        if (flag) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.v(content[0], content[1]);
        }
    }

    public static String getMsgWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (className.equals(st.getClassName()) || methods.contains(st.getMethodName())) {
                    continue;
                } else {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String TAG = st.getClassName().substring(b);
                    String message = TAG + "->" + st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                    return message;
                }

            }
        } catch (Exception e) {

        }
        return msg;
    }

    public static String[] getMsgAndTagWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (className.equals(st.getClassName()) || methods.contains(st.getMethodName())) {
                    continue;
                } else {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String TAG = st.getClassName().substring(b);
                    String message = st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                    String[] content = new String[]{TAG, message};
                    return content;
                }

            }
        } catch (Exception e) {

        }
        return new String[]{"universal tag", msg};
    }


}

