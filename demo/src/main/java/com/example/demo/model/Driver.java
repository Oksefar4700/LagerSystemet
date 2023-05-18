package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;

@Entity
public class Driver extends Person {
    @Column
    private String companyName;
    @OneToMany(mappedBy = "driver")
    private ArrayList<Visit> visitsList = new ArrayList<>();

    public Driver() {

    }

    public Driver(String firstName, String lastName, String pictureId, boolean isEu, String companyName) {
        super(firstName, lastName, pictureId, isEu);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ArrayList<Visit> getVisitsList() {
        return visitsList;
    }

    public void setVisitsList(ArrayList<Visit> visitsList) {
        this.visitsList = visitsList;
    }




}
