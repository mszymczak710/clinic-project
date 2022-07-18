package org.clinic.database.tables;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "patient")
public class Patient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "patient_id")
    private long patientId;
    @Basic
    @Column(name = "pesel")
    private String pesel;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "zip_code")
    private String zipCode;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "email_address")
    private String emailAddress;
    @OneToMany(mappedBy = "patientByPatientId")
    private Collection<Visit> visitsByPatientId;

    public Patient() {}

    public Patient(long patientId, String pesel, String firstName, String lastName, Date dateOfBirth, String address, String city, String zipCode, String phoneNumber, String emailAddress) {
        this.patientId = patientId;
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }


    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Collection<Visit> getVisitsByPatientId() {
        return visitsByPatientId;
    }

    public void setVisitsByPatientId(Collection<Visit> visitsByPatientId) {
        this.visitsByPatientId = visitsByPatientId;
    }

}
