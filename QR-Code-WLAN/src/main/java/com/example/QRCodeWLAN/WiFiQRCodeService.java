package com.example.QRCodeWLAN;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class WiFiQRCodeService {

    public byte[] generateWiFiQRCode(String ssid, String networkType, String password) throws Exception {
        String wifiConfig = "WIFI:T:" + networkType + ";S:" + ssid + ";P:" + password + ";;";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(wifiConfig, BarcodeFormat.QR_CODE, 300, 300);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Generieren des QR-Codes", e);
        }
    }
}

