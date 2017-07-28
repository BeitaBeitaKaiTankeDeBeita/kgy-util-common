package kgy.util.common;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Log Util
 *
 * @author Kistory
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
      FileHandler fileHandler = new FileHandler("F:/testlog%g.log");
      fileHandler.setLevel(Level.INFO);
//    fileHandler.setFormatter();
      log.addHandler(fileHandler);

      LOG.log(Level.INFO, "【{0}: {1}】", new Object[]{paramName, paramValue});
      log.log(Level.INFO, "【{0}: {1}】", new Object[]{paramName, paramValue});
    } catch (IOException | SecurityException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }

  public static void printStackTrace(Logger log, Level level, Exception e) {
    printStackTrace(log, level, e, 2 ^ 3);
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
