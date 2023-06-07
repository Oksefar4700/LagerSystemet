// ImageService.java
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class ImageService {
    private final String uploadDirectory = "./src/main/resources/static/images";

    public String saveImage(MultipartFile imageFile) throws IOException {
        byte[] imageBytes = imageFile.getBytes();
        String fileName = "img_" + UUID.randomUUID().toString().substring(0, 8) + ".png";
        String pathToSaveImage = uploadDirectory + File.separator + fileName;
        File imagesDirectory = new File(uploadDirectory);

        if (!imagesDirectory.exists()) {
            imagesDirectory.mkdirs();
        }

        File outputFile = new File(pathToSaveImage);
        OutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(imageBytes);
        outputStream.close();

        return "/images/" + fileName;
    }
}
