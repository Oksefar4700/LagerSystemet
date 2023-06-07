package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository<T extends Person> extends JpaRepository<T, Integer> {
    Optional<T> findByLicencePassportNr(String licencePassportNr);
    Optional<T> findByPictureUrl(String pictureUrl);
    List<T> findAllByAccountStatus(String accountStatus);
}
