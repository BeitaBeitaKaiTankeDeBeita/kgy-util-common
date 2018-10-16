package kgy.util.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Collection 工具类
 *
 * @author KistoryG
 */
public class CollectionUtil {

  public static String join(List<Object[]> objsList, String separator, boolean withBrackets) {
    String separatorPlaceholder = "＾＿＾占位符＞＿＜";
    String str = "";
    for (Object[] objs : objsList) {
      str += separatorPlaceholder;
      if (null != objs) {
        str += ArrayUtil.join(objs, separator, withBrackets);
      }
    }
    str = str.replaceFirst(separatorPlaceholder, "").replaceAll(separatorPlaceholder, separator);

    if (withBrackets) {
      str = "[" + str + "]";
    }

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

  private CollectionUtil() {
  }
}
