package kgy.util.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Transparency;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

/**
 * 图片 工具类
 *
 * @author KistoryG
 */
public class ImageUtil {

  private static final Logger LOG = Logger.getLogger(ImageUtil.class.getName());

  public static final String SCALE_POSITION_CENTER = "center";
  public static final String SCALE_SIZE_COVER = "cover";

  public static BufferedImage drawStr(BufferedImage bufferedImage, Font font, String str, int offset) {
    Graphics2D graphics2D = bufferedImage.createGraphics();

    // 使用抗锯齿模式完成呈现
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

    Shape shape = font.createGlyphVector(graphics2D.getFontMetrics().getFontRenderContext(), str).getOutline();
    Rectangle2D rectangle2D = shape.getBounds2D();
    graphics2D.translate(
        bufferedImage.getWidth() - (int) rectangle2D.getWidth() - 10,
        bufferedImage.getHeight() - font.getSize() - offset - 60);
    graphics2D.setPaint(Color.WHITE);
    graphics2D.fill(shape);
    graphics2D.setPaint(Color.BLACK);
    graphics2D.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1));
    graphics2D.draw(shape);
    graphics2D.dispose();

    return bufferedImage;
  }

  public static BufferedImage drawStr(BufferedImage bufferedImage, Font font, String str) {
    return drawStr(bufferedImage, font, str, 0);
  }

  public static BufferedImage drawStrs(BufferedImage bufferedImage, Font font, String[] strs) {
    int offset = 0;
    for (int i = strs.length - 1; i >= 0; i--) {
      String str = strs[i];
      bufferedImage = drawStr(bufferedImage, font, str, offset);
      offset += font.getSize() + 5;
    }

    return bufferedImage;
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

  /**
   * Returns a BufferedImage as the result of decoding a supplied InputStream with an ImageReader chosen automatically
   * from among those currently registered. The InputStream is wrapped in an ImageInputStream. If no registered
   * ImageReader claims to be able to read the resulting stream, null is returned.
   * <p>
   * The current cache settings from getUseCacheand getCacheDirectory will be used to control caching in the
   * ImageInputStream that is created.
   * <p>
   * This method does not attempt to locate ImageReaders that can read directly from an InputStream; that may be
   * accomplished using IIORegistry and ImageReaderSpi.
   * <p>
   * This method does not close the provided InputStream after the read operation has completed; it is the
   * responsibility of the caller to close the stream, if desired.
   *
   * @param input an InputStream to read from.
   *
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
   * Writes an image using an arbitrary ImageWriter that supports the given format to an OutputStream.
   * <p>
   * This method does not close the provided OutputStream after the write operation has completed; it is the
   * responsibility of the caller to close the stream, if desired.
   * <p>
   * The current cache settings from getUseCacheand getCacheDirectory will be used to control caching.
   *
   * @param im         a RenderedImage to be written.
   * @param formatName a String containing the informal name of the format.
   *
   * @return false if no appropriate writer is found.
   *
   * @throws java.io.IOException
   */
  public static byte[] toBytes(BufferedImage im, String formatName) throws IOException {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      ImageIO.write(im, formatName, output);
      return output.toByteArray();
    }
  }

  /**
   * @param input      a File to read from
   * @param width      the width of the rectangle
   * @param height     the height of the rectangle
   * @param formatName a String containing the informal name of the format
   *
   * @return
   *
   * @throws java.io.IOException
   */
  public static byte[] toBytes(InputStream input, int width, int height, String formatName) throws IOException {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics graphics = im.createGraphics();
      graphics.drawImage(toBufferedImage(input), 0, 0, width, height, null);
      ImageIO.write(im, formatName, output);
      return output.toByteArray();
    }
  }

  /**
   * 变换 > 缩放
   *
   * @param src
   * @param width
   * @param height
   * @param positionX
   * @param positionY
   * @param size
   *
   * @return
   *
   * @throws IOException
   */
  public static String scale(String src, int width, int height, String positionX, String positionY, String size) throws IOException {
    BufferedImage img = ImageIO.read(new File(src));

    int currentWidth, currentHeight;
    int x = 0, y = 0;
    int originalWidth = img.getWidth();
    int originalHeight = img.getHeight();

    if (positionX.equals(SCALE_POSITION_CENTER) && positionX.equals(SCALE_POSITION_CENTER) && size.equals(SCALE_SIZE_COVER)) {
      if ((double) width / height >= (double) originalWidth / originalHeight) {
        currentWidth = width;
        currentHeight = new BigDecimal(originalHeight).multiply(new BigDecimal(width)).divide(new BigDecimal(originalWidth), RoundingMode.CEILING).intValue();

        if (currentHeight > height) {
          y = -(currentHeight - height) / 2;
        }
      } else {
        currentWidth = new BigDecimal(originalWidth).multiply(new BigDecimal(height)).divide(new BigDecimal(originalHeight), RoundingMode.CEILING).intValue();
        currentHeight = height;

        if (currentWidth > width) {
          x = -(currentWidth - width) / 2;
        }
      }
    } else {
      throw new UnsupportedOperationException(positionX + ", " + positionY + ", " + size);
    }

    BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics graphics = im.createGraphics();
    graphics.drawImage(img, x, y, currentWidth, currentHeight, null);

    int lastIndex = src.lastIndexOf('.');
    String formatName = src.substring(lastIndex + 1, src.length());
    String tar = src.substring(0, lastIndex) + "." + width + "x" + height + "." + formatName;
    ImageIO.write(im, formatName, new File(tar));

    return tar;
  }

  /**
   *
   * 变换 > 旋转
   *
   * @param src
   * @param ang
   *
   * @return
   */
  public static BufferedImage rotate(BufferedImage src, double ang) {
    int srcWidth = src.getWidth(null);
    int srcHeight = src.getHeight(null);
    Rectangle tarRect = rotatedRect(new Rectangle(new Dimension(srcWidth, srcHeight)), ang);
    int tarWidth = tarRect.width;
    int tarHeight = tarRect.height;

    BufferedImage tar = new BufferedImage(tarWidth, tarHeight, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = tar.createGraphics();

    // 透明背景
    tar = graphics.getDeviceConfiguration().createCompatibleImage(tarWidth, tarHeight, Transparency.TRANSLUCENT);
    graphics.dispose();
    graphics = tar.createGraphics();

    // 旋转
    graphics.translate((tarWidth - srcWidth) / 2, (tarHeight - srcHeight) / 2);
    graphics.rotate(Math.toRadians(ang), srcWidth / 2, srcHeight / 2);

    graphics.drawImage(src, null, null);
    return tar;
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

    int tarWidth = srcWidth + lenDaltaWidth * 2;
    int tarHeight = srcHeight + lenDaltaHeight * 2;
    tarWidth = tarWidth > 0 ? tarWidth : -tarWidth;
    tarHeight = tarHeight > 0 ? tarHeight : -tarHeight;
    return new Rectangle(new Dimension(tarWidth, tarHeight));
  }

  protected ImageUtil() {
  }
}
