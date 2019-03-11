package kgy.util.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Class 工具类
 *
 * @author KistoryG
 */
public class ClassUtil {

  public static Object get(Object obj, String name) {
    try {
      try {
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(obj);
      } catch (IllegalAccessException iae) {
        // Ignore
        return null;
      }
    } catch (NoSuchFieldException nsfe) {
      // Ignore
      return null;
    }
  }

  public static void set(Object obj, String name, Object value) {
    try {
      try {
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(obj, value);
      } catch (IllegalAccessException iae) {
        // Ignore
      }
    } catch (NoSuchFieldException nsfe) {
      // Ignore
    }
  }

  public static Object invoke(String name, Object obj) throws NoSuchMethodException {
    try {
      return obj.getClass().getMethod(name).invoke(obj);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // Ignore
      return null;
    }
  }

  private ClassUtil() {
  }
}
