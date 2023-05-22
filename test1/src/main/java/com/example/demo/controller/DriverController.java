package com.example.demo.controller;

import com.example.demo.model.Driver;
import com.example.demo.model.Visit;
import com.example.demo.repository.TransportCompanyRepository;
import com.example.demo.service.DriverService;
import com.example.demo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

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
    public String getIndex(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("companies", transportCompanyRepository.findAll());
        model.addAttribute("locations", Arrays.asList("BO", "HA"));
        return "driver/driverForm";
    }

    @PostMapping("/submit")
    public String submitDriverForm(@ModelAttribute Driver driver) {
        Driver createdDriver = driverService.createDriver(driver);

        Visit visit = new Visit();
        visit.setDriver(createdDriver);
        visitService.createVisit(visit);

        return "success";
    }
}
