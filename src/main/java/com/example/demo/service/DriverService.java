package com.example.demo.service;

import com.example.demo.model.Driver;
import com.example.demo.model.Visit;
import com.example.demo.repository.DriverRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
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


    public List<Driver> searchDriversByName(String keyword) {
        List<Driver> drivers = new ArrayList<>();

        // Search for drivers by name using repository methods
        List<Driver> byFirstName = repository.findByFirstNameContainingIgnoreCase(keyword);
        List<Driver> byLastName = repository.findByLastNameContainingIgnoreCase(keyword);

        // Add the matching drivers to the final list
        drivers.addAll(byFirstName);
        drivers.addAll(byLastName);

        return drivers;
    }

    public void deleteDriver(Integer id) {
        visitService.deleteAllVisitsForDriver(id);
        repository.deleteById(id);
    }


    public void deleteUsersWithOldVisits() {
        ZonedDateTime threeYearsAgo = ZonedDateTime.now().minusYears(3);
        List<Driver> drivers = repository.findAll();

        for (Driver driver : drivers) {
            if (!driver.getVisitsList().isEmpty()) {
                Visit lastVisit = driver.getVisitsList().get(driver.getVisitsList().size() - 1);
                if (lastVisit.getCheck_in_time().isBefore(threeYearsAgo.toLocalDate().atStartOfDay(ZoneId.systemDefault()))) {
                    deletePerson(driver.getPersonId());
                }
            }
        }
    }

}
