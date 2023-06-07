package com.example.demo.repository;

import com.example.demo.model.Driver;
import com.example.demo.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    Optional<Visitor> findByLicencePassportNr(String licencePassportNr);
    Optional<Visitor> findByPictureUrl(String pictureId);
}
