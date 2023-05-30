package com.example.demo.model;

import jakarta.persistence.*;


@Entity
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transportCompanyId;
    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTransportCompanyId() {
        return transportCompanyId;
    }

    public void setTransportCompanyId(int transportCompanyId) {
        this.transportCompanyId = transportCompanyId;
    }
}
