package database.tables;

import jakarta.persistence.*;
import java.util.Base64;
import java.util.Base64.*;
import java.util.Objects;

@Entity
public class DoctorLoginData {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "login")
    private int login;
    @Basic
    @Column(name = "password")
    private String password;

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public String getPassword() {
        byte[] decodedBytes = Base64.getDecoder().decode(this.password);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    public void setPassword(String password) {
        String encodedString = Base64.getEncoder().encodeToString(password.getBytes());
        this.password = encodedString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorLoginData that = (DoctorLoginData) o;

        if (login != that.login) return false;
        if (!Objects.equals(password, that.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
