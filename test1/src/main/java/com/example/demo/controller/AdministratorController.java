package com.example.demo.controller;

import com.example.demo.service.AdministratorService;
import com.example.demo.service.DriverService;
import com.example.demo.service.TransportCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    private final DriverService driverService;
    private final AdministratorService administratorService;
    private final TransportCompanyService transportCompanyService;

    @Autowired
    public AdministratorController(DriverService driverService, AdministratorService administratorService, TransportCompanyService transportCompanyService) {
        this.driverService = driverService;
        this.administratorService = administratorService;
        this.transportCompanyService = transportCompanyService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "administrator/login";
    }

    @PostMapping("/login")
    public String authenticateAdministrator(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean isAuthenticated = administratorService.authenticateAdministrator(username, password);

        if (isAuthenticated) {
            return "redirect:/administrator/dashboard";
        } else {
            return "redirect:/administrator/login?error";
        }
    }

    @GetMapping("/dashboard")
    public String getAdminDashboard(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("transportService", transportCompanyService);
        return "administrator/admin-dashboard";
    }

    // other admin operations, like edit driver, can be implemented here

}
