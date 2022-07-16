package org.clinic.database.tables;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "doctor")
public class Doctor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "doctor_id")
    private int doctorId;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "job_execution_number")
    private int jobExecutionNumber;
    @Basic
    @Column(name = "specialization")
    private String specialization;
    @Basic
    @Column(name = "is_manager")
    private boolean isManager;
    @OneToMany(mappedBy = "doctorByDoctorId")
    private Collection<Visit> visitsByDoctorId;

    public Doctor() {}

    public Doctor(int doctorId, String firstName, String lastName, int jobExecutionNumber, String specialization, boolean isManager) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobExecutionNumber = jobExecutionNumber;
        this.specialization = specialization;
        this.isManager = isManager;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public int getJobExecutionNumber() {
        return jobExecutionNumber;
    }

    public void setJobExecutionNumber(int jobExecutionNumber) {
        this.jobExecutionNumber = jobExecutionNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Collection<Visit> getVisitsByDoctorId() {
        return visitsByDoctorId;
    }

    public void setVisitsByDoctorId(Collection<Visit> visitsByDoctorId) {
        this.visitsByDoctorId = visitsByDoctorId;
    }
}
