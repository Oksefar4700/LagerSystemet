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

    public Driver(String firstName, String lastName, String pictureId, String location, String licencePassportNr, TransportCompany transportCompany) {
        super(firstName, lastName, pictureId, location, licencePassportNr);
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
    public int getTransportCompanyId() {
        if (transportCompany != null) {
            return transportCompany.getTransport_company_id();
        }
        return 0;
    }
    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }
}
