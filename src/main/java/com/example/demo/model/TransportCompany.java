package com.example.demo.model;

import jakarta.persistence.*;


@Entity
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transport_company_id")
    private int transportCompanyId;

    @Column(name = "company_name")
    private String company_name;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getTransportCompanyId() {
        return transportCompanyId;
    }

    public void setTransportCompanyId(int transportCompanyId) {
        this.transportCompanyId = transportCompanyId;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String companyName) {
        this.company_name = companyName;
    }

}
