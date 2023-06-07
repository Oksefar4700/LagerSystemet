package com.example.demo.repository;

import com.example.demo.model.TransportCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Integer> {
}

