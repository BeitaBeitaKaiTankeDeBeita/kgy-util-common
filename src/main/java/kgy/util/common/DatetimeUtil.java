package kgy.util.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * 日期时间工具类
 *
 * @author Kistory
 */
public class DatetimeUtil {

  public static final String ZH_CN = "zh-CN";
  public static final String QUARTER_OF_YEAR = "QUARTER_OF_YEAR";
  public static final String MONTH_OF_YEAR = "MONTH_OF_YEAR";

  private static final Logger LOG = Logger.getLogger(DatetimeUtil.class.getName());

  public static String format(Date date, String pattern) {
    return new SimpleDateFormat(pattern).format(date);
  }

  public static String format(Date date, String pattern, Locale locale) {
    if (Locale.ENGLISH.equals(locale) && pattern.contains("ddd")) {
      String dayOfMonthStr;
      int dayOfMonth = get(date, Calendar.DAY_OF_MONTH);
      switch (dayOfMonth % 10) {
        case 1:
          dayOfMonthStr = dayOfMonth + "st";
          break;
        case 2:
          dayOfMonthStr = dayOfMonth + "nd";
          break;
        case 3:
          dayOfMonthStr = dayOfMonth + "rd";
          break;
        default:
          dayOfMonthStr = dayOfMonth + "th";
      }
      pattern = pattern.replaceAll("ddd", "'ddd'").replaceAll("''", "");
      return new SimpleDateFormat(pattern, locale).format(date).replaceAll("ddd", dayOfMonthStr);
    } else {
      return new SimpleDateFormat(pattern, locale).format(date);
    }
  }

  public static String format(Date date) {
    return format(date, "yyyy-MM-dd HH:mm:ss");
  }

  public static String format(String source, String pattern, String pattern1, Locale locale) {
    return format(parse(source, pattern, locale), pattern1, locale);
  }

  public static Date parse(String source, String pattern) {
    try {
      if (null != source) {
        return new SimpleDateFormat(pattern).parse(source);
      }
    } catch (ParseException e) {
      // 忽略
    }

    return null;
  }

  public static Date parse(String source, String pattern, Locale locale) {
    try {
      if (null != source) {
        String newSource = "";
        if (Locale.ENGLISH.equals(locale) && pattern.contains("ddd")) {
          String[] sources = source.split(" |,");
          String[] patterns = pattern.split(" |,");
          for (int i = 0; i < patterns.length; i++) {
            newSource += " ";
            if ("ddd".equals(patterns[i])) {
              newSource += sources[i].replaceAll("st|nd|rd|th", "");
            } else {
              newSource += sources[i];
            }
          }
          return new SimpleDateFormat(" " + pattern.replaceAll(" |,", " ").replaceAll("ddd", "d"), locale).parse(newSource);
        } else {
          return new SimpleDateFormat(pattern, locale).parse(source);
        }
      }
      return null;
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static Date parse(Integer year, Integer month, Integer dayOfMonth, Integer hourOfDay, Integer minute, Integer second) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, second);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }

  public static Date parse(Integer year, Integer month, Integer dayOfMonth, Integer hourOfDay, Integer minute) {
    return parse(year, month, dayOfMonth, hourOfDay, minute, 0);
  }

  public static Date parse(Integer year, Integer month, Integer dayOfMonth, Integer hourOfDay) {
    return parse(year, month, dayOfMonth, hourOfDay, 0, 0);
  }

  public static Date parse(Integer year, Integer month, Integer dayOfMonth) {
    return parse(year, month, dayOfMonth, 0, 0, 0);
  }

  public static Date parse(Integer year, Integer month) {
    return parse(year, month, 1, 0, 0, 0);
  }

  public static Date parse(Integer year) {
    return parse(year, 1, 1, 0, 0, 0);
  }

