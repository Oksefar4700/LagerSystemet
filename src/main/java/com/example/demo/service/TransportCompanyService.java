package com.example.demo.service;

import com.example.demo.model.TransportCompany;
import com.example.demo.repository.TransportCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public String getTransportNameById(int id) {
        Optional<TransportCompany> transportCompanyOpt = transportCompanyRepository.findById(id);
        if (transportCompanyOpt.isPresent()) {
            return transportCompanyOpt.get().getName();
        } else {
            return null;  // or throw an exception, or return a default value
        }
    }
}
