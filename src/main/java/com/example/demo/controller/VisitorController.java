package com.example.demo.controller;

import com.example.demo.model.Visit;
import com.example.demo.service.VisitService;
import com.example.demo.service.VisitorService;
import com.example.demo.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class VisitorController {

    private final VisitorService visitorService;
    private final VisitService visitService;


    @Autowired
    public VisitorController(VisitorService visitorService, VisitService visitService) {
        this.visitorService = visitorService;
        this.visitService = visitService;
    }

    @GetMapping("/visitorForm")
    public String getVisitorForm(Model model) {
        model.addAttribute("visitor", new Visitor());
        model.addAttribute("locations", Arrays.asList("BO", "HA"));
        return "/visitor/visitorForm";
    }

    @PostMapping("/visitorSubmit")
    public String submitVisitorForm(@ModelAttribute Visitor visitor, Model model) {
        visitorService.createVisitorAndVisit(visitor);

        model.addAttribute("message", "Visitor submitted successfully!");
        return "success";
    }

}
