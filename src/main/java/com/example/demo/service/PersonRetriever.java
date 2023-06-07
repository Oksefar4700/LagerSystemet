package com.example.demo.service;

import com.example.demo.model.Administrator;
import com.example.demo.model.Driver;
import com.example.demo.model.Person;
import com.example.demo.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonRetriever {

    private final PersonService personService;
    private final DriverService driverService;
    private final VisitorService visitorService;
    private final AdministratorService administratorService;

    @Autowired
    public PersonRetriever(
            PersonService personService,
            DriverService driverService,
            VisitorService visitorService,
            AdministratorService administratorService
    ) {
        this.personService = personService;
        this.driverService = driverService;
        this.visitorService = visitorService;
        this.administratorService = administratorService;
    }

    public Person getPersonById(Integer personId) {
        Person person = personService.getPersonById(personId);
        if (person == null) {
            person = driverService.getPersonById(personId);
        }
        if (person == null) {
            person = visitorService.getPersonById(personId);
        }
        if (person == null) {
            person = administratorService.getPersonById(personId);
        }
        if (person == null) {
            throw new RuntimeException("Person not found with id: " + personId);
        }
        return person;
    }

    public Driver getDriverById(Integer personId) {
        return driverService.getPersonById(personId);
    }

    public Visitor getVisitorById(Integer personId) {
        return visitorService.getPersonById(personId);
    }

    public Administrator getAdministratorById(Integer personId) {
        return administratorService.getPersonById(personId);
    }
}
