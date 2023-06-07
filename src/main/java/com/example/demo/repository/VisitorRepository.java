package com.example.demo.repository;

import com.example.demo.model.Visitor;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends PersonRepository<Visitor> {
    // specific methods for Visitor
}
