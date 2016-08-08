
import java.util.Calendar;
import java.util.Date;
import kgy.util.common.DatetimeUtil;

public class TestDatetimeUtil {

  public static void main(String[] args) {
//    String year = "2016";
//    String week_of_year = "32";
//
//    String str = DatetimeUtil.format(DatetimeUtil.get(Integer.parseInt(year),
//                                                      Integer.parseInt(week_of_year), Calendar.MONDAY, Calendar.MONDAY), "yyyy_MM");
//    String tHeartData = "T_HeartData_" + str;
//    String tLocation = "T_Location_" + str;
//    String deviceImei = "666666666666666";
//    String sql_2 = "SELECT *"
//                   + "\n" + " FROM ("
//                   + "\n" + "SELECT 'queryHealthCharts' replay, 'ok' status, t.id userID,t.device_imei, '' temperatureX, '' temperatureData"
//                   + "\n" + " FROM hh_oldman t"
//                   + "\n" + " WHERE t.device_imei = '" + deviceImei + "') t4"
//                   + "\n" + " LEFT JOIN ("
//                   + "\n" + "SELECT t1.IMEI, group_concat(t1.HeartRate order by date_format(t1.ProbeTime, '%m/%d %H:%i')) heartrateData,group_concat(date_format(t1.ProbeTime,'%m/%d %H:%i') order by date_format(t1.ProbeTime, '%m/%d %H:%i')) heartrateX"
//                   + "\n" + " FROM ("
//                   + "\n" + "SELECT `t`.`DataID` AS `DataID`, `t`.`IMEI` AS `IMEI`, `t`.`HeartRate` AS `HeartRate`, `t`.`ProbeTime` AS `ProbeTime`, `t`.`UpdateTime` AS `UpdateTime`,(CASE DAYOFWEEK(`t`.`ProbeTime`) WHEN 1 THEN 7 ELSE (DAYOFWEEK(`t`.`ProbeTime`) - 1) END) AS `day_of_week`, (CASE DAYOFWEEK(`t`.`ProbeTime`) WHEN 1 THEN (WEEK(`t`.`ProbeTime`, 0) - 1) ELSE WEEK(`t`.`ProbeTime`, 0) END) AS `week_of_year`, YEAR(`t`.`ProbeTime`) AS `year`"
//                   + "\n" + " FROM `" + tHeartData + "` `t`) t1"
//                   + "\n" + " WHERE t1.year = " + year + ""
//                   + "\n" + " AND t1.week_of_year = " + week_of_year
//                   + "\n" + " AND t1.IMEI = '" + deviceImei + "') t5"
//                   + "\n" + " ON (t4.device_imei = t5.IMEI)"
//                   + "\n" + " LEFT JOIN ("
//                   + "\n" + "SELECT t3.IMEI, group_concat(t3.steps ORDER BY day_of_week) stepsData, group_concat(date_week ORDER BY day_of_week) stepsX"
//                   + "\n" + " FROM ("
//                   + "\n" + "SELECT t2.IMEI,max(ifnull(t2.Step, 0)) steps,case t2.day_of_week when 1 then '周一' when 2 then '周二' when 3 then '周三' when 4 then '周四' when 5 then '周五' when 6 then '周六' when 7 then '周日' end date_week, day_of_week FROM (SELECT `t`.`LocationID` AS `LocationID`, `t`.`IMEI` AS `IMEI`, `t`.`GPSLat` AS `GPSLat`, `t`.`GPSLng` AS `GPSLng`, `t`.`LBS` AS `LBS`, `t`.`Location` AS `Location`, `t`.`Step` AS `Step`, `t`.`MapLat` AS `MapLat`, `t`.`MapLng` AS `MapLng`, `t`.`ProbeTime` AS `ProbeTime`, `t`.`UpdateTime` AS `UpdateTime`,(CASE DAYOFWEEK(`t`.`ProbeTime`) WHEN 1 THEN 7 ELSE (DAYOFWEEK(`t`.`ProbeTime`) - 1) END) AS `day_of_week`, (CASE DAYOFWEEK(`t`.`ProbeTime`) WHEN 1 THEN (WEEK(`t`.`ProbeTime`, 0) - 1) ELSE WEEK(`t`.`ProbeTime`, 0) END) AS `week_of_year`, YEAR(`t`.`ProbeTime`) AS `year`"
//                   + "\n" + " FROM `" + tLocation + "` `t`) t2"
//                   + "\n" + " WHERE t2.IMEI = '" + deviceImei + "'"
//                   + "\n" + " AND t2.year = " + year
//                   + "\n" + " AND t2.week_of_year = " + week_of_year
//                   + "\n" + " GROUP BY t2.day_of_week) t3"
//                   + "\n" + " GROUP BY t3.IMEI) t6"
//                   + "\n" + " ON (t4.device_imei = t6.IMEI)";
//    System.out.println(sql_2);
//
//    System.out.println(str);
//
//    System.out.println(DatetimeUtil.get(new Date(), Calendar.WEEK_OF_YEAR));

    System.out.println(DatetimeUtil.get(2016, 32, Calendar.MONDAY, Calendar.MONDAY));

    System.out.println(DatetimeUtil.get(2016, 32, Calendar.MONDAY, Calendar.SUNDAY));
  }

}
