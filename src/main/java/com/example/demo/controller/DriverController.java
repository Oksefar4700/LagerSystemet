package com.example.demo.controller;

import com.example.demo.model.Visit;
import com.example.demo.service.DriverService;
import com.example.demo.model.Driver;
import com.example.demo.service.TransportCompanyService;
import com.example.demo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@Controller
public class DriverController {

    private final DriverService driverService;
    private final TransportCompanyService transportCompanyService;
    private final VisitService visitService;

    @Autowired
    public DriverController(DriverService driverService, TransportCompanyService transportCompanyService, VisitService visitService) {
        this.driverService = driverService;
        this.transportCompanyService = transportCompanyService;
        this.visitService = visitService;
    }

    @GetMapping("/driverForm")
    public String getDriverForm(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("companies", transportCompanyService.getAllTransportCompanies());
        model.addAttribute("locations", Arrays.asList("BO", "HA"));
        return "/driver/driverForm";
    }

    @PostMapping("/driverSubmit")
    public String submitDriverForm(@ModelAttribute Driver driver, Model model) {
        driverService.createDriverAndVisit(driver);

        model.addAttribute("message", "Driver submitted successfully!");
        return "success";
    }
}
