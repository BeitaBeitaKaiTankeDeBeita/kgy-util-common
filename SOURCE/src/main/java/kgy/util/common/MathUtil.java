package kgy.util.common;

import java.util.logging.Logger;

/**
 * Math Util
 *
 * @author KistoryG
 */
public class MathUtil {

  private static final Logger LOG = Logger.getLogger(MathUtil.class.getName());

  public static int gcd(int... ints) {
    int gcd = ints[0];
    for (int i = 1; i < ints.length; i++) {
      gcd = ints[i] == 0 ? gcd : gcd(ints[i], gcd % ints[i]);
    }
    return gcd;
  }

  public static int lcm(int... ints) {
    int lcm = ints[0];
    for (int i = 1; i < ints.length; i++) {
      lcm *= ints[i] / gcd(lcm, ints[i]);
    }
    return lcm;
  }

  private MathUtil() {
  }
}
