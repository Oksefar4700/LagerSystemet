package com.example.demo.service;

import com.example.demo.model.Administrator;
import com.example.demo.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AdministratorService extends PersonService<Administrator, AdministratorRepository> {

    private String currentAdministratorUsername;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository) {
        super(administratorRepository);
    }

    public void setCurrentAdministratorUsername(String username) {
        this.currentAdministratorUsername = username;
    }

    public String getCurrentAdministratorUsername() {
        return currentAdministratorUsername;
    }

    public Administrator findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public Administrator getLoggedInAdministrator() {
        String username = getCurrentAdministratorUsername();
        return findByUserName(username);
    }

    public boolean authenticateAdministrator(String username, String password) {
        Administrator administrator = repository.findByUserName(username);
        if (administrator != null && administrator.getPassWord().equals(password)) {
            setCurrentAdministratorUsername(username); // Set the current administrator's username
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }
}
