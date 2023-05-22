package com.example.demo.service;

import com.example.demo.model.TransportCompany;
import com.example.demo.repository.TransportCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportCompanyService {
    private final TransportCompanyRepository transportCompanyRepository;

    @Autowired
    public TransportCompanyService(TransportCompanyRepository transportCompanyRepository) {
        this.transportCompanyRepository = transportCompanyRepository;
    }

    public List<TransportCompany> getAllTransportCompanies() {
        return transportCompanyRepository.findAll();
    }
}
