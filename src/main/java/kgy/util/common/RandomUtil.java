package kgy.util.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 随机工具类
 *
 * @author Kistory
 */
public class RandomUtil {

  /**
   * 随机整数
   *
   * @param min
   * @param max
   * @return
   */
  public static int randomInt(int min, int max) {
    return new BigDecimal(min - 1 + (max - min + 1) * Math.random()).setScale(0, RoundingMode.CEILING).intValue();
  }

  /**
   * 随机整数
   *
   * @param min
   * @param max
   * @return
   */
  public static int randomInt(double min, double max) {
    return new BigDecimal(min - 1 + (max - min + 1) * Math.random()).setScale(0, RoundingMode.CEILING).intValue();
  }

  /**
   * 有概率的随机整数
   *
   * @param min
   * @param max
   * @param probability
   * @return
   */
  public static int randomInt(int min, int max, double[] probability) {
    double r = Math.random();
    double p0, p1 = 1;
    for (int i = 0; i < max - min; i++) {
      try {
        p0 = probability[i];
        p1 -= p0;
      } catch (ArrayIndexOutOfBoundsException e) {
        p0 = p1 / (max - min - probability.length + 1);
      }
      if (r < p0) {
        return min + i;
      } else {
        r -= p0;
      }
    }
    return max;
  }

  /**
   * 随机浮点数
   *
   * @param min
   * @param max
   * @param scale
   * @return
   */
  public static float randomFloat(float min, float max, int scale) {
    double dbl = 1 / Math.pow(10, scale);
    return new BigDecimal(min - dbl + (max - min + dbl) * Math.random()).setScale(scale, RoundingMode.CEILING).floatValue();
  }

  /**
   * 随机双精度型
   *
   * @param min
   * @param max
   * @param scale
   * @return
   */
  public static double randomDouble(double min, double max, int scale) {
    double dbl = 1 / Math.pow(10, scale);
    return new BigDecimal(min - dbl + (max - min + dbl) * Math.random()).setScale(scale, RoundingMode.CEILING).doubleValue();
  }

  /**
   * 有概率的随机布尔型
   *
   * @param probability
   * @return
   */
  public static boolean randomBoolean(double probability) {
    return Math.random() >= (1 - probability);
  }

  /**
   * 随机布尔型
   *
   * @return
   */
  public static boolean randomBoolean() {
    return randomBoolean(0.5);
  }

  /**
   * 随机字符串
   *
   * @param length
   * @return
   */
  public static String randomString(int length) {
    return randomString("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", length);
  }

  /**
   * 随机字符串
   *
   * @param seeds
   * @param length
   * @return
   */
  public static String randomString(String[] seeds, int length) {
    String result = "";
    for (int i = 0; i < length; i++) {
      result += seeds[RandomUtil.randomInt(0, seeds.length - 1)];
    }
    return result;
  }

  /**
   * 随机字符串
   *
   * @param seedsStr
   * @param length
   * @return
   */
  public static String randomString(String seedsStr, int length) {
    return RandomUtil.randomString(seedsStr.split(""), length);
  }

  /**
   * 随机日期
   *
   * @param minYear
   * @param maxYear
   * @return
   */
  public static Date randomData(int minYear, int maxYear) {
    int intYear = RandomUtil.randomInt(minYear, maxYear);
    int intMonth = RandomUtil.randomInt(1, 12);
    int intDay;
    if (2 == intMonth) {
      if ((intYear % 4 == 0 && intYear % 100 != 0) || intYear % 400 == 0) {
        intDay = RandomUtil.randomInt(1, 29);
      } else {
        intDay = RandomUtil.randomInt(1, 28);
      }
    } else if (4 == intMonth || 6 == intMonth || 9 == intMonth || 11 == intMonth) {
      intDay = RandomUtil.randomInt(1, 30);
    } else {
      intDay = RandomUtil.randomInt(1, 31);
    }
    try {
      return new SimpleDateFormat("y-M-d").parse(intYear + "-" + intMonth + "-" + intDay);
    } catch (ParseException e) {
      throw new RuntimeException("ParseException");
    }
  }

  /**
   * 随机日期时间
   *
   * @param minYear
   * @param maxYear
   * @return
   */
  public static Date randomDatatime(int minYear, int maxYear) {
    int intYear = RandomUtil.randomInt(minYear, maxYear);
    int intMonth = RandomUtil.randomInt(1, 12);
    int intDay;
    int intHour = RandomUtil.randomInt(0, 23);
    int intMinute = RandomUtil.randomInt(0, 59);
    int intSecond = RandomUtil.randomInt(0, 59);
    int intMillisecond = RandomUtil.randomInt(0, 999);
    if (2 == intMonth) {
      if ((intYear % 4 == 0 && intYear % 100 != 0) || intYear % 400 == 0) {
        intDay = RandomUtil.randomInt(1, 29);
      } else {
        intDay = RandomUtil.randomInt(1, 28);
      }
    } else if (4 == intMonth || 6 == intMonth || 9 == intMonth || 11 == intMonth) {
      intDay = RandomUtil.randomInt(1, 30);
    } else {
      intDay = RandomUtil.randomInt(1, 31);
    }
    try {
      return new SimpleDateFormat("y-M-d h:m:s.S").parse(intYear + "-" + intMonth + "-" + intDay + " " + intHour + ":" + intMinute + ":" + intSecond + "." + intMillisecond);
    } catch (ParseException e) {
      throw new RuntimeException("ParseException");
    }
  }

  private RandomUtil() {
  }
}
