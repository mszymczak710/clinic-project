package database.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "visits", schema = "public", catalog = "clinic")
public class Visits {
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator = "kaugen")
    @Id
    @Column(name = "visit_id")
    private int visitId;
    @Basic
    @Column(name = "date_of_visit")
    private java.sql.Timestamp dateOfVisit;
    @Basic
    @Column(name = "duration_in_minutes")
    private int durationInMinutes;
    @Basic
    @Column(name = "patient_id")
    private int patientId;
    @Basic
    @Column(name = "doctor_id")
    private int doctorId;
    @Basic
    @Column(name = "office_number")
    private int officeNumber;
    @OneToMany(mappedBy = "visitsByVisitId")
    private Collection<Prescriptions> prescriptionsByVisitId;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false, insertable = false, updatable = false)
    private Patients patientsByPatientId;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", nullable = false, insertable = false, updatable = false)
    private Doctors doctorsByDoctorId;
    @ManyToOne
    @JoinColumn(name = "office_number", referencedColumnName = "office_number", nullable = false, insertable = false, updatable = false)
    private Offices officesByOfficeNumber;



    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public java.sql.Timestamp getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit( java.sql.Timestamp dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }


    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visits that = (Visits) o;

        if (visitId != that.visitId) return false;
        if (durationInMinutes != that.durationInMinutes) return false;
        if (patientId != that.patientId) return false;
        if (doctorId != that.doctorId) return false;
        if (officeNumber != that.officeNumber) return false;
        if (!Objects.equals(dateOfVisit, that.dateOfVisit)) return false;

        return true;
    }

    public Visits(int visitId, java.sql.Timestamp dateOfVisit, int durationInMinutes, int patientId, int doctorId, int officeNumber){
        this.visitId = visitId;
        this.dateOfVisit = dateOfVisit;
        this.durationInMinutes = durationInMinutes;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.officeNumber = officeNumber;
    }

    public Visits() {
    }
    @Override
    public String toString() {
        return "Wizta{" +
                "visitId=" + visitId +
                ", dateOfVisit= " + dateOfVisit +
                ", durationInMinutes= " + durationInMinutes +
                ", patientId= " + patientId +
                ", doctorId= " + doctorId +
                ", officeNumber= " + officeNumber +
                '}';
    }


    @Override
    public int hashCode() {
        int result = visitId;
        result = 31 * result + (dateOfVisit != null ? dateOfVisit.hashCode() : 0);
        result = 31 * result + durationInMinutes;
        result = 31 * result + patientId;
        result = 31 * result + doctorId;
        result = 31 * result + officeNumber;
        return result;
    }

    public Collection<Prescriptions> getPrescriptionsByVisitId() {
        return prescriptionsByVisitId;
    }

    public void setPrescriptionsByVisitId(Collection<Prescriptions> prescriptionsByVisitId) {
        this.prescriptionsByVisitId = prescriptionsByVisitId;
    }

    public Patients getPatientsByPatientId() {
        return patientsByPatientId;
    }

    public void setPatientsByPatientId(Patients patientsByPatientId) {
        this.patientsByPatientId = patientsByPatientId;
    }

    public Doctors getDoctorsByDoctorId() {
        return doctorsByDoctorId;
    }

    public void setDoctorsByDoctorId(Doctors doctorsByDoctorId) {
        this.doctorsByDoctorId = doctorsByDoctorId;
    }

    public Offices getOfficesByOfficeNumber() {
        return officesByOfficeNumber;
    }

    public void setOfficesByOfficeNumber(Offices officesByOfficeNumber) {
        this.officesByOfficeNumber = officesByOfficeNumber;
    }
}
