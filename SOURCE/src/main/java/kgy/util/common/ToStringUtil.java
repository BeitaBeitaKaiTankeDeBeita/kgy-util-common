package kgy.util.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * toString 工具
 *
 * @author Kistory管音鹏
 * @deprecated
 */
public class ToStringUtil {

    public static String obj2Str(Object obj) {
        String result = "";

        switch (obj.getClass().getSimpleName()) {
            case "ArrayList":
                ArrayList list = (ArrayList) obj;

                for (Object value : list) {
                    result += "," + obj2Str(value);
                }
                result = "[" + result.replaceFirst(",", "") + "]L";
                break;
            case "BigDecimal":
                result = obj.toString() + "BD";
                break;
            case "BigDecimal[]":
            case "Object[]":
                Object[] objs = (Object[]) obj;

                for (Object value : objs) {
                    result += "," + obj2Str(value);
                }
                result = "[" + result.replaceFirst(",", "") + "]";
                break;
            case "Double":
                result = obj.toString() + "D";
                break;
            case "HashMap":
                HashMap map = (HashMap) obj;
                for (Object key : map.keySet()) {
                    result += "," + obj2Str(key) + "=" + obj2Str(map.get(key));
                }
                result = "{" + result.replaceFirst(",", "") + "}";
                break;
            case "Integer":
                result = obj.toString();
                break;
            case "LinkedHashSet":
            case "TreeSet":
                Set set = (Set) obj;

                for (Object value : set) {
                    result += "," + obj2Str(value);
                }
                result = "[" + result.replaceFirst(",", "") + "]S";
                break;
            case "String":
                result = "\"" + obj + "\"";
                break;
            default:
                System.out.println("警告：" + obj.getClass().getSimpleName());
                return obj.toString();
        }

        return result;
    }

    public static Object str2Obj(String str) {
        if (str.startsWith("[")) {
            if (str.endsWith("]S")) {
                Set set = new LinkedHashSet();

                str = str.substring(1, str.length() - 2);

                if (!str.contains("],[")) {                                     // Set<Object>
                    String[] strs0 = str.split(",");
                    for (String str0 : strs0) {
                        set.add(str2Obj(str0));
                    }
                } else {                                                        // Set<Object[]>
                    str = str.substring(1, str.length() - 1);
                    String[] strs0 = str.split("\\],\\[");
                    for (String str0 : strs0) {
                        set.add(str2Obj("[" + str0 + "]"));
                    }
                }

                return set;
            } else if (str.endsWith("]L")) {
                List list = new ArrayList();

                str = str.substring(1, str.length() - 2);

                if (!str.contains("],[")) {                                     // List<Object>
                    String[] strs0 = str.split(",");
                    for (String str0 : strs0) {
                        list.add(str2Obj(str0));
                    }
                } else {                                                        // List<Object[]>
                    str = str.substring(1, str.length() - 1);
                    String[] strs0 = str.split("\\],\\[");
                    for (String str0 : strs0) {
                        list.add(str2Obj("[" + str0 + "]"));
                    }
                }

                return list;
            } else {                                                            // Object[]
                Object[] objs;

                str = str.substring(1, str.length() - 1);

                if (str.contains("L,")) {
                    str = str.substring(0, str.length() - 1);
                    String[] strs0 = str.split("L,");
                    objs = new Object[strs0.length];
                    for (int i = 0; i < strs0.length; i++) {
                        objs[i] = str2Obj(strs0[i] + "L");
                    }
                } else {
                    String[] strs0 = str.split(",");
                    objs = new Object[strs0.length];
                    for (int i = 0; i < strs0.length; i++) {
                        objs[i] = str2Obj(strs0[i]);
                    }
                }

                return objs;
            }
        } else if (str.startsWith("{")) {
            Map map = new HashMap();

            str = str.substring(1, str.length() - 1);

            if (!str.endsWith("}")) {                                           // Map<Object, Object>
                String[] strs0 = str.split(",");
                for (String str0 : strs0) {
                    String[] strs1 = str0.split("=");
                    map.put(str2Obj(strs1[0]), str2Obj(strs1[1]));
                }
            } else {                                                            // Map<Object, Map<Object, Object>>
                str = str.substring(0, str.length() - 1);
                String[] strs0 = str.split("},");
                for (String str0 : strs0) {
                    String[] strs1 = str0.split("=\\{");
                    map.put(str2Obj(strs1[0]), str2Obj("{" + strs1[1] + "}"));
                }
            }

            return map;
        } else {
            if (str.startsWith("\"") && str.endsWith("\"")) {                   // String
                return str.replaceAll("\"", "");
            } else if (str.endsWith("BD")) {                                    // BigDecimal
                return new BigDecimal(str.replace("BD", ""));
            } else if (str.endsWith("D")) {                                     // Double
                return new Double(str.replace("D", ""));
            } else {                                                            //Integer
                return new Integer(str);
            }
        }
    }

    private ToStringUtil() {
    }
}
