package com.LagerSystem.demo.model;

import jakarta.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String pictureId;
    @Column
    private boolean isEu;


    public Person(){

    }

    public Person(String firstName, String lastName,String pictureId, boolean isEu) {
        this.pictureId = pictureId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isEu = isEu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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



}
