package com.heyudev.qrcode.example;

import com.heyudev.qrcode.utils.ImageUtil;
import com.heyudev.qrcode.utils.QrCodeUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by heyudev on 2017/8/30.
 */
public class Example {
    public static void main(String[] args) {
        try {
            String qrCodeUrl = "https://www.heyudev.com";
            String imagePath = "/Users/heyudev";
            String fileName = "qrcode";
            String logoUrl = "https://raw.githubusercontent.com/heyudev/heyudev.github.io/master/uploads/portrait.png";
            String filePath = "/Users/heyudev/qrcode.jpg";
            String fontContents = "Hello World!";
            /**
             * create qrcode
             */
            File file = QrCodeUtil.createQrCode(qrCodeUrl, imagePath, fileName, "jpg", 2048, 2048);
            BufferedImage image = ImageIO.read(file);
            /**
             * set logo
             */
            BufferedImage logoQrCode = QrCodeUtil.setQrCodeLogo(image, logoUrl, true);
            /**
             * set font
             */
            BufferedImage result = ImageUtil.drawFontOnImage(logoQrCode, fontContents, 50, 930, 2010);
            /**
             * zoom
             */
            BufferedImage zoomImage = ImageUtil.zoomImage(result, 1024, 1024);
            ImageIO.write(zoomImage, "jpg", new File(filePath));
            System.out.println(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
