package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int personId;

    @Column(name = "licence_passport_nr", unique = true)
    private String licencePassportNr;

    @Column(name = "account_status", nullable = false, columnDefinition = "varchar(255) default 'pending'")
    private String accountStatus;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(name = "picture_url", length = 5000)
    private String pictureUrl;

    @Column
    private String location;

    @ManyToOne
    @JoinColumn(name = "approved_by", referencedColumnName = "person_id")
    private Administrator approvedBy;

    public Person() {}

    public Person(String accountStatus, String firstName, String lastName, String pictureId, String location, String licencePassportNr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureId;
        this.location = location;
        this.licencePassportNr = licencePassportNr;
        this.accountStatus = accountStatus;
    }

    // Getters and setters for firstName, lastName, pictureId, location, licencePassportNr
    // ...

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getLicencePassportNr() {
        return licencePassportNr;
    }

    public void setLicencePassportNr(String licencePassportNr) {
        this.licencePassportNr = licencePassportNr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Administrator getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Administrator approvedBy) {
        this.approvedBy = approvedBy;
    }

}
