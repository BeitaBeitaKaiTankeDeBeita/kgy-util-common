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

  private NumericUtil() {
  }
}
