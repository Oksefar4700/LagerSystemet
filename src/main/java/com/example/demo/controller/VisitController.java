package com.example.demo.controller;

import com.example.demo.model.Driver;
import com.example.demo.model.Visitor;
import com.example.demo.model.Visit;
import com.example.demo.service.DriverService;
import com.example.demo.service.VisitorService;
import com.example.demo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visit")
public class VisitController {

    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/form")
    public String showDriverForm(Model model) {
        model.addAttribute("visit", new Visit());
        return "driver-form";
    }

    @PostMapping("/submit")
    public String submitDriverForm(@ModelAttribute("visit") Visit visit) {
        visitService.createVisit(visit);
        return "redirect:/success"; // Redirect to a success page
    }
}
