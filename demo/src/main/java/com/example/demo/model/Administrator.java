package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrator")
public class Administrator extends Person {

    private String userName;
    private String passWord;


    public Administrator() {

    }

    public Administrator(String firstName, String lastName,String userName, String passWord,String pictureId, String location, String driver_licence_passport_nr) {
        super(firstName, lastName, pictureId, location,driver_licence_passport_nr);
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
