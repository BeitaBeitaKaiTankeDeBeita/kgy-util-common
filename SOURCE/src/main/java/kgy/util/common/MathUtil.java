package kgy.util.common;

import java.util.Map;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

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

  public static Number calculate(String script, Map<String, Object> m) {
    try {
      ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");

      if (null != m) {
        return (Number) scriptEngine.eval(script, new SimpleBindings(m));
      }
      return (Number) scriptEngine.eval(script);
    } catch (ScriptException se) {
      throw new RuntimeException(se);
    }
  }

  public static Number calculate(String script) {
    return calculate(script, null);
  }

  private MathUtil() {
  }
}
