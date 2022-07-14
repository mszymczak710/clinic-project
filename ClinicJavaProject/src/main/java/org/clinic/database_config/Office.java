package org.clinic.database_config;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "office")
public class Office {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "office_number")
    private int officeNumber;
    @Basic
    @Column(name = "type_of_office")
    private String typeOfOffice;
    @OneToMany(mappedBy = "officeByOfficeNumber")
    private Collection<Visit> visitsByOfficeNumber;

    public Office() {}

    public Office(int officeNumber, String typeOfOffice) {
        this.officeNumber = officeNumber;
        this.typeOfOffice = typeOfOffice;
    }

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

    public Collection<Visit> getVisitsByOfficeNumber() {
        return visitsByOfficeNumber;
    }

    public void setVisitsByOfficeNumber(Collection<Visit> visitsByOfficeNumber) {
        this.visitsByOfficeNumber = visitsByOfficeNumber;
    }
}
