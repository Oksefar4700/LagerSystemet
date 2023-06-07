package com.example.demo.repository;

import com.example.demo.model.Driver;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends PersonRepository<Driver> {
    // specific methods for Driver
}
