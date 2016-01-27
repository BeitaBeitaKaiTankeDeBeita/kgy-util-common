package kgy.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数组工具类
 *
 * @author Kistory
 * @version 1.1.0
 */
public class ArrayUtil {

    public static Double[] toDoubleArray(String[] strs) {
        Double[] dbls = new Double[strs.length];
        for (int i = 0; i < strs.length; i++) {
            dbls[i] = Double.parseDouble(strs[i].trim());
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
            ints[i] = Integer.parseInt(strs[i].trim());
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
        return null;
    }

    public static Object[] mergeArray(Object[] objs, Object[] objs1) {
        if (null != objs && null != objs1) {
            List<Object> l = new ArrayList<>();
            l.addAll(Arrays.asList(objs));
            l.addAll(Arrays.asList(objs1));
            return l.toArray(new Object[l.size()]);
        } else if (null != objs) {
            return objs;
        } else if (null != objs1) {
            return objs1;
        } else {
            return null;
        }
    }

    public static Integer[] mergeIntegerArray(Integer[] ints, Integer[] ints1) {
        if (null != ints && null != ints1) {
            Set<Integer> set = new HashSet<>();
            set.addAll(Arrays.asList(ints));
            set.addAll(Arrays.asList(ints1));
            return set.toArray(new Integer[set.size()]);
        } else if (null != ints) {
            return ints;
        } else if (null != ints1) {
            return ints1;
        } else {
            return null;
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
     * ∩
     *
     * @param str
     * @param str1
     * @return
     */
    public static String intersection(String str, String str1) {
        String r = "";
        for (String s2 : str.substring(1, str.length() - 1).split("\\}\\{")) {
            s2 = "{" + s2 + "}";
            if (!r.contains(s2)) {
                if (!str1.contains(s2)) {
                    r = r.replace(s2, "");
                } else {
                    r += s2;
                }
            }
        }
        return r;
    }

    private ArrayUtil() {
    }
}
