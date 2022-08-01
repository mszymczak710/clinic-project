package database.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Table(name = "prescriptions", schema = "public", catalog = "clinic")
public class Prescriptions {
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator = "kaugen")
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
    private int visitId;
    @Basic
    @Column(name = "patient_id")
    private int patientId;
    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "visit_id",insertable = false, updatable = false)
    private Visits visitsByVisitId;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id",insertable = false, updatable = false)
    private Patients patientsByPatientId;

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

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prescriptions that = (Prescriptions) o;

        if (prescriptionId != that.prescriptionId) return false;
        if (codeOfPrescription != that.codeOfPrescription) return false;
        if (visitId != that.visitId) return false;
        if (patientId != that.patientId) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (dateOfIssue != null ? !dateOfIssue.equals(that.dateOfIssue) : that.dateOfIssue != null) return false;
        if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prescriptionId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + codeOfPrescription;
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + visitId;
        result = 31 * result + patientId;
        return result;
    }

    public Visits getVisitsByVisitId() {
        return visitsByVisitId;
    }

    public void setVisitsByVisitId(Visits visitsByVisitId) {
        this.visitsByVisitId = visitsByVisitId;
    }

    public Patients getPatientsByPatientId() {
        return patientsByPatientId;
    }

    public void setPatientsByPatientId(Patients patientsByPatientId) {
        this.patientsByPatientId = patientsByPatientId;
    }
}
