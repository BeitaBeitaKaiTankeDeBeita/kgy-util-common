import java.lang.reflect.Field;
import kgy.util.common.ReflectionUtil;

public class TestReflectionUtil {
  public static void main(String[] args) throws ClassNotFoundException {

    for (Field field : ReflectionUtil.getAllProtectedOrPrivateNotStaticFields(Class.forName("One"))) {
      System.out.println(field.getModifiers() + "\t" + field);
    }
    System.out.println("");
    for (Field field : ReflectionUtil.getAllPublicStaticFinalFields(Class.forName("One"))) {
      System.out.println(field.getModifiers() + "\t" + field);
    }
//
//    Set<String> setA = new HashSet<String>() {{
//      add("a");
//      add("b");
//      add("c");
//    }};
//    Set<String> setB = new HashSet<String>() {{
//      add("c");
//      add("d");
//      add("e");
//    }};
////
////    setA.retainAll(setB);
////    System.out.println(setA.size());
//
//    setB.retainAll(setA);
//    System.out.println(setA.size());
//    System.out.println(setB.size());
  }
}
