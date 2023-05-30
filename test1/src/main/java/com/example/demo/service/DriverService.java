package com.example.demo.service;

import com.example.demo.model.Driver;
import com.example.demo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver updateDriverStatus(Integer driverId, String accountStatus) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + driverId));
        driver.setAccountStatus(accountStatus);
        return driverRepository.save(driver);
    }

    public Optional<Driver> findByLicencePassportNr(String licencePassportNr) {
        return driverRepository.findByLicencePassportNr(licencePassportNr);
    }

    public Optional<Driver> findByPictureUrl(String pictureUrl) {
        return driverRepository.findByPictureUrl(pictureUrl);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public List<Driver> findAllPendingDrivers() {
        return driverRepository.findAllByAccountStatus("pending");
    }

    // Other methods in the DriverService class

    public void approveDriver(Integer driverId) {
        updateDriverStatus(driverId, "Approved");
    }

    public void declineDriver(Integer driverId) {
        updateDriverStatus(driverId, "Declined");
    }
    public List<Driver> findAllApprovedDrivers() {
        return driverRepository.findAllByAccountStatus("Approved");
    }

}
