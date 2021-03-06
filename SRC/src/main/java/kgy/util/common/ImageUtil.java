package kgy.util.common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

@SuppressWarnings("unused")
public class ImageUtil {
  private static final Logger LOG = Logger.getLogger(ImageUtil.class.getName());

  public static final String SCALE_POSITION_CENTER = "center";
  public static final String SCALE_SIZE_COVER = "cover";

  /**
   * Returns a BufferedImage as the result of decoding a supplied InputStream with an ImageReader chosen automatically from among those currently registered. The InputStream is wrapped in an ImageInputStream. If no registered ImageReader claims to be able to read the resulting stream, null is returned.<br>
   * The current cache settings from getUseCacheand getCacheDirectory will be used to control caching in the ImageInputStream that is created.<br>
   * This method does not attempt to locate ImageReaders that can read directly from an InputStream; that may be accomplished using IIORegistry and ImageReaderSpi.<br>
   * This method does not close the provided InputStream after the read operation has completed; it is the responsibility of the caller to close the stream, if desired.
   *
   * @param input an InputStream to read from.
   * @return a BufferedImage containing the decoded contents of the input, or null.
   */
  public static BufferedImage toBufferedImage(InputStream input) {
    try {
      return ImageIO.read(input);
    } catch (IOException ioe) {
      LOG.warning(ioe.toString());
      return null;
    }
  }

