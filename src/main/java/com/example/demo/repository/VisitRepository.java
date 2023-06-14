package com.example.demo.repository;

import com.example.demo.model.Visit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

    @Transactional
    void deleteAllByDriver_PersonId(Integer driverPersonId);

    @Transactional
    void deleteAllByVisitor_PersonId(Integer visitorPersonId);
}
