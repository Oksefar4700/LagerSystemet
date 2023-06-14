package com.example.demo;

import com.example.demo.model.Driver;
import com.example.demo.model.Visit;
import com.example.demo.repository.DriverRepository;
import com.example.demo.service.AdministratorService;
import com.example.demo.service.DriverService;
import com.example.demo.service.VisitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @Mock
    DriverRepository driverRepository;

    @Mock
    VisitService visitService;

    @Mock
    AdministratorService administratorService;

    @InjectMocks
    DriverService driverService;

    @Test
    public void testCreateDriverAndVisitNewDriver() {
        // Arrange
        Driver driver = new Driver();
        driver.setLicencePassportNr("123456");
        Visit visit = new Visit();
        visit.setDriver(driver);

        when(driverRepository.findByLicencePassportNr(anyString())).thenReturn(Optional.empty());

        // Act
        driverService.createDriverAndVisit(driver);

        // Assert
        verify(driverRepository, times(1)).save(any(Driver.class));
        verify(visitService, times(1)).save(any(Visit.class));
    }

    @Test
    public void testCreateDriverAndVisitExistingDriver() {
        // Arrange
        Driver existingDriver = new Driver();
        existingDriver.setLicencePassportNr("123456");
        Visit visit = new Visit();
        visit.setDriver(existingDriver);

        when(driverRepository.findByLicencePassportNr(anyString())).thenReturn(Optional.of(existingDriver));

        // Act
        driverService.createDriverAndVisit(existingDriver);

        // Assert
        verify(driverRepository, times(0)).save(any(Driver.class));
        verify(visitService, times(1)).save(any(Visit.class));
    }

    @Test
    public void testCreateDriverAndVisitNullDriver() {
        // Arrange
        when(driverRepository.findByLicencePassportNr(anyString())).thenReturn(Optional.empty());

        // Act
        driverService.createDriverAndVisit(null);

        // Assert
        verify(driverRepository, times(0)).save(any(Driver.class));
        verify(visitService, times(0)).save(any(Visit.class));
    }

    @Test
    public void testCreateDriverAndVisitNullLicencePassportNr() {
        // Arrange
        Driver driver = new Driver();

        when(driverRepository.findByLicencePassportNr(null)).thenReturn(Optional.empty());

        // Act
        driverService.createDriverAndVisit(driver);

        // Assert
        verify(driverRepository, times(1)).save(any(Driver.class));
        verify(visitService, times(1)).save(any(Visit.class));
    }

}
