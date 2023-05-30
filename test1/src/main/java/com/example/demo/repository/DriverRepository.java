package com.example.demo.repository;

import com.example.demo.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findByLicencePassportNr(String licencePassportNr);
    Optional<Driver> findByPictureUrl(String pictureId);
    List<Driver> findAllByAccountStatus(String accountStatus);
}