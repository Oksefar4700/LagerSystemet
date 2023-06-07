package com.example.demo.controller;

import com.example.demo.model.Visit;
import com.example.demo.service.DriverService;
import com.example.demo.service.VisitorService;
import com.example.demo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VisitController {

    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/visits")
    public String listVisits(Model model) {
        List<Visit> visits = visitService.findAll();
        model.addAttribute("visits", visits);
        return "visit/visitList"; // return the thymeleaf template for visit list
    }

}
