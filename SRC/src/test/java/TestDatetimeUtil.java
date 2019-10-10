
import java.util.Calendar;
import java.util.Date;
import kgy.util.common.DatetimeUtil;

public class TestDatetimeUtil {

  public static void main(String[] args) {
//    int second = 997261;
//
//    Date date = DatetimeUtil.set(DatetimeUtil.ignoreTime(new Date()), Calendar.SECOND, second);
//    System.out.println(DatetimeUtil.format(date, "HH:mm:ss"));
//
//    System.out.println(second / 3600);
//    System.out.println(second % 3600 / 60);
//    System.out.println(second % 60);
//
//    System.out.println(DatetimeUtil.secondToHourMinuteSecond(second));

    System.out.println(DatetimeUtil.format(DatetimeUtil.parse(String.valueOf(2.52 * 1000), "SSS"), "mm:ss"));
  }
}
