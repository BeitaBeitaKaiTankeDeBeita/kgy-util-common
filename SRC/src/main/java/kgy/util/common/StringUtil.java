package kgy.util.common;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串 工具类
 *
 * @author KistoryG
 */
public class StringUtil {

  private static final Logger LOG = Logger.getLogger(StringUtil.class.getName());

  public static int countContains(String str1, String str2) {
    int count = 0;
    while (str1.contains(str2)) {
      str1 = str1.substring(str1.indexOf(str2) + str2.length());
      count++;
    }
    return count;
  }

  public static String getFirstMatcher(String content, String regex) {
    Matcher matcher = Pattern.compile(regex).matcher(content);
    while (matcher.find()) {
      return matcher.group(0);
    }
    return "";
  }

  public static String[] split(String src, Integer splitSize) {
    String[] srcs = src.split(",");

    String[] result = new String[srcs.length / splitSize + 1];
    String buffer = "";
    for (int i = 0; i < srcs.length; i++) {
      buffer += "," + srcs[i];
      if (i % splitSize == splitSize - 1 || i == srcs.length - 1) {
        result[i / splitSize] = buffer.replaceFirst(",", "");
        buffer = "";
      }
    }

    return result;
  }

  public static String toLowercaseCamelCase(String src) {
    String str = "";

    src = src.replaceAll("-", "_");
    if (src.contains("_")) {
      int i = 0;
      for (String str1 : src.replaceAll("-", "_").split("_")) {
        for (int j = 0; j < str1.length(); j++) {
          char char0 = str1.charAt(j);
          if (char0 >= 97 && char0 <= 122 && i != 0 && j == 0) {
            str += (char) (char0 - 32);
          } else if (char0 >= 65 && char0 <= 90 && (i == 0 || j > 0)) {
            str += (char) (char0 + 32);
          } else {
            str += char0;
          }
        }
        i++;
      }

      return str.replaceAll("_+", "_");
    } else {

      String[] strs = src.split("", 2);
      return strs[0].toLowerCase() + strs[1];
    }
  }

  public static String toUppercaseCamelCase(String src) {
    String str = "";

    src = src.replaceAll("-", "_");
    if (src.contains("_")) {
      for (String str1 : src.replaceAll("-", "_").split("_")) {
        for (int j = 0; j < str1.length(); j++) {
          char char0 = str1.charAt(j);
          if (char0 >= 97 && char0 <= 122 && j == 0) {
            str += (char) (char0 - 32);
          } else if (char0 >= 65 && char0 <= 90 && j > 0) {
            str += (char) (char0 + 32);
          } else {
            str += char0;
          }
        }
      }

      return str.replaceAll("_+", "_");
    } else {

      String[] strs = src.split("", 2);
      return strs[0].toUpperCase() + strs[1];
    }
  }

  public static String toLowercaseSnakeCase(String src) {
    String str = "";

    src = src.replaceAll("-", "_");
    if (!src.contains("_")) {
      for (int i = 0; i < src.length(); i++) {
        char char0 = src.charAt(i);
        if (char0 >= 65 && char0 <= 90) {
          if (i > 0) {
            str += "_";
          }
          str += (char) (char0 + 32);
        } else if (char0 >= 97 && char0 <= 122) {
          str += char0;
        } else if (char0 == 45) {
          str += "_";
        } else {
          str += "_" + char0 + "_";
        }
      }

      str = str.replaceAll("_+", "_");
      if (str.endsWith("_")) {
        str = str.substring(0, str.length() - 1);
      }

      return str;
    } else {

      return src.toLowerCase();
    }
  }

  public static String toUppercaseSnakeCase(String src) {
    String str = "";

    src = src.replaceAll("-", "_");
    if (!src.contains("_")) {
      for (int i = 0; i < src.length(); i++) {
        char char0 = src.charAt(i);
        if (char0 >= 65 && char0 <= 90) {
          if (i > 0) {
            str += "_";
          }
          str += char0;
        } else if (char0 >= 97 && char0 <= 122) {
          str += (char) (char0 - 32);
        } else if (char0 == 45) {
          str += "_";
        } else {
          str += "_" + char0 + "_";
        }
      }

      str = str.replaceAll("_+", "_");
      if (str.endsWith("_")) {
        str = str.substring(0, str.length() - 1);
      }

      return str.replaceAll("_._", "_");
    } else {
      return src.toUpperCase();
    }
  }

  public static String toLowercaseKebabCase(String src) {
    String str = "";

    src = src.replaceAll("_", "-");
    if (!src.contains("-")) {
      for (int i = 0; i < src.length(); i++) {
        char char0 = src.charAt(i);
        if (char0 >= 65 && char0 <= 90) {
          if (i > 0) {
            str += "-";
          }
          str += (char) (char0 + 32);
        } else if (char0 >= 97 && char0 <= 122) {
          str += char0;
        } else if (char0 == 45) {
          str += "-";
        } else {
          str += "-" + char0 + "-";
        }
      }

      str = str.replaceAll("-+", "-");
      if (str.endsWith("-")) {
        str = str.substring(0, str.length() - 1);
      }

      return str;
    } else {

      return src.toLowerCase();
    }
  }

  public static String toUppercaseKebabCase(String src) {
    String str = "";

    src = src.replaceAll("_", "-");
    if (!src.contains("-")) {
      for (int i = 0; i < src.length(); i++) {
        char char0 = src.charAt(i);
        if (char0 >= 65 && char0 <= 90) {
          if (i > 0) {
            str += "-";
          }
          str += char0;
        } else if (char0 >= 97 && char0 <= 122) {
          str += (char) (char0 - 32);
        } else if (char0 == 45) {
          str += "-";
        } else {
          str += "-" + char0 + "-";
        }
      }

      str = str.replaceAll("-+", "-");
      if (str.endsWith("-")) {
        str = str.substring(0, str.length() - 1);
      }

      return str.replaceAll("-.-", "-");
    } else {
      return src.toUpperCase();
    }
  }

  public static String fromUnicode(String unicode) {
    unicode = unicode.replaceAll("\\\\u\\w{4}", "|$0|");
    String rtn = "";
    String[] strs = unicode.split("\\|");
    for (String str : strs) {
      if (str.contains("\\u")) {
        rtn += (char) Integer.valueOf(str.replace("\\u", ""), 16).intValue();
      } else {
        rtn += str;
      }
    }
    return rtn;
  }

  public static String toUnicode(String src) {
    char[] chars = src.toCharArray();
    String rtn = "";
    for (char charElem : chars) {
      if ((charElem >= 0 && charElem <= 127)) {
        rtn += charElem;
      } else {
        String hexStr = Integer.toString(charElem, 16);
        for (int i = hexStr.length(); i < 4; i++) {
          hexStr = "0" + hexStr;
        }
        rtn += "\\u" + hexStr.toUpperCase();
      }
    }
    return rtn;
  }

  private StringUtil() {
  }
}
