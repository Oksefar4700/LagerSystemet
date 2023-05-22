package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator {

    @Id
    @Column(name = "administrator_id")
    private int id;

    @Column
    private String userName;

    @Column
    private String passWord;

    public Administrator() {}

    public Administrator(String userName, String passWord) {
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
