package com.LagerSystem.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.ArrayList;

@Entity
public class Visitor extends Person {

    @Column
    private String cause_for_visit;

    private ArrayList<Visit> visitsList = new ArrayList<>();

    public Visitor() {

    }

    public Visitor(String cause_for_visit,String firstName, String lastName,String pictureId, boolean isEu) {
        super(firstName, lastName, pictureId, isEu);
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
