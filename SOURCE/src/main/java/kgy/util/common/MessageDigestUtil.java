package kgy.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要工具类
 *
 * @author KistoryG
 * @version 0.0.2
 * @build 2017-7-1 00:27:07
 */
public class MessageDigestUtil {

  @SuppressWarnings("empty-statement")
  public static String get(String algorithm, Object obj) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

      if (obj instanceof File) {
        try (FileInputStream fileInputStream = new FileInputStream((File) obj); DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, messageDigest)) {

          byte[] bytes = new byte[1024 ^ 2];
          while (digestInputStream.read(bytes) > 0);

          return bytes2HEX(digestInputStream.getMessageDigest().digest());
        }

      } else {
        messageDigest.update(obj.toString().getBytes());
        return bytes2HEX(messageDigest.digest());
      }

    } catch (NoSuchAlgorithmException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String getMD5(Object obj) {
    return get("MD5", obj);
  }

  public static String getSHA1(Object obj) {
    return get("SHA1", obj);
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
