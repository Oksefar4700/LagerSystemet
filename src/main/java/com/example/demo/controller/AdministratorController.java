package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;


@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    private final AdministratorService administratorService;
    private final DriverService driverService;
    private final VisitorService visitorService;
    private final PersonRetriever personRetriever;
    private final TransportCompanyService transportCompanyService;
    private final VisitService visitService;
    private final PersonService<Person, PersonRepository<Person>> personService;


    @Autowired
    public AdministratorController(
            AdministratorService administratorService,
            DriverService driverService,
            VisitorService visitorService,
            PersonRetriever personRetriever,
            TransportCompanyService transportCompanyService,
            VisitService visitService,
            PersonService personService) {
        this.administratorService = administratorService;
        this.driverService = driverService;
        this.visitorService = visitorService;
        this.personRetriever = personRetriever;
        this.transportCompanyService = transportCompanyService;
        this.visitService = visitService;
        this.personService = personService;
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
        Person person = personRetriever.getPersonById(personId);

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
        model.addAttribute("locations", Arrays.asList("BO", "HA"));
        return "administrator/admin-create";
    }

    @GetMapping("/drivers")
    public String searchDrivers(@RequestParam(required = false) String keyword,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate visitDate,
                                Model model) {
        List<Driver> drivers;
        drivers = driverService.searchDriversByName(keyword);

        // Get the visits for the searched drivers
        List<Visit> driverVisits = new ArrayList<>();
        for (Driver driver : drivers) {
            driverVisits.addAll(driver.getVisitsList());
        }

        model.addAttribute("drivers", drivers);
        model.addAttribute("driverVisits", driverVisits);
        return "/administrator/driver-database";
    }

    @GetMapping("/visitors")
    public String searchVisitors(@RequestParam(required = false) String keyword,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate visitDate,
                                 Model model) {
        List<Visitor> visitors;
        visitors = visitorService.searchVisitorsByName(keyword);

        // Get the visits for the searched visitors
        List<Visit> visitorVisits = new ArrayList<>();
        for (Visitor visitor : visitors) {
            visitorVisits.addAll(visitor.getVisitsList());
        }

        model.addAttribute("visitors", visitors);
        model.addAttribute("visitorVisits", visitorVisits);
        return "/administrator/visitor-database";
    }

    @GetMapping("/administrators")
    public String searchAdministrators(@RequestParam(required = false) String keyword,
                                       Model model) {
        List<Administrator> administrators;
        administrators = administratorService.searchPersonsByName(keyword);

        model.addAttribute("administrators", administrators);
        return "/administrator/admin-database";
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
