package kgy.util.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Collection 工具类
 *
 * @author KistoryG
 * @build 2017-4-28 10:37:37
 */
public class CollectionUtil {

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
