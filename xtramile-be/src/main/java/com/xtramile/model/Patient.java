package com.xtramile.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column(name="first_name")
    private String FirstName;

    @Column(name="last_name")
    private String LastName;

    @Column(name="dob")
    private Date Dob;

    @Column(name="address")
    private String Address;

    @Column(name="suburb")
    private String Suburb;

    @Column(name="state")
    private String State;

    @Column(name="post_code")
    private String PostCode;

    @Column(name="phone_no")
    private String PhoneNo;

    public Patient(){

    }

    public Patient(String firstName, String lastName, Date dob, String address, String suburb, String state, String postCode, String phoneNo) {
        super();
        FirstName = firstName;
        LastName = lastName;
        Dob = dob;
        Address = address;
        Suburb = suburb;
        State = state;
        PostCode = postCode;
        PhoneNo = phoneNo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSuburb() {
        return Suburb;
    }

    public void setSuburb(String suburb) {
        Suburb = suburb;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }
}
