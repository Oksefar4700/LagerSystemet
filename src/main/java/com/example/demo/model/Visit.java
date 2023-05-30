package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "visit_id")
    private int id;
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime check_in_time;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Visit() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ZonedDateTime getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(ZonedDateTime check_in_time) {
        this.check_in_time = check_in_time;
    }

}
