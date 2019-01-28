package kgy.util.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 日期时间工具类
 *
 * @author KistoryG
 */
public class DatetimeUtil {

  public static final String ZH_CN = "zh-CN";
  public static final String QUARTER_OF_YEAR = "QUARTER_OF_YEAR";
  public static final String MONTH_OF_YEAR = "MONTH_OF_YEAR";

  public static boolean isDate(String source) {
    return Pattern.matches("^\\d{1,4}-\\d{2}-\\d{2}$", source);
  }

  public static boolean isDateTime(String source) {
    return Pattern.matches("^\\d{1,4}-\\d{2}-\\d{2}\\s{1}\\d{2}\\:\\d{2}\\:\\d{2}$", source);
  }

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
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
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
        throw new UnsupportedOperationException(field);
    }
  }

  public static int[] get(Date date, int... fields) {
    int[] ints = new int[fields.length];

    Calendar calendar = Calendar.getInstance();
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.setTime(date);

    for (int i = 0; i < fields.length; i++) {
      ints[i] = calendar.get(fields[i]);
    }

    return ints;
  }

  public static int getActualMaximum(Date date, int field) {
    Calendar calendar = Calendar.getInstance();
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
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

  public static Date set(Date date, int[] fields, int[] values) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    for (int i = 0; i < fields.length; i++) {
      calendar.set(fields[i], values[i]);
    }
    return calendar.getTime();
  }

  @SuppressWarnings("fallthrough")
  public static Date setTime(Date date, String source, boolean carry) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    String[] timeStrPart = source.split(":");
    calendar.set(Calendar.MILLISECOND, 0);
    switch (timeStrPart.length) {
      case 3:
        calendar.set(Calendar.SECOND, carry ? Integer.parseInt(timeStrPart[2]) : Integer.parseInt(timeStrPart[2]) % 60);
      case 2:
        calendar.set(Calendar.MINUTE, carry ? Integer.parseInt(timeStrPart[1]) : Integer.parseInt(timeStrPart[1]) % 60);
      case 1:
        calendar.set(Calendar.HOUR_OF_DAY, carry ? Integer.parseInt(timeStrPart[0]) : Integer.parseInt(timeStrPart[0]) % 24);
        break;
      default:
        throw new UnsupportedOperationException(source);
    }

    return calendar.getTime();
  }

  public static Date add(Date date, int... fieldAndAmounts) {
    if (null != date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      for (int i = 0; i < fieldAndAmounts.length / 2; i++) {
        calendar.add(fieldAndAmounts[i * 2], fieldAndAmounts[i * 2 + 1]);
      }
      return calendar.getTime();
    }
    return null;
  }

  /**
   * @param dateStr
   * @param fieldAndAmounts
   * @return

   * @deprecated
   */
  public static Date add(String dateStr, int... fieldAndAmounts) {
    return add(parse(dateStr, "yyyy-MM-dd"), fieldAndAmounts);
  }

  public static String add(String source, String pattern, int... fieldAndAmounts) {
    return format(add(parse(source, pattern), fieldAndAmounts), pattern);
  }

  /**
   *
   * @param date
   * @param date1
   * @param field
   *
   * @return
   *
   * @deprecated
   */
  public static int compare(Date date, Date date1, int field) {

    long difference = date.getTime() - date1.getTime();

    switch (field) {
      case Calendar.DAY_OF_MONTH:
        return new Long(difference / 86400000).intValue();
      default:
        return 0;
    }
  }

  public static double difference(Date date, Date date1, int field) {

    long difference = date1.getTime() - date.getTime();

    switch (field) {
      case Calendar.DAY_OF_MONTH:
        return difference / 86400000d;
      case Calendar.HOUR_OF_DAY:
        return difference / 3600000d;
      case Calendar.MINUTE:
        return difference / 60000d;
      case Calendar.SECOND:
        return difference / 1000d;
      default:
        throw new UnsupportedOperationException(String.valueOf(field));
    }
  }

  public static double difference(Date date, Date date1, int field, int scale) {
    return new BigDecimal(difference(date, date1, field)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  public static int[] difference(Date date, Date date1) {
    if (date.before(date1)) {
      int[] result = new int[3];

      int[] ints = get(date, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
      int[] ints1 = get(date1, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

      int year = ints[0];
      int month = ints[1];
      int dayOfMonth = ints[2];
      int year1 = ints1[0];
      int month1 = ints1[1];
      int dayOfMonth1 = ints1[2];

      if (dayOfMonth1 < dayOfMonth) {
        month1 -= 1;
        dayOfMonth1 += getActualMaximum(add(date1, Calendar.MONTH, -1), Calendar.DAY_OF_MONTH);
      }
      result[2] = dayOfMonth1 - dayOfMonth;

      if (month1 < month) {
        year1 -= 1;
        month1 += 12;
      }
      result[1] = month1 - month;

      result[0] = year1 - year;

      return result;
    } else {
      throw new UnsupportedOperationException();
    }
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
   * @param year
   * @param weekOfYear
   * @param firstDayOfWeek
   * @param dayOfWeek
   *
   * @return
   *
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
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      // ignore
    }
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

  /**
   *
   * @param beginDate
   * @param endDate
   * @param timeStrs
   *
   * @return
   *
   * @deprecated
   */
  public static double hitHourOfDay(Date beginDate, Date endDate, String... timeStrs) {
    double sum = 0;

    int diffDayOfMonth = (int) DatetimeUtil.difference(DatetimeUtil.ignoreTime(beginDate), DatetimeUtil.ignoreTime(endDate), Calendar.DAY_OF_MONTH);
    for (int i = 0; i <= diffDayOfMonth; i++) {
      Date date1 = DatetimeUtil.add(beginDate, Calendar.DAY_OF_MONTH, i);
      Date date2 = DatetimeUtil.add(DatetimeUtil.ignoreTime(date1), Calendar.DAY_OF_MONTH, 1);
      if (i != 0) {
        date1 = DatetimeUtil.ignoreTime(date1);
      }
      if (i == diffDayOfMonth) {
        date2 = endDate;
      }

      if (timeStrs.length % 2 == 0) {
        for (int j = 0; j < timeStrs.length / 2; j++) {
          Date date3 = DatetimeUtil.setTime(beginDate, timeStrs[j * 2], true);
          Date date4 = DatetimeUtil.setTime(beginDate, timeStrs[j * 2 + 1], true);
          if (date1.after(date3)) {
            date3 = date1;
          }
          if (date2.before(date4)) {
            date4 = date2;
          }

          Double diffHourOfDay = DatetimeUtil.difference(date3, date4, Calendar.HOUR_OF_DAY);
          if (diffHourOfDay > 0) {
            sum += diffHourOfDay;
          }
        }
      } else {
        throw new UnsupportedOperationException();
      }
    }

    return sum;
  }
//
//  /**
//   *
//   * @param beginDate
//   * @param endDate
//   * @param timeStrs
//   * @param carry
//   *
//   * @return
//   */
//  public static double hitHourOfDay(Date beginDate, Date endDate, String[] timeStrs, Boolean carry) {
//    double sum = 0;
//
//    int diffDayOfMonth = (int) DatetimeUtil.difference(DatetimeUtil.ignoreTime(beginDate), DatetimeUtil.ignoreTime(endDate), Calendar.DAY_OF_MONTH);
//    for (int i = 0; i <= diffDayOfMonth; i++) {
//      Date date1 = DatetimeUtil.add(beginDate, Calendar.DAY_OF_MONTH, i);
//      Date date2 = DatetimeUtil.add(DatetimeUtil.ignoreTime(date1), Calendar.DAY_OF_MONTH, 1);
//      if (i != 0) {
//        date1 = DatetimeUtil.ignoreTime(date1);
//      }
//      if (i == diffDayOfMonth) {
//        date2 = endDate;
//      }
//
//      if (timeStrs.length % 2 == 0) {
//        for (int j = 0; j < timeStrs.length / 2; j++) {
//          String timeStr1 = timeStrs[j * 2];
//          String timeStr2 = timeStrs[j * 2 + 1];
//          System.out.println(timeStr1);
//          System.out.println(timeStr2);
//
//          if (carry) {
//            Date date3 = DatetimeUtil.setTime(beginDate, timeStr1, carry);
//            Date date4 = DatetimeUtil.setTime(beginDate, timeStr2, carry);
//            if (date1.after(date3)) {
//              date3 = date1;
//            }
//            if (date2.before(date4)) {
//              date4 = date2;
//            }
//
//            Double diffHourOfDay = DatetimeUtil.difference(date3, date4, Calendar.HOUR_OF_DAY);
//            if (diffHourOfDay > 0) {
//              sum += diffHourOfDay;
//            }
//          } else {
//            Integer timeStrHourIndex1 = Integer.parseInt(timeStr1.split(":")[0]) / 24;
//            Integer timeStrHourIndex2 = Integer.parseInt(timeStr2.split(":")[0]) / 24;
//            System.out.println(timeStrHourIndex1);
//            System.out.println(timeStrHourIndex2);
//
//            if (timeStrHourIndex1.equals(timeStrHourIndex2)) {
//              System.out.println(DatetimeUtil.setTime(date1, timeStr1, carry));
//              System.out.println(DatetimeUtil.setTime(date1, timeStr2, carry));
//              Date date3 = DatetimeUtil.setTime(date1, timeStr1, carry);
//              Date date4 = DatetimeUtil.setTime(date1, timeStr2, carry);
//              if (date1.after(date3)) {
//                date3 = date1;
//              }
//              if (date2.before(date4)) {
//                date4 = date2;
//              }
//
//              Double diffHourOfDay = DatetimeUtil.difference(date3, date4, Calendar.HOUR_OF_DAY);
//              if (diffHourOfDay > 0) {
//                sum += diffHourOfDay;
//              }
//            } else {
//              Integer diff = timeStrHourIndex2 - timeStrHourIndex1;
//              for (int k = 0; k < diff; k++) {
//                Date date3 = DatetimeUtil.setTime(date1, timeStr1, carry);
//                Date date4 = DatetimeUtil.setTime(date1, timeStr2, carry);
//                if (k == 0) {
//                  date3 = date1;
//                } else if (k == diff - 1) {
//                  date4 = date2;
//                }
//                System.out.println(DatetimeUtil.setTime(date1, timeStr1, carry));
//                System.out.println(DatetimeUtil.setTime(date1, timeStr2, carry));
//                if (date1.after(date3)) {
//                  date3 = date1;
//                }
//                if (date2.before(date4)) {
//                  date4 = date2;
//                }
//
//                Double diffHourOfDay = DatetimeUtil.difference(date3, date4, Calendar.HOUR_OF_DAY);
//                if (diffHourOfDay > 0) {
//                  sum += diffHourOfDay;
//                }
//              }
//            }
//          }
//        }
//      } else {
//        throw new UnsupportedOperationException();
//      }
//    }
//
//    return sum;
//  }

  /**
   *
   * @param beginDate 开始时间
   * @param endDate 结束时间
   * @param matchDatePairs 匹配开始/结束时间对
   *
   * @return
   */
  public static double hitHourOfDay(Date beginDate, Date endDate, Date... matchDatePairs) {
    double sum = 0;

    int diffDayOfMonth = (int) DatetimeUtil.difference(DatetimeUtil.ignoreTime(beginDate), DatetimeUtil.ignoreTime(endDate), Calendar.DAY_OF_MONTH);
    for (int i = 0; i <= diffDayOfMonth; i++) {
      Date date1 = DatetimeUtil.add(beginDate, Calendar.DAY_OF_MONTH, i);
      Date date2 = DatetimeUtil.add(DatetimeUtil.ignoreTime(date1), Calendar.DAY_OF_MONTH, 1);
      if (i != 0) {
        date1 = DatetimeUtil.ignoreTime(date1);
      }
      if (i == diffDayOfMonth) {
        date2 = endDate;
      }

      if (matchDatePairs.length % 2 == 0) {
        for (int j = 0; j < matchDatePairs.length / 2; j++) {
          Date date3 = matchDatePairs[j * 2];
          Date date4 = matchDatePairs[j * 2 + 1];
          if (date1.after(date3)) {
            date3 = date1;
          }
          if (date2.before(date4)) {
            date4 = date2;
          }

          Double diffHourOfDay = DatetimeUtil.difference(date3, date4, Calendar.HOUR_OF_DAY);
          if (diffHourOfDay > 0) {
            sum += diffHourOfDay;
          }
        }
      } else {
        throw new UnsupportedOperationException();
      }
    }

    return sum;
  }

  /**
   *
   * @param beginDate 开始时间
   * @param endDate 结束时间
   * @param matchDatePairs 匹配开始/结束时间对
   * @param dayToHourRates 天转换到时比例
   *
   * @return {时,天}
   */
  public static double[] hitHourOfDay(Date beginDate, Date endDate, Date[] matchDatePairs, Double dayToHourRates[]) {
    double[] sums = {0, 0};

    int diffDayOfMonth = (int) DatetimeUtil.difference(DatetimeUtil.ignoreTime(beginDate), DatetimeUtil.ignoreTime(endDate), Calendar.DAY_OF_MONTH);
    for (int i = 0; i <= diffDayOfMonth; i++) {
      Date date1 = DatetimeUtil.add(beginDate, Calendar.DAY_OF_MONTH, i);
      Date date2 = DatetimeUtil.add(DatetimeUtil.ignoreTime(date1), Calendar.DAY_OF_MONTH, 1);
      if (i != 0) {
        date1 = DatetimeUtil.ignoreTime(date1);
      }
      if (i == diffDayOfMonth) {
        date2 = endDate;
      }

      if (matchDatePairs.length % 2 == 0) {
        for (int j = 0; j < matchDatePairs.length / 2; j++) {
          Date date3 = matchDatePairs[j * 2];
          Date date4 = matchDatePairs[j * 2 + 1];
          if (date1.after(date3)) {
            date3 = date1;
          }
          if (date2.before(date4)) {
            date4 = date2;
          }

          Double diffHourOfDay = DatetimeUtil.difference(date3, date4, Calendar.HOUR_OF_DAY);
          if (diffHourOfDay > 0) {
            sums[0] += diffHourOfDay;
            sums[1] += diffHourOfDay / dayToHourRates[j];
          }
        }
      } else {
        throw new UnsupportedOperationException();
      }
    }

    return sums;
  }

  public static double intersection(Date startDate, Date endDate, Date startDate1, Date endDate1, int field) {
    if (!startDate.after(endDate) && !startDate1.after(endDate1)) {
      return DatetimeUtil.difference(startDate.after(startDate1) ? startDate : startDate1, endDate.before(endDate1) ? endDate : endDate1, field);
    }

    return 0;
  }

  private DatetimeUtil() {
  }
}
