import kgy.util.common.ReflectionUtil;

public class TestReflectionUtil {
  public static void main(String[] args) {

    for (Class<?> myClass : ReflectionUtil.getAllClassByNotPackagesAndAnnotation(
        "D:\\SE\\Projects\\KGY\\0.6.1 Template - Spring Boot\\SRC\\target\\classes\\",
        new String[]{"kgy.app", "kgy.extended", "kgy.process"}, null)) {
      System.out.println(myClass.getName());
    }
  }
}
