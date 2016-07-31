package kgy.util.common.test;

import java.util.Calendar;
import java.util.Date;
import kgy.util.common.DatetimeUtil;

@SuppressWarnings("ClassWithoutLogger")
public class TestMain {

  public static void main(String[] args) {

    Date todayMin = DatetimeUtil.parse(2016, 7, 31, 0, 0, 0);
    Date todayMax = DatetimeUtil.parse(2016, 7, 31, 16, 0, 0);
    Date yesterdayMin = DatetimeUtil.parse(2016, 7, 30, 0, 0, 0);
    Date yesterdayMax = DatetimeUtil.parse(2016, 7, 30, 23, 59, 59);
    Date fridayMin = DatetimeUtil.parse(2016, 7, 29, 0, 0, 0);
    Date fridayMax = DatetimeUtil.parse(2016, 7, 29, 23, 59, 59);
    Date thursdayMin = DatetimeUtil.parse(2016, 7, 28, 0, 0, 0);
    Date thursdayMax = DatetimeUtil.parse(2016, 7, 28, 23, 59, 59);
    Date wednesdayMin = DatetimeUtil.parse(2016, 7, 27, 0, 0, 0);
    Date wednesdayMax = DatetimeUtil.parse(2016, 7, 27, 23, 59, 59);
    Date tuesdayMin = DatetimeUtil.parse(2016, 7, 26, 0, 0, 0);
    Date tuesdayMax = DatetimeUtil.parse(2016, 7, 26, 23, 59, 59);
    Date mondayMin = DatetimeUtil.parse(2016, 7, 25, 0, 0, 0);
    Date mondayMax = DatetimeUtil.parse(2016, 7, 25, 23, 59, 59);
    Date sundayMin = DatetimeUtil.parse(2016, 7, 24, 0, 0, 0);
    Date sundayMax = DatetimeUtil.parse(2016, 7, 24, 23, 59, 59);
    Date saturdayMin = DatetimeUtil.parse(2016, 7, 23, 0, 0, 0);
    Date saturdayMax = DatetimeUtil.parse(2016, 7, 23, 23, 59, 59);
    Date date = DatetimeUtil.parse(2016, 7, 22, 23, 59, 59);
    Date date1 = DatetimeUtil.parse(2016, 7, 22, 23, 59, 59);
    Date date2 = DatetimeUtil.parse(2016, 6, 22, 23, 59, 59);
    Date date3 = DatetimeUtil.parse(2015, 7, 22, 23, 59, 59);

    System.out.println(getCreatedSignature(todayMin));
    System.out.println(getCreatedSignature(todayMax));
    System.out.println(getCreatedSignature(yesterdayMin));
    System.out.println(getCreatedSignature(yesterdayMax));
    System.out.println(getCreatedSignature(fridayMin));
    System.out.println(getCreatedSignature(fridayMax));
    System.out.println(getCreatedSignature(thursdayMin));
    System.out.println(getCreatedSignature(thursdayMax));
    System.out.println(getCreatedSignature(wednesdayMin));
    System.out.println(getCreatedSignature(wednesdayMax));
    System.out.println(getCreatedSignature(tuesdayMin));
    System.out.println(getCreatedSignature(tuesdayMax));
    System.out.println(getCreatedSignature(mondayMin));
    System.out.println(getCreatedSignature(mondayMax));
    System.out.println(getCreatedSignature(sundayMin));
    System.out.println(getCreatedSignature(sundayMax));
    System.out.println(getCreatedSignature(saturdayMin));
    System.out.println(getCreatedSignature(saturdayMax));
    System.out.println(getCreatedSignature(date));
    System.out.println(getCreatedSignature(date1));
    System.out.println(getCreatedSignature(date2));
    System.out.println(getCreatedSignature(date3));
  }

  public static String getCreatedSignature(Date dateEntered) {
    String createdBy = "Kistory";

    String dateEnteredStr = "";
    Date todayMin = DatetimeUtil.ignoreTime(new Date());
    Double diff = DatetimeUtil.difference(todayMin, dateEntered, Calendar.DAY_OF_MONTH);
    System.out.println("\ndiff: " + diff);
    if (diff >= 0) {
      dateEnteredStr = DatetimeUtil.format(dateEntered, "HH:mm");
    } else if (diff < 0 && diff >= -1) {
      dateEnteredStr = "yesterday";
    } else if (diff < -1 && diff >= -8) {
      switch (DatetimeUtil.get(dateEntered, Calendar.DAY_OF_WEEK)) {
        case Calendar.MONDAY:
          dateEnteredStr = "Monday";
          break;
        case Calendar.TUESDAY:
          dateEnteredStr = "Tuesday";
          break;
        case Calendar.WEDNESDAY:
          dateEnteredStr = "Wednesday";
          break;
        case Calendar.THURSDAY:
          dateEnteredStr = "Thursday";
          break;
        case Calendar.FRIDAY:
          dateEnteredStr = "Friday";
          break;
        case Calendar.SATURDAY:
          dateEnteredStr = "Saturday";
          break;
        case Calendar.SUNDAY:
          dateEnteredStr = "Sunday";
          break;
      }
    } else {
      dateEnteredStr = DatetimeUtil.format(dateEntered, "yy/M/d");
    }

    return createdBy + ", " + dateEnteredStr;
  }
}
