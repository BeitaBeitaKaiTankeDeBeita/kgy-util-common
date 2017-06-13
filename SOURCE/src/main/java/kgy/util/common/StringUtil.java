package kgy.util.common;

import java.util.logging.Logger;

/**
 * String工具
 *
 * @author KistoryGUAN
 */
public class StringUtil {

  private static final Logger LOG = Logger.getLogger(StringUtil.class.getName());

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

      return str;
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

      return str;
    } else {

      return src.toUpperCase();
    }
  }

  private StringUtil() {
  }
}
