package com.example.demo.service;

import com.example.demo.model.Administrator;
import com.example.demo.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public List<Administrator> getAllAdministrators() {
        return administratorRepository.findAll();
    }

    public Administrator getAdministratorById(Integer id) {
        return administratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrator not found with id: " + id));
    }

    public Administrator createAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public void deleteAdministrator(Integer id) {
        administratorRepository.deleteById(id);
    }
}