package com.heyudev.qrcode.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

/**
 * Created by heyudev on 2017/5/31.
 */
public class ImageUtil {

    private static final int DEFAULT_FONTSIZE = 20;

    public static BufferedImage drawFontOnImage(BufferedImage backgroudImage, String font, int fontLeftLocation, int fontTopLocation) throws Exception {
        return drawFontOnImage(backgroudImage, font, DEFAULT_FONTSIZE, fontLeftLocation, fontTopLocation);
    }

    public static BufferedImage drawFontOnImage(BufferedImage backgroudImage, String font, int fontSize, int fontLeftLocation, int fontTopLocation) throws Exception {
        Graphics2D g2d = (Graphics2D) backgroudImage.getGraphics();
        if (!Objects.equals(null, font) && !Objects.equals("", font)) {
            g2d.setColor(Color.black);
            g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, fontSize));
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(font, fontLeftLocation, fontTopLocation);
        }
        return backgroudImage;
    }

    /**
     * @param backgroudImage
     * @param frontImage
     * @param qrCodeUrl
     * @param qrCodeLeftLocation
     * @param qrCodeTopLocation
     * @return
     * @throws Exception
     */
    public static BufferedImage drawImageOnImage(BufferedImage backgroudImage, BufferedImage frontImage, String qrCodeUrl, int qrCodeLeftLocation, int qrCodeTopLocation) throws Exception {
        Graphics2D g2d = (Graphics2D) backgroudImage.getGraphics();
        if (!Objects.equals(null, qrCodeUrl) && !Objects.equals("", qrCodeUrl)) {
            g2d.drawImage(frontImage, qrCodeLeftLocation, qrCodeTopLocation, null);
        }
        return backgroudImage;
    }

    /**
     *
     * @param originalImage
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage zoomImage(BufferedImage originalImage, int width, int height) {
        BufferedImage zoomImage = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
        zoomImage.getGraphics().drawImage(originalImage.getScaledInstance(width, width, Image.SCALE_SMOOTH), 0, 0, null);
        return zoomImage;
    }
}
