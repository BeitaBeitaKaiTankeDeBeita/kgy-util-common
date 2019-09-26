
import java.math.RoundingMode;
import kgy.util.common.NumericUtil;

public class TestNumericUtil {

  public static void main(String[] args) {
    System.out.println(NumericUtil.format(152.8, "#", RoundingMode.DOWN));
  }
}
