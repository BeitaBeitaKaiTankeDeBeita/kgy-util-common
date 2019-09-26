
import kgy.util.common.StringUtil;

public class TestStringUtil {

  public static void main(String[] args) {
//
//    String str = "Abc1Def23Ghi456";
//
//    System.out.println(StringUtil.toLowercaseCamelCase(str));
//    System.out.println(StringUtil.toLowercaseKebabCase(str));
//    System.out.println(StringUtil.toLowercaseSnakeCase(str));
//    System.out.println(StringUtil.toUppercaseCamelCase(str));
//    System.out.println(StringUtil.toUppercaseKebabCase(str));
//    System.out.println(StringUtil.toUppercaseSnakeCase(str));
//
//    str = "JJ014";
//
//    System.out.println(StringUtil.toLowercaseCamelCase(str));
//    System.out.println(StringUtil.toLowercaseKebabCase(str));
//    System.out.println(StringUtil.toLowercaseSnakeCase(str));
//    System.out.println(StringUtil.toUppercaseCamelCase(str));
//    System.out.println(StringUtil.toUppercaseKebabCase(str));
//    System.out.println(StringUtil.toUppercaseSnakeCase(str));
//
//    System.out.println(StringUtil.countContains(",,1,,2,,3,,", ",,"));
//
//    System.out.println(StringUtil.toUnicode("[ { \"C_TYPE\": \"0\", \"C_CODE\": \"R01\", \"C_NAME\": \"实到\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"R02\", \"C_NAME\": \"迟到\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"R03\", \"C_NAME\": \"早退\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"R04\", \"C_NAME\": \"漏签\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"R05\", \"C_NAME\": \"外出\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"R06\", \"C_NAME\": \"旷工\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"R07\", \"C_NAME\": \"请假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"1\", \"C_CODE\": \"R08\", \"C_NAME\": \"加班\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"Y01\", \"C_NAME\": \"应勤\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"2\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"Y02\", \"C_NAME\": \"实勤\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"2\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y03\", \"C_NAME\": \"假日\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"Y04\", \"C_NAME\": \"迟到\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"Y05\", \"C_NAME\": \"早退\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"Y06\", \"C_NAME\": \"漏签\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"Y07\", \"C_NAME\": \"脱岗\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"0\", \"C_CODE\": \"Y08\", \"C_NAME\": \"旷工\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"1\", \"C_CODE\": \"Y09\", \"C_NAME\": \"平时加班\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"1\", \"C_CODE\": \"Y10\", \"C_NAME\": \"休息日加班\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"1\", \"C_CODE\": \"Y11\", \"C_NAME\": \"法定加班\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y12\", \"C_NAME\": \"带薪年休假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y13\", \"C_NAME\": \"因公外出\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y14\", \"C_NAME\": \"产假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y15\", \"C_NAME\": \"出差\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y16\", \"C_NAME\": \"事假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y17\", \"C_NAME\": \"婚假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y18\", \"C_NAME\": \"丧假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y19\", \"C_NAME\": \"陪产假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y20\", \"C_NAME\": \"病假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y21\", \"C_NAME\": \"工伤假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y22\", \"C_NAME\": \"流产假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y23\", \"C_NAME\": \"哺乳假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y24\", \"C_NAME\": \"续产假\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" }, { \"C_TYPE\": \"2\", \"C_CODE\": \"Y25\", \"C_NAME\": \"调休(领导安排)\", \"C_STATUS\": \"1\", \"C_COMPUTETYPE\": \"1\" } ]"));

    System.out.println(StringUtil.toUppercaseCamelCase("weixin.oplatform.thirdpartyplatformsDisanfangPingtai"));
    System.out.println(StringUtil.toUppercaseKebabCase("weixin.oplatform.thirdpartyplatformsDisanfangPingtai"));
    System.out.println(StringUtil.toUppercaseSnakeCase("weixin.oplatform.thirdpartyplatformsDisanfangPingtai"));
  }
}
