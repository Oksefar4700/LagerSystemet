package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public abstract class PersonService<T extends Person, R extends PersonRepository<T>> {

    protected final R repository;

    @Autowired
    public PersonService(R repository) {
        this.repository = repository;
    }

    public void deletePerson(Integer id) {
        repository.deleteById(id);
    }


    public T update(T person) {
        Optional<T> existingPerson = repository.findById(person.getPersonId());
        if (existingPerson.isPresent()) {
            return repository.save(person);
        } else {
            throw new IllegalArgumentException("No person found with ID: " + person.getPersonId());
        }
    }

    public T save(T person) {
        Optional<T> existingPerson = repository.findByLicencePassportNr(person.getLicencePassportNr());
        if (existingPerson.isPresent()) {
            throw new IllegalArgumentException("A visitor or driver with the same license/passport number already exists");
        } else {
            return repository.save(person);
        }
    }

    public List<T> searchPersonsByName(String keyword) {
        List<T> persons = new ArrayList<>();

        // Search for persons by name using repository methods
        List<T> byFirstName = repository.findByFirstNameContainingIgnoreCase(keyword);
        List<T> byLastName = repository.findByLastNameContainingIgnoreCase(keyword);

        // Add the matching persons to the final list
        persons.addAll(byFirstName);
        persons.addAll(byLastName);

        return persons;
    }


    public Optional<T> findByLicencePassportNr(String licencePassportNr) {
        return repository.findByLicencePassportNr(licencePassportNr);
    }

    public T getPersonById(Integer id) {
        Optional<T> person = repository.findById(id);
        return person.orElse(null);
    }

    public List<T> findAllPendingPersons() {
        return repository.findAllByAccountStatus("Pending");
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public abstract void approvePerson(Integer personId);

    public abstract void declinePerson(Integer personId);

}
