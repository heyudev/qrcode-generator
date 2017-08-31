package com.heyudev.qrcode.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

/**
 * Created by heyudev on 2017/8/22.
 */
public class QrCodeUtil {

    private static final String DEFAULT_CHARACTER = "UTF-8";

    private static final MatrixToImageConfig DEFAULT_CONFIG = new MatrixToImageConfig();

    private static final ErrorCorrectionLevel DEFAULT_LEVEL = ErrorCorrectionLevel.H;

    private static final int DEFAULT_MARGINBORDER = 2;

    private static final int DEFAULT_RATIO = 4;

    public static File createQrCode(String contents, String fileDir, String fileName, String suffix, int width, int height) throws Exception {
        return createQrCode(contents, fileDir, fileName, suffix, width, height, DEFAULT_CONFIG, DEFAULT_LEVEL, DEFAULT_MARGINBORDER);
    }

    public static File createQrCode(String contents, String fileDir, String fileName, String suffix, int width, int height, int marginBorder) throws Exception {
        return createQrCode(contents, fileDir, fileName, suffix, width, height, DEFAULT_CONFIG, DEFAULT_LEVEL, marginBorder);
    }

    public static File createQrCode(String contents, String fileDir, String fileName, String suffix, int width, int height, ErrorCorrectionLevel level) throws Exception {
        return createQrCode(contents, fileDir, fileName, suffix, width, height, DEFAULT_CONFIG, level, DEFAULT_MARGINBORDER);
    }

    public static File createQrCode(String contents, String fileDir, String fileName, String suffix, int width, int height, MatrixToImageConfig config) throws Exception {
        return createQrCode(contents, fileDir, fileName, suffix, width, height, config, DEFAULT_LEVEL, DEFAULT_MARGINBORDER);
    }

    public static File createQrCode(String contents, String fileDir, String fileName, String suffix, int width, int height, ErrorCorrectionLevel level, int marginBorder) throws Exception {
        return createQrCode(contents, fileDir, fileName, suffix, width, height, DEFAULT_CONFIG, level, marginBorder);
    }

    public static File createQrCode(String contents, String fileDir, String fileName, String suffix, int width, int height, MatrixToImageConfig config, int marginBorder) throws Exception {
        return createQrCode(contents, fileDir, fileName, suffix, width, height, config, DEFAULT_LEVEL, marginBorder);
    }

    public static File createQrCode(String contents, String fileDir, String fileName, String suffix, int width, int height, MatrixToImageConfig config, ErrorCorrectionLevel level) throws Exception {
        return createQrCode(contents, fileDir, fileName, suffix, width, height, config, level, DEFAULT_MARGINBORDER);
    }

    /**
     * create qrcode
     *
     * @param contents     qrcode contents
     * @param fileDir      temp file dir
     * @param fileName     temp file name
     * @param suffix       qrcode image suffix
     * @param width        qrcode width
     * @param height       qrcode height
     * @param config       MatrixToImageConfig
     * @param level        ErrorCorrectionLevel {M, L, H, Q}
     * @param marginBorder qrcode margin border
     * @return
     * @throws Exception
     */
    public static File createQrCode(String contents, String fileDir, String fileName, String suffix, int width, int height, MatrixToImageConfig config, ErrorCorrectionLevel level, int marginBorder) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, level);
        hints.put(EncodeHintType.CHARACTER_SET, DEFAULT_CHARACTER);
        hints.put(EncodeHintType.MARGIN, marginBorder);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                BarcodeFormat.QR_CODE,
                width,
                height,
                hints);
        if (fileDir == null || fileDir.trim().length() == 0) {
            fileDir = "/";
        }
        if (!fileDir.endsWith("/")) {
            fileDir = fileDir + "/";
        }
        Path dir = Paths.get(fileDir);
        Path tempFile = Files.createTempFile(dir, fileName, "." + suffix);
        MatrixToImageWriter.writeToPath(bitMatrix, suffix, tempFile, config);
        return tempFile.toFile();
    }

    /**
     * set qrcode logo
     *
     * @param matrixImage
     * @param logoPath
     * @param isUrl
     * @return
     * @throws Exception
     */
    public static BufferedImage setQrCodeLogo(BufferedImage matrixImage, String logoPath, boolean isUrl) throws Exception {
        return setQrCodeLogo(matrixImage, logoPath, isUrl, DEFAULT_RATIO);
    }

    /**
     * set qrcode logo
     *
     * @param matrixImage qrcode image
     * @param logoPath    logo image path
     * @param isUrl       is http url (true) or file path (false)
     * @param ratio       logo ratio
     * @return
     * @throws IOException
     */
    public static BufferedImage setQrCodeLogo(BufferedImage matrixImage, String logoPath, boolean isUrl, int ratio) throws Exception {
        Graphics2D g2 = matrixImage.createGraphics();
        int matrixWidth = matrixImage.getWidth();
        int matrixHeigh = matrixImage.getHeight();
        BufferedImage logo = null;
        if (isUrl) {
            logo = ImageIO.read(new URL(logoPath));
        } else {
            logo = ImageIO.read(new File(logoPath));
        }
        g2.drawImage(logo, (matrixWidth - matrixWidth / ratio) / 2, (matrixHeigh - matrixHeigh / ratio) / 2, matrixWidth / ratio, matrixHeigh / ratio, null);//绘制
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.dispose();
        matrixImage.flush();
        return matrixImage;
    }

}