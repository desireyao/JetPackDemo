package com.yaoh.jetpack.log;

import android.util.Log;

import com.yaoh.jetpack.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Log文件目录
 * ——basePath
 */
public class LogTool {
    private static final String TAG = "LogTool";
    private static final String LOG_POSITION_FORMAT = "[(%1$s:%2$d)#%3$s]";
    private static final int JSON_INDENT = 4;

    private static volatile LogTool mInstance;

    private static boolean isShowD = BuildConfig.DEBUG;
    private static boolean isShowE = true;

    private static boolean isShowI = true;
    private static boolean isShowV = true;
    private static boolean isShowW = true;
    private static final int LOG_MAXLENGTH = 3 * 1024;

    private LogTool() {
    }

    public static LogTool getInstance() {
        if (mInstance == null) {
            synchronized (LogTool.class) {
                if (mInstance == null) {
                    mInstance = new LogTool();
                }
            }
        }
        return mInstance;
    }

    public static void LogI(String TAG, String info) {
        if (isShowI) {
            if (isShowD)
                info = getLogPosition() + " " + info;
            Log.i(TAG, info);
        }
    }

    public static void LogV(String TAG, String info) {
        if (isShowV) {
//            info = getLogPosition() + " " + info;
            Log.v(TAG, info);
        }
    }

    public static void LogW(String TAG, String info) {
        if (isShowW) {
            info = getLogPosition() + " " + info;
            Log.w(TAG, info);
        }
    }

    public static void LogE(String TAG, String info) {
        if (isShowE) {
            if (isShowD)
                info = getLogPosition() + " " + info;
            Log.e(TAG, info);
        }
    }


    public static void LogD(String TAG, String info) {
        if (!isShowD)
            return;

        long length = info.length();
        if (length <= LOG_MAXLENGTH) {                 // 长度小于等于限制直接打印
            Log.d(TAG, getLogPosition() + " " + info);
        } else {
            boolean isFirstLine = true;
            while (info.length() > LOG_MAXLENGTH) {     // 循环分段打印日志
                String logContent = info.substring(0, LOG_MAXLENGTH);
                info = info.replace(logContent, "");
                if (isFirstLine) {
                    Log.d(TAG, getLogPosition() + " " + logContent);
                } else {
                    Log.d(TAG, logContent);
                }
            }
            Log.d(TAG, info);// 打印剩余日志
        }
    }


    public static void LogE_DEBUG(String TAG, String info) {
        if (!isShowE || !isShowD) {
            return;
        }

        long length = info.length();
        if (length <= LOG_MAXLENGTH) {                 // 长度小于等于限制直接打印
            Log.e(TAG, getLogPosition() + " " + info);
        } else {
            boolean isFirstLine = true;
            while (info.length() > LOG_MAXLENGTH) {     // 循环分段打印日志
                String logContent = info.substring(0, LOG_MAXLENGTH);
                info = info.replace(logContent, "");
                if (isFirstLine) {
                    Log.e(TAG, getLogPosition() + " " + logContent);
                } else {
                    Log.e(TAG, logContent);
                }
            }
            Log.e(TAG, info);// 打印剩余日志
        }
    }


    /**
     * 以json格式打印
     *
     * @param TAG
     * @param info
     */
    public static void json(String TAG, String info) {
        String message = "";
        try {
            if (info.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(info);
                message = jsonObject.toString(JSON_INDENT);
            } else if (info.startsWith("{")) {
                JSONArray jsonArray = new JSONArray(info);
                message = jsonArray.toString(JSON_INDENT);
            }
        } catch (JSONException e) {
            message = getLogPosition() + "\n" + info;
            LogE_DEBUG(TAG, message + " \n exception: " + e.toString());
        }
        message = getLogPosition() + "\n" + message;
        LogE_DEBUG(TAG, message);
    }


    /**
     * 获取调用log的位置
     *
     * @return
     */
    private static String getLogPosition() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        if (trace != null && trace.length >= 3) {
            String methodName = trace[2].getMethodName();
            int lineNumber = trace[2].getLineNumber();
            String fileName = trace[2].getFileName();
            caller = String.format(LOG_POSITION_FORMAT, fileName, lineNumber, methodName);
        }

        return caller;
    }

}
