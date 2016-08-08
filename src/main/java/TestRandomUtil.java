
import kgy.util.common.RandomUtil;

public class TestRandomUtil {

  public static void main(String[] args) {

    // 潍坊 12K
    // 西安  2K+
    // 大连  1K+
    // 沈阳  1K+
    
    for (int i = 0; i < 12; i++) {
      System.out.println("");
      System.out.print(RandomUtil.randomXingming());
      System.out.print("\t" + "IMEI");
      System.out.print("\t" + RandomUtil.randomPhoneNumber());
      System.out.print("\t" + RandomUtil.randomIDCradNumber("3707", 60, 80, RandomUtil.randomInt(0, 1)));
      System.out.print("\t" + RandomUtil.randomXingming());
      System.out.print("\t" + RandomUtil.randomPhoneNumber());
      System.out.print("\t" + RandomUtil.randomDouble(34.1964125831, 34.2679519901, 7));
      System.out.print("\t" + RandomUtil.randomDouble(108.8392654860, 108.9759231700, 7));
      System.out.print("\t" + RandomUtil.randomInt(65, 85));
      System.out.print("\t" + RandomUtil.randomInt(65, 85));
      System.out.print("\t" + RandomUtil.randomInt(65, 85));
      System.out.print("\t" + RandomUtil.randomInt(65, 85));
      System.out.print("\t" + RandomUtil.randomInt(65, 85));
      System.out.print("\t" + RandomUtil.randomInt(65, 85));
      System.out.print("\t" + RandomUtil.randomInt(65, 85));
      System.out.print("\t" + RandomUtil.randomInt(1100, 7200));
      System.out.print("\t" + RandomUtil.randomInt(1100, 7200));
      System.out.print("\t" + RandomUtil.randomInt(1100, 7200));
      System.out.print("\t" + RandomUtil.randomInt(1100, 7200));
      System.out.print("\t" + RandomUtil.randomInt(1100, 7200));
      System.out.print("\t" + RandomUtil.randomInt(1100, 7200));
      System.out.print("\t" + RandomUtil.randomInt(1100, 7200));
    }
  }
}
