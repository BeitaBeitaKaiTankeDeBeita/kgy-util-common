package kgy.util.common;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Codec Util
 *
 * @author KistoryG
 */
public class CodecUtil {

  public static byte[] base64Decode(String src) {
    return Base64.getDecoder().decode(src);
  }

  public static String base64DecodeString(String src) {
    try {
      return new String(base64Decode(src), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  public static String base64DecodeString(String src, int times) {
    for (int i = 0; i < times; i++) {
      src = base64DecodeString(src);
    }
    return src;
  }

  public static byte[] base64Encode(byte[] src) {
    return Base64.getEncoder().encode(src);
  }

  public static String base64EncodeString(byte[] src) {
    try {
      return new String(base64Encode(src), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  public static String base64EncodeString(String src) {
    try {
      return new String(base64Encode(src.getBytes("UTF-8")), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  public static String base64EncodeString(String src, int times) {
    for (int i = 0; i < times; i++) {
      src = base64EncodeString(src);
    }
    return src;
  }
}
