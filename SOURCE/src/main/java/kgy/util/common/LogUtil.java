package kgy.util.common;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kistory
 */
public class LogUtil {

  private static final Logger LOG = Logger.getLogger(LogUtil.class.getName());

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

  private LogUtil() {
  }
}