  public static int get(Date date, int field) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(field);
  }

  public static int get(Date date, String field) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int month = calendar.get(Calendar.MONTH);
    switch (field) {
      case QUARTER_OF_YEAR:
        if (month <= 2) {
          return 1;
        } else if (month >= 3 && month <= 5) {
          return 2;
        } else if (month >= 6 && month <= 8) {
          return 3;
        } else {
          return 4;
        }
      default:
        return -1;
    }
  }

  public static int getActualMaximum(Date date, int field) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.getActualMaximum(field);
  }

  public static Date set(Date date, int... fieldOrValues) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    for (int i = 0; i < fieldOrValues.length / 2; i++) {
      calendar.set(fieldOrValues[i * 2], fieldOrValues[i * 2 + 1]);
    }
    return calendar.getTime();
  }

  public static Date setTime(Date date, Date time) {
    return set(date, Calendar.HOUR_OF_DAY, get(time, Calendar.HOUR_OF_DAY), Calendar.MINUTE, get(time, Calendar.MINUTE), Calendar.SECOND, get(time, Calendar.SECOND));
  }

  public static Date setTime(Date date, String source, String pattern) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    Date time = parse(source, pattern);
    calendar.set(Calendar.HOUR_OF_DAY, get(time, Calendar.HOUR_OF_DAY));
    calendar.set(Calendar.MINUTE, get(time, Calendar.MINUTE));
    calendar.set(Calendar.SECOND, get(time, Calendar.SECOND));
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }

  public static Date add(Date date, int field, int amount) {
    if (null != date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(field, amount);
      return calendar.getTime();
    }
    return null;
  }

  public static Date add(Date date, int field, double amount) {
    if (null != date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      switch (field) {
        case Calendar.DAY_OF_MONTH:
          amount *= 24 * 60 * 60 * 1000;
          calendar.add(Calendar.MILLISECOND, (int) amount);
          break;
        default:
          throw new RuntimeException("未支持的 " + field);
      }

      return calendar.getTime();
    }
    return null;
  }

  public static Date add(String dateStr, int field, int amount) {
    return add(parse(dateStr, "yyyy-MM-dd"), field, amount);
  }

  public static int compare(Date date0, Date date1, int field) {

    long difference = date0.getTime() - date1.getTime();

    switch (field) {
      case Calendar.DAY_OF_MONTH:
        return new Long(difference / 86400000).intValue();
      default:
        return 0;
    }
  }

  public static double difference(Date date0, Date date1, int field) {

    long difference = date1.getTime() - date0.getTime();

    switch (field) {
      case Calendar.DAY_OF_MONTH:
        return difference / 86400000d;
      case Calendar.MINUTE:
        return difference / 60000d;
      case Calendar.SECOND:
        return difference / 1000d;
      default:
        return 0d;
    }
  }

  public static double difference(Date date0, Date date1, int field, int scale) {
    return new BigDecimal(difference(date0, date1, field)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  @SuppressWarnings("fallthrough")
  public static Date ignoreCascade(Date date, int field) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    switch (field) {
      case Calendar.YEAR:
        calendar.set(Calendar.YEAR, 1970);
      case Calendar.MONTH:
        calendar.set(Calendar.MONTH, 0);
      case Calendar.DAY_OF_MONTH:
        calendar.set(Calendar.DAY_OF_MONTH, 1);
      case Calendar.MINUTE:
        calendar.set(Calendar.MINUTE, 0);
      case Calendar.SECOND:
        calendar.set(Calendar.SECOND, 0);
      case Calendar.MILLISECOND:
        calendar.set(Calendar.MILLISECOND, 0);
      case Calendar.HOUR_OF_DAY:
        calendar.set(Calendar.HOUR_OF_DAY, 0);
    }
    return calendar.getTime();
  }

  public static Date ignoreTime(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  public static Date ignoreMillisecond(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  public static String translate(String languageCountry, int field, int value) {
    switch (languageCountry) {
      case ZH_CN:
        switch (field) {
          case Calendar.MONTH:
            switch (value) {
              case 0:
                return "一月份";
              case 1:
                return "二月份";
              case 2:
                return "三月份";
              case 3:
                return "四月份";
              case 4:
                return "五月份";
              case 5:
                return "六月份";
              case 6:
                return "七月份";
              case 7:
                return "八月份";
              case 8:
                return "九月份";
              case 9:
                return "十月份";
              case 10:
                return "十一月份";
              case 11:
                return "十二月份";
              default:
                return null;
            }
          default:
            return null;
        }
      default:
        return null;
    }
  }

  public static String translate(String languageCountry, String field, int value) {
    switch (languageCountry) {
      case ZH_CN:
        switch (field) {
          case QUARTER_OF_YEAR:
            switch (value) {
              case 1:
                return "一季度";
              case 2:
                return "二季度";
              case 3:
                return "三季度";
              case 4:
                return "四季度";
              default:
                return null;
            }
          case MONTH_OF_YEAR:
            switch (value) {
              case 1:
                return "一月份";
              case 2:
                return "二月份";
              case 3:
                return "三月份";
              case 4:
                return "四月份";
              case 5:
                return "五月份";
              case 6:
                return "六月份";
              case 7:
                return "七月份";
              case 8:
                return "八月份";
              case 9:
                return "九月份";
              case 10:
                return "十月份";
              case 11:
                return "十一月份";
              case 12:
                return "十二月份";
              default:
                return null;
            }
          default:
            return null;
        }
      default:
        return null;
    }
  }

  /**
   *
   * @param year
   * @param weekOfYear
   * @param firstDayOfWeek
   * @param dayOfWeek
   * @return
   * @deprecated
   */
  public static Date get(int year, int weekOfYear, int firstDayOfWeek, int dayOfWeek) {
    Calendar calendar = Calendar.getInstance();
    calendar.setFirstDayOfWeek(firstDayOfWeek);
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
    calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
    return calendar.getTime();
  }

  public static long time() {
    return new Date().getTime();
  }

  public static int getAgeByBirthdate(Date birthdate, Date now) {
    int age = get(now, Calendar.YEAR) - get(birthdate, Calendar.YEAR);
    if (get(now, Calendar.MONTH) < get(birthdate, Calendar.MONTH)) {
      age--;
    } else if (get(now, Calendar.MONTH) == get(birthdate, Calendar.MONTH) && get(now, Calendar.DAY_OF_MONTH) < get(birthdate, Calendar.DAY_OF_MONTH)) {
      age--;
    }
    return age;
  }

  public static Date getMinBirthdateByAge(int age, Date now) {
    return ignoreTime(add(add(now, Calendar.YEAR, -age - 1), Calendar.DAY_OF_YEAR, 1));
  }

  public static Date getMaxBirthdateByAge(int age, Date now) {
    return ignoreTime(add(now, Calendar.YEAR, -age));
  }

  private DatetimeUtil() {
  }
}
