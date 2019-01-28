package kgy.util.common;

/**
 * Iterable 工具类
 *
 * @author KistoryG
 */
public class IterableUtil {

    public static String join(Iterable<?> iterable, String separator, boolean withBrackets) {
        String separatorPlaceholder = "＾＿＾占位符＞＿＜";
        String str = "";
        for (Object obj : iterable) {
            str += separatorPlaceholder;
            if (null != obj) {
                str += obj.toString();
            }
        }
        str = str.replaceFirst(separatorPlaceholder, "").replaceAll(separatorPlaceholder, separator);

        if (withBrackets) {
            str = "[" + str + "]";
        }

        return str;
    }

    public static String join(Iterable<?> iterable, String separator) {
        return join(iterable, separator, false);
    }

    private IterableUtil() {
    }
}
