
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import kgy.util.common.MathUtil;
import kgy.util.common.NumericUtil;

public class TestMathUtil {

  public static void main(String[] args) {
//    System.out.println(Math.);
//    System.out.println(MathUtil.lcm(123, 456, 789));
//    System.out.println(MathUtil.gcd(123, 456, 789));
//
//    Map<String, Object> m = new HashMap<>(5);
//    m.put("B", new BigDecimal("500"));
//    m.put("b", 2.1);
//    m.put("c", 3.2);
//    m.put("d", 4.3);
//    m.put("e", 5.4);
//    m.put("f", 5.5);
//
//    Number n = MathUtil.calculate("B-(20+6.18+4)", m);
//    System.out.println(n.doubleValue());
//    System.out.println(NumericUtil.format(n, "#"));

    Map<String, Object> m = new HashMap<>(5);
    m.put("S", new BigDecimal("2000"));
    m.put("C", 1600);
    m.put("B", "400.00000");
    m.put("O", 4.3);

//    Number n = MathUtil.calculate("(S>420?2:1)+(B>420?2:1)", m);
//    Number n = MathUtil.calculate("(S>2500?6:S>2000?5:S>1500?4:S>1000?3:2)+(B>420?2:1)", m);
//    System.out.println(n.doubleValue());
//    System.out.println(NumericUtil.format(n, "#"));
    Number n = MathUtil.calculate("B+S-7", m);
    System.out.println(n);
    
    System.out.println(MathUtil.calculate("1+2-3*4/5*(6+7)", null));
  }
}
