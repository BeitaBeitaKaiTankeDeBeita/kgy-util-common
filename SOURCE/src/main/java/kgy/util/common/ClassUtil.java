package kgy.util.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class 工具类
 *
 * @author KistoryG
 */
public class ClassUtil {

  private static final Logger LOG = Logger.getLogger(ClassUtil.class.getName());

  public static Object get(Object obj, String name) {
    try {
      try {
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(obj);
      } catch (IllegalAccessException iae) {
        LogUtil.log(LOG, Level.WARNING, iae.toString());
        return null;
      }
    } catch (NoSuchFieldException nsfe) {
      LogUtil.log(LOG, Level.WARNING, nsfe.toString());
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
