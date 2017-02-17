package kgy.util.common;

/**
 * String Utility
 *
 * @author Kistory
 * @build 2016-6-2 10:21:48
 */
public class StringUtil {

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

  private StringUtil() {
  }
}
