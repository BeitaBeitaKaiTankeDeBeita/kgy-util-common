
import java.util.Calendar;
import java.util.Date;
import kgy.util.common.DatetimeUtil;

public class TestDatetimeUtil {

  public static void main(String[] args) {
    Date now = new Date();
    System.out.println(DatetimeUtil.difference(DatetimeUtil.add(now, Calendar.MINUTE, 1), now, Calendar.MINUTE, 0));
  }

}
