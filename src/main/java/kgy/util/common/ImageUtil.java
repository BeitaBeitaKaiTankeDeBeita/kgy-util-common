package kgy.util.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

/**
 * 图像工具类
 *
 * @author Kistory管音鹏
 * @version 1.2
 * @build 2015-10-9 14:59:01
 */
public class ImageUtil {

    public static BufferedImage read(String pathname) {
        try {
            return ImageIO.read(new File(pathname));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write2JPEG(BufferedImage bufferedImage, String pathname, float quality) {
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

    public static void write2JPEG(BufferedImage bufferedImage, String pathname) {
        ImageUtil.write2JPEG(bufferedImage, pathname, 0.75F);
    }

    public static boolean write2PNG(BufferedImage bufferedImage, String pathname) {
        try {
            return ImageIO.write(bufferedImage, "png", new File(pathname));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage drawStr(BufferedImage bufferedImage, Font font, String str, int offset) {
        Graphics2D graphics2D = bufferedImage.createGraphics();

        // 使用抗锯齿模式完成呈现
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        Shape shape = font.createGlyphVector(graphics2D.getFontMetrics().getFontRenderContext(), str).getOutline();
        Rectangle2D rectangle2D = shape.getBounds2D();
        graphics2D.translate(bufferedImage.getWidth() - (int) rectangle2D.getWidth() - 10,
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

    private ImageUtil() {
    }
}
