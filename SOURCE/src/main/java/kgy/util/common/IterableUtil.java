package kgy.util.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Iterable 工具类
 *
 * @author KistoryG
 * @build 2018-07-20 17:33:00
 */
public class IterableUtil {

  private static final Logger LOG = Logger.getLogger(IterableUtil.class.getName());

  public static String join(Iterable<?> iterable, String separator) {
    String str = "";
    for (Object obj : iterable) {
      str += "*PLACEHOLDER*" + obj.toString();
    }
    str = str.replaceFirst("\\*PLACEHOLDER\\*", "").replaceAll("\\*PLACEHOLDER\\*", separator);

    return str;
  }

  public static List<List<?>> split(Collection<?> collection, int limit) {
    int size = collection.size();
    List<List<?>> result = new ArrayList<>((int) Math.ceil((double) size / limit));

    List<?> sourceList = new ArrayList<>(collection);
    int fromIndex = 0;
    int toIndex = limit;
    while (size > toIndex) {
      result.add(new ArrayList<>(sourceList.subList(fromIndex, toIndex)));
      fromIndex += limit;
      toIndex += limit;
    }
    result.add(new ArrayList<>(sourceList.subList(fromIndex, size)));

    return result;
  }

  private IterableUtil() {
  }
}
