package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("visitor")
public class Visitor extends Person {

    @Column(name = "cause_for_visit")
    private String causeForVisit;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    private List<Visit> visitsList;

    public Visitor() {}

    public Visitor(String accountStatus,String idNumber, String causeForVisit, String firstName, String lastName, String pictureUrl, String location, String licencePassportNr) {
        super(firstName, lastName, pictureUrl, location, licencePassportNr, accountStatus);
        this.causeForVisit = causeForVisit;
    }

    public String getCauseForVisit() {
        return causeForVisit;
    }

    public void setCauseForVisit(String causeForVisit) {
        this.causeForVisit = causeForVisit;
    }

    public List<Visit> getVisitsList() {
        return visitsList;
    }

    public void setVisitsList(List<Visit> visitsList) {
        this.visitsList = visitsList;
    }
}
