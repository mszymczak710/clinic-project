package org.clinic.database_config;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
@Table(name = "visit")
public class Visit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "visit_id")
    private long visitId;
    @Basic
    @Column(name = "date_of_visit")
    private Date dateOfVisit;
    @Basic
    @Column(name = "time_of_visit")
    private Time timeOfVisit;
    @Basic
    @Column(name = "duration_in_minutes")
    private int durationInMinutes;
    @Basic
    @Column(name = "patient_id")
    private long patientId;
    @Basic
    @Column(name = "doctor_id")
    private int doctorId;
    @Basic
    @Column(name = "office_number")
    private int officeNumber;
    @OneToMany(mappedBy = "visitByVisitId")
    private Collection<Prescription> prescriptionsByVisitId;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    private Patient patientByPatientId;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", nullable = false)
    private Doctor doctorByDoctorId;
    @ManyToOne
    @JoinColumn(name = "office_number", referencedColumnName = "office_number", nullable = false)
    private Office officeByOfficeNumber;

    public Visit() {}

    public Visit(long visitId, Date dateOfVisit, Time timeOfVisit, int durationInMinutes, long patientId, int doctorId, int officeNumber) {
        this.visitId = visitId;
        this.dateOfVisit = dateOfVisit;
        this.timeOfVisit = timeOfVisit;
        this.durationInMinutes = durationInMinutes;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.officeNumber = officeNumber;
    }

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public Time getTimeOfVisit() {
        return timeOfVisit;
    }

    public void setTimeOfVisit(Time timeOfVisit) {
        this.timeOfVisit = timeOfVisit;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public Collection<Prescription> getPrescriptionsByVisitId() {
        return prescriptionsByVisitId;
    }

    public void setPrescriptionsByVisitId(Collection<Prescription> prescriptionsByVisitId) {
        this.prescriptionsByVisitId = prescriptionsByVisitId;
    }

    public Patient getPatientByPatientId() {
        return patientByPatientId;
    }

    public void setPatientByPatientId(Patient patientByPatientId) {
        this.patientByPatientId = patientByPatientId;
    }

    public Doctor getDoctorByDoctorId() {
        return doctorByDoctorId;
    }

    public void setDoctorByDoctorId(Doctor doctorByDoctorId) {
        this.doctorByDoctorId = doctorByDoctorId;
    }

    public Office getOfficeByOfficeNumber() {
        return officeByOfficeNumber;
    }

    public void setOfficeByOfficeNumber(Office officeByOfficeNumber) {
        this.officeByOfficeNumber = officeByOfficeNumber;
    }
}
