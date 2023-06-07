// DriverController.java
package com.example.demo.controller;

import com.example.demo.model.Driver;
import com.example.demo.model.TransportCompany;
import com.example.demo.model.Visit;
import com.example.demo.repository.TransportCompanyRepository;
import com.example.demo.service.DriverService;
import com.example.demo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Controller
public class DriverController {
    private final DriverService driverService;
    private final VisitService visitService;
    private final TransportCompanyRepository transportCompanyRepository;

    @Autowired
    public DriverController(DriverService driverService, VisitService visitService, TransportCompanyRepository transportCompanyRepository) {
        this.driverService = driverService;
        this.visitService = visitService;
        this.transportCompanyRepository = transportCompanyRepository;
    }

    @GetMapping("/driverForm")
    public String getDriverForm(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("companies", transportCompanyRepository.findAll());
        model.addAttribute("locations", Arrays.asList("BO", "HA"));
        return "driver/driverForm.html";
    }

    @PostMapping("/driverSubmit")
    public String submitDriverForm(@ModelAttribute Driver formDriver, Model model) {
        // Get the selected company
        Optional<TransportCompany> selectedCompany = transportCompanyRepository.findById(formDriver.getTransportCompany().getTransportCompanyId());
        if (!selectedCompany.isPresent()) {
            // Handle this case, perhaps throw an exception
        }

        // Set the selected company to the driver
        formDriver.setTransportCompany(selectedCompany.get());

        // Check if the user already exists
        Optional<Driver> existingUser = driverService.findByLicencePassportNr(formDriver.getLicencePassportNr());
        if (existingUser.isPresent()) {
            // User exists, create a new visit
            Driver existingDriver = existingUser.get();
            Visit visit = new Visit();
            visit.setDriver(existingDriver);
            visitService.createVisit(visit);

            model.addAttribute("message", "Visit created successfully for an existing driver.");
        } else {
            // User does not exist, create new user
            formDriver.setAccountStatus("pending"); // Set status to pending

            // Create the new driver
            Driver createdDriver = driverService.createDriver(formDriver);

            // Create a new visit
            Visit visit = new Visit();
            visit.setDriver(createdDriver);
            visitService.createVisit(visit);

            model.addAttribute("message", "Account created successfully. Visit created for the new driver.");
        }

        return "success";
    }
}
