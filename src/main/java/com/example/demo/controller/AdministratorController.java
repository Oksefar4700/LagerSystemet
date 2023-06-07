package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.*;
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
    private final VisitorService visitorService;
    private final PersonRetriever personRetriever;
    private final TransportCompanyService transportCompanyService;
    private final VisitService visitService;

    @Autowired
    public AdministratorController(
            AdministratorService administratorService,
            DriverService driverService,
            VisitorService visitorService,
            PersonRetriever personRetriever,
            TransportCompanyService transportCompanyService,
            VisitService visitService) {
        this.administratorService = administratorService;
        this.driverService = driverService;
        this.visitorService = visitorService;
        this.personRetriever = personRetriever;
        this.transportCompanyService = transportCompanyService;
        this.visitService = visitService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "administrator/login";
    }

    @PostMapping("/login")
    public String authenticateAdministrator(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        boolean isAuthenticated = administratorService.authenticateAdministrator(username, password);

        if (isAuthenticated) {
            return "redirect:/administrator/dashboard";
        } else {
            return "redirect:/administrator/login?error";
        }
    }

    @GetMapping("/dashboard")
    public String getAdminDashboard() {
        return "administrator/admin-dashboard";
    }


    @GetMapping("/person-requests")
    public String showPersonRequests(Model model) {
        List<Driver> pendingDrivers = driverService.findAllPendingPersons();
        List<Visitor> pendingVisitors = visitorService.findAllPendingPersons();
        List<Administrator> pendingAdministrators = administratorService.findAllPendingPersons();

        model.addAttribute("pendingDrivers", pendingDrivers);
        model.addAttribute("pendingVisitors", pendingVisitors);
        model.addAttribute("pendingAdministrators", pendingAdministrators);

        return "administrator/person-requests"; // Update template name here
    }

    @PostMapping("/person/approve/{personId}")
    public String approvePerson(@PathVariable("personId") Integer personId) {
        Administrator currentAdministrator = administratorService.getLoggedInAdministrator();
        Person person = personRetriever.getDriverById(personId);

        if (currentAdministrator != null && person != null) {
            if (person instanceof Driver) {
                driverService.approvePerson(personId);
            } else if (person instanceof Visitor) {
                visitorService.approvePerson(personId);
            } else if (person instanceof Administrator) {
                administratorService.approvePerson(personId);
            }
        }

        return "redirect:/administrator/person-requests";
    }

    @PostMapping("/person/decline/{personId}")
    public String declinePerson(@PathVariable("personId") Integer personId) {
        Administrator currentAdministrator = administratorService.getLoggedInAdministrator();
        Person person = personRetriever.getPersonById(personId);

        if (currentAdministrator != null && person != null) {
            if (person instanceof Driver) {
                driverService.declinePerson(personId);
            } else if (person instanceof Visitor) {
                visitorService.declinePerson(personId);
            } else if (person instanceof Administrator) {
                administratorService.declinePerson(personId);
            }
        }

        return "redirect:/administrator/person-requests";
    }


    @GetMapping("/admin-create")
    public String showAdminCreateForm(Model model) {
        model.addAttribute("administrator", new Administrator());
        return "administrator/admin-create";
    }

    @PostMapping("/create-admin")
    public String saveAdministrator(@ModelAttribute Administrator administrator) {
        // Save the administrator
        administratorService.save(administrator);
        return "redirect:/";
    }

    @GetMapping("/view-database")
    public String showViewDatabase(Model model) {
        return "administrator/view-database";
    }

    @GetMapping("/admin-database")
    public String showAllAdmins(Model model) {
        List<Administrator> administrators = administratorService.findAll();
        List<Visit> visits = visitService.findAll();

        model.addAttribute("administrators", administrators);
        model.addAttribute("visits", visits);

        return "administrator/admin-database"; // Update with your driver HTML template name
    }

    @GetMapping("/driver-database")
    public String showAllDrivers(Model model) {
        List<Driver> drivers = driverService.findAll();
        List<Visit> visits = visitService.findAll();

        model.addAttribute("drivers", drivers);
        model.addAttribute("visits", visits);

        return "administrator/driver-database";
    }

    @GetMapping("/visitor-database")
    public String showVisitorDatabase(Model model) {
        List<Visitor> visitors = visitorService.findAll();
        List<Visit> visits = visitService.findAll();

        model.addAttribute("visitors", visitors);
        model.addAttribute("visits", visits);

        return "administrator/visitor-database";
    }
}
