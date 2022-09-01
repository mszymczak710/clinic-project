package database.tables;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;
import server.JsonDemo;

import java.sql.Date;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "visit_id",insertable = false, updatable = false)
    private Visits visitsByVisitId;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prescriptions that = (Prescriptions) o;

        if (prescriptionId != that.prescriptionId) return false;
        if (codeOfPrescription != that.codeOfPrescription) return false;
        if (visitId != that.visitId) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(dateOfIssue, that.dateOfIssue)) return false;
        if (!Objects.equals(expirationDate, that.expirationDate))
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
        return result;
    }

    public Prescriptions() {
    }
    public Prescriptions(JSONObject jsonObject) {
        this.prescriptionId = Integer.parseInt( jsonObject.get("prescriptionId").toString());
        this.description = (String) jsonObject.get("description");
        this.codeOfPrescription = Integer.parseInt( jsonObject.get("codeOfPrescription").toString());
        this.dateOfIssue = Date.valueOf( jsonObject.get("dateOfIssue").toString());
        this.expirationDate = Date.valueOf( jsonObject.get("expirationDate").toString());
        this.visitId = Integer.parseInt( jsonObject.get("visitId").toString());
    }
    public JSONObject toJSON()
    {
        JSONObject jsonObject = new JSONObject();
       jsonObject.put("prescriptionId",prescriptionId);
        jsonObject.put("description",description);
        jsonObject.put("codeOfPrescription",codeOfPrescription);
        jsonObject.put("dateOfIssue",dateOfIssue.toString());
        jsonObject.put("expirationDate",expirationDate.toString());
        jsonObject.put("visitId",visitId);
        return jsonObject;


    }

    public Visits getVisitsByVisitId() {
        return visitsByVisitId;
    }

    public void setVisitsByVisitId(Visits visitsByVisitId) {
        this.visitsByVisitId = visitsByVisitId;
    }

}
