package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestCollectionUtil {

  public static void main(String[] args) {
    List<String> strs = new ArrayList<>();
    strs.add("a");
    strs.add("b");
    strs.add("c");
//
//    for (List<String> strs2 : CollectionUtil.split(strs, 2)) {
//      for (String str : strs2) {
//        System.out.println(str);
//      }
//      System.out.println("");
//    }
//
//    System.out.println(CollectionUtil.join(strs, "*PLACEHOLDER*"));

    List<List<?>> objListList = split(strs, 2);
    for (List<?> objList : objListList) {
      System.out.print(objList.getClass() + ":" + "\t");
      for (Object obj : objList) {
        System.out.print(obj.getClass() + ":" + obj + "\t");
      }
      System.out.println("");
    }
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
}
