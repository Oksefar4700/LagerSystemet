package com.example.demo.service;

import com.example.demo.model.Visit;
import com.example.demo.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    public List<Visit> findAll() {
        return visitRepository.findAll();
    }
}

