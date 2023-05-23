package com.qstar.demo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.File;
public class QRcodeGenerator {

    public void generate(String url){
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 50, 50);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            File outputFile = new File("qrcode.png");
            ImageIO.write(image, "png", outputFile);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
