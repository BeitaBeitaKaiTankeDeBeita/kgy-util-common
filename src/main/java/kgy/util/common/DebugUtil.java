package kgy.util.common;

import java.util.Date;

/**
 * 调试工具类
 *
 * @author Kistory管音鹏
 * @version 1.0.1
 * @build 2015-11-17 11:24:05
 */
@SuppressWarnings({"UseOfSystemOutOrSystemErr", "CallToPrintStackTrace"})
public class DebugUtil {

    public static void print(Class cls, int lineNum, String context) {
        System.out.print("\n"
                + "              __....__\n"
                + "         .-~~/  \\__/  \\~~-.\n"
                + "        /_/``\\__/  \\__/``\\_\\.--.\n"
                + "       /  \\__/  \\__/  \\__/  \\   o`.\n"
                + "   `==/\\__/__\\__/__\\__/__\\__/\\`'--'\n"
                + "      ~/__/__/^^^^^^^^\\__\\__\\~\n"
                + "调试:\t" + (null == cls ? "" : ("(" + cls.getName() + (lineNum < 0 ? "" : (":" + lineNum)) + ")")) + "\n"
                + "\t" + DatetimeUtil.format(new Date()) + "\t" + context + "\n");
    }

    public static void print(Class cls, String context) {
        print(cls, -1, context);
    }

    public static void print(String context) {
        System.out.print("\n"
                + "调试:\t" + DatetimeUtil.format(new Date()) + "\t" + context + "\n");
    }

    public static void print(Object context) {
        print(context.toString());
    }

    public static void printStackTrace(Exception exception) {
        System.out.print("\n");
        exception.printStackTrace();
        System.out.print("\n");
    }

    private DebugUtil() {
    }
}
