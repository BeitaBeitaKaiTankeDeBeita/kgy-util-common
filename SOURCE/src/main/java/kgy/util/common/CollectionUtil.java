package kgy.util.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Collection 工具类
 *
 * @author KistoryG
 * @build 2018-7-6 10:03:25
 */
public class CollectionUtil {

  private static final Logger LOG = Logger.getLogger(CollectionUtil.class.getName());

  public static String join(Collection collection, String separator) {
    String str = "";
    for (Object obj : collection) {
      str += "*PLACEHOLDER*" + obj.toString();
    }
    str = str.replaceFirst("\\*PLACEHOLDER\\*", "").replaceAll("\\*PLACEHOLDER\\*", separator);

    return str;
  }

  public static Set<List<String>> split(List<String> list, int limit) {
    int size = list.size();
    Set<List<String>> result = new HashSet<>((int) Math.ceil((double) size / limit));

    List<String> sourceList = list;
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

  public static Set<Set<Long>> split(Set<Long> set, int limit) {
    int size = set.size();
    Set<Set<Long>> result = new HashSet<>((int) Math.ceil((double) size / limit));

    List<Long> sourceList = new ArrayList<>(set);
    int fromIndex = 0;
    int toIndex = limit;
    while (size > toIndex) {
      result.add(new HashSet<>(sourceList.subList(fromIndex, toIndex)));
      fromIndex += limit;
      toIndex += limit;
    }
    result.add(new HashSet<>(sourceList.subList(fromIndex, size)));

    return result;
  }

  private CollectionUtil() {
  }
}
