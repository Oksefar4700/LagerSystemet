package com.example.demo.controller;

import com.example.demo.model.Administrator;
import com.example.demo.model.Driver;
import com.example.demo.service.AdministratorService;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    private final AdministratorService administratorService;
    private final DriverService driverService;

    @Autowired
    public AdministratorController(AdministratorService administratorService, DriverService driverService) {
        this.administratorService = administratorService;
        this.driverService = driverService;
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
        // Retrieve the necessary data from the backend
        List<Driver> approvedDrivers = driverService.findAllApprovedDrivers();

        // Add the data to the model
        model.addAttribute("drivers", approvedDrivers);

        return "administrator/admin-dashboard";
    }


    @GetMapping("/driver-requests")
    public String showDriverRequests(Model model) {
        List<Driver> pendingDrivers = driverService.findAllPendingDrivers();
        model.addAttribute("pendingDrivers", pendingDrivers);
        return "administrator/adminDriverRequests";
    }


    @PostMapping("/driver/approve/{driverId}")
    public String approveDriver(@PathVariable("driverId") Integer driverId) {
        driverService.approveDriver(driverId);
        return "redirect:/administrator/driver-requests";
    }

    @PostMapping("/driver/decline/{driverId}")
    public String declineDriver(@PathVariable("driverId") Integer driverId) {
        driverService.declineDriver(driverId);
        return "redirect:/administrator/driver-requests";
    }

    // Other methods and mappings for administrator operations
}
