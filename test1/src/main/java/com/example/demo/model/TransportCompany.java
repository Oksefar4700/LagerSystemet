package com.example.demo.model;

import jakarta.persistence.*;


@Entity
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transport_company_id;
    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTransport_company_id() {
        return transport_company_id;
    }

    public void setTransport_company_id(int transport_company_id) {
        this.transport_company_id = transport_company_id;
    }
}
