package kgy.util.common;

import java.text.DecimalFormat;

/**
 * 数字工具类
 *
 * @author KistoryG
 * @version 1.1
 * @build 2017-6-13 09:19:18
 */
public class NumericUtil {

    public static String format(Object obj, String pattern) {
        if (null != obj) {
            return new DecimalFormat(pattern).format(obj);
        }
        return "";
    }

    public static String format(Object obj) {
        return format(obj, ",##0.00######");
    }

    public static String formatPercent(Object obj) {
        return format(obj, ",##0.00######%");
    }

    /**
     * 阿拉伯数字转罗马数字
     *
     * @param arabic
     *
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
     *
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

    /**
     * 人民币大写
     *
     * @param money
     *
     * @return
     */
    public static String toCapital(String money) {
        String hanziDigit = "零壹贰叁肆伍陆柒捌玖";
        String unit = "仟佰拾亿仟佰拾万仟佰拾元角分";

        boolean isNegative = money.contains("-");
        if (isNegative) {
            money = money.replace("-", "");
        }

        money += "00";

        int idx = money.indexOf('.');
        money = idx >= 0 ? money.substring(0, idx) + money.substring(idx + 1, idx + 3) : money;
        unit = unit.substring(unit.length() - money.length());

        String rtn = "";
        for (int i = 0; i < money.length(); i++) {
            int digit = Integer.parseInt(money.substring(i, i + 1));
            rtn += hanziDigit.substring(digit, digit + 1) + unit.substring(i, i + 1);
        }

        rtn = rtn.replaceAll("零角零分$", "整")
                .replaceAll("零[仟佰拾]", "零")
                .replaceAll("零{2,}", "零")
                .replaceAll("零([亿|万])", "$1")
                .replaceAll("零+元", "元")
                .replaceAll("亿零{0,3}万", "亿")
                .replaceAll("^元", "零元");

        return isNegative ? "负" + rtn : rtn;
    }

    private NumericUtil() {
    }
}
