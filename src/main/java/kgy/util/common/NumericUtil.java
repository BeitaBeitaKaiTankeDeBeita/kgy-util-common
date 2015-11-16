package kgy.util.common;

import java.text.DecimalFormat;

/**
 * 数字工具类
 *
 * @author Kistory管音鹏
 * @version 1.0
 * @build 2015-9-18 12:01:28
 */
public class NumericUtil {

    public static String format(Double dbl, String pattern) {
        if (null != dbl) {
            return new DecimalFormat(pattern).format(dbl);
        }
        return "";
    }

    public static String format(Double dbl) {
        return format(dbl, ",##0.00######");
    }

    public static String formatPercent(Double dbl) {
        return format(dbl, ",##0.00######%");
    }

    /**
     * 阿拉伯数字转罗马数字
     *
     * @param arabic
     * @return
     */
    public static String toRoman(int arabic) {
        if (arabic < 1 || arabic > 3999) {
            return "";
        }
        int[] arabicFigures = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanFigures = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String result = "";
        for (int i = 0; i < arabicFigures.length; i++) {
            while (arabic >= arabicFigures[i]) {
                result += romanFigures[i];
                arabic -= arabicFigures[i];
            }
        }
        return result;
    }

    /**
     * 格式化数据大小
     *
     * @param size
     * @return
     */
    public static String formateDateSize(Long size) {
        String unitsString = "KMGTPEB";
        String unit = "";
        int i = 0;
        double dbl = size;
        while (dbl >= 1024) {
            unit = unitsString.substring(i++, i);
            dbl /= 1024;
        }

        return format(dbl, "0.###") + " " + unit + "B";
    }

    private NumericUtil() {
    }
}
