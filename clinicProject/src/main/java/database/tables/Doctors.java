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
    @Basic
    @Column(name = "pass")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public Doctors() {
    }
    public Doctors(JSONObject jsonObject) {
        this.doctorId = (int) jsonObject.get("doctor_id");
        this.firstName = (String) jsonObject.get("first_name");
        this.lastName = (String) jsonObject.get("last_name");
        this.jobExecutionNumber = (int) jsonObject.get("job_execution_number");
        this.specialization = (String) jsonObject.get("specialization");
    }

    public Collection<Visits> getVisitsByDoctorId() {
        return visitsByDoctorId;
    }

    public void setVisitsByDoctorId(Collection<Visits> visitsByDoctorId) {
        this.visitsByDoctorId = visitsByDoctorId;
    }
}
