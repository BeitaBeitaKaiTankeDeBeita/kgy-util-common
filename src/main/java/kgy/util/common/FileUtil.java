package kgy.util.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * 文件工具类
 *
 * @author Kistory管音鹏
 * @version 1.2
 * @build 2016-09-24 11:47:16
 */
public class FileUtil {

  private static final Logger LOG = Logger.getLogger(FileUtil.class.getName());

  /**
   * 获取完整扩展名
   *
   * @param file
   * @return
   */
  public static String getExtensions(File file) {
    String extension = file.getName();
    if (extension.contains(".")) {
      return extension.substring(extension.indexOf(".") + 1);
    }
    return "";
  }

  /**
   * 获取顶级扩展名
   *
   * @param file
   * @return
   */
  public static String getExtension(File file) {
    String extension = file.getName();
    if (extension.contains(".")) {
      return extension.substring(extension.lastIndexOf(".") + 1);
    }
    return "";
  }

  /**
   *
   * @param name - the system-dependent file name.
   * @return
   * @throws IOException
   */
  public static byte[] fileToBytes(String name) throws IOException {
    try (InputStream in = new FileInputStream(name); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      byte[] bytes = new byte[in.available()];
      in.read(bytes);
      out.write(bytes);
      out.flush();

      return bytes;
    }
  }

  private FileUtil() {
  }
}
