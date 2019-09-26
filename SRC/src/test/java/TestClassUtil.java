
import kgy.util.common.ClassUtil;

public class TestClassUtil {

  public static void main(String[] args) {
    A a = new A();

    ClassUtil.set(a, "field1", "^_^");

    System.out.println(a.getField1());
  }
}

class A {

  private String field1;

  public A() {
  }

  public String getField1() {
    return field1;
  }

  public void setField1(String field1) {
    this.field1 = field1;
  }

}
