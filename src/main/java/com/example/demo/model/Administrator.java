package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrator")
@DiscriminatorValue("administrator")
public class Administrator extends Person {

    @Column(name="userName")
    private String userName;

    @Column(name="passWord")
    private String passWord;

    public Administrator() {}

    public Administrator(String accountStatus, String firstName, String lastName, String pictureUrl, String location, String licencePassportNr, String userName, String passWord) {
        super(accountStatus, firstName, lastName, pictureUrl, location, licencePassportNr);
        this.userName = userName;
        this.passWord = passWord;
    }

    // Getters and setters
    // ...

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
