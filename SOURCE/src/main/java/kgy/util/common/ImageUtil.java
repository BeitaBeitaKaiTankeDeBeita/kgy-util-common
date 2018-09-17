package kgy.util.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

/**
 * Image Util
 *
 * @author KistoryG
 * @build 2018-09-17 16:16:53
 */
public class ImageUtil {

  private static final Logger LOG = Logger.getLogger(ImageUtil.class.getName());

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
   * Returns a BufferedImage as the result of decoding a supplied InputStream
   * with an ImageReader chosen automatically from among those currently
   * registered. The InputStream is wrapped in an ImageInputStream. If no
   * registered ImageReader claims to be able to read the resulting stream, null
   * is returned.
   * <p>
   * The current cache settings from getUseCacheand getCacheDirectory will be
   * used to control caching in the ImageInputStream that is created.
   * <p>
   * This method does not attempt to locate ImageReaders that can read directly
   * from an InputStream; that may be accomplished using IIORegistry and
   * ImageReaderSpi.
   * <p>
   * This method does not close the provided InputStream after the read
   * operation has completed; it is the responsibility of the caller to close
   * the stream, if desired.
   *
   * @param input an InputStream to read from.
   *
   * @return a BufferedImage containing the decoded contents of the input, or
   *         null.
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
   * This method does not close the provided OutputStream after the write operation has completed; it is the responsibility of the caller to close the stream, if desired.
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
    try (ByteArrayOutputStream outputSteam = new ByteArrayOutputStream()) {
      ImageIO.write(im, formatName, outputSteam);

      return outputSteam.toByteArray();
    }
  }

  /**
   * @param input      a File to read from
   * @param newWidth   new width of the created image
   * @param newHeight  new height of the created image
   * @param formatName a String containing the informal name of the format
   *
   * @return
   *
   * @throws java.io.IOException
   */
  public static byte[] toBytes(InputStream input, int newWidth, int newHeight, String formatName) throws IOException {
    try (ByteArrayOutputStream outputSteam = new ByteArrayOutputStream()) {
      BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
      Graphics graphics = image.createGraphics();
      graphics.drawImage(toBufferedImage(input), 0, 0, newWidth, newHeight, null);
      ImageIO.write(image, formatName, outputSteam);

      return outputSteam.toByteArray();
    }
  }

  protected ImageUtil() {
  }
}
