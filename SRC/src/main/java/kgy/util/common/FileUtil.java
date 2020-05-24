package kgy.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class FileUtil {
  private static final Logger LOG = Logger.getLogger(FileUtil.class.getName());

  /**
   * 获取文件名
   *
   * @param url
   * @return
   */
  public static String getFileName(String url) {
    if (url.contains("?")) {
      url = url.substring(0, url.indexOf('?'));
    }
    String[] pathnameParts = url.split("\\\\|/|:|\\*|\\?|<|>|\\|");
    return pathnameParts[pathnameParts.length - 1];
  }

  /**
   * 获取完整扩展名
   *
   * @param pathname
   * @return
   */
  public static String getExtensions(String pathname) {
    String fileName = getFileName(pathname);
    if (fileName.contains(".")) {
      return fileName.substring(fileName.indexOf(".") + 1);
    }
    return "";
  }

  /**
   * 获取顶级扩展名
   *
   * @param pathname
   * @return
   */
  public static String getExtension(String pathname) {
    String fileName = getFileName(pathname);
    if (pathname.contains(".")) {
      return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    return "";
  }

  /**
   * 获取完整扩展名
   *
   * @param file
   * @return
   */
  public static String getExtensions(File file) {
    return getExtensions(file.getName());
  }

  /**
   * 获取顶级扩展名
   *
   * @param file
   * @return
   */
  public static String getExtension(File file) {
    return getExtension(file.getName());
  }

  /**
   * @param first the path string or initial part of the path string
   * @return String
   */
  public static String probeContentType(String first) {
    try {
      return Files.probeContentType(Paths.get(first));
    } catch (IOException e) {
      LogUtil.printStackTrace(LOG, Level.WARNING, e);
      return "";
    }
  }

  public static byte[] toBytes(File file) {
    try (FileInputStream fileInputStream = new FileInputStream(file)) {
      byte[] bytes = new byte[(int) file.length()];
      fileInputStream.read(bytes);
      return bytes;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] toBytes(String pathname) {
    try (FileInputStream fileInputStream = new FileInputStream(pathname)) {
      byte[] bytes = new byte[(int) new File(pathname).length()];
      fileInputStream.read(bytes);
      return bytes;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static byte[] toBytesByFileChannel(String pathname) {
    try (FileChannel channel = new RandomAccessFile(pathname, "r").getChannel()) {
      MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size()).load();
      byte[] bytes = new byte[(int) channel.size()];
      if (buffer.hasRemaining()) {
        buffer.get(bytes, 0, buffer.remaining());
      }
      return bytes;
    } catch (IOException e) {
      LogUtil.printStackTrace(LOG, Level.WARNING, e);
      return null;
    }
  }
}
