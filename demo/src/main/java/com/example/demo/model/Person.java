package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@MappedSuperclass
public class Person {

    @Id
    @Column(unique = true)
    private String driver_licence_passport_nr;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String pictureId;
    @Column
    private String location;


    public Person(){

    }

    public Person(String firstName, String lastName,String pictureId, String location, String driver_licence_passport_nr) {
        this.pictureId = pictureId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.driver_licence_passport_nr = driver_licence_passport_nr;
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

    public String getDriver_licence_passport_nr() {
        return driver_licence_passport_nr;
    }

    public void setDriver_licence_passport_nr(String driver_licence_passport_nr) {
        this.driver_licence_passport_nr = driver_licence_passport_nr;
    }

}
