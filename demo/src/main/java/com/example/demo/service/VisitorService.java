package com.example.demo.service;

import com.example.demo.model.Visitor;
import com.example.demo.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public Visitor getVisitorById(Integer id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found with id: " + id));
    }

    public Visitor createVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public void deleteVisitor(Integer id) {
        visitorRepository.deleteById(id);
    }
}