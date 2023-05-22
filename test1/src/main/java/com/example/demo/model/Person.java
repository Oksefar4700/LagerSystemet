package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int personId;
    @Column(name = "person_type", insertable = false, updatable = false)
    private String person_type;

    @Column(name = "licence_passport_nr", unique = true)
    private String licencePassportNr;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String pictureId;

    @Column
    private String location;

    public Person() {}

    public Person(String firstName, String lastName, String pictureId, String location, String licencePassportNr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureId = pictureId;
        this.location = location;
        this.licencePassportNr = licencePassportNr;
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

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPerson_type() {
        return person_type;
    }

    public void setPerson_type(String person_type) {
        this.person_type = person_type;
    }


}
