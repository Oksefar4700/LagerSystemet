package com.example.demo.repository;

import com.example.demo.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends PersonRepository<Administrator> {
    Administrator findByUserName(String userName);
    Administrator findByPassWord(String passWord);
}
