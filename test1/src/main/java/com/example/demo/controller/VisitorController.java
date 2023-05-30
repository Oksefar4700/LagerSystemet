package com.example.demo.controller;

import com.example.demo.model.Visit;
import com.example.demo.model.Visitor;
import com.example.demo.service.VisitorService;
import com.example.demo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Controller
public class VisitorController {
    private final VisitorService visitorService;
    private final VisitService visitService;

    // Define the directory where you want to save the uploaded images
    private final String uploadDirectory = System.getProperty("user.dir") + "/uploads";

    @Autowired
    public VisitorController(VisitorService visitorService, VisitService visitService) {
        this.visitorService = visitorService;
        this.visitService = visitService;
    }

    @GetMapping("/visitorForm")
    public String getVisitorForm(Model model) {
        model.addAttribute("visitor", new Visitor());
        model.addAttribute("locations", Arrays.asList("BO", "HA"));
        return "visitor/visitorForm.html";
    }

    @PostMapping("/submitVisitor")
    public String submitVisitorForm(@ModelAttribute Visitor visitor, @RequestParam("image") MultipartFile file, Model model) {
        try {
            // Normalize file name
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = Paths.get(uploadDirectory + File.separator + fileName);

            // Check if the image URL already exists in the database
            Optional<Visitor> existingVisitor = visitorService.findByPictureUrl(targetLocation.toString());
            if (existingVisitor.isPresent()) {
                model.addAttribute("message", "Image URL already in use. Please choose a different one.");
                return "visitor/visitorForm.html";
            }

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // After storing the image locally, you can store its URL in the database.
            String pictureUrl = "/static/" + fileName;
            visitor.setPictureUrl(pictureUrl);
        } catch (IOException ex) {
            model.addAttribute("message", "An error occurred while uploading the file. Please try again.");
            return "visitor/visitorForm.html";
        }

        // Check if the user already exists
        Optional<Visitor> existingUser = visitorService.findByLicencePassportNr(visitor.getLicencePassportNr());
        if (existingUser.isPresent()) {
            // User exists, create a new visit
            Visit visit = new Visit();
            visit.setVisitor(existingUser.get());
            visitService.createVisit(visit);
        } else {
            // User does not exist, create new user and a new visit
            Visitor createdVisitor = visitorService.createVisitor(visitor);
            Visit visit = new Visit();
            visit.setVisitor(createdVisitor);
            visitService.createVisit(visit);
        }

        model.addAttribute("message", "Operation success!");
        return "success";
    }
}