  /**
   * Writes an image using an arbitrary ImageWriter that supports the given format to an OutputStream.<br>
   * This method does not close the provided OutputStream after the write operation has completed; it is the responsibility of the caller to close the stream, if desired.<br>
   * The current cache settings from getUseCacheand getCacheDirectory will be used to control caching.
   *
   * @param im         a RenderedImage to be written.
   * @param formatName a String containing the informal name of the format.
   * @return null if an error occurs
   */
  public static byte[] toBytes(BufferedImage im, String formatName) {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      ImageIO.write(im, formatName, output);
      return output.toByteArray();
    } catch (IOException e) {
      LogUtil.printStackTrace(LOG, Level.WARNING, e);
      return null;
    }
  }

  /**
   * @param input      a File to read from
   * @param width      the width of the rectangle
   * @param height     the height of the rectangle
   * @param formatName a String containing the informal name of the format
   * @return null if an error occurs
   */
  public static byte[] toBytes(InputStream input, int width, int height, String formatName) {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      BufferedImage src = toBufferedImage(input);
      if (null != src) {
        BufferedImage dst = new BufferedImage(width, height, src.getType());
        Graphics2D graphics2D = dst.createGraphics();
        // 抗锯齿 - 使用抗锯齿来实现渲染。
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(src, 0, 0, width, height, null);

        ImageIO.write(dst, formatName, output);
        return output.toByteArray();
      } else {
        return null;
      }
    } catch (IOException e) {
      LogUtil.printStackTrace(LOG, Level.WARNING, e);
      return null;
    }
  }

  public static InputStream toInputStream(BufferedImage src, String formatName) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
    ImageIO.write(src, formatName, imageOutput);

    return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  }

  public static void writeToJPEG(BufferedImage bufferedImage, String pathname, float quality) {
    try {
      ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpeg").next();
      imageWriter.setOutput(ImageIO.createImageOutputStream(new File(pathname)));
      ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
      imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      imageWriteParam.setCompressionQuality(quality);
      imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void writeToJPEG(BufferedImage bufferedImage, String pathname) {
    ImageUtil.writeToJPEG(bufferedImage, pathname, 0.75F);
  }

  public static boolean writeToPNG(BufferedImage bufferedImage, String pathname) {
    try {
      return ImageIO.write(bufferedImage, "png", new File(pathname));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
//
//  /**
//   * @param bufferedImage
//   * @param font
//   * @param str
//   * @param offset
//   * @return
//   * @deprecated
//   */
//  public static BufferedImage drawStr(BufferedImage bufferedImage, Font font, String str, int offset) {
//    Graphics2D graphics2D = bufferedImage.createGraphics();
//
//    // 使用抗锯齿模式完成呈现
//    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//
//    Shape shape = font.createGlyphVector(graphics2D.getFontMetrics().getFontRenderContext(), str).getOutline();
//    Rectangle2D rectangle2D = shape.getBounds2D();
//    graphics2D.translate(
//        bufferedImage.getWidth() - (int) rectangle2D.getWidth() - 10,
//        bufferedImage.getHeight() - font.getSize() - offset - 60);
//    graphics2D.setPaint(Color.WHITE);
//    graphics2D.fill(shape);
//    graphics2D.setPaint(Color.BLACK);
//    graphics2D.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1));
//    graphics2D.draw(shape);
//    graphics2D.dispose();
//
//    return bufferedImage;
//  }
//
//  /**
//   * @param bufferedImage
//   * @param font
//   * @param str
//   * @return
//   * @deprecated
//   */
//  public static BufferedImage drawStr(BufferedImage bufferedImage, Font font, String str) {
//    return drawStr(bufferedImage, font, str, 0);
//  }
//
//  /**
//   * @param bufferedImage
//   * @param font
//   * @param strs
//   * @return
//   * @deprecated
//   */
//  public static BufferedImage drawStrs(BufferedImage bufferedImage, Font font, String[] strs) {
//    int offset = 0;
//    for (int i = strs.length - 1; i >= 0; i--) {
//      String str = strs[i];
//      bufferedImage = drawStr(bufferedImage, font, str, offset);
//      offset += font.getSize() + 5;
//    }
//
//    return bufferedImage;
//  }

  public static BufferedImage drawImage(BufferedImage src, BufferedImage img, int x, int y, Map<String, Object> settings) {
    Graphics2D graphics2D = src.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // border-radius
    if (null != settings && settings.containsKey("border-radius")) {
      img = ImageUtil.roundImage(img, (int) settings.get("border-radius"));
    }

    if (src.getHeight() < y + img.getHeight()) {
      BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight() + img.getHeight(), BufferedImage.TYPE_INT_ARGB);

      Graphics2D dstGraphics2D = dst.createGraphics();

      // 抗锯齿 - 使用抗锯齿来实现渲染。
      dstGraphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      dstGraphics2D.drawImage(src, 0, 0, null);

      // grow
      if (null != settings && settings.containsKey("grow")) {
        BufferedImage growBufferedImage = (BufferedImage) settings.get("grow");
        int incY = img.getHeight();
        if (incY > 0) {
          for (int i = 0; i < incY; i++) {
            dstGraphics2D.drawImage(growBufferedImage, 0, src.getHeight() + i, null);
          }

          // draw
          dstGraphics2D.drawImage(img, x, y, null);

          dstGraphics2D.dispose();

          return dst;
        }
      }

      // draw
      dstGraphics2D.drawImage(img, x, y, null);

      dstGraphics2D.dispose();

      return dst;
    }

    // draw
    graphics2D.drawImage(img, x, y, null);

    graphics2D.dispose();

    return src;
  }

  public static BufferedImage drawImage(BufferedImage src, BufferedImage img, int x, int y) {
    return drawImage(src, img, x, y, null);
  }

  public static BufferedImage drawString(BufferedImage src, String str, int x, int y, Map<String, Object> settings) {
    Graphics2D graphics2D = src.createGraphics();

    // 文本抗锯齿 - 文本渲染是通过某种形式的抗锯齿来完成的。
    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    String[] strs = str.split("\n");

    Font font = (Font) settings.get("font");
    Paint paint = (Paint) settings.get("paint");
    graphics2D.setFont(font);
    graphics2D.setPaint(paint);

    int lineHeight;
    if (!settings.containsKey("line-height")) {
      lineHeight = graphics2D.getFontMetrics().getHeight();
    } else {
      lineHeight = ((Number) settings.get("line-height")).intValue();
    }

    List<Map<String, Object>> strMapList = new ArrayList<>(strs.length);
    int currentX,
        currentY = y;
    for (int i = 0; i < strs.length; i++) {
      String currentStr = strs[i];
      currentX = x;
      if (i == 0) {
        currentY += graphics2D.getFontMetrics().getHeight();
      } else {
        currentY += lineHeight;
      }

      if (settings.containsKey("container-width")) {
        int containerWidth = (int) settings.get("container-width");
        int minCharNums = (int) Math.floor((double) containerWidth / graphics2D.getFontMetrics().getMaxAdvance());
        if (strs[i].length() <= minCharNums || font.getStringBounds(strs[i], graphics2D.getFontRenderContext()).getWidth() <= containerWidth) {
          // justify-content
          if (settings.containsKey("justify-content")) {
            currentX = calculateXByContainerWidthAndJustifyContent(graphics2D, font, currentStr, currentX, containerWidth, (String) settings.get("justify-content"));
          }

          Map<String, Object> strMap = new HashMap<>(3);
          strMap.put("str", currentStr);
          strMap.put("x", currentX);
          strMap.put("y", currentY);
          strMapList.add(strMap);
        } else {
          for (int j = minCharNums; j < strs[i].length(); j++) {
            if (font.getStringBounds(strs[i].substring(0, j), graphics2D.getFontRenderContext()).getWidth() > containerWidth) {
              currentStr = strs[i].substring(0, j - 1);
              currentX = x;

              // justify-content
              if (settings.containsKey("justify-content")) {
                currentX = calculateXByContainerWidthAndJustifyContent(graphics2D, font, currentStr, currentX, containerWidth, (String) settings.get("justify-content"));
              }

              Map<String, Object> strMap = new HashMap<>(3);
              strMap.put("str", currentStr);
              strMap.put("x", currentX);
              strMap.put("y", currentY);
              strMapList.add(strMap);

              strs[i] = strs[i].substring(j - 1);
              currentY += lineHeight;
              j = minCharNums;
            }
          }

          // 截取后剩余内容
          // justify-content
          if (settings.containsKey("justify-content")) {
            currentX = calculateXByContainerWidthAndJustifyContent(graphics2D, font, strs[i], currentX, containerWidth, (String) settings.get("justify-content"));
          }

          Map<String, Object> strMap = new HashMap<>(3);
          strMap.put("str", strs[i]);
          strMap.put("x", currentX);
          strMap.put("y", currentY);
          strMapList.add(strMap);
        }
      } else {
        Map<String, Object> strMap = new HashMap<>(3);
        strMap.put("str", currentStr);
        strMap.put("x", currentX);
        strMap.put("y", currentY);
        strMapList.add(strMap);
      }
    }

    // align-items
    if (settings.containsKey("container-height") && settings.containsKey("align-items")) {
      int offsetY;

      int containerHeight = ((Number) settings.get("container-height")).intValue();
      String alignItems = (String) settings.get("align-items");
      int minY = (int) strMapList.get(0).get("y");
      int maxY = (int) strMapList.get(strMapList.size() - 1).get("y");
      if ("center".equals(alignItems)) {
        offsetY = (containerHeight - (maxY - minY + lineHeight)) / 2;
      } else {
        throw new UnsupportedOperationException(alignItems);
      }

      for (Map<String, Object> strMap : strMapList) {
        strMap.put("y", (int) strMap.get("y") + offsetY);
      }
    }

    // grow
    if (settings.containsKey("grow")) {
      BufferedImage growBufferedImage = (BufferedImage) settings.get("grow");
      int incY = (int) strMapList.get(strMapList.size() - 1).get("y") - (int) strMapList.get(0).get("y");
      if (incY > 0) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight() + incY, BufferedImage.TYPE_INT_ARGB);

        Graphics2D dstGraphics2D = dst.createGraphics();

        // 文本抗锯齿 - 文本渲染是通过某种形式的抗锯齿来完成的。
        dstGraphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        dstGraphics2D.setFont(font);
        dstGraphics2D.setPaint(paint);

        dstGraphics2D.drawImage(src, 0, 0, null);
        for (int i = 0; i < incY; i++) {
          dstGraphics2D.drawImage(growBufferedImage, 0, src.getHeight() + i, null);
        }

        // draw
        for (Map<String, Object> strMap : strMapList) {
          dstGraphics2D.drawString(
              (String) strMap.get("str"),
              (int) strMap.get("x"),
              (int) strMap.get("y")
          );
        }

        dstGraphics2D.dispose();

        return dst;
      }
    }

    // draw
    for (Map<String, Object> strMap : strMapList) {
      graphics2D.drawString(
          (String) strMap.get("str"),
          (int) strMap.get("x"),
          (int) strMap.get("y")
      );
    }

    graphics2D.dispose();

    return src;
  }

  /**
   * 变换 Transform > 缩放 Scale
   *
   * @param src
   * @param width
   * @param height
   * @param positionX
   * @param positionY
   * @param size
   * @return
   * @throws IOException
   */
  public static String scale(String src, Integer width, Integer height, String positionX, String positionY, String size) throws IOException {
    int lastIndex = src.lastIndexOf('.');
    String formatName = src.substring(lastIndex + 1, src.length());
    File dst = new File(src.substring(0, lastIndex) + "." + (null == width ? "" : width) + "x" + (null == height ? "" : height) + "." + formatName);

    if (!dst.exists()) {
      BufferedImage img = ImageIO.read(new File(src));
      int srcWidth = img.getWidth();
      int srcHeight = img.getHeight();

      if (width == null && height != null) {
        width = (int) ((double) srcWidth / srcHeight * height);
      }
      if (width != null && height == null) {
        height = (int) ((double) srcHeight / srcWidth * width);
      }

      int dstWidth, dstHeight;
      int x = 0, y = 0;

      if (positionX.equals(SCALE_POSITION_CENTER) && positionY.equals(SCALE_POSITION_CENTER) && size.equals(SCALE_SIZE_COVER)) {

        if ((double) width / height >= (double) srcWidth / srcHeight) {
          dstWidth = width;
          dstHeight = new BigDecimal(srcHeight).multiply(new BigDecimal(width)).divide(new BigDecimal(srcWidth), RoundingMode.CEILING).intValue();

          if (dstHeight > height) {
            y = -(dstHeight - height) / 2;
          }
        } else {
          dstWidth = new BigDecimal(srcWidth).multiply(new BigDecimal(height)).divide(new BigDecimal(srcHeight), RoundingMode.CEILING).intValue();
          dstHeight = height;

          if (dstWidth > width) {
            x = -(dstWidth - width) / 2;
          }
        }
      } else {
        throw new UnsupportedOperationException(positionX + ", " + positionY + ", " + size);
      }

      BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D graphics2D = im.createGraphics();

      // 抗锯齿 - 使用抗锯齿来实现渲染。
      graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      graphics2D.drawImage(img, x, y, dstWidth, dstHeight, null);

      ImageIO.write(im, formatName, dst);
    }

    return dst.getAbsolutePath();
  }

  /**
   * 变换 Transform > 缩放 Scale
   *
   * @param src
   * @param width
   * @param height
   * @param positionX
   * @param positionY
   * @param size
   * @return
   * @throws IOException
   */
  public static BufferedImage scale(BufferedImage src, int width, int height, String positionX, String positionY, String size) throws IOException {
    int dstWidth = width, dstHeight = height;
    int srcWidth = src.getWidth();
    int srcHeight = src.getHeight();
    if (dstWidth == srcWidth && dstHeight == srcHeight) {
      return src;
    }

    int x = 0, y = 0;
    if (positionX.equals(SCALE_POSITION_CENTER) && positionY.equals(SCALE_POSITION_CENTER) && size.equals(SCALE_SIZE_COVER)) {
      if ((double) width / height >= (double) srcWidth / srcHeight) {
        dstHeight = new BigDecimal(srcHeight).multiply(new BigDecimal(width)).divide(new BigDecimal(srcWidth), RoundingMode.CEILING).intValue();

        if (dstHeight > height) {
          y = -(dstHeight - height) / 2;
        }
      } else {
        dstWidth = new BigDecimal(srcWidth).multiply(new BigDecimal(height)).divide(new BigDecimal(srcHeight), RoundingMode.CEILING).intValue();

        if (dstWidth > width) {
          x = -(dstWidth - width) / 2;
        }
      }
    } else {
      throw new UnsupportedOperationException(positionX + ", " + positionY + ", " + size);
    }

    BufferedImage dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics2D = dst.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.drawImage(src, x, y, dstWidth, dstHeight, null);

    return dst;
  }

  /**
   * 变换 Transform > 旋转 Rotate
   *
   * @param src
   * @param ang
   * @return
   */
  public static BufferedImage rotate(BufferedImage src, double ang) {
    int srcWidth = src.getWidth(null);
    int srcHeight = src.getHeight(null);
    Rectangle dstRect = rotatedRect(new Rectangle(new Dimension(srcWidth, srcHeight)), ang);
    int dstWidth = dstRect.width;
    int dstHeight = dstRect.height;

    BufferedImage dst = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = dst.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // 透明背景
    dst = graphics2D.getDeviceConfiguration().createCompatibleImage(dstWidth, dstHeight, Transparency.TRANSLUCENT);
    graphics2D.dispose();
    graphics2D = dst.createGraphics();

    // 旋转
    graphics2D.translate((dstWidth - srcWidth) / 2, (dstHeight - srcHeight) / 2);
    graphics2D.rotate(Math.toRadians(ang), (double) srcWidth / 2, (double) srcHeight / 2);

    graphics2D.drawImage(src, null, null);
    return dst;
  }

  /**
   * 变换 Transform > 旋转 180 度 Rotate 180°
   *
   * @param src
   * @return
   */
  public static BufferedImage rotate180(BufferedImage src) {
    int srcWidth = src.getWidth(null);
    int srcHeight = src.getHeight(null);

    BufferedImage dst = new BufferedImage(srcWidth, srcHeight, src.getType());
    Graphics2D graphics2D = dst.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.rotate(Math.toRadians(180), (double) srcWidth / 2, (double) srcHeight / 2);

    graphics2D.drawImage(src, null, null);
    return dst;
  }

  /**
   * 变换 Transform > 顺时针旋转 90 度 Rotate 90° Clockwise
   *
   * @param src
   * @return
   */
  public static BufferedImage rotate90Clockwise(BufferedImage src) {
    int srcWidth = src.getWidth(null);
    int srcHeight = src.getHeight(null);

    BufferedImage dst = new BufferedImage(srcHeight, srcWidth, src.getType());
    Graphics2D graphics2D = dst.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.translate((srcHeight - srcWidth) / 2, (srcHeight - srcWidth) / 2);
    graphics2D.rotate(Math.toRadians(90), (double) srcHeight / 2, (double) srcWidth / 2);

    graphics2D.drawImage(src, null, null);
    return dst;
  }

  /**
   * 变换 Transform > 逆时针旋转 90 度 Rotate 90° Counter Clockwise
   *
   * @param src
   * @return
   */
  public static BufferedImage rotate90CounterClockwise(BufferedImage src) {
    int srcWidth = src.getWidth(null);
    int srcHeight = src.getHeight(null);

    BufferedImage dst = new BufferedImage(srcHeight, srcWidth, src.getType());
    Graphics2D graphics2D = dst.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.translate((srcWidth - srcHeight) / 2, (srcWidth - srcHeight) / 2);
    graphics2D.rotate(-Math.toRadians(90), (double) srcHeight / 2, (double) srcWidth / 2);

    graphics2D.drawImage(src, null, null);
    return dst;
  }

  /**
   * 变换 Transform > 水平翻转 Flip Horizontal
   *
   * @param src
   * @return
   */
  public static BufferedImage flipHorizontal(BufferedImage src) {
    int srcWidth = src.getWidth(null);
    int srcHeight = src.getHeight(null);

    BufferedImage dst = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics2D = dst.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.drawImage(src, 0, 0, srcWidth, srcHeight, srcWidth, 0, 0, srcHeight, null);
    return dst;
  }

  /**
   * 变换 Transform > 垂直翻转 Flip Vertical
   *
   * @param src
   * @return
   */
  public static BufferedImage flipVertical(BufferedImage src) {
    int srcWidth = src.getWidth(null);
    int srcHeight = src.getHeight(null);

    BufferedImage dst = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics2D = dst.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.drawImage(src, 0, 0, srcWidth, srcHeight, 0, srcHeight, srcWidth, 0, null);
    return dst;
  }

  /**
   * 图像 Iamge > 裁剪 Crop
   *
   * @param src
   * @param sx1
   * @param sy1
   * @param sx2
   * @param sy2
   * @return
   */
  public static BufferedImage crop(BufferedImage src, int sx1, int sy1, int sx2, int sy2) {
    int dstWidth = sx2 - sx1;
    int dstHeight = sy2 - sy1;

    BufferedImage dst = new BufferedImage(dstWidth, dstHeight, src.getType());
    Graphics2D graphics2D = dst.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.drawImage(src, 0, 0, dstWidth, dstHeight, sx1, sy1, sx2, sy2, null);

    return dst;
  }

  /**
   * 设置角半径
   *
   * @param src
   * @param arc
   * @return
   */
  public static BufferedImage roundImage(BufferedImage src, int arc) {
    BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics2D = dst.createGraphics();

    // 抗锯齿 - 使用抗锯齿来实现渲染。
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.fillRoundRect(0, 0, src.getWidth(), src.getHeight(), arc, arc);

    graphics2D.setComposite(AlphaComposite.SrcIn);
    graphics2D.drawImage(src, 0, 0, null);

    graphics2D.dispose();

    return dst;
  }

  private static int calculateXByContainerWidthAndJustifyContent(Graphics2D graphics2D, Font font, String str, int x, int containerWidth, String justifyContent) {
    switch (justifyContent) {
      case "center":
        x += (int) ((containerWidth - font.getStringBounds(str, graphics2D.getFontRenderContext()).getWidth()) / 2);
        break;
      default:
        throw new UnsupportedOperationException(justifyContent);
    }

    return x;
  }

  private static Rectangle rotatedRect(Rectangle srcRect, double ang) {
    int srcWidth = srcRect.width;
    int srcHeight = srcRect.height;
    if (ang >= 90) {
      if (ang / 90 % 2 == 1) {
        srcWidth = srcRect.height;
        srcHeight = srcRect.width;
      }
      ang %= 90;
    }

    double r = Math.sqrt(Math.pow(srcWidth, 2) + Math.pow(srcHeight, 2)) / 2;
    double len = 2 * Math.sin(Math.toRadians(ang) / 2) * r;
    double angelAlpha = (Math.PI - Math.toRadians(ang)) / 2;
    double angelDaltaWidth = Math.atan((double) srcHeight / srcWidth);
    double angelDaltaHeight = Math.atan((double) srcWidth / srcHeight);

    int lenDaltaWidth = (int) (len * Math.cos(Math.PI - angelAlpha - angelDaltaWidth));
    lenDaltaWidth = lenDaltaWidth > 0 ? lenDaltaWidth : -lenDaltaWidth;

    int lenDaltaHeight = (int) (len * Math.cos(Math.PI - angelAlpha - angelDaltaHeight));
    lenDaltaHeight = lenDaltaHeight > 0 ? lenDaltaHeight : -lenDaltaHeight;

    int dstWidth = srcWidth + lenDaltaWidth * 2;
    int dstHeight = srcHeight + lenDaltaHeight * 2;
    dstWidth = dstWidth > 0 ? dstWidth : -dstWidth;
    dstHeight = dstHeight > 0 ? dstHeight : -dstHeight;
    return new Rectangle(new Dimension(dstWidth, dstHeight));
  }

  /**
   * @param w   宽度
   * @param h   高度
   * @param x   水平位置
   * @param y   垂直位置
   * @param deg 旋转角度
   * @return {x, y, w, h}
   */
  private static double[] rotatedRectBounds(double w, double h, double x, double y, double deg) {
    double x1, y1, x2, y2, x3, y3;
    double r, theta;

    r = w;
    theta = deg;
    x1 = x + Math.cos(Math.toRadians(theta)) * r;
    y1 = y + Math.sin(Math.toRadians(theta)) * r;

    r = Math.sqrt(Math.pow(w, 2) + Math.pow(h, 2));
    theta = deg + Math.toDegrees(Math.atan2(h, w));
    x2 = x + Math.cos(Math.toRadians(theta)) * r;
    y2 = y + Math.sin(Math.toRadians(theta)) * r;

    r = h;
    theta = deg + 90;
    x3 = x + Math.cos(Math.toRadians(theta)) * r;
    y3 = y + Math.sin(Math.toRadians(theta)) * r;

    List<Double> xs = new ArrayList<>(4);
    xs.add(x);
    xs.add(x1);
    xs.add(x2);
    xs.add(x3);
    Collections.sort(xs);

    List<Double> ys = new ArrayList<>(4);
    ys.add(y);
    ys.add(y1);
    ys.add(y2);
    ys.add(y3);
    Collections.sort(ys);

    return new double[]{xs.get(0), ys.get(0), xs.get(3) - xs.get(0), ys.get(3) - ys.get(0)};
  }
}
