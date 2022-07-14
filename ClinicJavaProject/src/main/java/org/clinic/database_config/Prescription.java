package org.clinic.database_config;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "prescription")
public class Prescription {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "prescription_id")
    private int prescriptionId;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "code_of_prescription")
    private int codeOfPrescription;
    @Basic
    @Column(name = "date_of_issue")
    private Date dateOfIssue;
    @Basic
    @Column(name = "expiration_date")
    private Date expirationDate;
    @Basic
    @Column(name = "visit_id")
    private long visitId;
    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "visit_id", nullable = false)
    private Visit visitByVisitId;

    public Prescription() {}

    public Prescription(int prescriptionId, String description, int codeOfPrescription, Date dateOfIssue, Date expirationDate, long visitId) {
        this.prescriptionId = prescriptionId;
        this.description = description;
        this.codeOfPrescription = codeOfPrescription;
        this.dateOfIssue = dateOfIssue;
        this.expirationDate = expirationDate;
        this.visitId = visitId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCodeOfPrescription() {
        return codeOfPrescription;
    }

    public void setCodeOfPrescription(int codeOfPrescription) {
        this.codeOfPrescription = codeOfPrescription;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public Visit getVisitByVisitId() {
        return visitByVisitId;
    }

    public void setVisitByVisitId(Visit visitByVisitId) {
        this.visitByVisitId = visitByVisitId;
    }
}
