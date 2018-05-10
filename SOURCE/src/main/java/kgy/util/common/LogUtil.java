package kgy.util.common;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Log Util
 *
 * @author KistoryG
 * @build 2018/5/10 21:08:52
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

  public static void printStackTrace(Logger log, Level level, Exception e) {
    printStackTrace(log, level, e, 16);
  }

  public static void printStackTrace(Logger log, Level level, Exception e, int lines) {
    log.log(level, e.toString());
    StackTraceElement[] stackTraceElements = e.getStackTrace();

    for (int i = 0; i < stackTraceElements.length; i++) {
      if (i < lines) {
        log.log(level, "  at {0}", stackTraceElements[i].toString());
      } else if (i == lines) {
        log.log(level, "  ...");
      }
    }
  }

  private LogUtil() {
  }
}
