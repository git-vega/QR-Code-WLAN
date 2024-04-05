package com.example.QRCodeWLAN;


import com.example.QRCodeWLAN.WiFiQRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wifi")
public class WiFiQRCodeController {

    @Autowired
    private WiFiQRCodeService wiFiQRCodeService;

    @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getWiFiQRCode() {
        try {
            byte[] image = wiFiQRCodeService.generateWiFiQRCode("FRITZ!Box 7530 FT", "WPA2", "517420793000891390123");
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
