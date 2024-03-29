package database.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "offices", schema = "public", catalog = "clinic")
public class Offices {
    @Override
    public String toString() {
        return "Offices{" +
                "officeNumber=" + officeNumber +
                ", typeOfOffice='" + typeOfOffice + '\'' +
                '}';
    }

    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator = "kaugen")
    @Id
    @Column(name = "office_number")
    private int officeNumber;
    @Basic
    @Column(name = "type_of_office")
    private String typeOfOffice;
    @OneToMany(mappedBy = "officesByOfficeNumber")
    private Collection<Visits> visitsByOfficeNumber;

    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getTypeOfOffice() {
        return typeOfOffice;
    }

    public void setTypeOfOffice(String typeOfOffice) {
        this.typeOfOffice = typeOfOffice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offices that = (Offices) o;

        if (officeNumber != that.officeNumber) return false;
        if (!Objects.equals(typeOfOffice, that.typeOfOffice)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = officeNumber;
        result = 31 * result + (typeOfOffice != null ? typeOfOffice.hashCode() : 0);
        return result;
    }

    public Offices() {
    }



    public Collection<Visits> getVisitsByOfficeNumber() {
        return visitsByOfficeNumber;
    }

    public void setVisitsByOfficeNumber(Collection<Visits> visitsByOfficeNumber) {
        this.visitsByOfficeNumber = visitsByOfficeNumber;
    }
}
