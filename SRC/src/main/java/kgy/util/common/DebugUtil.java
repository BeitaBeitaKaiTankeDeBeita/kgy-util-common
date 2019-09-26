package kgy.util.common;

/**
 * 调试工具类
 *
 * @author KistoryG
 * @version 2.0.0
 */
public class DebugUtil {

  public static final byte FG_COLOR_BLACK = 30;
  public static final byte FG_COLOR_RED = 31;
  public static final byte FG_COLOR_GREEN = 32;
  public static final byte FG_COLOR_YELLOW = 33;
  public static final byte FG_COLOR_BLUE = 34;
  public static final byte FG_COLOR_MAGENTA = 35;
  public static final byte FG_COLOR_CYAN = 36;
  public static final byte FG_COLOR_WHITE = 37;
  public static final byte BG_COLOR_BLACK = 40;
  public static final byte BG_COLOR_RED = 41;
  public static final byte BG_COLOR_GREEN = 42;
  public static final byte BG_COLOR_YELLOW = 43;
  public static final byte BG_COLOR_BLUE = 44;
  public static final byte BG_COLOR_MAGENTA = 45;
  public static final byte BG_COLOR_CYAN = 46;
  public static final byte BG_COLOR_WHITE = 47;

  /**
   * @param x The String to be printed.
   * @param color
   */
  @SuppressWarnings("UseOfSystemOutOrSystemErr")
  public static void println(String x, byte color) {
    System.out.println("\033[" + color + "m" + x + "\033[0m");
  }

  /**
   * @param x The String to be printed.
   */
  public static void println(String x) {
    println(x, FG_COLOR_RED);
  }

  private DebugUtil() {
  }
}
