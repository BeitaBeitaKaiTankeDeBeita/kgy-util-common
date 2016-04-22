package kgy.util.common;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 属性文件工具类
 *
 * @author Kistory
 * @build 2016-4-22 13:49:37
 */
public class PropertiesUtil {

  private static final Logger LOG = Logger.getLogger(PropertiesUtil.class.getName());

  public static Properties load(String pathname) {
    Properties properties = new Properties();
    try {
      properties.load(PropertiesUtil.class.getResourceAsStream(pathname));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return properties;
  }

  public static Properties load() {
    return load("/conf.properties");
  }

  public static Object get(Object key) {
    Properties properties = load();
    return properties.get(key);
  }

  private PropertiesUtil() {
  }
}
