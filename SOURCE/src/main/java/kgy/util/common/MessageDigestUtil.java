package kgy.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 消息摘要工具类
 *
 * @author Kistory管音鹏
 * @version 1.0.1
 * @build 2015-09-04 23:46:16
 */
public class MessageDigestUtil {

  private static final Logger LOG = Logger.getLogger(MessageDigestUtil.class.getName());

  public static String get(String algorithm, String string) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
      messageDigest.update(string.getBytes());
      return bytes2HEX(messageDigest.digest());
    } catch (NoSuchAlgorithmException e) {
      // ignore
      return null;
    }
  }

  public static String get(String algorithm, File file) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

      try (FileInputStream fileInputStream = new FileInputStream(file); DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, messageDigest)) {

        byte[] bytes = new byte[1024 ^ 2];
        while (digestInputStream.read(bytes) > 0);

        return bytes2HEX(digestInputStream.getMessageDigest().digest());
      }
    } catch (NoSuchAlgorithmException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String getMD5(String string) {
    return get("MD5", string);
  }

  public static String getMD5(File file) {
    return get("MD5", file);
  }

  public static String getSHA1(String string) {
    return get("SHA1", string);
  }

  public static String getSHA1(File file) {
    return get("SHA1", file);
  }

  private static String bytes2HEX(byte[] bytes) {
    final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    int length = bytes.length;
    StringBuilder stringBuilder = new StringBuilder(length * 2);
    // 把密文转换成十六进制的字符串形式
    for (int j = 0; j < length; j++) {
      stringBuilder.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
      stringBuilder.append(HEX_DIGITS[bytes[j] & 0x0f]);
    }
    return stringBuilder.toString();
  }

  private MessageDigestUtil() {
  }
}