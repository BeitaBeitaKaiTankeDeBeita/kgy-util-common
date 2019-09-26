package kgy.util.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * IO Util
 *
 * @author KistoryGUAN
 * @build 2017-8-12 16:34:25
 */
public class IoUtil {

  public static void fileToOutputStream(File file, OutputStream outputStream) {
    try (FileInputStream inputStream = new FileInputStream(file);) {
      inputStreamToOutputStream(inputStream, outputStream);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] inputStreamToBytes(InputStream inputStream) {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      return ((ByteArrayOutputStream) inputStreamToOutputStream(inputStream, outputStream)).toByteArray();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static File inputStreamToFile(InputStream inputStream, File file) {
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      inputStreamToOutputStream(inputStream, outputStream);
      return file;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static OutputStream inputStreamToOutputStream(InputStream inputStream, OutputStream outputStream) {
    try {
      int len;
      byte[] b = new byte[1024 ^ 2];
      while ((len = inputStream.read(b, 0, b.length)) != -1) {
        outputStream.write(b, 0, len);
      }

      return outputStream;

    } catch (IOException e) {
      throw new RuntimeException(e);

    } finally {
      try {
        inputStream.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private IoUtil() {
  }
}
