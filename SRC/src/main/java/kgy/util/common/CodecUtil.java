package kgy.util.common;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Base64;
import java.util.logging.Level;
import javax.imageio.ImageIO;

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
//
//  public static String imageSrcData(BufferedImage im, String formatName) {
//    try {
//      return "data:" + FileUtil.getMimeType(formatName) + ";base64," + CodecUtil.encodeBase64String(toBytes(im, formatName));
//    } catch (IOException e) {
//      LogUtil.printStackTrace(LOG, Level.WARNING, e);
//      return "";
//    }
//  }
//
//  public static String imageSrcData(String pathname) {
//    try {
//      return imageSrcData(ImageIO.read(new File(pathname)), FileUtil.getExtension(pathname));
//    } catch (IOException e) {
//      LogUtil.printStackTrace(LOG, Level.WARNING, e);
//      return "";
//    }
//  }
//
//  public static String imageSrcData(InputStream input, int width, int height, String pathname) {
//    try {
//      return "data:" + FileUtil.getMimeType(pathname) + ";base64," + CodecUtil.encodeBase64String(toBytes(input, width, height, FileUtil.getExtension(pathname)));
//    } catch (IOException e) {
//      LogUtil.printStackTrace(LOG, Level.WARNING, e);
//      return "";
//    }
//  }

  private static byte[] fileToBytes(String pathname) throws IOException {
    try (FileChannel channel = new RandomAccessFile(pathname, "r").getChannel()) {
      MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size()).load();
      byte[] result = new byte[(int) channel.size()];
      if (buffer.hasRemaining()) {
        buffer.get(result, 0, buffer.remaining());
      }
      return result;
    }
  }
}
