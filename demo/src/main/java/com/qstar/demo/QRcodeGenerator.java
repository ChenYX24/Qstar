package com.qstar.demo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.util.Base64;
import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
public class QRcodeGenerator {

    public static String generate(String url,String id){
        String base64String="";
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            System.out.println(id);
            File outputFile = new File(id + ".png");
            ImageIO.write(image, "png", outputFile);
            Path imagePath = Path.of(id + ".png");
            byte[] imageBytes = Files.readAllBytes(imagePath);
            base64String = Base64.getEncoder().encodeToString(imageBytes);
            System.out.println(base64String);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return base64String;
    }
}
