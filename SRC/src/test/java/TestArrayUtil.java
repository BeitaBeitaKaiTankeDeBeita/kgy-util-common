
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kgy.util.common.ArrayUtil;

public class TestArrayUtil {

  public static void main(String[] args) {
    Object[] objs = new Object[]{
      "string",
      1024,
      true,
      false,
      null
    };

    System.out.println(ArrayUtil.join(objs, ",", true));

    List<Object[]> objsList = new ArrayList<>(0);
    objsList.add(objs);
    objsList.add(objs);
    objsList.add(objs);

    System.out.println(ArrayUtil.join(objsList, ",", true));
  }
}
