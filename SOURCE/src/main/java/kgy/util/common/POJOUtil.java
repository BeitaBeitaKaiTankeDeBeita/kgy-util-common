package kgy.util.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * POJO 工具类
 *
 * @author Kistory管音鹏
 * @version 1.0.0
 * @build 2015-06-15 21:22:59
 */
public class POJOUtil {

    public static Object get(Object o, String s) {
        try {
            try {
                Field f = o.getClass().getDeclaredField(s);
                f.setAccessible(true);
                return f.get(o);
            } catch (IllegalAccessException e) {
                return null;
            }
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public static Object invoke(Object o, String s) throws NoSuchMethodException {
        try {
            return o.getClass().getMethod(s).invoke(o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    private POJOUtil() {
    }
}
