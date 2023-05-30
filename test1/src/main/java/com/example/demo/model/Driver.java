package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("driver")
public class Driver extends Person {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_company_id")
    private TransportCompany transportCompany;

    @OneToMany(mappedBy = "driver")
    private List<Visit> visitsList = new ArrayList<>();

    public Driver() {}

    public Driver(String accountStatus,String firstName, String lastName, String pictureUrl, String location, String licencePassportNr, TransportCompany transportCompany) {
        super(firstName, lastName, pictureUrl, location, licencePassportNr,accountStatus);
        this.transportCompany = transportCompany;
    }

    public List<Visit> getVisitsList() {
        return visitsList;
    }

    public void setVisitsList(List<Visit> visitsList) {
        this.visitsList = visitsList;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }
}
