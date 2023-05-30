// ImageController.java
package com.example.demo.controller;

import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

@Controller
public class ImageController {
    private final String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images";

    @Autowired
    private DriverService driverService;

    @PostMapping("/upload-picture")
    public ResponseEntity<String> uploadPicture(@RequestParam("picture") MultipartFile picture) {
        try {
            // Read the image bytes
            byte[] imageBytes = picture.getBytes();

            // Generate a unique file name using a shorter identifier
            String fileName = "img_" + UUID.randomUUID().toString().substring(0, 8) + ".png";

            // Define the file path
            String pathToSaveImage = uploadDirectory + File.separator + fileName;

            // Create the directory if it doesn't exist
            File imagesDirectory = new File(uploadDirectory);
            if (!imagesDirectory.exists()) {
                imagesDirectory.mkdirs();
            }

            // Write the image bytes to a file
            File outputFile = new File(pathToSaveImage);
            OutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(imageBytes);

            // Close the stream
            outputStream.close();

            // Construct the full image URL
            String imageUrl = "/images/" + fileName;

            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        }
    }
}
