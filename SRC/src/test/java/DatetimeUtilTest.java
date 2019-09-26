
import java.util.Date;
import kgy.util.common.DatetimeUtil;

public class DatetimeUtilTest {

    public static void main(String[] args) {
//    Date kaishiShijian = DatetimeUtil.parse("2017-06-10 19:30", "yyyy-MM-dd HH:mm");
//    Date jieshuShijian = DatetimeUtil.parse("2017-06-11 07:30", "yyyy-MM-dd HH:mm");
//
//    Double jiabanXiaoshishu = DatetimeUtil.difference(kaishiShijian, jieshuShijian, Calendar.HOUR_OF_DAY);
//    System.out.println("jiabanXiaoshishu: " + jiabanXiaoshishu);
//
//    String xiuxiShijian = "11:30-12:30,16:30-17:30,23:30-24:30,28:30-29:30";
//    String[] xiuxiShijianParts = xiuxiShijian.split("[-|,]");
//    List<String> timeStrsList = new ArrayList<>(xiuxiShijianParts.length);
//    for (String xiuxiShijianPart : xiuxiShijianParts) {
//      timeStrsList.add(xiuxiShijianPart);
//    }
//
//    jiabanXiaoshishu -= DatetimeUtil.hitHourOfDay(
//        kaishiShijian, jieshuShijian,
//        timeStrsList.toArray(new String[timeStrsList.size()]),
//        false);
//    System.out.println("jiabanXiaoshishu: " + jiabanXiaoshishu);
//
//    System.out.println(DatetimeUtil.format(DatetimeUtil.add("2018-01-01", Calendar.MONTH, 1, Calendar.DAY_OF_MONTH, -1), "yyyy-MM-dd"));

        System.out.println(DatetimeUtil.format(new Date(), "yyyy.MM.dd E HH:mm"));
        System.out.println(DatetimeUtil.format(new Date(), "yyyy.MM.dd EE HH:mm"));
        System.out.println(DatetimeUtil.format(new Date(), "yyyy.MM.dd EEE HH:mm"));
        System.out.println(DatetimeUtil.format(new Date(), "yyyy.MM.dd EEEE HH:mm"));
        System.out.println(DatetimeUtil.format(new Date(), "yyyy.MM.dd EEEEE HH:mm"));
    }
}
