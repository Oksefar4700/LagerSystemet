package com.LagerSystem.demo.model;

import jakarta.persistence.*;

@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String check_in_time;
    @Column
    private String check_out_time;
    @Column
    private String location;

    public Visit() {

    }

    public Visit(String check_in_time, String check_out_time, String location) {
        this.check_in_time = check_in_time;
        this.check_out_time = check_out_time;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(String check_in_time) {
        this.check_in_time = check_in_time;
    }

    public String getCheck_out_time() {
        return check_out_time;
    }

    public void setCheck_out_time(String check_out_time) {
        this.check_out_time = check_out_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
