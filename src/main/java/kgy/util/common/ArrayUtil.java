package kgy.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * 数组工具类
 *
 * @author Kistory
 * @build 2016-1-31 16:23:52
 */
public class ArrayUtil {

  private static final Logger LOG = Logger.getLogger(ArrayUtil.class.getName());

  public static Double[] toDoubleArray(String[] strs) {
    Double[] dbls = new Double[strs.length];
    for (int i = 0; i < strs.length; i++) {
      String str = strs[i].trim();
      if ("".equals(str)) {
        dbls[i] = 0D;
      } else {
        dbls[i] = Double.parseDouble(str);
      }
    }
    return dbls;
  }

  public static Double[] toDoubleArray(String str) {
    if (null != str && !str.isEmpty()) {
      if (str.contains(",")) {
        return toDoubleArray(str.split(","));
      } else if (str.contains("{")) {
        return toDoubleArray(str.substring(1, str.length() - 1).split("\\}\\{"));
      } else {
        return new Double[]{
          Double.parseDouble(str)
        };
      }
    }
    return new Double[0];
  }

  public static Integer[] toIntegerArray(String[] strs) {
    Integer[] ints = new Integer[strs.length];
    for (int i = 0; i < strs.length; i++) {
      String str = strs[i].trim();
      if ("".equals(str)) {
        ints[i] = 0;
      } else {
        ints[i] = Integer.parseInt(str);
      }
    }
    return ints;
  }

  public static Integer[] toIntegerArray(String str) {
    if (null != str && !str.isEmpty()) {
      if (str.contains(",")) {
        return toIntegerArray(str.split(","));
      } else if (str.contains("{")) {
        return toIntegerArray(str.substring(1, str.length() - 1).split("\\}\\{"));
      } else {
        return new Integer[]{
          Integer.parseInt(str)
        };
      }
    }
    return new Integer[0];
  }

  public static String[] toStringArray(String str) {
    if (!str.isEmpty()) {
      if (str.contains(",")) {
        return str.split(",");
      } else if (str.contains("{")) {
        return str.replace("}{", ",").replaceAll("\\{|\\}", "").split(",");
      } else {
        return new String[]{
          str
        };
      }
    }
    return new String[0];
  }

  public static Object[] mergeArray(Object[] objs, Object[] objs1) {
    if (null != objs && null != objs1) {
      List<Object> l = new ArrayList<>(objs.length + objs1.length);
      l.addAll(Arrays.asList(objs));
      l.addAll(Arrays.asList(objs1));
      return l.toArray(new Object[l.size()]);
    } else if (null != objs) {
      return objs;
    } else if (null != objs1) {
      return objs1;
    } else {
      return new Object[0];
    }
  }

  public static Integer[] mergeIntegerArray(Integer[] ints, Integer[] ints1) {
    if (null != ints && null != ints1) {
      Set<Integer> set = new HashSet<>(ints.length + ints1.length);
      set.addAll(Arrays.asList(ints));
      set.addAll(Arrays.asList(ints1));
      return set.toArray(new Integer[set.size()]);
    } else if (null != ints) {
      return ints;
    } else if (null != ints1) {
      return ints1;
    } else {
      return new Integer[0];
    }
  }

  public static Integer[] mergeIntegerArray(Integer[] ints, String str) {
    return mergeIntegerArray(ints, toIntegerArray(str));
  }

  public static String toString(Object[] objs) {
    String str = "";
    for (Object obj : objs) {
      if (null != obj) {
        str += ", " + obj.toString();
      }
    }
    return str.replaceFirst(", ", "");
  }

  /**
   * ∩ (String<{}{}>)
   *
   * @param str
   * @param str1
   * @return
   * @deprecated
   */
  public static String intersection(String str, String str1) {
    String result = "";
    for (String str2 : str.substring(1, str.length() - 1).split("\\}\\{")) {
      str2 = "{" + str2 + "}";
      if (!result.contains(str2)) {
        if (!str1.contains(str2)) {
          result = result.replace(str2, "");
        } else {
          result += str2;
        }
      }
    }
    return result;
  }

  private ArrayUtil() {
  }
}
