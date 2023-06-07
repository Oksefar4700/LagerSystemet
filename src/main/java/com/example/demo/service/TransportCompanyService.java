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

    public TransportCompany getTransportCompanyById(int id) {
        return transportCompanyRepository.findById(id).orElse(null);
    }

    public String getTransportNameById(Integer id) {
        TransportCompany transportCompany = transportCompanyRepository.findById(id).orElse(null);
        if (transportCompany != null) {
            return transportCompany.getCompanyName();
        }
        return "Not found";
    }
}

