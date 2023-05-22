package com.example.demo.controller;

import com.example.demo.model.Visitor;
import com.example.demo.model.Visit;
import com.example.demo.service.VisitorService;
import com.example.demo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

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
        return "visitor/visitorForm";
    }

    @PostMapping("/submitVisitor")
    public String submitVisitorForm(@ModelAttribute Visitor visitor) {
        Visitor createdVisitor = visitorService.createVisitor(visitor);

        Visit visit = new Visit();
        visit.setVisitor(createdVisitor);
        visitService.createVisit(visit);

        return "success";
    }
}
