package kgy.util.common;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Log 工具类
 *
 * @author KistoryG
 */
public class LogUtil {

  private static final Logger LOG = Logger.getLogger(LogUtil.class.getName());

  /**
   *
   * @param log
   * @param paramName
   * @param paramValue
   *
   * @deprecated
   */
  public static void info(Logger log, String paramName, Object paramValue) {

    try {
      ConsoleHandler consoleHandler = new ConsoleHandler();
      consoleHandler.setLevel(Level.ALL);
      log.addHandler(consoleHandler);

      FileHandler fileHandler = new FileHandler("E:/testlog%g.log");
      fileHandler.setLevel(Level.INFO);
//    fileHandler.setFormatter();
      log.addHandler(fileHandler);

      LOG.log(Level.INFO, "【{0}: {1}】", new Object[]{paramName, paramValue});
      log.log(Level.INFO, "【{0}: {1}】", new Object[]{paramName, paramValue});
    } catch (IOException | SecurityException e) {
      LOG.log(Level.SEVERE, null, e);
    }
  }

  /**
   *
   * @param log
   * @param level One of the message level identifiers, e.g., SEVERE
   * @param msg The string message (or a key in the message catalog)
   */
  public static void log(Logger log, Level level, String msg) {
    log.log(level, msg);
  }

  public static void printStackTrace(Logger log, Level level, Exception e, int lines) {
    byte color;
    switch (level.intValue()) {
      case 900:
        color = 43;
        break;
      case 1000:
        color = 41;
        break;
      default:
        color = 30;
    }

    log.log(level, "\u001b[{0}m{1}\u001b[0m", new Object[]{color, e.toString()});
    StackTraceElement[] stackTraceElements = e.getStackTrace();

    for (int i = 0; i < stackTraceElements.length; i++) {
      if (i < lines) {
        log.log(level, "\u001b[{0}m    at {1}\u001b[0m", new Object[]{color, stackTraceElements[i].toString()});
      } else if (i == lines) {
        log.log(level, "\u001b[{0}m  ...\033[0m", color);
      }
    }
  }

  public static void printStackTrace(Logger log, Level level, Exception e) {
    printStackTrace(log, level, e, 16);
  }

  private LogUtil() {
  }
}
