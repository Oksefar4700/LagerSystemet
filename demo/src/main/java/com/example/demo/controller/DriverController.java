package com.example.demo.controller;

import com.example.demo.model.Driver;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("driver", new Driver());
        return "index"; // Replace "index" with your actual index page name if different
    }

    @GetMapping("/form")
    public String getDriverForm(Model model) {
        model.addAttribute("driver", new Driver());
        return "DriverService"; // Changed this from "form" to "DriverService"
    }

    @PostMapping("/submit")
    public String submitDriverForm(@ModelAttribute Driver driver) {
        driverService.createDriver(driver);
        return "success"; // redirect to a success page, or adjust as needed
    }
}
