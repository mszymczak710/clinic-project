package database.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "patients", schema = "public", catalog = "clinic")
public class Patients {
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator = "kaugen")
    @Id
    @Column(name = "patient_id")
    private int patientId;
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
    @OneToMany(mappedBy = "patientsByPatientId", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Collection<Prescriptions> prescriptionsByPatientId = new java.util.ArrayList<>();
    @OneToMany(mappedBy = "patientsByPatientId", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Collection<Visits> visitsByPatientId = new java.util.ArrayList<>();

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
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

    @Override
    public String toString() {
        return "Patients{" +
                "patientId=" + patientId +
                ", pesel='" + pesel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress='" + emailAddress + '\'' +
                ", prescriptionsByPatientId=" + prescriptionsByPatientId +
                ", visitsByPatientId=" + visitsByPatientId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patients that = (Patients) o;

        if (patientId != that.patientId) return false;
        if (phoneNumber != that.phoneNumber) return false;
        if (pesel != null ? !pesel.equals(that.pesel) : that.pesel != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null) return false;
        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = patientId;
        result = 31 * result + (pesel != null ? pesel.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
    }

    public Collection<Prescriptions> getPrescriptionsByPatientId() {
        return prescriptionsByPatientId;
    }

    public void setPrescriptionsByPatientId(Collection<Prescriptions> prescriptionsByPatientId) {
        this.prescriptionsByPatientId = prescriptionsByPatientId;
    }

    public Collection<Visits> getVisitsByPatientId() {
        return visitsByPatientId;
    }

    public void setVisitsByPatientId(Collection<Visits> visitsByPatientId) {
        this.visitsByPatientId = visitsByPatientId;
    }
}
