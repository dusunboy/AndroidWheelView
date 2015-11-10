package androidwheelview.dusunboy.github.com.library.util;

import android.util.Log;

/**
 * Log日志管理
 * @author Vincent
 */
public class LogUtil {

    private static final String logMark;
    private static boolean isDebug;

    static {
        logMark = "================================================";
    }
    /**
     * 设置开启调试模式
     * @param mode
     */
    public static void setDebugMode(boolean mode) {
        isDebug = mode;
    }




    public static void v(Object msg) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.v(infos[0], logMark);
                Log.v(infos[0], logMark);
                Log.v(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2]);
                Log.v(infos[0], logMark);
                Log.v(infos[0], logMark);
            } else {
                Log.v(infos[0], "null");
            }
        }
    }

    public static void v(Object msg, Throwable t) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.v(infos[0], logMark);
                Log.v(infos[0], logMark);
                Log.v(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2], t);
                Log.v(infos[0], logMark);
                Log.v(infos[0], logMark);
            } else {
                Log.v(infos[0], "null", t);
            }
        }
    }

    public static void d(Object msg) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.d(infos[0], logMark);
                Log.d(infos[0], logMark);
                Log.d(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2]);
                Log.d(infos[0], logMark);
                Log.d(infos[0], logMark);
            } else {
                Log.d(infos[0], "null");
            }
        }
    }

    public static void d(Object msg, Throwable t) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.d(infos[0], logMark, t);
                Log.d(infos[0], logMark, t);
                Log.d(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2], t);
                Log.d(infos[0], logMark, t);
                Log.d(infos[0], logMark, t);
            } else {
                Log.d(infos[0], "null", t);
            }
        }
    }

    public static void i(Object msg) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.i(infos[0], logMark);
                Log.i(infos[0], logMark);
                Log.i(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2]);
                Log.i(infos[0], logMark);
                Log.i(infos[0], logMark);
            } else {
                Log.i(infos[0], "null");
            }
        }
    }

    public static void i(Object msg, Throwable t) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.i(infos[0], logMark, t);
                Log.i(infos[0], logMark, t);
                Log.i(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2], t);
                Log.i(infos[0], logMark, t);
                Log.i(infos[0], logMark, t);
            } else {
                Log.i(infos[0], "null", t);
            }
        }
    }

    public static void w(Object msg) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.w(infos[0], logMark);
                Log.w(infos[0], logMark);
                Log.w(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2]);
                Log.w(infos[0], logMark);
                Log.w(infos[0], logMark);
            } else {
                Log.w(infos[0], "null");
            }
        }
    }

    public static void w(Object msg, Throwable t) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.w(infos[0], logMark, t);
                Log.w(infos[0], logMark, t);
                Log.w(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2], t);
                Log.w(infos[0], logMark, t);
                Log.w(infos[0], logMark, t);
            } else {
                Log.w(infos[0], "null", t);
            }
        }
    }

    public static void e(Object msg) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.e(infos[0], logMark);
                Log.e(infos[0], logMark);
                Log.e(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2]);
                Log.e(infos[0], logMark);
                Log.e(infos[0], logMark);
            } else {
                Log.e(infos[0], "null");
            }
        }
    }

    public static void e(Object msg, Throwable t) {
        String[] infos = getAutoJumpLogInfos();

        if (isDebug) {
            if (msg != null) {
                Log.e(infos[0], logMark, t);
                Log.e(infos[0], logMark, t);
                Log.e(infos[0], String.valueOf(msg) + " : " + infos[1] + infos[2], t);
                Log.e(infos[0], logMark, t);
                Log.e(infos[0], logMark, t);
            } else {
                Log.e(infos[0], "null", t);
            }
        }
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     * @return
     */
    private static String[] getAutoJumpLogInfos() {
        String[] infos = new String[] { "", "", "" };
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length < 5) {
            Log.e("MyLogger", "Stack is too shallow!!!");
            return infos;
        } else {
            infos[0] = elements[4].getClassName().substring(
                    elements[4].getClassName().lastIndexOf(".") + 1);
            infos[1] = elements[4].getMethodName() + "()";
            infos[2] = " at (" + elements[4].getClassName() + ".java:"
                    + elements[4].getLineNumber() + ")";
            return infos;
        }
    }
}

