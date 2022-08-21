package database.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import java.util.Collection;

@Entity
@Table(name = "doctors", schema = "public", catalog = "clinic")
public class Doctors {
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator = "kaugen")
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
    @OneToMany(mappedBy = "doctorsByDoctorId")
    private Collection<Visits> visitsByDoctorId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctors that = (Doctors) o;

        if (doctorId != that.doctorId) return false;
        if (jobExecutionNumber != that.jobExecutionNumber) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (specialization != null ? !specialization.equals(that.specialization) : that.specialization != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = doctorId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + jobExecutionNumber;
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        return result;
    }
    public JSONObject toJSON ()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("doctor_id",doctorId);
        jsonObject.put("first_name",firstName);
        jsonObject.put("last_name",lastName);
        jsonObject.put("job_execution_number",jobExecutionNumber);
        jsonObject.put("specialization",specialization);
        return jsonObject;

    }

    public Collection<Visits> getVisitsByDoctorId() {
        return visitsByDoctorId;
    }

    public void setVisitsByDoctorId(Collection<Visits> visitsByDoctorId) {
        this.visitsByDoctorId = visitsByDoctorId;
    }
}
