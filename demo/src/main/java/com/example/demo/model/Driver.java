package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Driver extends Person {
    @Column
    private String companyName;
    @OneToMany(mappedBy = "driver")
    private List<Visit> visitsList = new ArrayList<>();

    public Driver() {

    }

    public Driver(String firstName, String lastName, String pictureId, String location, String companyName, String driver_licence_passport_nr) {
        super(firstName, lastName, pictureId, location,driver_licence_passport_nr);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Visit> getVisitsList() {
        return visitsList;
    }

    public void setVisitsList(ArrayList<Visit> visitsList) {
        this.visitsList = visitsList;
    }




}
