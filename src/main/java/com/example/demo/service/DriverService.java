package com.example.demo.service;

import com.example.demo.model.Driver;
import com.example.demo.model.Visit;
import com.example.demo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService extends PersonService<Driver, DriverRepository> {

    private final AdministratorService administratorService;
    private final VisitService visitService;

    @Autowired
    public DriverService(DriverRepository driverRepository, AdministratorService administratorService, VisitService visitService) {
        super(driverRepository);
        this.administratorService = administratorService;
        this.visitService = visitService;
    }

    public Driver getDriverById(Integer id) {
        Optional<Driver> driver = repository.findById(id);
        return driver.orElse(null);
    }

    public void createDriverAndVisit(Driver driver) {
        Optional<Driver> existingDriver = findByLicencePassportNr(driver.getLicencePassportNr());
        if (existingDriver.isPresent()) {
            // Driver exists, create a new visit
            Driver foundDriver = existingDriver.get();
            Visit visit = new Visit();
            visit.setDriver(foundDriver);
            visitService.save(visit);
        } else {
            // Driver does not exist, save the driver
            save(driver);

            // Create a new visit for the newly created driver
            Visit visit = new Visit();
            visit.setDriver(driver);
            visitService.save(visit);
        }
    }

    @Override
    public void approvePerson(Integer personId) {
        Driver driver = getDriverById(personId);
        if (driver != null) {
            if (!driver.getAccountStatus().equals("Approved")) {
                driver.setAccountStatus("Approved");
                driver.setApprovedBy(administratorService.getLoggedInAdministrator());
                update(driver);
            } else {
                throw new RuntimeException("Driver with id " + personId + " is already approved.");
            }
        } else {
            throw new RuntimeException("Driver not found with id: " + personId);
        }
    }

    @Override
    public void declinePerson(Integer personId) {
        Driver driver = getPersonById(personId);
        if (driver != null) {
            driver.setAccountStatus("Declined");
            driver.setApprovedBy(administratorService.getLoggedInAdministrator());
            update(driver);
        }
    }
}
