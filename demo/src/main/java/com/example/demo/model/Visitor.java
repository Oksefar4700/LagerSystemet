package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;

@Entity
@Table(name = "visitor")
public class Visitor extends Person {

    @Column
    private String cause_for_visit;
    @OneToMany(mappedBy = "visitor")
    private ArrayList<Visit> visitsList = new ArrayList<>();

    public Visitor() {

    }

    public Visitor(String cause_for_visit,String firstName, String lastName,String pictureId, String location, String driver_licence_passport_nr) {
        super(firstName, lastName, pictureId, location,driver_licence_passport_nr);
        this.cause_for_visit = cause_for_visit;
    }

    public String getCause_for_visit() {
        return cause_for_visit;
    }

    public void setCause_for_visit(String cause_for_visit) {
        this.cause_for_visit = cause_for_visit;
    }

    public ArrayList<Visit> getVisitsList() {
        return visitsList;
    }

    public void setVisitsList(ArrayList<Visit> visitsList) {
        this.visitsList = visitsList;
    }
}
